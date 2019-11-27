/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.core.ws.message.soap.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ump.core.util.ValidateUtils;

import lombok.Data;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-08-12 20:13:35
 *
 */
@Data
public class ParameterInfo implements Cloneable {
	private String name;

	private String kind;

	private int id;

	private String value;

	private String serviceid;

	private String operationname;

	private String inputtype;

	private String refType;

	private ParameterInfo refTypeParameterInfo;

	private List<String> documenactionlist;

	private boolean nillable = false;

	private boolean emptiable = false;

	private long length;

	private long maxLength;

	private long minLength;

	private String defaultValue;

	private boolean isValid;

	private String totalDigits;

	private String fractionDigits;

	private ParameterInfo parentParameter;

	private String typeNamespace;

	private String typeNamespacePrefix;

	private String orderType;

	private String elementFormDefault;

	private List<ParameterInfo> childParameters = new ArrayList<>();

	private Map<String, ParameterInfo> choiceParameters = new HashMap<>();

	public ParameterInfo getAncestorsParameterInfoByRefTypeName(String refTypeName) {
		if (ValidateUtils.isNotEmpty(parentParameter) && ValidateUtils.isNotEmpty(refTypeName)) {
			if (refTypeName.equals(this.parentParameter.getRefType())) {
				return this.parentParameter;
			} else {
				return this.parentParameter.getAncestorsParameterInfoByRefTypeName(refTypeName);
			}
		}
		return null;
	}

	public ParameterInfo cloneSimple() {
		ParameterInfo o = null;
		try {
			o = (ParameterInfo) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
		if (null != childParameters) {
			o.childParameters = new ArrayList<ParameterInfo>(0);
		}
		return o;
	}

	public ParameterInfo clone() {
		ParameterInfo o = null;
		try {
			o = (ParameterInfo) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
		if (childParameters != null) {
			o.childParameters = new ArrayList<ParameterInfo>(this.childParameters.size());
			for (ParameterInfo p : childParameters) {
				o.childParameters.add(p.clone());
			}
		}

		return o;
	}

	public List<ParameterInfo> getChildParameters() {
		if (ValidateUtils.isEmpty(childParameters) && ValidateUtils.isNotEmpty(refTypeParameterInfo)) {
			return refTypeParameterInfo.getChildParameters();
		}
		return childParameters;
	}

	public void addChoiceParam(String paramName, ParameterInfo choiceParam) {
		this.choiceParameters.put(paramName, choiceParam);
	}

	public void addChildParameter(ParameterInfo childParameter) {
	    this.childParameters.add(childParameter);
	}
}
