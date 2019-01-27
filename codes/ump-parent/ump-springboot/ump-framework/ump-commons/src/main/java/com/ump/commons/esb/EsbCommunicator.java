package com.ump.commons.esb;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.net.SocketFactory;

import org.apache.commons.lang3.StringUtils;

import com.ump.commons.exception.CommonException;
import com.ump.commons.web.StatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author fangyh
 * @date 2018-09-08 22:17:08
 * @version 1.0.0
 */
@AllArgsConstructor
public class EsbCommunicator {

	@Getter
	@Setter
	private String host;

	@Getter
	@Setter
	private int port;

	@Getter
	@Setter
	private int timeout;

	@Getter
	@Setter
	private int connTimeout;

	public String requestMessage(String reqXml) {
		if (StringUtils.isBlank(reqXml)) {
			throw new CommonException("send msg can not be empty.");
		}
		try {
			return new String(requestMessage(reqXml.getBytes(getCharset())), getCharset());
		} catch (IOException e) {
			throw new CommonException(StatusCode.ESB_REQ_EXCEPTION, e);
		}
	}

	private Charset getCharset() {
		return StandardCharsets.UTF_8;  
	}

	private byte[] requestMessage(byte[] data) throws IOException {
		if (null == data || data.length == 0) {
			return new byte[0];
		}
		try (Socket socket = createConnect();
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream())) {
			out.writeInt(data.length);
			out.write(data);
			out.flush();
			int len = in.readInt();
			byte[] returnData = new byte[len];
			in.readFully(returnData);
			return returnData;
		}
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	private Socket createConnect() throws IOException {
		InetAddress address = InetAddress.getByName(getHost());
		Socket socket = SocketFactory.getDefault().createSocket();
		InetSocketAddress socketAddr = new InetSocketAddress(address, getPort());
		socket.setSoTimeout(getTimeout());
		socket.setReuseAddress(true);
		socket.connect(socketAddr, getConnTimeout());
		return socket;
	}
}
