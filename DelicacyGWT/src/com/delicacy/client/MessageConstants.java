package com.delicacy.client;

/**
 * 
 * @ClassName: MessageConstants
 * @Description: 前端数据验证错误信息提示
 * @author CL
 * @date 2016年10月9日
 *
 */
public class MessageConstants {

	public String emailError() {
		return "邮箱格式不正确";
	}

	public String telephoneError() {
		return "电话号码不正确";
	}

	public String faxError() {
		return "传真号码格式不正确";
	}

	public String mobileError() {
		return "手机号码格式不正确";
	}

	public String stringLenError(int startLen) {
		return "长度必须为" + startLen;
	}

	public String stringLenRangeError(int startLen, int endLen) {
		return "长度需要在" + startLen + "~" + endLen + "之间";
	}

	public String floatRangeError(float minValue, float maxValue) {
		return "数值必须大于" + minValue + "且小于" + maxValue;
	}

	public String intError() {
		return "数据非纯整数";
	}

	public String floatError() {
		return "数据非数字类型";
	}

	public String containStringError(String containString) {
		return "没有包含[" + containString + "]字符";
	}

	public String intRangeError(int minValue, int maxValue) {
		return "数值必须大于" + minValue + "且小于" + maxValue;
	}

	public String zipCodeError() {
		return "邮编格式错误";
	}

	public String urlError() {
		return "url地址格式错误";
	}

	public String isLengthAndNumValidatorError(int len) {
		return "长度必须为" + len + "且为纯数字";
	}
	
	public String bankAccountError(){
		return "银行账号格式错误,只能输入数字和字母不能有空格或特殊字符";
	}
	public String ChineseError(){
		return "不能输入中文";
	}
}
