package ir.com.application.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Welcome {

	@GetMapping(value = { "/", "/home" })
	public String homePage() {

		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "login_page";
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {

		return "error";
	}

	@ResponseBody
	@RequestMapping(value = "/username", method = RequestMethod.GET)
	public String currentUserName(Authentication authentication) {

		return authentication.getName();
	}

	@PostMapping("/sec/admin")
	public String postAdminPage() {

		return "admin";
	}

	@GetMapping("/sec/admin")
	public String getAdminPage() {

		return "admin";
	}

	@PostMapping("/sec/user")
	public String postUserPage() {

		return "user";
	}

	@GetMapping("/sec/user")
	public String getUserPage() {

		return "user";
	}

}
