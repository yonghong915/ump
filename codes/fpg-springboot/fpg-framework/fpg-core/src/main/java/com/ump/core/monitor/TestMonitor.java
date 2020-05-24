package com.ump.core.monitor;

public class TestMonitor {

    public static void main(String[] args){
        MonitorService ms =  MonitorService.getInstance();
        MonitorMessage msg = new MonitorMessage();
        msg.setContent("aaaa");
        msg.setMonitorTime("bbbb");
        ms.put(msg);
        ms.get();
    }
}
