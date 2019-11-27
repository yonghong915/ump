package com.ump.core.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.fsg.uid.UidGenerator;

//@Component
public class UidUtil {

	@Autowired
	private UidGenerator cachedUidGenerator;

	private static UidGenerator uidGenerator;

	@PostConstruct
	public void init() {
		uidGenerator = cachedUidGenerator;
	}

	public static long getUid() {
		return uidGenerator.getUID();
	}
}
