package pe.edu.pucp.iweb.lab7.beans;

/** Ejercicio 2b: equipo de tipo 'SWITCH'// Soporte mensual (S/) = 0.015 x precio x cantidad */

public class EquipoSwitch extends EquipoRed {

    @Override
    public double calcularSoporteMensual() {
        return 0.015 * getPrecio() * getCantidad();
    }
}
