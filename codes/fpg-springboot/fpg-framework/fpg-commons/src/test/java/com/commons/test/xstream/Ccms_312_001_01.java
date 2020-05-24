package com.commons.test.xstream;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.AllArgsConstructor;
import lombok.Data;

@XStreamAlias("Document")
@Data
@AllArgsConstructor
public class Ccms_312_001_01 {

	@XStreamAlias("CmonSgntrInfBiz")
	private String cmonSgntrInfBiz;

	@XStreamAlias("TxTpCd")
	private String transactionTypeCode;

	@XStreamAlias("GrpHdr")
	private GroupHeader groupHeader;

	// @XStreamImplicit
	@XStreamAlias("addressList")
	private List<Address> addresses;

	private Calendar created = new GregorianCalendar();

	@Data
	static class GroupHeader {
		@XStreamAlias("MsgId")
		private String messageIdentification;

		@XStreamAlias("CreDtTm")
		private String creationDateTime;

		@XStreamAlias("SysCd")
		private String systemCode;
	}

	@XStreamAlias("address")
	@Data
	@AllArgsConstructor
	static class Address {
		private String street;
		private String zipcode;
		private String mobile;
	}
}
