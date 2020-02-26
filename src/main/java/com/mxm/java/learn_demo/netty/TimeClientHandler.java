package com.mxm.java.learn_demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author maxianming
 * @date 2019/3/8 14:44
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取server端的响应
     * @author maxianming
     * @date 2019/3/8 17:01
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("time server response msg " + body);
    }

    /**
     * 当client端与server端建立连接后。触发该事件。
     * channelActive 后。将信息发给server端
     * @author maxianming
     * @date 2019/3/8 16:59
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        byte[] req = "query time order".getBytes();
        ByteBuf buf = Unpooled.buffer(req.length);
        buf.writeBytes(req);
        ctx.writeAndFlush(buf);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
    }
}
