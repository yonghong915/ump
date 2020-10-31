package com.commons.test;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MinaClientHandler extends IoHandlerAdapter {
    // 当发送信息异常时
    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
//        if(Debug.infoOn()){
//            Debug.logInfo("Mina通信，客户端发送信息异常...", module);
//        }
        super.exceptionCaught(session, cause);
    }

    //信息发送成功时
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
//        if(Debug.infoOn()){
//            Debug.logInfo("Mina通信，信息发送成功："+message.toString(), module);
//        }
        super.messageSent(session, message);
    }

    // 当客户端发送消息到达时
    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
//        if(UtilValidate.isEmpty(message)){
//            session.close(true);
//            return;
//        }
        session.setAttribute("result", message.toString());
//        if(Debug.infoOn()){
//            Debug.logInfo("Mina通信，服务器返回的数据："+message.toString(), module);
//        }
        super.messageReceived(session, message);
    }

    // 当关闭连接时
    @Override
    public void sessionClosed(IoSession session) throws Exception {
//        if(Debug.infoOn()){
//            Debug.logInfo("Mina通信，客户端与服务端断开连接...", module);
//        }
        super.sessionClosed(session);
    }

    // 当创建连接时
    @Override
    public void sessionCreated(IoSession session) throws Exception {
//        if(Debug.infoOn()){
//            Debug.logInfo("Mina通信，创建连接...", module);
//        }
        super.sessionCreated(session);
    }

    //当客户端连接进入时
    @Override
    public void sessionOpened(IoSession session) throws Exception {
//        if(Debug.infoOn()){
//            Debug.logInfo("Mina通信，连接已创建（incomming）", module);
//        }
        super.sessionOpened(session);
    }
}
