package com.desafioSefaz.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.desafioSefaz.entities.Telefone;
import com.desafioSefaz.entities.Usuario;
import com.desafioSefaz.services.TelefoneServices;
import com.desafioSefaz.services.UsuarioServices;
import com.desafioSefaz.util.JSFUtil;

@ManagedBean(name = "MBUsuarios")
@ViewScoped
public class UsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Telefone telefone;

	private List<Usuario> itens;
	private List<Usuario> itensFiltrados;
	private List<Telefone> listaTel;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getItens() {
		return itens;
	}

	public List<Telefone> getListaTel() {
		return listaTel;
	}

	public void setListaTel(List<Telefone> listaTel) {
		this.listaTel = listaTel;
	}

	public void setItens(List<Usuario> itens) {
		this.itens = itens;
	}

	public List<Usuario> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(List<Usuario> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public String logIn() {
		UsuarioServices servicesUso = new UsuarioServices();
		List<Usuario> listLogin = servicesUso.findAll();
		Usuario admin = new Usuario();
		admin.setNome("admin");
		admin.setSenha(JSFUtil.MD5("admin"));
		listLogin.add(admin);
		List<Usuario> userTempf = listLogin.stream()
				.filter(c -> c.getNome().toUpperCase().equals(usuario.getNome().toUpperCase().toString()))
				.collect(Collectors.toList());
		if (userTempf.isEmpty()) {
			JSFUtil.addMsgErrorNome("Usu�rio n�o cadastrado!");
		} else {
			Usuario acesso = userTempf.get(0);
			if (acesso != null && acesso.getSenha().equals(JSFUtil.MD5(usuario.getSenha().toString()))) {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
						.getSession(false);
				if (session != null) {
					session.setAttribute("usuario", usuario);
				}
				JSFUtil.addMsgSucesso("Logado com sucesso!");
				return "home?faces-redirect=true";

			} else {
				JSFUtil.addMsgErrorNome("Senha incorreta!");
				return null;
			}
		}
		return null;
	}

	public String logOff() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();
		return "login?faces-redirect=true";
	}

	@PostConstruct
	public void prepararPesquisa() {

		UsuarioServices servicesUso = new UsuarioServices();
		itens = servicesUso.findAll();

		for (Usuario itl : itens) {
			TelefoneServices servTel = new TelefoneServices();
			List<Telefone> tempTel = servTel.findAll(itl);
			itl.setTelefones(tempTel);
		}
	}

	public void prepararNovo() {
		usuario = new Usuario();
	}

	public void prepararNovoLista() {
		listaTel = new ArrayList<>();
		telefone = new Telefone();
		usuario = new Usuario();
	}

	public void prepararTelEdit() {
		TelefoneServices servTel = new TelefoneServices();
		telefone = new Telefone();
		listaTel = servTel.findAll(usuario);
		usuario.setTelefones(listaTel);

	}

	public void addTel() {

		listaTel.add(telefone);
		telefone = new Telefone();
	}

	public void addTel2() {
		TelefoneServices servTel = new TelefoneServices();
		listaTel.add(telefone);
		telefone.setUsuario(usuario);
		servTel.insert(telefone);
		telefone = new Telefone();
	}

	public void removeTel2() {
		TelefoneServices servTel = new TelefoneServices();

		telefone.setUsuario(usuario);
		servTel.deleteByNumero(telefone);
		listaTel = servTel.findAll(usuario);
		telefone = new Telefone();
	}

	public void novo() {
		UsuarioServices servicesUso = new UsuarioServices();
		TelefoneServices servicesTel = new TelefoneServices();
		servicesUso.insert(usuario);
		Usuario ut = servicesUso.findByNome(usuario);

		listaTel.forEach(x -> x.setUsuario(ut));
		listaTel.forEach(x -> servicesTel.insert(x));

		itens = servicesUso.findAll();
		for (Usuario itl : itens) {
			TelefoneServices servTel = new TelefoneServices();
			List<Telefone> tempTel = servTel.findAll(itl);
			itl.setTelefones(tempTel);
		}

	}

	public void excluir() {
		UsuarioServices servicesUso = new UsuarioServices();
		TelefoneServices servTel = new TelefoneServices();
		servTel.deleteById(usuario);
		servicesUso.deleteById(usuario);
		itens = servicesUso.findAll();
		for (Usuario itl : itens) {
			servTel = new TelefoneServices();
			List<Telefone> tempTel = servTel.findAll(itl);
			itl.setTelefones(tempTel);
		}

		JSFUtil.addMsgSucesso("Excluido com sucesso!");
	}

	public void editar() {
		UsuarioServices servicesUso = new UsuarioServices();
		servicesUso.update(usuario);
		itens = servicesUso.findAll();
		for (Usuario itl : itens) {
			TelefoneServices servTel = new TelefoneServices();
			List<Telefone> tempTel = servTel.findAll(itl);
			itl.setTelefones(tempTel);
		}

		JSFUtil.addMsgSucesso("Atualizado com sucesso!");
	}

}
