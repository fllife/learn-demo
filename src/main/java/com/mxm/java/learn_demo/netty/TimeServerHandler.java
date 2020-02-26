package com.mxm.java.learn_demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author maxianming
 * @date 2019/3/5 17:46
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * Server端接受到client信息
     * @author maxianming
     * @date 2019/3/8 17:02
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("time server receive msg " + body);
        String time = new Date().toString();
        ByteBuf resp = Unpooled.copiedBuffer(time.getBytes());
        ctx.write(resp);
    }

    /**
     * 将写入信息刷新，返回给client
     * @author maxianming
     * @date 2019/3/8 17:04
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
