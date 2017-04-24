package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsuarioController {
	
	public String loggin(){
		return "entrou";
	}
	
	@RequestMapping(path="/logout")
	public String logout(){
		return "saiu";
	}

}
