package com.desafioSefaz.DAO;


import java.util.List;

import com.desafioSefaz.entities.Usuario;

public interface UsuarioDao {
	void insert(Usuario obj);
	void update(Usuario obj);
	void deleteById(Long id);
	Usuario findByNome(String nome);
	List<Usuario> findAll();
}
