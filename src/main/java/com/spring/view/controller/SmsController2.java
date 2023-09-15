//package com.spring.view.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import net.nurigo.sdk.NurigoApp;
//import net.nurigo.sdk.message.model.Message;
//import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
//import net.nurigo.sdk.message.response.SingleMessageSentResponse;
//import net.nurigo.sdk.message.service.DefaultMessageService;
//
//@Controller
//public class SmsController2 {
//
//	@RequestMapping(value = "/sendSms.do")
//	public String sms(HttpServletRequest request)  {
//		
//		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSKXOOKCT9HR3D8", "KFJXBYFIOAHXKHHXNTQ794BBKBRXLAFA", "https://api.coolsms.co.kr");
//
//		Message message = new Message();
//		// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
//		message.setFrom("01026500656");
//		message.setTo((String)request.getParameter("to"));
//		message.setText((String)request.getParameter("text"));
//
//		SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
//
//	    return "main.do";
//	}
//}
