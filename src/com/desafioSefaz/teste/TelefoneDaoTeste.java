package com.desafioSefaz.teste;





import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;

import com.desafioSefaz.entities.Telefone;
import com.desafioSefaz.entities.Usuario;
import com.desafioSefaz.services.TelefoneServices;


@FixMethodOrder
public class TelefoneDaoTeste {

	
	@Test
	@Ignore
	public void teste1() {
		//INSERT
		TelefoneServices servicesTel = new TelefoneServices();
		Usuario u = new Usuario();
		u.setId(1L);
		Telefone t = new Telefone();
		t.setDd(81);
		t.setNumero("9 9999 - 9999");
		t.setTipo("Celular");
		t.setUsuario(u);
		servicesTel.insert(t);
	
	}
	
	@Test
	public void teste2(){
		// LISTAR
		Usuario u = new Usuario();
		u.setId(33L);
		TelefoneServices servicesTel = new TelefoneServices();
		servicesTel.findAll(u).forEach(x -> System.out.println(x));
	}
	
	@Test
	@Ignore
	public void teste3() {
		// EXCLUIR
		TelefoneServices servicesTel = new TelefoneServices();
		Usuario u = new Usuario();
		u.setId(2L);
		servicesTel.deleteById(u);
	}
	
	@Test
	@Ignore
	public void teste4() {
		// ATUALIZAR
		TelefoneServices servicesTel = new TelefoneServices();
		Telefone t = new Telefone();
		Usuario u = new Usuario();
		u.setId(1L);
		
		t.setId(1L);
		t.setDd(82);
		t.setNumero("91 9 9877 - 9999");
		t.setTipo("Celular");
		t.setUsuario(u);
		servicesTel.update(t);
	}


}
