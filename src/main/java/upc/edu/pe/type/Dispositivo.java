package upc.edu.pe.type;

import java.io.Serializable;

/**
 *
 * @author Miguel Cardoso
 */
public class Dispositivo implements Serializable{

	private String usuario;
	private String codigoGCM;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCodigoGCM() {
		return codigoGCM;
	}

	public void setCodigoGCM(String codigoGCM) {
		this.codigoGCM = codigoGCM;
	}

}