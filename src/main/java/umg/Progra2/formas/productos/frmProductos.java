package umg.Progra2.formas.productos;

import umg.Progra2.formas.productos.DataBase.Model.Producto;
import umg.Progra2.formas.productos.DataBase.Service.ProductoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class frmProductos {
    private JPanel jPanelPrincipal;
    private JLabel lblTitulo;
    private JLabel lblid;
    private JTextField textFieldidProducto;
    private JLabel lblNombre;
    private JTextField textFieldNombreProducto;
    private JLabel lblOrigen;

    private JButton buttonGrabar;
    private JButton buttonBuscar;
    private JButton buttonActualizar;
    private JLabel lblPrecio;
    private JLabel lblCantidad;
    private JTextField textFieldPrecio;
    private JTextField textFieldCantidad;
    private JTextField textFieldOrigen;
    private JButton buttonReportes;

    public static void main(String[] args) {
        JFrame frame = new JFrame("frmProductos");
        frame.setContentPane(new frmProductos().jPanelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public frmProductos() {
        buttonGrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto producto = new Producto();
                producto.setDescripcion(textFieldNombreProducto.getText());
                producto.setOrigen(textFieldOrigen.getText());
                producto.setPrecio(Double.parseDouble(textFieldPrecio.getText()));
                producto.setCantidad(Integer.parseInt(textFieldCantidad.getText()));

                try {
                    new ProductoService().agregarProducto(producto);
                    JOptionPane.showMessageDialog(null, "Producto agregado exitosamente");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error de base de datos: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                } finally {
                    // Limpiar los campos de texto
                    textFieldidProducto.setText("");
                    textFieldNombreProducto.setText("");
                    textFieldOrigen.setText("");
                    textFieldPrecio.setText("");
                    textFieldCantidad.setText("");
                }
            }
        });

        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProducto = textFieldidProducto.getText().isEmpty() ? 0 : Integer.parseInt(textFieldidProducto.getText());
                try {
                    Producto productoEncontrado = new ProductoService().obtenerProductoPorId(idProducto);
                    if (productoEncontrado != null) {
                        textFieldNombreProducto.setText(productoEncontrado.getDescripcion());
                        textFieldOrigen.setText(productoEncontrado.getOrigen());
                        textFieldPrecio.setText(String.valueOf(productoEncontrado.getPrecio()));
                        textFieldCantidad.setText(String.valueOf(productoEncontrado.getCantidad()));
                    } else {
                        JOptionPane.showMessageDialog(null, "El producto no existe");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR de Base de Datos: " + ex.getMessage());
                }
            }
        });

        buttonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la información del producto desde los campos de texto
                int idProducto = textFieldidProducto.getText().isEmpty() ? 0 : Integer.parseInt(textFieldidProducto.getText());
                String descripcion = textFieldNombreProducto.getText();
                String origen = textFieldOrigen.getText();
                double precio = Double.parseDouble(textFieldPrecio.getText());
                int cantidad = Integer.parseInt(textFieldCantidad.getText());

                // Verificar si el ID es válido
                if (idProducto <= 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID válido.");
                    return;
                }

                Producto producto = new Producto(idProducto, descripcion, origen, precio, cantidad);
                try {
                    // Llamar al método de actualización del servicio
                    boolean actualizado = new ProductoService().actualizarProducto(producto);
                    // Mostrar un mensaje al usuario sobre el éxito o fracaso de la actualización
                    if (actualizado) {
                        JOptionPane.showMessageDialog(null, "Producto actualizado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar el producto. Verifique si el ID es correcto.");
                    }
                } catch (SQLException ex) {
                    // Manejar excepciones de base de datos
                    JOptionPane.showMessageDialog(null, "Error de base de datos: " + ex.getMessage());
                }
            }
        });

        buttonReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {
                        "Productos con existencia menor a 20",
                        "Productos de un país específico",
                        "Productos con precio mayor a 2000",
                        "Productos agrupados por país y ordenados por precio"
                };

                // Show the option dialog
                int seleccion = JOptionPane.showOptionDialog(
                        null,
                        "Seleccione el reporte que desea ver:",
                        "Reportes",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (seleccion >= 0 && seleccion < options.length) {
                    try {
                        List<Producto> productosMenoresA20 = null;
                        List<Producto> productosDePais = null;
                        List<Producto> productosMayor2000 = null;
                        List<Producto> productosAgrupadosYOrdenados = null;

                        switch (seleccion) {
                            case 0: // Productos con existencia menor a 20
                                productosMenoresA20 = new ProductoService().obtenerProductosConExistenciaMenorA20();
                                mostrarReporte(productosMenoresA20, null, null, null);
                                break;

                            case 1: // Productos de un país específico
                                String paisEspecifico = JOptionPane.showInputDialog("Ingrese el país:");
                                if (paisEspecifico != null && !paisEspecifico.isEmpty()) {
                                    productosDePais = new ProductoService().obtenerProductosPorPais(paisEspecifico);
                                    mostrarReporte(null, productosDePais, null, null);
                                }
                                break;

                            case 2: // Productos con precio mayor a 2000
                                productosMayor2000 = new ProductoService().obtenerProductosConPrecioMayorA2000();
                                mostrarReporte(null, null, productosMayor2000, null);
                                break;

                            case 3: // Productos agrupados por país y ordenados por precio
                                productosAgrupadosYOrdenados = new ProductoService().obtenerProductosAgrupadosPorPaisYPrecioDesc();
                                mostrarReporte(null, null, null, productosAgrupadosYOrdenados);
                                break;
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error generando informes: " + ex.getMessage());
                    }
                }
            }
        });
    }

    private void mostrarReporte(List<Producto> productosMenoresA20, List<Producto> productosDePais, List<Producto> productosMayor2000, List<Producto> productosAgrupadosYOrdenados) {
        // ANSI escape code for red text
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m"; // Reset to default color

        if (productosMenoresA20 != null) {
            System.out.println(RED + "\n\nProductos con existencia menor a 20:" + RESET);
            for (Producto p : productosMenoresA20) {
                System.out.println("   " + p.getDescripcion() + " - Cantidad: " + p.getCantidad());
            }
        }

        if (productosDePais != null) {
            System.out.println(RED + "\n\nProductos de un país específico:" + RESET);
            for (Producto p : productosDePais) {
                System.out.println("   " + p.getDescripcion() + " - Origen: " + p.getOrigen());
            }
        }

        if (productosMayor2000 != null) {
            System.out.println(RED + "\n\nProductos con precio mayor a 2000:" + RESET);
            for (Producto p : productosMayor2000) {
                System.out.println("   " + p.getDescripcion() + " - Precio: " + p.getPrecio());
            }
        }

        if (productosAgrupadosYOrdenados != null) {
            System.out.println(RED + "\n\nProductos agrupados por país y ordenados por precio (desc):" + RESET);
            for (Producto p : productosAgrupadosYOrdenados) {
                System.out.println("   " + p.getDescripcion() + " - Origen: " + p.getOrigen() + " - Precio: " + p.getPrecio());
            }
        }
    }
}
