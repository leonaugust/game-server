package common.connection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CommandDecoder extends ByteToMessageDecoder {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    private static final int HEADER_SIZE = 6;

    private short messageId = -1;
    private int messageLength = -1;

    private boolean needReadHeader = true;

    private final Map<Short, Class<?>> registeredMessages;

    public CommandDecoder(Map<Short, Class<?>> registeredMessages) {
        this.registeredMessages = registeredMessages;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        Channel channel = ctx.channel();
        if (needReadHeader) {
            if (buffer.readableBytes() < HEADER_SIZE) {
                if (log.isTraceEnabled()) {
                    log.trace("waiting for the missing parts of the header, available={}", buffer.readableBytes());
                }
                return;
            }

            messageId = buffer.readShort();
            messageLength = buffer.readInt();

            if (log.isTraceEnabled()) {
                log.trace("header: messageId={}, messageLength={}", messageId, messageLength);
            }
            needReadHeader = false;
        }

        if (buffer.readableBytes() < messageLength) {
            if (log.isTraceEnabled()) {
                log.trace("waiting for the missing parts of the message, available={}", buffer.readableBytes());
            }
            return;
        }

        needReadHeader = true;

        Object obj = null;
        try {
            byte[] payload = new byte[messageLength];
            buffer.readBytes(payload);
            obj = objectMapper.readValue(payload, registeredMessages.get(messageId));
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        if (obj != null) {
            if (log.isDebugEnabled()) {
                log.debug("{} message in << {}", channel, obj);
            }
            out.add(obj);
        }
    }

}
