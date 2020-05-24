package com.ump.mq.config.rocketmq;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ RocketMQProperties.class })
@ConditionalOnProperty(prefix = "rocketmq", value = "isEnable", havingValue = "true")
public class RocketMQConfiguation {
	private RocketMQProperties properties;

	private ApplicationContext applicationContext;

	private Logger log = LoggerFactory.getLogger(RocketMQConfiguation.class);

	public RocketMQConfiguation(RocketMQProperties properties, ApplicationContext applicationContext) {
		this.properties = properties;
		this.applicationContext = applicationContext;
	}

	/**
	 * 注入一个默认的消费者
	 * 
	 * @return
	 * @throws MQClientException
	 */
	@Bean
	public DefaultMQProducer getRocketMQProducer() throws MQClientException {
		if (StringUtils.isEmpty(properties.getGroupName())) {
			throw new MQClientException(-1, "groupName is blank");
		}

		if (StringUtils.isEmpty(properties.getNamesrvAddr())) {
			throw new MQClientException(-1, "nameServerAddr is blank");
		}
		DefaultMQProducer producer;
		producer = new DefaultMQProducer(properties.getGroupName());

		producer.setNamesrvAddr(properties.getNamesrvAddr());
		// producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");

		// 如果需要同一个jvm中不同的producer往不同的mq集群发送消息，需要设置不同的instanceName
		// producer.setInstanceName(instanceName);
		producer.setMaxMessageSize(properties.getProducerMaxMessageSize());
		producer.setSendMsgTimeout(properties.getProducerSendMsgTimeout());
		// 如果发送消息失败，设置重试次数，默认为2次
		producer.setRetryTimesWhenSendFailed(properties.getProducerRetryTimesWhenSendFailed());

		try {
			producer.start();
			log.info("producer is start ! groupName:{},namesrvAddr:{}", properties.getGroupName(),
					properties.getNamesrvAddr());
		} catch (MQClientException e) {
			log.error(String.format("producer is error {}", e.getMessage(), e));
			throw e;
		}
		return producer;

	}

	/**
	 * SpringBoot启动时加载所有消费者
	 */
	@PostConstruct
	public void initConsumer() {
		Map<String, AbstractRocketConsumer> consumers = applicationContext.getBeansOfType(AbstractRocketConsumer.class);
		if (consumers == null || consumers.size() == 0) {
			log.info("init rocket consumer 0");
		}
		Iterator<String> beans = consumers.keySet().iterator();
		while (beans.hasNext()) {
			String beanName = (String) beans.next();
			AbstractRocketConsumer consumer = consumers.get(beanName);
			consumer.init();
			createConsumer(consumer);
			log.info("init success consumer title {} , toips {} , tags {}", consumer.consumerTitel, consumer.tags,
					consumer.topics);
		}
	}

	/**
	 * 通过消费者信心创建消费者
	 * 
	 * @param consumerPojo
	 */
	public void createConsumer(AbstractRocketConsumer arc) {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(this.properties.getGroupName());
		consumer.setNamesrvAddr(this.properties.getNamesrvAddr());
		consumer.setConsumeThreadMin(this.properties.getConsumerConsumeThreadMin());
		consumer.setConsumeThreadMax(this.properties.getConsumerConsumeThreadMax());
		
		//注册消费的监听 并在此监听中消费信息，并返回消费的状态信息
		consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            // msgs中只收集同一个topic，同一个tag，并且key相同的message
            // 会把不同的消息分别放置到不同的队列中
            try {
                for (Message msg : msgs) {

                    //消费者获取消息 这里只输出 不做后面逻辑处理
                    String body = new String(msg.getBody(), "utf-8");
                    log.info("Consumer-获取消息-主题topic为={}, 消费消息为={}", msg.getTopic(), body);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
		/**
		 * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费 如果非第一次启动，那么按照上次消费的位置继续消费
		 */
		// consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		/**
		 * 设置消费模型，集群还是广播，默认为集群
		 */
		// consumer.setMessageModel(MessageModel.CLUSTERING);

		/**
		 * 设置一次消费消息的条数，默认为1条
		 */
		consumer.setConsumeMessageBatchMaxSize(this.properties.getConsumerConsumeMessageBatchMaxSize());
		try {
			consumer.subscribe(arc.topics, arc.tags);
			consumer.start();
			arc.mqPushConsumer = consumer;
		} catch (MQClientException e) {
			log.error("info consumer title {}", arc.consumerTitel, e);
		}

	}
}
