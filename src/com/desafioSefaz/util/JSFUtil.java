package com.desafioSefaz.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {
	
		public static void addMsgSucesso(String msg) {
			FacesMessage fcmsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, fcmsg);
		}
		public static void addMsgErrorNome(String msg) {
			FacesMessage fcmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, fcmsg);
		}
		 public static String MD5(String senha) {
			 if(null == senha) return null;
		        try {
		            MessageDigest md = MessageDigest.getInstance("MD5");
		            byte[] array = md.digest(senha.getBytes());
		            StringBuilder sb = new StringBuilder();
		            for (int i = 0; i < array.length; ++i) {
		                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
		            }
		            return sb.toString();
		        } catch (NoSuchAlgorithmException ex) {
		        }
		        return null;
		 }

}
