package co.edu.ean.poo.tests;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import co.edu.ean.poo.App;
import co.edu.ean.poo.ventas.CalculadoraVentas;
import co.edu.ean.poo.ventas.Vendedor;
import co.edu.ean.poo.ventas.Venta;

public class Tests {
    private static final String FALLO = "FALLIDO";
    private static final String EXITO = "EXITOSO";
    
    public static void main(String[] args) {
        Runnable[] tests = {
            Tests::testValidarFechaIngreso,
            Tests::testValidarArchivoNoExiste,
            Tests::testTotalVentasVendedor,
            Tests::testTotalVentasVendedores,
            Tests::testTopNVendedores,
            Tests::testValidarSalidaProcesar,
        };
        int i=1;
        for ( var test : tests ) {
            try {
                test.run();
                i++;
            } catch (Exception e) {
                plr("Failed test " + i + ": " + e.getMessage());
            }
        }
    }

    public static void testValidarFechaIngreso()  {
        var vdr = new Vendedor(1, "Ana", "Perez", LocalDate.of(2020, 1, 15));
        var venta1 = new Venta(LocalDate.of(2020, 1, 10), 500000);
        var venta2 = new Venta(LocalDate.of(2020, 1, 20), 750000);

        pw("Registrar venta con fecha anterior a la fecha de ingreso: ");
        boolean agregado = vdr.registrarVenta(venta1);
        boolean noAgregado = vdr.getVentas().size() == 0;
        if ( !agregado && noAgregado ) plg(EXITO); else plr(FALLO);
        pw("Registrar venta con fecha posterior a la fecha de ingreso: ");
        agregado = vdr.registrarVenta(venta2);
        noAgregado = vdr.getVentas().size() == 0;
        if ( agregado && !noAgregado ) plg(EXITO); else plr(FALLO);
    }

    public static void testValidarArchivoNoExiste()  {
        pw("Validar que se lance IOException al procesar archivos que no existen: ");
        try {
            System.setOut(new PrintStream(new OutputStream() {
                public void write(int b) throws IOException {}
            }));
            App.main("nonexistent_vendedores.csv", "nonexistent_ventas.csv");
            plr(FALLO);
        } catch (java.io.IOException e) {
            plg(EXITO);
        }
    }

    public static void testTotalVentasVendedor()  {
        pw("Calcular el total de ventas de un vendedor en un rango de fechas: ");
        var vdr = new Vendedor(2, "Juan", "Gomez", LocalDate.of(2023, 1, 1));
        vdr.registrarVenta(new Venta(LocalDate.of(2023, 5, 10), 100));
        vdr.registrarVenta(new Venta(LocalDate.of(2023, 6, 15), 200));
        vdr.registrarVenta(new Venta(LocalDate.of(2023, 4, 20), 50));
        vdr.registrarVenta(new Venta(LocalDate.of(2023, 8, 1), 300));

        var fechaInicio = LocalDate.of(2023, 5, 10);
        var fechaFin = LocalDate.of(2023, 6, 15);

        int total = CalculadoraVentas.totalVentasVendedor(vdr, fechaInicio, fechaFin);
        if (total == 300) {
            plg(EXITO);
        } else {
            plr(FALLO);
            throw new FailedTestException("El total de ventas del vendedor es incorrecto. Se esperaba 300 pero se obtuvo " + total);
        }
    }

    public static void testTotalVentasVendedores()  {
        pw("Calcular el total de ventas de múltiples vendedores en un rango de fechas: ");
        
        var vdr1 = new Vendedor(1, "Ana", "Perez", LocalDate.of(2023, 1, 1));
        vdr1.registrarVenta(new Venta(LocalDate.of(2023, 5, 10), 100));
        vdr1.registrarVenta(new Venta(LocalDate.of(2023, 6, 15), 200));
        vdr1.registrarVenta(new Venta(LocalDate.of(2023, 4, 20), 50));
        vdr1.registrarVenta(new Venta(LocalDate.of(2023, 7, 20), 50));
        
        var vdr2 = new Vendedor(2, "Juan", "Gomez", LocalDate.of(2023, 1, 1));
        vdr2.registrarVenta(new Venta(LocalDate.of(2023, 5, 12), 150));
        vdr2.registrarVenta(new Venta(LocalDate.of(2023, 7, 1), 300));
        vdr2.registrarVenta(new Venta(LocalDate.of(2023, 6, 10), 250));
        vdr2.registrarVenta(new Venta(LocalDate.of(2023, 2, 12), 150));
        
        var vdr3 = new Vendedor(3, "Carlos", "Lopez", LocalDate.of(2023, 1, 1));
        vdr3.registrarVenta(new Venta(LocalDate.of(2023, 5, 20), 100));
        vdr3.registrarVenta(new Venta(LocalDate.of(2023, 3, 20), 100));
        vdr3.registrarVenta(new Venta(LocalDate.of(2023, 7, 20), 100));
        
        var vendedores = java.util.List.of(vdr1, vdr2, vdr3);
        var fechaInicio = LocalDate.of(2023, 5, 1);
        var fechaFin = LocalDate.of(2023, 6, 30);

        long total = CalculadoraVentas.totalVentasVendedores(vendedores, fechaInicio, fechaFin);
        if (total == 800) {
            plg(EXITO);
        } else {
            plr(FALLO);
            throw new FailedTestException("El total de ventas de los vendedores es incorrecto. Se esperaba 800 pero se obtuvo " + total);
        }
    }

    public static void testTopNVendedores() {
        pw("Calcular el top N de vendedores con mayor valor en ventas: ");
        
        var vdr1 = new Vendedor(1, "Ana", "Perez", LocalDate.of(2023, 1, 1));
        vdr1.registrarVenta(new Venta(LocalDate.of(2023, 5, 10), 100));
        vdr1.registrarVenta(new Venta(LocalDate.of(2023, 6, 15), 200));
        vdr1.registrarVenta(new Venta(LocalDate.of(2023, 7, 20), 50));
        // Total en rango: 350
        
        var vdr2 = new Vendedor(2, "Juan", "Gomez", LocalDate.of(2023, 1, 1));
        vdr2.registrarVenta(new Venta(LocalDate.of(2023, 5, 12), 150));
        vdr2.registrarVenta(new Venta(LocalDate.of(2023, 6, 10), 250));
        vdr2.registrarVenta(new Venta(LocalDate.of(2023, 7, 1), 300));
        // Total en rango: 700
        
        var vdr3 = new Vendedor(3, "Carlos", "Lopez", LocalDate.of(2023, 1, 1));
        vdr3.registrarVenta(new Venta(LocalDate.of(2023, 5, 20), 100));
        vdr3.registrarVenta(new Venta(LocalDate.of(2023, 6, 20), 400));
        // Total en rango: 500
        
        var vdr4 = new Vendedor(4, "Maria", "Rodriguez", LocalDate.of(2023, 1, 1));
        vdr4.registrarVenta(new Venta(LocalDate.of(2023, 5, 25), 80));
        vdr4.registrarVenta(new Venta(LocalDate.of(2023, 6, 5), 120));
        // Total en rango: 200
        
        var vendedores = java.util.List.of(vdr1, vdr2, vdr3, vdr4);
        var fechaInicio = LocalDate.of(2023, 5, 1);
        var fechaFin = LocalDate.of(2023, 7, 31);

        Vendedor[] top3 = CalculadoraVentas.topNVendedores(vendedores, 3, fechaInicio, fechaFin);
        
        if (top3.length == 3 && 
            top3[0].getNumeroVendedor() == 2 && // Juan con 700
            top3[1].getNumeroVendedor() == 3 && // Carlos con 500
            top3[2].getNumeroVendedor() == 1) { // Ana con 350
            plg(EXITO);
        } else {
            plr(FALLO);
            StringBuilder sb = new StringBuilder("El top de vendedores es incorrecto. Se esperaba [2, 3, 1] pero se obtuvo [");
            for (int i = 0; i < top3.length; i++) {
                sb.append(top3[i].getNumeroVendedor());
                if (i < top3.length - 1) sb.append(", ");
            }
            sb.append("]");
            throw new FailedTestException(sb.toString());
        }
    }

    public static void testValidarSalidaProcesar() {
        String[] expectedLines = {
            "47996000",
            "El vendedor 11 no existe",
            "10348152186",
            "12",
            "128954000",
            "Vendedor[04:Sebastian Sanchez:2024-10-20]"
        };
        pw("Validar salida del procesamiento de archivos de datos: ");
        try {
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            App.main("data/vendedores.csv", "data/ventas.csv");
            String salida = baos.toString().trim();
            String[] lineas = salida.split("\n");
            if (lineas.length != expectedLines.length) {
                plr(FALLO);
                throw new FailedTestException("La cantidad de líneas en la salida es incorrecta. Se esperaban " + expectedLines.length + " pero se obtuvieron " + lineas.length);
            }
            for (int i = 0; i < expectedLines.length; i++) {
                if (!lineas[i].trim().equals(expectedLines[i].trim())) {
                    plr(FALLO);
                    throw new FailedTestException("La línea " + (i+1) + " de la salida es incorrecta. Se esperaba: '" + expectedLines[i] + "' pero se obtuvo: '" + lineas[i] + "'");
                }
            }
            // Aquí se podrían agregar más validaciones específicas sobre el contenido de cada línea
            plg(EXITO);
        } catch (IOException e) {
            plr(FALLO);
            throw new FailedTestException("Ocurrió una IOException durante el procesamiento: " + e.getMessage());
        }
    }
    private static void plr(String msg) {
        System.err.println("\u001B[31m" + msg + "\u001B[0m");
    }
    private static void plg(String msg) {
        System.err.println("\u001B[32m" + msg + "\u001B[0m");
    }
    private static void pw(String msg) {
        System.err.print("\u001B[37m" + msg + "\u001B[0m");
    }
}
