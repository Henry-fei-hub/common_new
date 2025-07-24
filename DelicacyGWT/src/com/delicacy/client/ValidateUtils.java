package com.delicacy.client;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.widgets.form.validator.ContainsValidator;
import com.smartgwt.client.widgets.form.validator.FloatRangeValidator;
import com.smartgwt.client.widgets.form.validator.IntegerRangeValidator;
import com.smartgwt.client.widgets.form.validator.IsFloatValidator;
import com.smartgwt.client.widgets.form.validator.IsIntegerValidator;
import com.smartgwt.client.widgets.form.validator.LengthRangeValidator;
import com.smartgwt.client.widgets.form.validator.MaskValidator;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.smartgwt.client.widgets.form.validator.Validator;

/**
 * 
 * @ClassName: ValidateUtils
 * @Description: 前端表单验证工具类
 * @author CL
 * @date 2016年10月9日
 *
 */
public class ValidateUtils {

	private static MessageConstants messageConstants;

	static {
		messageConstants = GWT.create(MessageConstants.class);
	}

	// 验证邮箱
	public static Validator emailValidator() {
		RegExpValidator emailValidator = new RegExpValidator();
		emailValidator.setErrorMessage(messageConstants.emailError());
		emailValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");
		return emailValidator;
	}

	// 验证电话号码
	public static Validator telValidator() {
		MaskValidator maskValidator = new MaskValidator();
		maskValidator.setErrorMessage(messageConstants.telephoneError());
		maskValidator.setMask("^0(10|2[0-5789]|\\d{3})\\d{7,8}$");
		return maskValidator;

	}

	// 验证传真号码
	public static Validator faxValidator() {
		MaskValidator faxValidator = new MaskValidator();
		faxValidator.setErrorMessage(messageConstants.faxError());
		faxValidator.setMask("^0(10|2[0-5789]|\\d{3})\\d{7,8}$");
		return faxValidator;

	}

	// 验证手机号码
	public static Validator mobileValidator() {
		MaskValidator maskValidator = new MaskValidator();
		maskValidator.setErrorMessage(messageConstants.mobileError());
//		maskValidator.setMask("^\\s*(1?)\\s*\\(?\\s*(\\d{3})\\s*\\)?\\s*-?\\s*(\\d{3})\\s*-?\\s*(\\d{4})\\s*$");
		maskValidator.setMask("^[1][3-9]\\d{9}$|^[8][5][2]\\d{8}$|^[6]([8|6])\\d{5}$");
//		maskValidator.setTransformTo("$1($2) $3 - $4");
		return maskValidator;

	}

	// 验证字符串长度范围
	public static Validator StringLenValidator(int startLen, int endLen) {
		LengthRangeValidator lengthValidator = new LengthRangeValidator();
		if (startLen == endLen) {
			lengthValidator.setErrorMessage(messageConstants.stringLenError(startLen));
		} else {
			lengthValidator.setErrorMessage(messageConstants.stringLenRangeError(startLen, endLen));
		}
		lengthValidator.setMin(startLen);
		lengthValidator.setMax(endLen);
		return lengthValidator;
	}

	// 验证小数范围
	public static Validator decimalRangeValidator(float minValue, float maxValue) {
		FloatRangeValidator floatRangeValidator = new FloatRangeValidator();
		floatRangeValidator.setErrorMessage(messageConstants.floatRangeError(minValue, maxValue));
		floatRangeValidator.setMin(minValue);
		floatRangeValidator.setMax(maxValue);
		return floatRangeValidator;
	}

	// 验证是否纯整数
	public static Validator isIntValidator() {
		IsIntegerValidator intValidator = new IsIntegerValidator();
		intValidator.setErrorMessage(messageConstants.intError());
		return intValidator;
	}

	// 验证是否小数
	public static Validator isFloatValidator() {
		IsFloatValidator floatValidator = new IsFloatValidator();
		floatValidator.setErrorMessage(messageConstants.floatError());
		return floatValidator;
	}

	// 验证是否字符串中的字符是否有效
	public static Validator containStringValidator(String containString) {
		ContainsValidator containsValidator = new ContainsValidator();
		containsValidator.setSubstring(containString);
		containsValidator.setErrorMessage(messageConstants.containStringError(containString));
		return containsValidator;
	}

	// 验证整数范围
	public static Validator IntRangeValidator(int minValue, int maxValue) {
		IntegerRangeValidator integerRangeValidator = new IntegerRangeValidator();
		integerRangeValidator.setErrorMessage(messageConstants.intRangeError(minValue, maxValue));
		integerRangeValidator.setMin(minValue);
		integerRangeValidator.setMax(maxValue);

		return integerRangeValidator;
	}

	// 验证邮编
	public static Validator ZipCodeValidator() {
		RegExpValidator zipCodeValidator = new RegExpValidator();
		zipCodeValidator.setErrorMessage(messageConstants.zipCodeError());
		zipCodeValidator.setExpression("^\\d{6}(-\\d{5})?$");
		return zipCodeValidator;
	}

	// 验证网址是否合法
	public static Validator urlValidator() {
		RegExpValidator urlValidator = new RegExpValidator();
		urlValidator.setErrorMessage(messageConstants.urlError());
		urlValidator.setExpression("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?");
		return urlValidator;
	}
	
	public static Validator bankAccountValidator() {
		RegExpValidator urlValidator = new RegExpValidator();
		urlValidator.setErrorMessage(messageConstants.bankAccountError());
		urlValidator.setExpression("^[0-9a-zA-Z]+$");
		return urlValidator;
	}

	public static Validator chineseValidator(){
		RegExpValidator chineseValidator = new RegExpValidator();
		chineseValidator.setErrorMessage(messageConstants.ChineseError());
		chineseValidator.setExpression("^[^\\u4e00-\\u9fa5]{0,}$");
		return chineseValidator;
	}
}
