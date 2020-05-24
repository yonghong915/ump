package com.ump.core.monitor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MonitorService {

    private BlockingQueue<MonitorMessage> queue = new ArrayBlockingQueue<MonitorMessage>(10);
    private ExecutorService executorService  = Executors.newFixedThreadPool(5);

    private MonitorService(){}

    public static MonitorService getInstance(){
        return MonitorHolder.instance;
    }
    private static class MonitorHolder{
        private  final static  MonitorService instance = new MonitorService();
    }

    public void put(MonitorMessage msg){
        MonitorProducer producer = new MonitorProducer(msg,queue);
        executorService.execute(producer);
    }

    public void get(){
        MonitorConsumer consumer = new MonitorConsumer(queue);
        executorService.execute(consumer);
    }
}
