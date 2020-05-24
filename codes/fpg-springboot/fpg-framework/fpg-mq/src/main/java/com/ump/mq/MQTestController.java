package com.ump.mq;

import java.io.UnsupportedEncodingException;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class MQTestController {
	@Autowired
	private DefaultMQProducer defaultMQProducer;

	@RequestMapping(value = "/qmtest", method = RequestMethod.GET)
	public String qmtest(/* @PathVariable("name") */ String name) throws MQClientException, RemotingException,
			MQBrokerException, InterruptedException, UnsupportedEncodingException {
		Message msg = new Message("TopicTest", "tags1", name.getBytes(RemotingHelper.DEFAULT_CHARSET));

		// 发送消息到一个Broker
		SendResult sendResult = defaultMQProducer.send(msg);
		// 通过sendResult返回消息是否成功送达
		System.out.printf("%s%n", sendResult);
		return null;
	}
}
