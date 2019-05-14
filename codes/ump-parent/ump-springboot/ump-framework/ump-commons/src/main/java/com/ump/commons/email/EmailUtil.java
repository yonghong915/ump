package com.ump.commons.email;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ump.commons.CommUtils;

public final class EmailUtil {
	private EmailUtil() {
	}

	private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);

	private static Properties getMailProps(MailInfo mailInfo) {
		// 连接邮件服务器的参数配置
		Properties props = new Properties();
		// 设置用户的认证方式
		props.put("mail.smtp.auth", mailInfo.isAuthFlag());
		// 设置传输协议
		props.put("mail.transport.protocol", mailInfo.getProtocol());
		// 设置发件人的SMTP服务器地址
		props.put("mail.smtp.host", mailInfo.getHost());

		props.put("mail.smtp.port", mailInfo.getPort());
		return props;
	}

	public static Session getSession(MailInfo mailInfo) {
		Properties props = getMailProps(mailInfo);
		Authenticator authenticator = new EmailAuthenticator(mailInfo.getUsername(), mailInfo.getPassword());
		Session session = Session.getDefaultInstance(props, authenticator);
		session.setDebug(true);
		return session;
	}

	private static Message getMimeMessage(Session session, MailInfo mailInfo)
			throws MessagingException, FileNotFoundException, UnsupportedEncodingException {
		Message message = new MimeMessage(session);

		// sender
		message.setFrom(new InternetAddress(mailInfo.getSenderAddress()));

		// recipients
		String[] recipientAddresses = mailInfo.getRecipientAddresses();
		if (null == recipientAddresses || recipientAddresses.length == 0) {
			throw new AddressException("recipient address could not be empty.");
		}
		int recipientAddrLen = recipientAddresses.length;
		InternetAddress[] toAddresses = new InternetAddress[recipientAddrLen];
		for (int i = 0; i < recipientAddrLen; i++) {
			toAddresses[i] = new InternetAddress(recipientAddresses[i]);
		}
		message.setRecipients(RecipientType.TO, toAddresses);

		// carbon copy recipients
		String[] ccAddresses = mailInfo.getCcAddresses();
		if (CommUtils.isNotEmpty(ccAddresses)) {
			int ccAddrLen = ccAddresses.length;
			toAddresses = new InternetAddress[ccAddrLen];
			for (int i = 0; i < ccAddrLen; i++) {
				toAddresses[i] = new InternetAddress(ccAddresses[i]);
			}
			message.setRecipients(RecipientType.CC, toAddresses);
		}
		message.setSentDate(mailInfo.getSentDate());

		// mail subject
		message.setSubject(mailInfo.getSubject());

		Multipart multipart = new MimeMultipart();

		BodyPart bodyPart = createBodyContent(mailInfo);
		multipart.addBodyPart(bodyPart);

		String[] attachFiles = mailInfo.getAttachFiles();
		if (CommUtils.isNotEmpty(attachFiles)) {
			for (String attchFile : attachFiles) {
				if (!new File(attchFile).exists()) {
					throw new FileNotFoundException("attach file is not exists.");
				}
				BodyPart attachPart = createAttachment(attchFile);
				multipart.addBodyPart(attachPart);
			}
		}
		message.setContent(multipart);
		message.saveChanges();
		return message;
	}

	private static BodyPart createAttachment(String attachFile)
			throws MessagingException, UnsupportedEncodingException {
		BodyPart attachPart = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(attachFile);
		attachPart.setDataHandler(new DataHandler(fds));
		attachPart.setFileName(MimeUtility.encodeText(fds.getName()));
		return attachPart;
	}

	private static BodyPart createBodyContent(MailInfo mailInfo) throws MessagingException {
		BodyPart bodyPart = new MimeBodyPart();
		// 用于组合文本和图片，"related"型的MimeMultipart对象
		MimeMultipart contentMulti = new MimeMultipart("related");

		// 正文的文本部分
		MimeBodyPart textBody = new MimeBodyPart();
		textBody.setContent(mailInfo.getContent(), "text/html;charset=" + mailInfo.getCharset());
		contentMulti.addBodyPart(textBody);

		// 正文的图片部分
		if (CommUtils.isNotEmpty(mailInfo.getBodyImgPath())) {
			MimeBodyPart jpgBody = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(mailInfo.getBodyImgPath());
			jpgBody.setDataHandler(new DataHandler(fds));
			jpgBody.setContentID("logo_jpg");
			contentMulti.addBodyPart(jpgBody);
		}

		// 将上面"related"型的 MimeMultipart 对象作为邮件的正文
		bodyPart.setContent(contentMulti);
		return bodyPart;
	}

	public static boolean sendMail(MailInfo mailInfo) {
		boolean retFlag = true;
		// 1.create session
		Session session = getSession(mailInfo);
		try {
			// 2.obtain Transport Object by Session
			Transport transport = session.getTransport();

			// 3.connect mail server
			transport.connect();

			// 4.encapsulate Message Object
			Message message = getMimeMessage(session, mailInfo);

			// 5.send mail
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException | FileNotFoundException | UnsupportedEncodingException e) {
			logger.error("Failed to send mail.", e);
			retFlag = false;
		}
		return retFlag;
	}

	public static void main(String[] args) {
		String stmpServer = "smtp.163.com";
		int stmpPort = 25;
		String protocol = "smtp";
		String username = "yonghong915";
		String emailPass = "enjoyLIFE915*#";
		String from = "yonghong915@163.com";
		String to = "13684088503@139.com";
		String subject = "测试邮件发送";
		String content = "你好，测试发送邮件";
		MailInfo mi = new MailInfo();
		mi.setHost(stmpServer);
		mi.setAuthFlag(true);
		mi.setPort(stmpPort);
		mi.setProtocol(protocol);
		mi.setUsername(username);
		mi.setPassword(emailPass);
		mi.setSenderAddress(from);
		mi.setRecipientAddresses(new String[] { to });
		mi.setCcAddresses(new String[] { to });
		mi.setSubject(subject);
		mi.setContent(content);

		String bodyImgPath = "F:\\test\\temp-data\\IMG_20180927_104434.jpg";
		mi.setBodyImgPath(bodyImgPath);
		String[] attachFiles = new String[] {"F:\\test\\temp-data\\vue.js","F:\\test\\temp-data\\出差申请.png"};
		mi.setAttachFiles(attachFiles);
		boolean sendFlag = sendMail(mi);
		logger.info("sendFlag={}", sendFlag);
	}
}
