package umg.Progra2.formas.productos.Reportes;

import umg.Progra2.formas.productos.DataBase.Model.Producto;
import umg.Progra2.formas.productos.DataBase.Service.ProductoService;

import javax.swing.*;
import java.util.List;

public class pruebas {
    public static void main(String[] args) {
        ProductoService productoService = new ProductoService();
        String[] opciones = {
                "Eliminar productos con precio 0",
                "Generar reporte de productos con precio menor a 100",
                "Generar reporte de productos con existencia menor a 30",
                "Generar reporte de productos con precio entre 200 y 400",
                "Generar reporte de productos ordenados por precio (mayor a menor)",
                "Generar reporte de productos ordenados por existencia (menor a mayor)",
                "Salir"
        };

        while (true) {
            // Crear un StringBuilder para mostrar las opciones
            StringBuilder opcionesMenu = new StringBuilder("Menú de Reportes:\n");
            for (int i = 0; i < opciones.length; i++) {
                opcionesMenu.append(i + 1).append(". ").append(opciones[i]).append("\n");
            }

            // Solicitar al usuario que ingrese una opción
            String input = JOptionPane.showInputDialog(opcionesMenu.toString());
            if (input == null || input.isEmpty()) {
                // Si se cierra el diálogo o se deja vacío, salimos
                break;
            }

            try {
                int seleccion = Integer.parseInt(input);

                if (seleccion < 1 || seleccion > opciones.length) {
                    JOptionPane.showMessageDialog(null, "Opción no válida. Intente nuevamente.");
                    continue; // Vuelve a mostrar el menú
                }

                switch (seleccion) {
                    case 1:
                        // Eliminar productos con precio igual a 0
                        productoService.eliminarProductosConPrecioCero();
                        JOptionPane.showMessageDialog(null, "Productos con precio 0 eliminados.");
                        break;
                    case 2:
                        // Generar reporte de productos con precio menor a 100
                        List<Producto> productosConPrecioMenorA100 = productoService.obtenerProductosConPrecioMenorA100();
                        if (productosConPrecioMenorA100.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No se encontraron productos con precio menor a 100.");
                        } else {
                            new PdfReport().generateProductReport(productosConPrecioMenorA100, "C:\\tmp\\reporte_precio_menor_100.pdf");
                            JOptionPane.showMessageDialog(null, "Reporte de productos con precio menor a 100 generado.");
                        }
                        break;
                    case 3:
                        // Generar reporte de productos con existencia menor a 30
                        List<Producto> productosConExistenciaMenorA30 = productoService.obtenerProductosConExistenciaMenorA30();
                        if (productosConExistenciaMenorA30.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No se encontraron productos con existencia menor a 30.");
                        } else {
                            new PdfReport().generateProductReport(productosConExistenciaMenorA30, "C:\\tmp\\reporte_existencia_menor_30.pdf");
                            JOptionPane.showMessageDialog(null, "Reporte de productos con existencia menor a 30 generado.");
                        }
                        break;
                    case 4:
                        // Generar reporte de productos con precio entre 200 y 400
                        List<Producto> productosConPrecioEntre200Y400 = productoService.obtenerProductosConPrecioEntre200Y400();
                        if (productosConPrecioEntre200Y400.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No se encontraron productos con precio entre 200 y 400.");
                        } else {
                            new PdfReport().generateProductReport(productosConPrecioEntre200Y400, "C:\\tmp\\reporte_precio_200_a_400.pdf");
                            JOptionPane.showMessageDialog(null, "Reporte de productos con precio entre 200 y 400 generado.");
                        }
                        break;
                    case 5:
                        // Generar reporte de productos ordenados por precio (mayor a menor)
                        List<Producto> productosOrdenadosPorPrecioDesc = productoService.obtenerProductosOrdenadosPorPrecioDesc();
                        if (productosOrdenadosPorPrecioDesc.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No se encontraron productos para ordenar por precio.");
                        } else {
                            new PdfReport().generateProductReport(productosOrdenadosPorPrecioDesc, "C:\\tmp\\reporte_precio_desc.pdf");
                            JOptionPane.showMessageDialog(null, "Reporte de productos ordenados por precio (mayor a menor) generado.");
                        }
                        break;
                    case 6:
                        // Generar reporte de productos ordenados por existencia (menor a mayor)
                        List<Producto> productosOrdenadosPorExistenciasAsc = productoService.obtenerProductosOrdenadosPorExistenciasAsc();
                        if (productosOrdenadosPorExistenciasAsc.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No se encontraron productos para ordenar por existencia.");
                        } else {
                            new PdfReport().generateProductReport(productosOrdenadosPorExistenciasAsc, "C:\\tmp\\reporte_existencias_asc.pdf");
                            JOptionPane.showMessageDialog(null, "Reporte de productos ordenados por existencia (menor a mayor) generado.");
                        }
                        break;
                    case 7:
                        JOptionPane.showMessageDialog(null, "Saliendo del menú.");
                        return; // Sale del programa
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Por favor, ingrese un número.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }
}