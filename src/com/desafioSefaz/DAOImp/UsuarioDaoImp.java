package com.desafioSefaz.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.desafioSefaz.DAO.UsuarioDao;
import com.desafioSefaz.DBconfig.BD;
import com.desafioSefaz.entities.Usuario;
import com.desafioSefaz.util.JSFUtil;

public class UsuarioDaoImp implements UsuarioDao {

	@Override
	public void insert(Usuario obj) {
		try {
			Connection conexao = BD.conectar();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_usuarios ");
			sql.append("(nome, email, senha) ");
			sql.append("VALUES (?,?,?) ");

			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, obj.getNome());
			comando.setString(2, obj.getEmail());
			comando.setString(3, obj.getSenha());
			comando.executeUpdate();
			JSFUtil.addMsgSucesso("Salvo com sucesso!");
		} catch (SQLException e) {
			JSFUtil.addMsgErrorNome(e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public void update(Usuario obj) {
		try {
			Connection conexao = BD.conectar();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_usuarios ");
			sql.append("SET nome = (?), email = (?), senha = (?)");
			sql.append("WHERE id = (?) ");
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, obj.getNome());
			comando.setString(2, obj.getEmail());
			comando.setString(3, obj.getSenha());
			comando.setLong(4, obj.getId());
			comando.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void deleteById(Long id) {

		try {
			Connection conexao = BD.conectar();
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM tb_usuarios ");
			sql.append("WHERE ");
			sql.append("id = (?) ");
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setLong(1, id);
			comando.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public Usuario findByNome(String nome) {
		Usuario u = new Usuario();
		try {
			
			Connection conexao = BD.conectar();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u.id, u.nome, u.email, u.senha  ");
			sql.append("FROM tb_usuarios u where u.nome = (?) ");
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, nome);
			ResultSet resultado = comando.executeQuery();

			if (resultado.next()) {
				u.setId(resultado.getLong("u.id"));
				u.setNome(resultado.getString("u.nome"));
				u.setEmail(resultado.getString("u.email"));
				u.setSenha(resultado.getString("u.senha"));

				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public List<Usuario> findAll() {
		List<Usuario> list = new ArrayList<Usuario>();

		try {
			Connection conexao = BD.conectar();
			StringBuilder sql = new StringBuilder();
			Usuario u;

			sql.append("SELECT u.id, u.nome, u.email, u.senha ");
			sql.append("FROM tb_usuarios u ");
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {
				u = new Usuario();
				u.setId(resultado.getLong("u.id"));
				u.setNome(resultado.getString("u.nome"));
				u.setEmail(resultado.getString("u.email"));
				u.setSenha(resultado.getString("u.senha"));

				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
