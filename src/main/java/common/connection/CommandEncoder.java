package common.connection;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ChannelHandler.Sharable
public class CommandEncoder extends MessageToMessageEncoder<Object> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Map<Class<?>, Short> registeredMessages;

    public CommandEncoder(Map<Short, Class<?>> registeredMessages) {
        this.registeredMessages = registeredMessages.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
        try {
            var buffer = ctx.alloc().buffer();

            var payload = CommandDecoder.objectMapper.writeValueAsBytes(msg);
            var messageId = registeredMessages.get(msg.getClass());

            buffer.writeShort(messageId);
            buffer.writeInt(payload.length);
            buffer.writeBytes(payload);

            if (log.isDebugEnabled()) {
                log.debug("{} message out >> {}", ctx.channel(), msg);
            }

            out.add(buffer);
        } catch (Exception e) {
            log.error(e.toString(), e);
        }
    }

}
