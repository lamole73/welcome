package lo.example.welcome.welcomeapp.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	@Value("${welcome.currentenv:DEFAULT}")
	private String currentenv;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("currentenv", currentenv);
		return "index";
	}

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("currentenv", currentenv);
		model.addAttribute("name", name);
		return "greeting";
	}

}
