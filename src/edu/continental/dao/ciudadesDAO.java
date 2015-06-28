package edu.continental.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

import org.codehaus.jettison.json.JSONArray;

import edu.continental.util.ToJSON;
import edu.continental.util.CONEXION;

public class ciudadesDAO {
	public JSONArray listaciudades(){
		//obtener direccion a la BD
		CONEXION cn = new CONEXION();
		
		Connection con = cn.getConnection(); 
			
		//sentencia sql
		String sql = "Select id, nombre, altitud, estado from ciudades where estado = 'A'";
		Statement st = null;
		ResultSet rs = null;
		
		//para convertir a JSOn
		ToJSON convertidor = new ToJSON();
		JSONArray arreglo = new JSONArray();
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			arreglo = convertidor.toJSONArray(rs);
			st.close();
			
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("error: "+ ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		return arreglo;
	}
}
