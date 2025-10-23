package co.edu.ean.poo;
/**
 * Clase principal de la aplicación de gestión de ventas.
 * <p>
 * Esta clase contiene el método main que inicia la ejecución del programa y el método
 * procesarArchivos que realiza las operaciones principales sobre los datos de vendedores y ventas.
 * </p>
 *
 * @author (Tu Nombre)
 * @version 1.0
 */
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import co.edu.ean.poo.ventas.CalculadoraVentas;
import co.edu.ean.poo.ventas.Vendedor;
import co.edu.ean.poo.ventas.datos.ParseadorVentasVendedores;

public class App {
    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Path pathVendedores = Path.of("data/vendedores.csv");
        Path pathVentas = Path.of("data/ventas.csv");
        ParseadorVentasVendedores pvv = // instancia aquí tu implementación de ParseadorVentasVendedores
        try {
            procesarArchivos( pvv, pathVendedores, pathVentas );
        } catch (IOException e) {
            System.out.println("Error al procesar los archivos: " + e.getMessage());
        }
    }

    /**
     * Procesa los archivos de vendedores y ventas, y realiza varias consultas sobre los datos.
     *
     * @param pvv             Instancia de ParseadorVentasVendedores para parsear los archivos.
     * @param pathVendedores  Ruta al archivo CSV de vendedores.
     * @param pathVentas      Ruta al archivo CSV de ventas.
     * @throws IOException    Si ocurre un error al leer los archivos.
     */
    public static void procesarArchivos(ParseadorVentasVendedores pvv, Path pathVendedores, Path pathVentas) throws IOException {
        Map<Integer,Vendedor> mapVendedores = pvv.parsearVendedores(pathVendedores);
        pvv.parsearVentas(pathVentas, mapVendedores);

        // 1. valor total de ventas que el vendedor 26 tuvo en el mes de noviembre 2024
        System.out.println( CalculadoraVentas.totalVentasVendedor(
            mapVendedores.get(26),
            LocalDate.of(2024, Month.NOVEMBER, 1), 
            LocalDate.of(2024, Month.NOVEMBER, 30) 
        ));

        // 2. total de ventas que el vendedor 11 tuvo en el mes de septiembre de 2024
        try {
            System.out.println( CalculadoraVentas.totalVentasVendedor(
                mapVendedores.get(11),
                LocalDate.of(2024, Month.SEPTEMBER, 1), 
                LocalDate.of(2024, Month.SEPTEMBER, 30) 
            ));
        } catch (IllegalArgumentException e) {
            System.out.println("El vendedor 11 no existe");
        }

        // 3. total de ventas de todos los vendedores en 2024 fue de
        System.out.println( CalculadoraVentas.totalVentasVendedores(
            mapVendedores.values(),
            LocalDate.of(2024, Month.JANUARY, 1), 
            LocalDate.of(2024, Month.DECEMBER, 31) 
        ));

        // 4. vendedor con el mayor total en ventas en 2025
        System.out.println( CalculadoraVentas.topNVendedores(
            mapVendedores.values(), 1, 
            LocalDate.of(2025, Month.JANUARY, 1), 
            LocalDate.of(2025, Month.MAY, 31) 
        )[0].getNumeroVendedor());

        // 5. vendedor con el mayor total de ventas en diciembre
        Vendedor v = CalculadoraVentas.topNVendedores(
            mapVendedores.values(), 1, 
            LocalDate.of(2024, Month.DECEMBER, 1), 
            LocalDate.of(2024, Month.DECEMBER, 31) 
        )[0];
        System.out.println( CalculadoraVentas.totalVentasVendedor(
            v, 
            LocalDate.of(2024, Month.DECEMBER, 1), 
            LocalDate.of(2024, Month.DECEMBER, 31) 
        ));

        // 8. mostrar los datos del vendedor 4
        System.out.println( mapVendedores.get(4) );
    }
}
