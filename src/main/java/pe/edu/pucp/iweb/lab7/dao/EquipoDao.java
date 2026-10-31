package pe.edu.pucp.iweb.lab7.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import pe.edu.pucp.iweb.lab7.beans.EquipoAP;

public class EquipoDao extends BaseDao {

    /** Ejercicio 2c: retorna los equipos de la sede indicada, ordenados por sede y modelo */
    public ArrayList<EquipoRed> listarPorSede(int idSede) {
        ArrayList<EquipoRed> lista = new ArrayList<>();

        String sql =
                "SELECT e.idequipo, e.modelo, e.tipo, e.precio, e.cantidad, "
              + "       s.idsede, s.nombre "
              + "FROM equipo e "
              + "INNER JOIN sede s ON e.idsede = s.idsede ";

        // Si idSede es 0 -> todos los equipos (sin filtro de sede)
        if (idSede != 0) {
            sql += "WHERE e.idsede = ? ";
        }
        sql += "ORDER BY s.nombre, e.modelo";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (idSede != 0) {
                ps.setInt(1, idSede);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Sede del equipo
                    Sede sede = new Sede();
                    sede.setIdSede(rs.getInt("idsede"));
                    sede.setNombre(rs.getString("nombre"));

                    // Según el tipo se crea la subclase correspondiente
                    String tipo = rs.getString("tipo");
                    EquipoRed equipo;
                    if ("SWITCH".equals(tipo)) {
                        equipo = new EquipoSwitch();
                    } else {
                        equipo = new EquipoAP();
                    }

                    equipo.setIdEquipo(rs.getInt("idequipo"));
                    equipo.setModelo(rs.getString("modelo"));
                    equipo.setTipo(tipo);
                    equipo.setPrecio(rs.getDouble("precio"));
                    equipo.setCantidad(rs.getInt("cantidad"));
                    equipo.setSede(sede);

                    lista.add(equipo);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al listar equipos", e);
        }
        return lista;
    }
}
