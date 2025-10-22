package co.edu.ean.poo.ventas;

import java.time.LocalDate;
import java.util.Collection;

/**
 * La clase CalculadoraVentas contiene métodos para calcular el total de ventas de un vendedor, 
 * el total de ventas de un grupo de vendedores y el top N de vendedores con mayor valor en ventas.
 */
public class CalculadoraVentas {
    public static int totalVentasVendedor( Vendedor vendedor,  LocalDate fechaInicio, LocalDate fechaFin ) {
       // TODO: implementar este método
       if ( vendedor == null ) {
           throw new IllegalArgumentException( "El vendedor no puede ser nulo" );
       }
       return vendedor.getVentas().stream()
           .filter( v -> !v.fecha().isBefore( fechaInicio ) && !v.fecha().isAfter( fechaFin ) )
           .mapToInt( v -> v.valor() )
           .sum();
    }

    /**
     * Calcula el total de ventas de un grupo de vendedores en un rango de fechas.
     * @param vendedores Arreglo de vendedores sobre los cuales se calculará el total de ventas.
     * @param fechaInicio Fecha de inicio del rango de fechas.
     * @param fechaFin Fecha de fin del rango de fechas.
     * @return Total de ventas de los vendedores en el rango de fechas.
     */
    public static long totalVentasVendedores( Collection<Vendedor> vendedores, LocalDate fechaInicio, LocalDate fechaFin ) {
        // TODO: implementar este método
    }

    /**
     * Calcula el top N de vendedores con mayor valor en ventas en un rango de fechas.
     * @param vendedores Arreglo de vendedores sobre los cuales se calculará el top N.
     * @param top Número de vendedores a incluir en el top.
     * @param fechaInicio Fecha de inicio del rango de fechas.
     * @param fechaFin Fecha de fin del rango de fechas.
     * @return Arreglo de vendedores de tamaño top con mayor valor en ventas en el rango de fechas.
     */
    public static Vendedor[] topNVendedores( Collection<Vendedor> vendedores, int top, LocalDate fechaInicio, LocalDate fechaFin ) {
        // TODO: implementar este método
    }
}
