package com.desafioSefaz.DAO;


import java.util.List;

import com.desafioSefaz.entities.Telefone;

public interface TelefoneDao {
	void insert(Telefone obj);
	void update(Telefone obj);
	void deleteById(Long id);
	List<Telefone> findAll(Long id);
	void deleteByNumero(String numero);
}
