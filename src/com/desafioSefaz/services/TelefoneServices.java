package com.desafioSefaz.services;

import java.util.List;

import com.desafioSefaz.DAO.DaoFactory;
import com.desafioSefaz.DAO.TelefoneDao;
import com.desafioSefaz.entities.Telefone;
import com.desafioSefaz.entities.Usuario;

public class TelefoneServices {
	private TelefoneDao dao = DaoFactory.createTelefoneDao();
	
	public List<Telefone> findAll(Usuario obj) {
		return dao.findAll(obj.getId());
	}
	public void insert(Telefone obj) {
		dao.insert(obj);

	}

	public void update(Telefone obj) {
		dao.update(obj);
	}

	public void deleteById(Usuario obj) {
		dao.deleteById(obj.getId());
	}
	public void deleteByNumero(Telefone obj) {
		dao.deleteByNumero(obj.getNumero());
		
	}
}
