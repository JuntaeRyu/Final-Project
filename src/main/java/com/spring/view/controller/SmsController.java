package com.spring.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Controller
public class SmsController {

	@RequestMapping(value = "/findIDPhoneCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public String findIDPhoneCheck(HttpServletRequest request)  {
		System.out.println("로그: EmailController: findIDPhoneCheck() ");

		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSBHHHR0PCUSYEY", "3RQY1UBQ6AM1UM6OGWCFR13OVKAYRUOT", "https://api.coolsms.co.kr");

		Message message = new Message();
		// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.

		int randomNumber = (int)((Math.random() * (999999 - 100000 + 1)) + 100000);

		message.setFrom("01047672570");
		message.setTo((String)request.getParameter("phoneNum"));
		message.setText("[TEST] 인증번호는" + "["+randomNumber+"]" + "입니다.");

		SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));

		return Integer.toString(randomNumber);
	}
	
	@RequestMapping(value = "/findPWPhoneCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public String findPWPhoneCheck(HttpServletRequest request)  {
		System.out.println("로그: EmailController: findPWPhoneCheck() ");
		
		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSBHHHR0PCUSYEY", "3RQY1UBQ6AM1UM6OGWCFR13OVKAYRUOT", "https://api.coolsms.co.kr");
		
		Message message = new Message();
		// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
		
		int randomNumber = (int)((Math.random() * (999999 - 100000 + 1)) + 100000);
		
		message.setFrom("01047672570");
		message.setTo((String)request.getParameter("phoneNum"));
		message.setText("[TEST] 인증번호는" + "["+randomNumber+"]" + "입니다.");
		
		SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
		
		return Integer.toString(randomNumber);
	}
	
	@RequestMapping(value = "/signupPhoneCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public String signupPhoneCheck(HttpServletRequest request)  {
		System.out.println("로그: EmailController: signupPhoneCheck() ");
		
		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSBHHHR0PCUSYEY", "3RQY1UBQ6AM1UM6OGWCFR13OVKAYRUOT", "https://api.coolsms.co.kr");
		
		Message message = new Message();
		// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
		
		int randomNumber = (int)((Math.random() * (999999 - 100000 + 1)) + 100000);
		
		message.setFrom("01047672570");
		message.setTo((String)request.getParameter("phoneNum"));
		message.setText("[TEST] 인증번호는" + "["+randomNumber+"]" + "입니다.");
		
		SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
		
		return Integer.toString(randomNumber);
	}
}