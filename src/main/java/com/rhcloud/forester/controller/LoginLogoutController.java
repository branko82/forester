package com.rhcloud.forester.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rhcloud.forester.dao.UserDao;

@Controller
public class LoginLogoutController {
	
	@Autowired
    private UserDao userDao;

	@RequestMapping(value = "/login/", method = RequestMethod.GET)
	public String handleLogin(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public String handleLogin(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String user=request.getParameter("user");
        String password=request.getParameter("password");
        if(userDao.login(user, password)) {
			HttpSession session = request.getSession();
            session.setAttribute("loginName", user);
            //setting session to expire in 30 minutes
            session.setMaxInactiveInterval(30*60);
            return "redirect:/";
		} else {
        	model.addAttribute("login_error", "greska prilikom prijavljivanja");
        	return "login";
        }
	}

	@RequestMapping(value = "/logout/", method = RequestMethod.GET)
	public String handleLogout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
		    session.invalidate();
		}
		return "redirect:/";
	}
}
