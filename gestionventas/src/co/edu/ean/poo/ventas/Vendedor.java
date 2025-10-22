package co.edu.ean.poo.ventas;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa un vendedor de la compañía.
 */
public class Vendedor {
    private int numeroVendedor;
    private String nombre;
    private String apellido;
    // fecha de ingreso del vendedor a la compañía
    private LocalDate fechaIngreso;
    private List<Venta> ventas = new LinkedList<>();

    public Vendedor(int nv, String nm, String ap, LocalDate f ) {
        numeroVendedor = nv;
        nombre = nm;
        apellido = ap;
        fechaIngreso = f;
    }

    public int getNumeroVendedor() {
        return numeroVendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * intenta registrar la venta para el vendedor actual
     * Si la venta tiene fecha anterior a la fecha de ingreso del vendedor entonces
     * no debe registrarse puesto que es una inconsistencia
     * 
     * @param v Venta a registrar
     * @return true si la venta fue registrada, en caso contrario false
     */
    public boolean registrarVenta(Venta v) {
        // TODO: implementar este método
    }

    /**
     * Registra una venta para el vendedor actual. 
     * La venta se realizó en la fecha y por el valor indicado.
     * Si la venta tiene fecha anterior a la fecha de ingreso del vendedor entonces
     * no debe registrarse puesto que es una inconsistencia
     * @param fechaVenta Fecha de la venta
     * @param valor Valor de la venta
     * @return true si la venta fue registrada, en caso contrario false
     */
    public boolean registrarVenta(LocalDate fechaVenta, int valor) {
        // TODO: implementar este método
    }

    /**
     * Devuelve la lista de ventas realizadas por el vendedor.
     */
    public List<Venta> getVentas() {
        return ventas;
    }

    @Override
    public String toString() {
        return String.format("Vendedor[%02d:%s %s:%s]", numeroVendedor, nombre, apellido, fechaIngreso);
    }
}
