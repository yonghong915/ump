package com.ump.commons.email;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import lombok.Data;

@Data
public class MailInfo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 535673058303304215L;

	/***/
	private String host;

	/***/
	private int port = 25;

	/***/
	private String protocol = "smtp";

	/***/
	private boolean authFlag;

	/***/
	private String username;

	/***/
	private String password;

	/***/
	private String senderAddress;

	/***/
	private String[] recipientAddresses;

	/***/
	private String[] ccAddresses;

	/***/
	private Date sentDate = new Date();

	/***/
	private String subject;

	/***/
	private String content;

	/***/
	private String charset = StandardCharsets.UTF_8.name();

	/***/
	private String bodyImgPath;

	/***/
	private String[] attachFiles;
}
