package com.ump;

import org.ump.exception.BusinessException;

import com.ump.util.exception.ErrorCode;

public class TestException {
	public static void main(String[] args) {
		try {

			//System.out.println(CommUtils.getErrorMsg("username"));
			testExp();

		} catch (BusinessException e) {
			System.out.println(e);
			System.out.println(e.getMessage());
			System.out.println(e.getMsg());
			System.out.println(ErrorCode.E_1001.getMessage());
		}
	}

	public static void testExp() {
		try {
			"".substring(3, 4);
		} catch (Exception e) {
//			throw new BusinessException(ErrorCode.E_1001,e,"abeg","ffg");
			throw new BusinessException("1002");
		}
	}
}