
package upc.edu.pe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import upc.edu.pe.conexion.ConexionBD;
import upc.edu.pe.type.Dispositivo;

/**
 *
 * @author Miguel Cardoso
 */
public class DispositivoDao  {
	
	public void insertar(Dispositivo vo) throws Exception {
		System.out.println("DispositivoDAO: insertar()");
		String query = "INSERT INTO dispositivo(usuario, codigo_gcm) VALUES (?,?)";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConexionBD.conexion();
			stmt = con.prepareStatement(query);
			stmt.setString(1, vo.getUsuario());
			stmt.setString(2, vo.getCodigoGCM());
			int i = stmt.executeUpdate();
			if (i != 1) {
				throw new SQLException("No se pudo insertar");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
		} finally {
			stmt.close();
                        con.close();
		}
	}

	public Dispositivo obtenerCodigoGCMPorUsuario(String usuario) throws Exception {
		System.out.println("DispositivoDAO: obtenerCodigoGCMPorUsuario()");
		Dispositivo vo = new Dispositivo();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT codigo_gcm FROM dispositivo WHERE usuario=?";
			con = ConexionBD.conexion();
			stmt = con.prepareStatement(query);
			stmt.setString(1, usuario);
			rs = stmt.executeQuery();
			if (rs.next()) {
				vo.setCodigoGCM(rs.getString(1));
				vo.setUsuario(usuario);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
		} finally {
			rs.close();
			stmt.close();
                        con.close();
		}
		return vo;
	}

	public void actualizar(Dispositivo vo) throws Exception {
		System.out.println("DispositivoDAO: actualizar(Dispositivo vo)");
		String query = "UPDATE dispositivo SET codigo_gcm=? where usuario=?";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConexionBD.conexion();
			stmt = con.prepareStatement(query);
			stmt.setString(1, vo.getCodigoGCM());
			stmt.setString(2, vo.getUsuario());
			int i = stmt.executeUpdate();
			if (i != 1) {
				throw new Exception("No se pudo actualizar");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new Exception(e.getMessage());
		} finally {
			stmt.close();
                        con.close();
		}
	}

}

