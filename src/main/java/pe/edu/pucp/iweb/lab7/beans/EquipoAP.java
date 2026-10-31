package pe.edu.pucp.iweb.lab7.beans;

/**
 Ejercicio 2b: equipo de tipo 'AP'. Soporte mensual (S/) = (0.02 x precio + 5) x cantidad */

public class EquipoAP extends EquipoRed {

    @Override
    public double calcularSoporteMensual() {
        return (0.02 * getPrecio() + 5) * getCantidad();
    }
}
