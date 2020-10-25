package com.desafioSefaz.teste;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.desafioSefaz.entities.Usuario;
import com.desafioSefaz.services.UsuarioServices;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioDaoTeste {

	
	@Test
	@Ignore
	public void teste1() {
		//INSERT
		UsuarioServices servicesUsu = new UsuarioServices();
		Usuario u = new Usuario();
		u.setNome("teste");
		u.setEmail("teste@email.com");
		u.setSenha("senha");
		
		servicesUsu.insert(u);
	
	}
	
	@Test
	public void teste2(){
		// LISTAR
		UsuarioServices servicesUsu = new UsuarioServices();
		servicesUsu.findAll().forEach(x -> System.out.println(x));
	}
	
	@Test
	@Ignore
	public void teste3() {
		// EXCLUIR
		UsuarioServices servicesUsu = new UsuarioServices();
		Usuario u = new Usuario();
		u.setId(2L);
		servicesUsu.deleteById(u);
	}
	
	@Test
	@Ignore
	public void teste4() {
		// ATUALIZAR
		UsuarioServices servicesUsu = new UsuarioServices();
		Usuario u = new Usuario();
		u.setId(1L);
		u.setNome("testeAtualizado");
		u.setEmail("testeAtualizado@email.com");
		u.setSenha("senhaAtualizada");
		servicesUsu.update(u);
	}
	@Test
	@Ignore
	public void teste5() {
		//PESQUISAR POR NOME
		UsuarioServices servicesUsu = new UsuarioServices();
		Usuario u = new Usuario();
		u.setId(2L);
		u.setNome("testeAtualizado");
		servicesUsu.findByNome(u);
	}


}
