package com.ump.core.monitor;

public class TestProducer {
    public static void main(String[] args){
        MonitorService ms =  MonitorService.getInstance();
        ms.get();

        for(int i = 0;i<10;i++){
            MonitorMessage msg = new MonitorMessage();
            msg.setContent("bbbb");
            msg.setMonitorTime("bbbb");
            msg.setContent(msg.getContent()+" "+i);
            System.out.println(msg.getContent()+" "+i);
            ms.put(msg);
        }


    }
}
