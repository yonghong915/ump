package com.ump.commons.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ProducerThread extends Thread {
	private BlockingQueue<String> queue;
	private volatile boolean flag = true;
	private static AtomicInteger count = new AtomicInteger();

	public ProducerThread(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		System.out.println("生产者线程启动。。。。。。。");
		try {
			while (flag) {
				System.out.println("生产者正在生产队列");
				String data = count.incrementAndGet() + "";
				// 添加队列
				boolean offer = queue.offer(data);
				if (offer) {
					System.out.println("生产者添加队列" + data + "成功！");
				} else {
					System.out.println("生产者添加队列" + data + "失败！");
				}
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("生产者线程停止。。。。。。。。");
		}
	}

	public void stopThread() {
		this.flag = false;
	}
}

class ConsumerThread extends Thread {
	private BlockingQueue<String> queue;
	private volatile boolean flag = true;
	public ConsumerThread(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		System.out.println("消费者线程启动。。。。。。。");
		try {
			while (flag) {
				// 获取完毕后，队列会删除头元素
				String data = (String) queue.poll(2, TimeUnit.SECONDS);
				if (data != null) {
					System.out.println("消费者获取data：" + data + "成功！");
				} else {
					System.out.println("消费者获取data失败！");
					this.flag = false;
				}
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("消费者线程停止。。。。。。。。");
		}
	}

	public void stopThread() {
		this.flag = false;
	}
}

public class ProConDemo {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
		ProducerThread p1 = new ProducerThread(queue);
		ProducerThread p2 = new ProducerThread(queue);

		ConsumerThread c1 = new ConsumerThread(queue);
//        ConsumerThread c2=new ConsumerThread(queue);
		p1.start();
		p2.start();
		c1.start();
		Thread.sleep(10 * 1000);
		p1.stopThread();
		p2.stopThread();
	}

}