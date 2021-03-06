package br.ufc.npi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.service.JogadorService;

@Controller
@RequestMapping(path="/jogadores/")
public class JogadorController {
	
	@Autowired
	JogadorService  service;
	
	@RequestMapping(path="/")
	public ModelAndView index(){
		ModelAndView model = new ModelAndView("jogadores");
		
		model.addObject("jogadores", service.getTodosJogadores());
		
		return model;
	}
	
	@RequestMapping(path="/salvar", method=RequestMethod.POST)
	public String salva(@RequestParam String nome, @RequestParam Integer idade){
		service.salvarJogador(nome, idade);
		
		return "redirect:/jogadores/";
	}
	
	@RequestMapping(path="/excluir/{idJogador}")
	public String deletaJogador(@PathVariable("idJogador") Integer idJogador){
		service.delJogador(idJogador);
		
		return "redirect:/jogadores/";
	}

}
