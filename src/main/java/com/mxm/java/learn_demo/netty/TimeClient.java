package com.mxm.java.learn_demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author maxianming
 * @date 2019/3/5 18:12
 */
@Slf4j
public class TimeClient {
    
    public static void connect(int port, String host) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new TimeClientHandler());
                    }
                });
        ChannelFuture future = bootstrap.connect(host, port);
        try {
            future.channel().closeFuture().sync();
            log.info("client 退出");
        } catch (Exception e) {
            log.error("server异常", e);
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        connect(8888, "127.0.0.1");
    }
}
