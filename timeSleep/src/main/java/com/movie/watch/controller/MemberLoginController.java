package com.movie.watch.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.movie.watch.service.MemberService;
import com.movie.watch.vo.MemberVO;

import lombok.AllArgsConstructor;
import net.sf.json.JSONObject;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/member/**")
public class MemberLoginController {
	private static final Logger logger = LoggerFactory.getLogger(MemberLoginController.class);

	private MemberService service;

	@ResponseBody
	@RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.GET }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
	public String loginMember(Model model, @RequestBody MemberVO vo, HttpServletRequest request) throws Exception {
		logger.info("회원가입요청");
		logger.info("뭐가 넘어오나요??" + vo);
		String resultJsonObject = null;
		HttpSession session = request.getSession();

		MemberVO object = service.memberLoginService(vo);

		JSONObject jsonObejct = new JSONObject();

		if (object != null) {
			jsonObejct.put("signal", "success");

			session.setAttribute("member", object);
			jsonObejct.put("member", session.getAttribute("member"));
			return jsonObejct.toString();
		} else {
			jsonObejct.put("signal", "fail");
			return jsonObejct.toString();
		}
	}

	@GetMapping(value = "/logout")
	public String logoutMember(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();

		MemberVO vo = (MemberVO) session.getAttribute("member");
		System.out.println("나는 무슨 세션이냐??" + vo);

		session.invalidate();
		System.out.println("session값을 지워 줍니다 .");

		return "/index";

	}

	// 회원가입으로이동
	@GetMapping(value = "/register")
	public String registerMember(Model model, HttpServletRequest request) {

		logger.info("회원 가입으로 이동");
		model.addAttribute("page", "register");
		return "/index";

	}

}
