package client.connection;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageHandler extends SimpleChannelInboundHandler<Object> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    BlockingQueue<Object> incomingMessages = new LinkedBlockingQueue<>();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object message) throws Exception {
        incomingMessages.add(message);
    }

}
