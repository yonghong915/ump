package com.ump.core.ws.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.exolab.castor.xml.schema.Schema;

import com.ump.core.util.ValidateUtils;
import com.ump.core.ws.message.soap.domain.ParameterInfo;

import lombok.Data;

@Data
public class ServiceInfo {
	String NEW_LINE = "\n";
	private String name;

	private String wsdllocation;

	private String endpoint;

	private String targetnamespace;

	private Schema wsdlType;

	private Map<?, ?> usedExtendedTypeNamespaces;

	private String extendedTypeNamespacesString;

	private List<OperationInfo> operations = new ArrayList<>();

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(this.name + "提供的操作有：");
		buf.append(NEW_LINE);
		if (ValidateUtils.isEmpty(this.operations)) {
			return "";
		}
		for (int i = 0, len = this.operations.size(); i < len; i++) {
			OperationInfo oper = this.operations.get(i);
			buf.append("操作：").append(i + 1).append(" ").append(oper.getTargetMethodName());
			buf.append(NEW_LINE);
			Map<String, ParameterInfo> paramTypes = oper.getTypes();
			List<ParameterInfo> inps = oper.getInputparamters();
			List<ParameterInfo> outps = oper.getOutputparamters();
			if (ValidateUtils.isEmpty(paramTypes)) {
				buf.append("执行此操作没有预定义参数！");
				buf.append(NEW_LINE);
			} else {
				buf.append("此操作的预定义参数为：");
				buf.append(NEW_LINE);
				for (String key : paramTypes.keySet()) {
					ParameterInfo element = paramTypes.get(key);
					recursiveParameter(element, buf, "");
				}
			}

			if (ValidateUtils.isEmpty(inps)) {
				buf.append("执行此操作不需要输入任何参数！");
				buf.append(NEW_LINE);
			} else {
				buf.append("此操作所需要的输入参数为：");
				buf.append(NEW_LINE);
				for (int j = 0; j < inps.size(); j++) {
					ParameterInfo element = inps.get(j);
					recursiveParameter(element, buf, "");
				}
			}

			if (ValidateUtils.isEmpty(outps)) {
				buf.append("执行此操作不需要返回任何参数！");
				buf.append(NEW_LINE);
			} else {
				buf.append("此操作所需要的输出参数为：");
				buf.append(NEW_LINE);
				for (int j = 0; j < outps.size(); j++) {
					ParameterInfo element = outps.get(j);
					recursiveParameter(element, buf, "");
				}
			}
		}
		return buf.toString();
	}

	private void recursiveParameter(ParameterInfo param, StringBuffer buf, String indent) {
		buf.append(indent).append("参数名：").append(param.getName()).append(",");
		buf.append("类型：").append(param.getKind()).append(",");
		buf.append((ValidateUtils.isEmpty(param.getRefType()) ? "" : "引用类型：")).append(param.getRefType());
		buf.append(NEW_LINE);
		ParameterInfo refParam = param.getRefTypeParameterInfo();
		if (ValidateUtils.isEmpty(refParam)) {
			for (ParameterInfo pa : param.getChildParameters()) {
				recursiveParameter(pa, buf, indent + "\t");
			}
		}
	}

}
