package co.edu.ean.poo.ventas;

import java.time.LocalDate;

/**
 * Clase que representa una venta realizada por un vendedor.
 */
public class Venta {
    private LocalDate fechaVenta;
    private int valorVenta;
    
    public Venta(LocalDate fechaVenta, int valorVenta) {
        this.fechaVenta = fechaVenta;
        this.valorVenta = valorVenta;
    }

    /**
     * Devuelve la fecha de la venta.
     */
    public LocalDate fecha() {
        return fechaVenta;
    }

    /**
     * Devuelve el valor de la venta.
     */
    public int valor() {
        return valorVenta;
    }

    @Override
    public String toString() {
        return String.format("Venta[F:%s, V:%,d]", fechaVenta, valorVenta);
    }
}