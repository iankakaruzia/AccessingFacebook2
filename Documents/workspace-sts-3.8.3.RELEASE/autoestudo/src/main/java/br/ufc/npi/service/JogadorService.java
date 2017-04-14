package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Jogador;
import br.ufc.npi.bean.Time;
import br.ufc.npi.repositorio.JogadorRepositorio;
import br.ufc.npi.repositorio.TimeRepositorio;

@Service
public class JogadorService {
	
	@Autowired
	JogadorRepositorio repo;
	
	@Autowired
	TimeRepositorio timeRepo;
	
	public Jogador salvarJogador(String nome, int idade){
		Jogador jogador = new Jogador();
		
		jogador.setIdade(idade);
		jogador.setNome(nome);
		
		repo.save(jogador);
		
		return jogador;
	}
	
	public List<Jogador> getTodosJogadores(){
		return repo.findAll();
	}
	
	public List<Jogador> getJogadorSemTime(){
		return repo.buscarJogadorSemTime();
	}
	
	public void delJogador(Integer idJogador){
		Jogador jogador = repo.findOne(idJogador);
		
		if(jogador.getTime() != null){
			Time time = timeRepo.findOne(jogador.getTime().getId());
			time.getJogadores().remove(jogador);
			timeRepo.save(time);
		}
		
		repo.delete(jogador);
	}

}
