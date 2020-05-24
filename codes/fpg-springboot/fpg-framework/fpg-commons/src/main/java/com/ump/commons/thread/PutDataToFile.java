package com.ump.commons.thread;

import java.io.BufferedWriter;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 将队列中的数据写进文件中（消费者）
 * 
 * @author fangyh
 * @version 1.0
 * @since 1.0
 */
public class PutDataToFile implements Runnable {
	private ConcurrentLinkedQueue<String> queue;
	public PutDataToFile(ConcurrentLinkedQueue<String> queue, BufferedWriter buff) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			if (!queue.isEmpty()) {
				//try {
					System.out.println("获取数据："+queue.poll());
					//buff.write(queue.poll());
					//buff.newLine();
					//buff.flush();
				//} catch (IOException e) {
				//	e.printStackTrace();
				//}
			}
		}

	}

}
