package com.desafioSefaz.DBconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {
	private static final String USUARIO = "root";
	private static final String SENHA = "1234567";
	private static final String URL = "jdbc:mysql://localhost:3306/desafioSefaz?useTimezone=true&serverTimezone=UTC";

	public static Connection conectar() throws SQLException {
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}

}
