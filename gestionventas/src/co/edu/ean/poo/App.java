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
import co.edu.ean.poo.ventas.datos.EjercicioProcesarArchivos;
import co.edu.ean.poo.ventas.datos.ParseadorVentasVendedores;

public class App {
    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String... args) throws IOException{
        Path pathVendedores = Path.of("data/vendedores.csv");
        Path pathVentas = Path.of("data/ventas.csv");
        
        if ( args.length == 2 ) {
            pathVendedores = Path.of( args[0] );
            pathVentas = Path.of( args[1] );
        }
        ParseadorVentasVendedores pvv = // instancia aquí tu implementación de ParseadorVentasVendedores
        EjercicioProcesarArchivos.procesarArchivos( pvv, pathVendedores, pathVentas );
    }

}