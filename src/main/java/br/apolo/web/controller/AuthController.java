package br.apolo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.apolo.common.util.MessageBundle;
import br.apolo.web.enums.Navigation;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(ModelMap model) {
		ModelAndView mav = new ModelAndView(Navigation.AUTH_LOGIN.getPath(), model);
		return mav;
	}

	@RequestMapping(value = "loginfailed", method = RequestMethod.GET)
	public ModelAndView loginFailed(ModelMap model) {
		ModelAndView mav = new ModelAndView(Navigation.AUTH_LOGIN.getPath(), model);
		mav.addObject("error", true);
		mav.addObject("message", MessageBundle.getMessageBundle("user.login.failure"));		
		return mav;
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ModelAndView logout(ModelMap model) {
		ModelAndView mav = new ModelAndView(Navigation.AUTH_LOGIN.getPath(), model);
		return mav;
	}
}
