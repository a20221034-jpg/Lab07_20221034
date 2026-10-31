package pe.edu.pucp.iweb.lab7.beans;

 /** Equipo de red de la tabla equipo.
 Ejercicio 2b: */

public abstract class EquipoRed {

    private int idEquipo;
    private String modelo;
    private String tipo;
    private double precio;
    private int cantidad;
    private Sede sede;


    public abstract double calcularSoporteMensual();

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }
}
