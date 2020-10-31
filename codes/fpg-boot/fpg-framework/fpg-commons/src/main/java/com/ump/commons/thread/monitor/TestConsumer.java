package com.ump.commons.thread.monitor;

public class TestConsumer {

	public static void main(String[] args) {
		MonitorMessage msg = new MonitorMessage();
		msg.setMsg("bbbb");
		ThreadPool thrcon = ThreadPool.getInstance();
		System.out.println("threadPool obj thrcon =" + thrcon.hashCode());
//		thrcon.putProducer(msg);
	}

}
