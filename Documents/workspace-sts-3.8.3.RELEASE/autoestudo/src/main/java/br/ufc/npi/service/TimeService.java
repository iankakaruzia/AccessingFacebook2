package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Jogador;
import br.ufc.npi.bean.Time;
import br.ufc.npi.repositorio.JogadorRepositorio;
import br.ufc.npi.repositorio.TimeRepositorio;

@Service
public class TimeService {
	
	@Autowired
	TimeRepositorio timeRepo;
	
	@Autowired
	JogadorRepositorio jogadorRepo;
	
	
	public Time salvarTime(String nome){
		Time time = new Time();
		time.setNome(nome);
		
		timeRepo.save(time);
		
		return time;
	}
	
	public List<Time> getTodosTimes(){
		return timeRepo.findAll();
	}
	
	public Time getTime(Integer id){
		Time time = timeRepo.findOne(id);
		
		return time;
	}
	
	public boolean addJogadorAoTime(Integer idTime, Integer jogadorSemTime){
		Time time = timeRepo.findOne(idTime);
		
		if(time.getJogadores().size() == 7){
			return false;
		}else{
		
			Jogador jogador = jogadorRepo.findOne(jogadorSemTime);
		
			time.getJogadores().add(jogador);
			jogador.setTime(time);
		
			timeRepo.save(time);
			jogadorRepo.save(jogador);
			return true;
		}
	}
	
	public void delJogadorDoTime(Integer idTime, Integer idJogador){
		Time time = timeRepo.findOne(idTime);
		Jogador jogador = jogadorRepo.findOne(idJogador);
		
		time.getJogadores().remove(jogador);
		jogador.setTime(null);
		
		timeRepo.save(time);
		jogadorRepo.save(jogador);
	}
	
	public void delTime(Integer idTime){
		Time time = timeRepo.findOne(idTime);
		
		Jogador jogador;
		
		for(int i = 0; i < time.getJogadores().size(); i++){
			jogador = time.getJogadores().get(i);
			jogador.setTime(null);
			
			jogadorRepo.save(jogador);
		}
		
		timeRepo.delete(time);
	}

}
