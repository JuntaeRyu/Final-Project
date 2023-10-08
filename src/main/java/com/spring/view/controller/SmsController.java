package com.spring.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Controller
public class SmsController {
	
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/findIDPhoneCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public String findIDPhoneCheck(HttpServletRequest request)  {
		System.out.println("로그: EmailController: findIDPhoneCheck() ");

		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSZV03JHSYTAXF0", "UORY9V8ZPMS9UQOZZ1CNQ72A87YX93DK", "https://api.coolsms.co.kr");

		Message message = new Message();
		// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.

		int randomNumber = (int)((Math.random() * (999999 - 100000 + 1)) + 100000);

		message.setFrom("01073200321");
		message.setTo((String)request.getParameter("phoneNum"));
		message.setText("[TEST] 인증번호는" + "[" + randomNumber + "]" + "입니다.");

		SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));

		return Integer.toString(randomNumber);
	}

	@RequestMapping(value = "/findPWPhoneCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public String findPWPhoneCheck(HttpServletRequest request)  {
		System.out.println("로그: EmailController: findPWPhoneCheck() ");

		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSZV03JHSYTAXF0", "UORY9V8ZPMS9UQOZZ1CNQ72A87YX93DK", "https://api.coolsms.co.kr");
		
		Message message = new Message();
		// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.

		int randomNumber = (int)((Math.random() * (999999 - 100000 + 1)) + 100000);

		message.setFrom("01073200321");
		message.setTo((String)request.getParameter("phoneNum"));
		message.setText("[TEST] 인증번호는" + "[" + randomNumber + "]" + "입니다.");

		SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));

		return Integer.toString(randomNumber);
	}

	@RequestMapping(value = "/signupPhoneCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public String signupPhoneCheck(MemberVO mVO, HttpServletRequest request)  {
		System.out.println("로그: EmailController: signupPhoneCheck() ");

		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSZV03JHSYTAXF0", "UORY9V8ZPMS9UQOZZ1CNQ72A87YX93DK", "https://api.coolsms.co.kr");

		Message message = new Message();
		// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.

		mVO.setSearchCondition("searchIDPhoneNum");
		
		mVO = memberService.selectOne(mVO);
		
		if(mVO == null) {
			int randomNumber = (int)((Math.random() * (999999 - 100000 + 1)) + 100000);
			
			message.setFrom("01073200321");
			message.setTo((String)request.getParameter("phoneNum"));
			message.setText("[TEST] 인증번호는" + "[" + randomNumber + "]" + "입니다.");
			
			SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
			
			return Integer.toString(randomNumber);
		}
		else {
			return "1";
		}
		
	}
}