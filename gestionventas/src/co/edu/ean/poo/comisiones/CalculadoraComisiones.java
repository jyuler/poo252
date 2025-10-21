package co.edu.ean.poo.comisiones;

import java.time.LocalDate;
import java.util.Collection;

import co.edu.ean.poo.ventas.Vendedor;
import co.edu.ean.poo.ventas.Venta;

/**
 * La clase CalculadoraComisiones se encarga de calcular las comisiones de ventas
 * basadas en la fecha de ingreso del empleado, la fecha de la venta y el valor de la venta.
 */
public class CalculadoraComisiones {

    private static FestivosColombia festivos = new FestivosColombia();

    /**
     * Calcula la comisión de una venta.
     * Si la venta se hizo un año posterior al ingreso del empleado, la comisión es del 3%.
     * Si la venta se hizo entre 6 y 12 meses después del ingreso del empleado, la comisión es del 2.5%.
     * Si la venta se hizo antes de 6 meses del ingreso del empleado, la comisión es del 2%.
     * Si la venta se hizo en un día festivo (incluye domingos), se suma un 0.5% adicional a la comisión.
     * 
     * @param fechaIngresoEmpleado La fecha de ingreso del empleado.
     * @param fechaVenta La fecha en que se realizó la venta.
     * @param valorVenta El valor de la venta.
     * @return Un objeto Comision que contiene información sobre si la venta fue en un día festivo,
     *         el porcentaje de comisión y el valor de la comisión.
     */
    public static Comision calcularComision(LocalDate fechaIngresoEmpleado, LocalDate fechaVenta, int valorVenta) {
        // Para efectos de calcular comisiones, los domingos se consideran dias festivos
        boolean festivo = fechaVenta.getDayOfWeek().getValue() == 7 || festivos.esFestivo(fechaVenta);
        var p = fechaIngresoEmpleado.until(fechaVenta);
        var meses = 12 * p.getYears() + p.getMonths();
        float pcom = meses >= 12 ? 0.03f : meses >= 6 ? 0.025f : 0.02f;
        if (festivo) pcom += 0.005f;
        return new Comision(festivo, pcom, valorVenta * pcom);
    }


    /**
     * Calcula las comisiones para todas las ventas de un vendedor, siempre que las ventas
     * estèn incluìdas en el rango de fechas dado.
     * @param vendedor Objeto vededor de donde se obtienen las ventas y la fecha de ingreso
     * @param fechaInicio fecha inicial para el rango de fechas de ventas
     * @param fechaFin fecha final para el rango de fechas de ventas
     * @return el valor total de la comisión.
     */
    public static int calcularComisionVentas( Vendedor vendedor, LocalDate fechaInicio, LocalDate fechaFin ) {
        if ( vendedor == null ) throw new IllegalArgumentException("El vendedor no puede ser nulo");
        float comision = 0;
        Collection<Venta> ventas = vendedor.getVentas();
        for (Venta venta : ventas) {
            if ( venta == null ) continue;
            if (venta.fecha().isBefore(fechaInicio) || venta.fecha().isAfter(fechaFin) ) continue;    
            comision += CalculadoraComisiones.calcularComision(vendedor.getFechaIngreso(), venta.fecha(), venta.valor() ).comision();
        }
        return Math.round(comision);
    }

    /**
     * Calcula las comisiones de las ventas para todos los vendedores en el rango de fechas dado
     * @param fechaInicio fecha inicial para el rango de fechas de ventas
     * @param fechaFin  fecha final para el rango de fechas de ventas
     * @param vendedores arreglo de objetos Vendedor sobre los cuales se calculan las comisiones
     * @return el valor total de las comisiones de todos los vendedores
     */
    public static int totalComisionesVendedores( LocalDate fechaInicio, LocalDate fechaFin, Collection<Vendedor> vendedores ) {
        int totalComisiones = 0;
        for (Vendedor v : vendedores) {
            totalComisiones += calcularComisionVentas(v, fechaInicio, fechaFin);
        }
        return totalComisiones;
    }
}