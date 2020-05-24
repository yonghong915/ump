package com.ump.commons.thread.monitor;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestProducer {

	public static void main(String[] args) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:hh:ss sss");
//		String currDate = sdf.format(new Date()) + "  ";
//		double a = Math.random();
//		Queue<MonitorMessage> queue = new ConcurrentLinkedQueue<MonitorMessage>();
//		MonitorMessage msg = new MonitorMessage();

		// Producer producer = new Producer(queue, msg);
		// Consumer consumer = new Consumer(queue);
		// new Thread(producer).start();
		// new Thread(consumer).start();
		// ExecutorService service = Executors.newFixedThreadPool(5);
		// service.execute(producer);
		// service.execute(consumer);
//		for (int i = 0; i < 10; i++) {
//			ThreadPool thr = ThreadPool.getInstance();
//			msg.setMsg("aaaa " + i);
//			System.out.println("threadPool obj=" + thr.hashCode());
//			thr.putProducer(msg);
//		}
//
//		ThreadPool thr1 = ThreadPool.getInstance();
//		System.out.println("threadPool obj1=" + thr1.hashCode());
//		thr1.putConsumer();
		ExecutorService service = Executors.newFixedThreadPool(4);
		Queue<MonitorMessage> queue = new ConcurrentLinkedQueue<MonitorMessage>();

		MonitorMessage msg = new MonitorMessage();
		for (int i = 0; i < 10; i++) {
			// new Thread(new Producer(queue, "写入数据为： " + i)).start();
			msg.setMsg("写入数据为： " + i);
			service.execute(new Producer(queue, msg));
		}
		// new Thread(new Consumer(queue)).start();
		service.execute(new Consumer(queue));
	}

}
