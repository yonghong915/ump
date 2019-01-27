package com.ump.commons.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EmailUtil {
	private EmailUtil() {
	}

	private Logger logger = LoggerFactory.getLogger(EmailUtil.class);
	private String stmpServer = "smtp.163.com";
	private String stmpPort = "25";
	private String protocol = "smtp";
	private String username = "yonghong915";
	private String emailPass = "enjoyLIFE915*#";
	private String from = "yonghong915@163.com";
	private String to = "13684088503@139.com";
	private String subject = "测试邮件发送";
	private String body = "你好，测试发送邮件";

	public Properties getMailProps() {
		// 1、连接邮件服务器的参数配置
		Properties props = new Properties();
		// 设置用户的认证方式
		props.setProperty("mail.smtp.auth", "true");
		// 设置传输协议
		props.setProperty("mail.transport.protocol", protocol);
		// 设置发件人的SMTP服务器地址
		props.setProperty("mail.smtp.host", stmpServer);

		props.setProperty("mail.smtp.port", stmpPort);
		return props;
	}

	public Session getSession() {
		Properties props = getMailProps();
		Authenticator authenticator = new EmailAuthenticator(username, emailPass);
		Session session = Session.getInstance(props, authenticator);
		session.setDebug(true);
		return session;
	}

	public Message getMimeMessage(Session session) throws MessagingException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(RecipientType.TO, InternetAddress.parse(to));
		message.setSentDate(new Date());
		message.setSubject(subject);
		message.setText(body);
		message.saveChanges();
		return message;
	}

	public void sendMail(Session session) {

		try {
			Message message = getMimeMessage(session);
			// Transport.send(message);//一种方式

			Transport transport = session.getTransport();
			transport.connect();
			transport.sendMessage(message, message.getRecipients(RecipientType.TO));
			transport.close();
		} catch (MessagingException e) {
			logger.error("发送邮件失败:", e);
		}

	}

	public static void main(String[] args) {
		EmailUtil test = new EmailUtil();
		Session session = test.getSession();
		test.sendMail(session);
	}
}
