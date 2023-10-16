package com.spring.view.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(value = "/signupSuccess.do")
	public String signupSuccess(HttpServletRequest request, Model model) {
		System.out.println("로그: EmailController: signupSuccess() ");

		String title = "[헬스해듀오] 더 나은 몸과 마음을 위한 당신만의 여정";
		String receiver = (String)request.getParameter("email");
		String name = (String)request.getParameter("name");
		String content = "<h2>" + name + "님의 회원가입을 진심으로 축하드립니다~!!</h2><br>"
				+ "헬스해듀오 관리자입니다. 헬스해듀오로 발걸음해주셔서 정말 감사합니다.<br>"
				+ "앞으로 더 나은 헬스해듀오가 되겠습니다~^^";
		String from = "rkdtmdcks012@gmail.com";

		// 이메일 제목과 내용 설정

		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");

			mailHelper.setFrom(from);
			mailHelper.setTo(receiver);
			mailHelper.setSubject(title);
			mailHelper.setText(content, true);

			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("title", "회원가입 성공!");
		model.addAttribute("text", "로그인을 해주세요");
		model.addAttribute("icon", "success");
		model.addAttribute("url", "main.do");
		
		return "SweetAlert2.jsp";
	}

	@RequestMapping(value = "/findIDEmailCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public String findIDEmailCheck(HttpServletRequest request)  {
		System.out.println("로그: EmailController: findIDEmailCheck() ");
		
		int randomNumber = (int)((Math.random() * (999999 - 100000 + 1)) + 100000);
		
		String title = "[헬스해듀오] 더 나은 몸과 마음을 위한 당신만의 여정";
		String receiver = (String)request.getParameter("email");
		String content = "<h2> [헬스해듀오] 에서 보낸 아이디 찾기 인증번호 입니다</h2><br>"
				+ randomNumber + "<br>"
				+ "인증번호 입력란에 입력해주세요";
		String from = "rkdtmdcks012@gmail.com";

		// 이메일 제목과 내용 설정

		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");

			mailHelper.setFrom(from);
			mailHelper.setTo(receiver);
			mailHelper.setSubject(title);
			mailHelper.setText(content, true);

			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Integer.toString(randomNumber);
	}
	
	@RequestMapping(value = "/findPWEmailCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public String findPWEmailCheck(HttpServletRequest request)  {
		System.out.println("로그: EmailController: findPWEmailCheck() ");
		
		int randomNumber = (int)((Math.random() * (999999 - 100000 + 1)) + 100000);
		
		String title = "[헬스해듀오] 더 나은 몸과 마음을 위한 당신만의 여정";
		String receiver = (String)request.getParameter("email");
		String content = "<h2> [헬스해듀오] 에서 보낸 아이디 찾기 인증번호 입니다</h2><br>"
				+ randomNumber + "<br>"
				+ "인증번호 입력란에 입력해주세요";
		String from = "rkdtmdcks012@gmail.com";
		
		// 이메일 제목과 내용 설정
		
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
			
			mailHelper.setFrom(from);
			mailHelper.setTo(receiver);
			mailHelper.setSubject(title);
			mailHelper.setText(content, true);
			
			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Integer.toString(randomNumber);
	}

}
