package co.edu.ean.poo.comisiones;

import java.time.LocalDate;

/**
 * La clase FestivosColombia contiene los días festivos en Colombia
 * para los años 2024 y 2025.
 */
public class FestivosColombia {
    private LocalDate[] festivos = new LocalDate[35]; // 18 festivos por año * 2 años

    /**
     * Constructor que inicializa el arreglo de festivos con las fechas correspondientes
     * a los años 2024 y 2025.
     */
    public FestivosColombia() {
        // Festivos 2024
        festivos[0] = LocalDate.of(2024, 1, 1);   // Año Nuevo
        festivos[1] = LocalDate.of(2024, 1, 8);   // Día de los Reyes Magos (Traslado)
        festivos[2] = LocalDate.of(2024, 3, 25);  // Día de San José (Traslado)
        festivos[3] = LocalDate.of(2024, 3, 28);  // Jueves Santo
        festivos[4] = LocalDate.of(2024, 3, 29);  // Viernes Santo
        festivos[5] = LocalDate.of(2024, 5, 1);   // Día del Trabajo
        festivos[6] = LocalDate.of(2024, 5, 13);  // Día de la Ascensión (Traslado)
        festivos[7] = LocalDate.of(2024, 6, 3);   // Corpus Christi (Traslado)
        festivos[8] = LocalDate.of(2024, 6, 10);  // Sagrado Corazón (Traslado)
        festivos[9] = LocalDate.of(2024, 7, 1);   // San Pedro y San Pablo (Traslado)
        festivos[10] = LocalDate.of(2024, 7, 20);  // Día de la Independencia
        festivos[11] = LocalDate.of(2024, 8, 7);   // Batalla de Boyacá
        festivos[12] = LocalDate.of(2024, 8, 19);  // La Asunción de la Virgen (Traslado)
        festivos[13] = LocalDate.of(2024, 10, 14); // Día de la Raza (Traslado)
        festivos[14] = LocalDate.of(2024, 11, 4);  // Día de todos los Santos (Traslado)
        festivos[15] = LocalDate.of(2024, 11, 11); // Independencia de Cartagena (Traslado)
        festivos[16] = LocalDate.of(2024, 12, 8);  // Día de la Inmaculada Concepción
        festivos[17] = LocalDate.of(2024, 12, 25); // Navidad

        // Festivos 2025
        festivos[18] = LocalDate.of(2025, 1, 1);   // Año Nuevo
        festivos[19] = LocalDate.of(2025, 1, 6);   // Día de los Reyes Magos
        festivos[20] = LocalDate.of(2025, 3, 24);  // Día de San José (Traslado)
        festivos[21] = LocalDate.of(2025, 4, 17);  // Jueves Santo
        festivos[22] = LocalDate.of(2025, 4, 18);  // Viernes Santo
        festivos[23] = LocalDate.of(2025, 5, 1);   // Día del Trabajo
        festivos[24] = LocalDate.of(2025, 6, 2);   // Día de la Ascensión (Traslado)
        festivos[25] = LocalDate.of(2025, 6, 23);  // Sagrado Corazón (Traslado)
        festivos[26] = LocalDate.of(2025, 6, 30);  // San Pedro y San Pablo (Traslado)
        festivos[27] = LocalDate.of(2025, 7, 20);  // Día de la Independencia
        festivos[28] = LocalDate.of(2025, 8, 7);   // Batalla de Boyacá
        festivos[29] = LocalDate.of(2025, 8, 18);  // La Asunción de la Virgen (Traslado)
        festivos[30] = LocalDate.of(2025, 10, 13); // Día de la Raza (Traslado)
        festivos[31] = LocalDate.of(2025, 11, 3);  // Día de todos los Santos (Traslado)
        festivos[32] = LocalDate.of(2025, 11, 17); // Independencia de Cartagena (Traslado)
        festivos[33] = LocalDate.of(2025, 12, 8);  // Día de la Inmaculada Concepción
        festivos[34] = LocalDate.of(2025, 12, 25); // Navidad
    }

    /**
     * Verifica si una fecha dada corresponde a un día festivo
     * 
     * @param fecha La fecha a verificar.
     * @return true si la fecha es un día festivo, false en caso contrario.
     */
    public boolean esFestivo(LocalDate fecha) {
        for (LocalDate f : festivos) {
            if (f != null && f.equals(fecha)) {
                return true;
            }
        }
        return false;
    }
}