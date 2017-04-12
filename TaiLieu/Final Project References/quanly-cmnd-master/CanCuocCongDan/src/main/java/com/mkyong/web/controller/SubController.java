package com.mkyong.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SubController {
	@RequestMapping(value = "/tro-giup")
	public ModelAndView troGiup(HttpSession session){
		ModelAndView model = new ModelAndView();
		model.setViewName("TroGiup");
		return model;
	}
	@RequestMapping(value = "/quy-dinh")
	public ModelAndView quyDinh(HttpSession session){
		ModelAndView model = new ModelAndView();
		model.setViewName("QuyDinh");
		return model;
	}
	@RequestMapping(value = "/quy-dinh-cap-can-cuoc-cong-dan")
	public ModelAndView quyDinhCapCCCD(HttpSession session){
		ModelAndView model = new ModelAndView();
		model.setViewName("QuyDinhCapCCCD");
		return model;
	}
	@RequestMapping(value = "/quy-dinh-tach-ho-khau")
	public ModelAndView quyDinhTachHoKhau(HttpSession session){
		ModelAndView model = new ModelAndView();
		model.setViewName("QuyDinhTachHoKhau");
		return model;
	}
}
