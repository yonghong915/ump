/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.core.ws.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ump.core.util.ValidateUtils;
import com.ump.core.util.constants.WSConstants;
import com.ump.core.ws.entity.OperationInfo;
import com.ump.core.ws.entity.Parameter;
import com.ump.core.ws.entity.WebServiceInterfaceInfo;
import com.ump.core.ws.message.soap.domain.ParameterInfo;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-08-26 20:35:54
 *
 */
public class WsInterfaceInfoUtils {

	public static WebServiceInterfaceInfo wsOperationInfo2InterfaceInfo(OperationInfo operInfo) {
		WebServiceInterfaceInfo interfaceInfo = null;
		if (null != operInfo) {
			interfaceInfo = new WebServiceInterfaceInfo();
			interfaceInfo.setName(operInfo.getTargetMethodName());
			interfaceInfo.setCategoryId(WSConstants.INTERFACE_CATEGORY_MEBSERVICE);
			interfaceInfo.setNamespaceURI(operInfo.getNamespaceURI());
			interfaceInfo.setNamespacePrefix(operInfo.getNamespacePrefix());
			interfaceInfo.setSoapAction(operInfo.getSoapActionURI());
			interfaceInfo.setInputMessagePartElementName(operInfo.getInputMessagePartElementName());
			interfaceInfo.setOutputMessagePartElementName(operInfo.getOutputMessagePartElementName());

			interfaceInfo.setStyle(operInfo.getStyle());
			interfaceInfo.setInputUse(operInfo.getInputUse());
			interfaceInfo.setOutputUse(operInfo.getOutputUse());

			interfaceInfo.setInputHeaderMessage(operInfo.getInputHeaderMessage());
			interfaceInfo.setOutputHeaderMessage(operInfo.getOutputHeaderMessage());

			interfaceInfo.setInputHeaderPart(operInfo.getInputHeaderPart());
			interfaceInfo.setOutputHeaderPart(operInfo.getOutputHeaderPart());

			interfaceInfo.setInputHeaderUse(operInfo.getInputHeaderUse());
			interfaceInfo.setOutputHeaderUse(operInfo.getOutputHeaderUse());

			Map<String, Parameter> paramTypes = new HashMap<>(0);
			Map<String, ParameterInfo> paramInfoTypes = operInfo.getTypes();
			if (ValidateUtils.isNotEmpty(paramInfoTypes)) {
				paramTypes = new HashMap<>(paramInfoTypes.size());
				interfaceInfo.setTypes(paramTypes);

				for (String key : paramInfoTypes.keySet()) {
					Parameter paramType = paramTypes.get(key);
					if (null == paramType) {
						paramType = new Parameter();
						paramTypes.put(key, paramType);
						wsParameterInfo2Parameter(paramInfoTypes.get(key), paramType, paramTypes, null);
					}
				}
			}
		}
		List<Parameter> inparameters = new ArrayList<>();
		return interfaceInfo;
	}

	private static void wsParameterInfo2Parameter(ParameterInfo wsParam, Parameter param,
			Map<String, Parameter> paramTypes, String inOutFlag) {
		if (null != wsParam) {
			param.setName(wsParam.getName());
			param.setDescription(WsInterfaceInfoUtils.toString(wsParam.getDocumenactionlist()));
			param.setInOutFlag(inOutFlag);
			param.setInternalType(wsParam.getKind());
		}

	}

	private static String toString(List<String> documenactionlist) {
		// TODO Auto-generated method stub
		return null;
	}

}
