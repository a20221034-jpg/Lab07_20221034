package pe.edu.pucp.iweb.lab7.beans;

/**  * Sede de Telecom Andina (tabla sede). Esta clase ya está completa. */

public class Sede {

    private int idSede;
    private String nombre;

    public Sede() {
    }

    public Sede(int idSede, String nombre) {
        this.idSede = idSede;
        this.nombre = nombre;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
