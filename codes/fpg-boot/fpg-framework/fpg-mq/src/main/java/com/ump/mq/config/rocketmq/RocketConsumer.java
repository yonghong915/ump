package com.ump.mq.config.rocketmq;

import org.apache.rocketmq.client.consumer.listener.MessageListener;

public interface RocketConsumer {
	/**
	 * 初始化消费者
	 */
	void init();

	/**
	 * 注册监听
	 * 
	 * @param messageListener
	 */
	void registerMessageListener(MessageListener messageListener);
}
