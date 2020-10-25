package com.desafioSefaz.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.desafioSefaz.DAO.TelefoneDao;
import com.desafioSefaz.DBconfig.BD;
import com.desafioSefaz.entities.Telefone;
import com.desafioSefaz.entities.Usuario;

public class TelefoneDaoImp implements TelefoneDao {

	@Override
	public void insert(Telefone obj) {
		try {
			Connection conexao = BD.conectar();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_telefones ");
			sql.append("(dd, numero, tipo, tb_usuarios_id) ");
			sql.append("VALUES (? , ? , ? , ? ) ");

			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setInt(1, obj.getDd());
			comando.setString(2, obj.getNumero());
			comando.setString(3, obj.getTipo());
			comando.setLong(4, obj.getUsuario().getId());
			comando.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Telefone obj) {

		try {
			Connection conexao = BD.conectar();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_telefones ");
			sql.append("SET dd = (?), numero = (?), tipo = (?), tb_usuarios_id = (?) ");
			sql.append("WHERE id = (?) ");
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setInt(1, obj.getDd());
			comando.setString(2, obj.getNumero());
			comando.setString(3, obj.getTipo());
			comando.setLong(4, obj.getUsuario().getId());
			comando.setLong(5, obj.getId());
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
			sql.append("DELETE FROM tb_telefones ");
			sql.append("WHERE ");
			sql.append("tb_usuarios_id = (?) ");
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setLong(1, id);
			comando.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	@Override
	public void deleteByNumero(String numero) {

		try {
			Connection conexao = BD.conectar();
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM tb_telefones ");
			sql.append("WHERE ");
			sql.append("numero = (?) ");
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, numero);
			comando.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Telefone> findAll(Long id) {
		List<Telefone> list = new ArrayList<Telefone>();

		try {
			Connection conexao = BD.conectar();
			StringBuilder sql = new StringBuilder();
			Telefone t;
			Usuario u;

			sql.append("SELECT t.id, t.dd, t.numero, t.tipo, u.id, u.nome, u.email, u.senha FROM tb_telefones t ");
			sql.append("INNER JOIN tb_usuarios u ON u.id = t.tb_usuarios_id WHERE u.id = (?) ");
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setLong(1, id);
			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {
				t = new Telefone();
				u = new Usuario();
				u.setId(resultado.getLong("u.id"));
				u.setNome(resultado.getString("u.nome"));
				u.setEmail(resultado.getString("u.email"));
				u.setSenha(resultado.getString("u.senha"));
				t.setId(resultado.getLong("t.id"));
				t.setDd(resultado.getInt("t.dd"));
				t.setNumero(resultado.getString("t.numero"));
				t.setTipo(resultado.getString("t.tipo"));
				t.setUsuario(u);

				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
