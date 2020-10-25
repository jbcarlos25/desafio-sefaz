package com.desafioSefaz.DAO;

import com.desafioSefaz.DAOImp.TelefoneDaoImp;
import com.desafioSefaz.DAOImp.UsuarioDaoImp;

public class DaoFactory {
	public static UsuarioDao createUsuarioDao() {
		return new UsuarioDaoImp();
	}
	public static TelefoneDao createTelefoneDao() {
		return new TelefoneDaoImp();
	}
}
