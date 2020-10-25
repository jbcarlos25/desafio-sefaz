package com.desafioSefaz.services;

import java.util.List;

import com.desafioSefaz.DAO.DaoFactory;
import com.desafioSefaz.DAO.UsuarioDao;
import com.desafioSefaz.entities.Usuario;

public class UsuarioServices {
	private UsuarioDao dao = DaoFactory.createUsuarioDao();
	
	public List<Usuario> findAll() {
		return dao.findAll();
	}
	public void insert(Usuario obj) {
		dao.insert(obj);

	}

	public void update(Usuario obj) {
		dao.update(obj);
	}
	
	public Usuario findByNome(Usuario obj) {
		return dao.findByNome(obj.getNome());
	}

	public void deleteById(Usuario obj) {
		dao.deleteById(obj.getId());
	}
}
