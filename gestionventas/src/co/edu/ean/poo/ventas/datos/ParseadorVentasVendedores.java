package co.edu.ean.poo.ventas.datos;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import co.edu.ean.poo.ventas.Vendedor;

/**
 * La clase ParseadorDatos se encarga de parsear datos de vendedores y ventas desde cadenas de texto.
 * 
 * Las implementaciones de esta interfaz deben asegurar el correcto parseo de los datos del archivo.
 * Deben realizar todas las validaciones necesarias para garantizar que los datos sean correctos.
 */
public interface ParseadorVentasVendedores {
    /**
     * Parsea los vendedores desde un archivo ubicado en la ruta especificada.
     * 
     * El archivo de vendedores tiene el siguiente formato por línea:
     * numeroVendedor,nombre,apellido,fechaIngreso
     * 
     * La fecha de ingreso está en formato ISO (yyyy-MM-dd)
     * @param rutaArchivoVendedores Ruta del archivo que contiene los datos de los vendedores.
     * @return Un mapa donde la llave es el número de vendedor y el valor es el objeto Vendedor correspondiente.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    Map<Integer, Vendedor> parsearVendedores(Path rutaArchivoVendedores) throws IOException;

    /**
     * Parsea las ventas desde un archivo ubicado en la ruta especificada y las asigna 
     * a los vendedores correspondientes.
     * 
     * El archivo de ventas tiene el siguiente formato por línea:
     * numeroVendedor,fechaVenta,valorVenta
     * 
     * @param rutaArchivoVentas Ruta del archivo que contiene los datos de las ventas.
     * @param mapaVendedores Mapa que relaciona el número de vendedor con su objeto Vendedor.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    void parsearVentas(Path rutaArchivoVentas, Map<Integer, Vendedor> mapaVendedores) throws IOException;
}
