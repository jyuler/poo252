# Sistema de gestión de ventas

## Objetivo 
- Analizar un problema de la vida real y crear una solución usando los conocimientos adquiridos en el lenguaje Java hasta el momento.

### Objectivos específicos:
- Leer los archivos que se encuentran en el directorio data y cargar la información en las estructuras de datos definidas.
- Implementar los métodos `registrarVenta` de la clase `Vendedor` para registrar una venta realizada por un vendedor.
- Implementar los métodos de la clase `CalculadoraVentas` para calcular el total de ventas de un vendedor, el total de ventas de un grupo de vendedores y el top N de vendedores con mayor valor en ventas.


## Requerimientos
1. Implementar los métodos que faltan. Hay métodos en la clase `Vendedor` y en la clase `CalculadoraVentas` que están con un comentario: `// TODO: implementar este método`. Debes reemplazar ese comentario por la implementación real del método.
2. Implementar la interfaz `co.edu.ean.poo.ventas.parser.ParseadorVentasVendedores` de acuerdo a la documentación proporcionada en la interfaz.
3. Modificar el método `main` de la clase `App` para instanaciar tu implementación de `ParseadorVentasVendedores` y usarla para leer los archivos de datos y cargar la información en las estructuras de datos definidas.

### Ten en cuenta que:
Algunos registros de vendedores presentan inconsistencias de diferente índole (formatos de fecha, registros vacios, etc.) Debes cargar los vendedores que no presenten inconsistencias y descartar aquellos que las presenten.

Algunos registros de ventas presentan inconsistencias como en el archivo de vendedores, pero además, aparecen ventas con fecha anterior a la fecha de ingreso del vendedor. Debes descartar las ventas que presenten estas inconsistencias.

