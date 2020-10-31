package com.commons.test;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

public class TestMina {
    public static void main(String[] args) {
        String minaHost = "127.0.0.1";
        String minaPort = "8001";
        NioSocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(2000);
        DefaultIoFilterChainBuilder chain = connector.getFilterChain();
        chain.addLast("codec", new ProtocolCodecFilter(new MinaTextLineCodecFactory()));
        connector.setHandler(new MinaClientHandler());
        ConnectFuture future = connector.connect(new InetSocketAddress(minaHost, Integer.valueOf(minaPort)));
        future.awaitUninterruptibly();
        IoSession session = null;
        String data = "hello";
        if (future.isConnected()) {
            // 获得session
            session = future.getSession();
            final WriteFuture write = session.write(data);
            future.awaitUninterruptibly();
            session.getCloseFuture().awaitUninterruptibly(3000);
            String result = (String) session.getAttribute("result");

        }


        connector.dispose();
    }
}
