package pe.edu.pucp.iweb.lab7.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SedeDao extends BaseDao {

    /** Ejercicio 2c: retorna todas las sedes ordenadas por nombre. */

    public ArrayList<Sede> listar() {
        ArrayList<Sede> lista = new ArrayList<>();
        String sql = "SELECT idsede, nombre FROM sede ORDER BY nombre";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Sede s = new Sede();
                s.setIdSede(rs.getInt("idsede"));
                s.setNombre(rs.getString("nombre"));
                lista.add(s);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al listar sedes", e);
        }
        return lista;
    }

    /** Ejercicio 4: retorna la sede con el id indicado,*/

    public Sede buscarPorId(int idSede) {
        Sede sede = null;
        String sql = "SELECT idsede, nombre FROM sede WHERE idsede = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idSede);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sede = new Sede();
                    sede.setIdSede(rs.getInt("idsede"));
                    sede.setNombre(rs.getString("nombre"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la sede", e);
        }
        return sede;
    }
}
