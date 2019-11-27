package com.ump.core.ws.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.management.RuntimeErrorException;
import javax.wsdl.WSDLException;

import com.sun.corba.se.spi.orb.Operation;
import com.ump.core.util.ValidateUtils;
import com.ump.core.ws.entity.OperationInfo;
import com.ump.core.ws.entity.ServiceInfo;
import com.ump.core.ws.entity.WebServiceInterfaceInfo;
import com.ump.core.ws.message.soap.domain.WSDLElementBuilder;

/**
 * WerbService接口管理器，用于缓存，获取InterfaceInfo对象
 * 
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-08-11 17:19:53
 */
public class WebServiceInterfaceManager {
	private static String wsdlRootDir = null;
	private static Map<String, ServiceInfo> wsdlCache = new ConcurrentHashMap<String, ServiceInfo>(128);
	private static Map<String, WebServiceInterfaceInfo> interfaceCache = new ConcurrentHashMap<String, WebServiceInterfaceInfo>(
			512);
	private static Map<String, ServiceInfo> relWsdlCache = new ConcurrentHashMap<String, ServiceInfo>(128);
	private static byte[] locks = new byte[0];

	public static void cleanupWsdlCache() {
		synchronized (locks) {
			wsdlCache.clear();
			interfaceCache.clear();
		}
	}

	/**
	 * 根据WSDL文件得到操作列表
	 * 
	 * @param wsdlFileFullPath
	 * @return
	 */
	public String[] getOperationsByWsdlFullPath(String wsdlFileFullPath) {
		ServiceInfo serviceInfo = loadWsdlByFullPath(wsdlFileFullPath);
		return getAllOperationName4ServiceInfo(serviceInfo);
	}

	private String[] getAllOperationName4ServiceInfo(ServiceInfo serviceInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void loadAllWsdlsFromDir(File rootFile) {

	}

	public static ServiceInfo loadWsdlByFullPath(String filePath) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ServiceInfo getServiceInfoByWsdl(String wsdlName) {
		ServiceInfo serviceInfo = wsdlCache.get(wsdlName);
		if (ValidateUtils.isEmpty(serviceInfo)) {
			serviceInfo = getServiceInfoByRelWsdlCache(wsdlName);
		}
		return serviceInfo;
	}

	private static ServiceInfo getServiceInfoByRelWsdlCache(String wsdlPath) {
		ServiceInfo serviceInfo = relWsdlCache.get(wsdlPath);
		if (ValidateUtils.isEmpty(serviceInfo)) {
			serviceInfo = WebServiceInterfaceManager.loadWsdl(new File(wsdlPath));
		}
		return serviceInfo;
	}

	private static ServiceInfo loadWsdl(File file) {
		ServiceInfo serviceInfo = null;
		String absolutePath = "esb:" + file.getAbsolutePath();
		ServiceInfo cachedServiceInfo = wsdlCache.get(absolutePath);
		if (!ValidateUtils.isEmpty(cachedServiceInfo)) {
			return cachedServiceInfo;
		}
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			String fileAbsolutePath = file.getParent();
			serviceInfo = loadWsdl(in, file.getAbsolutePath(), fileAbsolutePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WSDLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		wsdlCache.put(absolutePath, serviceInfo);
		return serviceInfo;
	}

	private static ServiceInfo loadWsdl(InputStream in, String contextUrl, String documentBase) throws WSDLException {
		ServiceInfo serviceInfo = null;
		WSDLElementBuilder builder = new WSDLElementBuilder();
		builder.setDocumentBase(documentBase);
		serviceInfo = builder.buildServiceInfo(in, contextUrl);
		return serviceInfo;
	}

	public static WebServiceInterfaceInfo getInterfaceInfoByFullPath(String wsdlFileFullPath, String operation) {
		// 输入参数校验
		File wsdlFile = new File(wsdlFileFullPath);
		validateWsdlFile(wsdlFile);
		String wsdlKey = wsdlFile.getAbsolutePath();
		String key = wsdlKey + "->" + operation;
		WebServiceInterfaceInfo interfaceInfo = interfaceCache.get(key);
		if (ValidateUtils.isNotEmpty(interfaceInfo)) {
			return interfaceInfo;
		}
		ServiceInfo serviceInfo = wsdlCache.get(wsdlKey);
		if (ValidateUtils.isEmpty(serviceInfo)) {
			serviceInfo = loadWsdl(wsdlFile);
			if (ValidateUtils.isEmpty(serviceInfo)) {
				throw new RuntimeException("the serviceinfo load wsdl file is null.");
			}
			wsdlCache.put(wsdlKey, serviceInfo);
		}

		interfaceInfo = getWsServiceInfoFromOperationInfo(serviceInfo, operation, wsdlFileFullPath);
		interfaceCache.put(key, interfaceInfo);
		return interfaceInfo;
	}

	private static void validateWsdlFile(File wsdlFile) {
		// TODO Auto-generated method stub

	}

	private static WebServiceInterfaceInfo getWsServiceInfoFromOperationInfo(ServiceInfo serviceInfo, String operation,
			String wsdlFileFullPath) {
		WebServiceInterfaceInfo interfaceInfo = null;
		OperationInfo operationInfo = getOperationInfo(serviceInfo, operation);
		if (null == operationInfo) {
			throw new RuntimeException("aaa");
		}
		interfaceInfo = WsInterfaceInfoUtils.wsOperationInfo2InterfaceInfo(operationInfo);
		interfaceInfo.setTypeNamespaceString(serviceInfo.getExtendedTypeNamespacesString());
		return interfaceInfo;
	}

	private static OperationInfo getOperationInfo(ServiceInfo serviceInfo, String operation) {
		for (OperationInfo operationInfo : serviceInfo.getOperations()) {
			if (operationInfo.getTargetMethodName().equals(operation)
					|| operationInfo.getInputMessagePartElementName().equals(operation)) {
				return operationInfo;
			}
		}
		return null;
	}

	public static WebServiceInterfaceInfo getInterfaceInfo(String wsdlFileName, String operation) {
		String key = wsdlFileName + "->" + operation;
		WebServiceInterfaceInfo interfaceInfo = interfaceCache.get(key);
		if (null != interfaceInfo) {
			return interfaceInfo;
		}
		ServiceInfo serviceInfo = wsdlCache.get(wsdlFileName);
		if (null == serviceInfo) {
			serviceInfo = getServiceInfoByRelWsdlCache(wsdlFileName);
		}
		if (null == serviceInfo) {
			throw new RuntimeException();
		}
		interfaceInfo = getWsServiceInfoFromOperationInfo(serviceInfo, operation, wsdlFileName);
		interfaceCache.put(key, interfaceInfo);
		return interfaceInfo;
	}

	public static WebServiceInterfaceInfo getInterfaceInfo(String qName) {
		return interfaceCache.get(qName);

	}
}
