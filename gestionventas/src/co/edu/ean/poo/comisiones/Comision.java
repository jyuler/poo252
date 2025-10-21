package co.edu.ean.poo.comisiones;

/**
 * La clase Comision representa una comisión por ventas y los datos usados para su calculo: 
 * porcentaje aplicado y un indicador si la fecha de la venta es un día festivo.
 */
public class Comision {
    private final boolean esFestivo;
    private final float porcentaje;
    private final float comision;

    /**
     * Constructor para crear una instancia de Comision.
     *
     * @param esFestivo Indica si la fecha corresponde a un día festivo.
     * @param porcentaje El porcentaje de la comisión aplicada.
     * @param comision El valor de la comisión.
     */
    public Comision(boolean esFestivo, float porcentaje, float comision) {
        this.esFestivo = esFestivo;
        this.porcentaje = porcentaje;
        this.comision = comision;
    }

    /**
     * Indica si la fecha para la que se calculo la comision era día festivo.
     *
     * @return true si es un día festivo, false en caso contrario.
     */
    public boolean esFestivo() {
        return esFestivo;
    }

    /**
     * Indica el porcentaje aplicado para el cálculo de la comisión.
     *
     * @return El porcentaje de la comisión.
     */
    public float porcentaje() {
        return porcentaje;
    }

    /**
     * Indica el valor de la comisión calculada.
     *
     * @return El valor de la comisión.
     */
    public float comision() {
        return comision;
    }
}
