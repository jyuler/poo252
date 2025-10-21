package co.edu.ean.poo.ventas.datos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import co.edu.ean.poo.ventas.Vendedor;

public class Parseador implements ParseadorVentasVendedores {

    @Override
    public Map<Integer, Vendedor> parsearVendedores(Path rutaArchivoVendedores) throws IOException {
        Map<Integer, Vendedor> mapaVendedores = new HashMap<Integer,Vendedor>();
        Files.readAllLines(rutaArchivoVendedores).stream()
            .map( this::parsearVendedor )
            .filter( v -> v != null)
            .forEach( v -> mapaVendedores.put(v.getNumeroVendedor(), v) );
        return mapaVendedores;
    }

    private Vendedor parsearVendedor(String linea) {
        String[] partes = linea.split(",");
        if (partes.length < 4) return null;
        try {
            int numero = Integer.parseInt(partes[0].trim());
            String nombre = partes[1].trim();
            String apellido = partes[2].trim();
            LocalDate fechaIngreso = LocalDate.parse(partes[3].trim());
            return new Vendedor(numero, nombre, apellido, fechaIngreso);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void parsearVentas(Path rutaArchivoVentas, Map<Integer, Vendedor> mapaVendedores) throws IOException {
        Files.readAllLines(rutaArchivoVentas).stream()
            .map( this::parsearVenta )
            .filter( v -> v != null)
            .forEach( vv -> {
                Vendedor vendedor = mapaVendedores.get(vv.numeroVendedor());
                if (vendedor != null) {
                    vendedor.registrarVenta( vv.fechaVenta(), vv.valorVenta());
                }
            });
    }

    record VV(int numeroVendedor, LocalDate fechaVenta, int valorVenta) {}
    private VV parsearVenta(String linea) {
        String[] partes = linea.split(",");
        if (partes.length < 3) return null;
        try {
            int numeroVendedor = Integer.parseInt(partes[0].trim());
            LocalDate fechaVenta = LocalDate.parse(partes[1].trim());
            int valorVenta = Integer.parseInt(partes[2].trim());
            return new VV(numeroVendedor, fechaVenta, valorVenta);
        } catch (Exception e) {
            return null;
        }
    }

}
