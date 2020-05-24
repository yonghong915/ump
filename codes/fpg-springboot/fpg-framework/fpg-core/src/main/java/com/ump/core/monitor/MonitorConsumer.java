package com.ump.core.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;

public class MonitorConsumer implements  Runnable {
    private Logger logger = LoggerFactory.getLogger(getClass());


    private Queue<MonitorMessage> queue;
    public MonitorConsumer(Queue<MonitorMessage> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
         MonitorMessage msg;
       while(true){
           if(!queue.isEmpty()){

               msg = queue.poll();
               logger.info("MonitorConsumer...."+msg.getContent());
           }
       }

    }
}
