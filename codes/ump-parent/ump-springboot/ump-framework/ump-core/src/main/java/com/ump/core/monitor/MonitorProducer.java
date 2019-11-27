package com.ump.core.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;

public class MonitorProducer implements  Runnable {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private MonitorMessage msg;

    private Queue<MonitorMessage> queue;
    public MonitorProducer(MonitorMessage msg,Queue<MonitorMessage> queue){
        this.msg  = msg;
        this.queue = queue;
    }

    @Override
    public void run() {
        logger.info("MonitorProducer..."+msg.getContent());
        queue.offer(msg);
    }
}
