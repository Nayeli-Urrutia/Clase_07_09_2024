package umg.Progra2.formas.productos;

import umg.Progra2.formas.productos.DataBase.Model.Producto;
import umg.Progra2.formas.productos.DataBase.Service.ProductoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class frmProductos {
    private JPanel jPanelPrincipal;
    private JLabel lblTitulo;
    private JLabel lblid;
    private JTextField textFieldidProducto;
    private JLabel lblNombre;
    private JTextField textFieldNombreProducto;
    private JLabel lblOrigen;
    private JComboBox comboBoxOrigen;
    private JButton buttonGrabar;
    private JButton buttonBuscar;
    private JButton buttonActualizar;

    public static void main(String[] args) {
        JFrame frame = new JFrame("frmProductos");
        frame.setContentPane(new frmProductos().jPanelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public frmProductos() {

        comboBoxOrigen.addItem("China");
        comboBoxOrigen.addItem("Japon");
        comboBoxOrigen.addItem("Corea");

        buttonGrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Producto producto = new Producto();
                producto.setDescripcion(textFieldNombreProducto.getText());
                producto.setOrigen(comboBoxOrigen.getSelectedItem().toString());

                try{
                    new ProductoService().agregarProducto(producto);
                    JOptionPane.showMessageDialog(null, "Producto agregado exitosamente");

                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al agregar producto"+ ex.getMessage());
                }

            }
        });
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProducto = textFieldidProducto.getText().isEmpty()? 0: Integer.parseInt(textFieldidProducto.getText());

                try {
                    Producto productoEncontrado = new ProductoService().obtenerProductoPorId(idProducto);
                    if (productoEncontrado !=null) {
                        textFieldNombreProducto.setText(productoEncontrado.getDescripcion());
                        comboBoxOrigen.setSelectedItem(productoEncontrado.getOrigen());
                    }else {
                        JOptionPane.showMessageDialog(null, "El producto no existe");


                    }
                } catch (Exception ex){

                    JOptionPane.showMessageDialog( null, "ERROR de base de Datos" + ex.getMessage());

                }
            }
        });
        buttonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Obtener la información del producto desde los campos de texto
                int idProducto = textFieldidProducto.getText().isEmpty() ? 0 : Integer.parseInt(textFieldidProducto.getText());
                String descripcion = textFieldNombreProducto.getText();
                String origen = comboBoxOrigen.getSelectedItem().toString();

                // Verificar si el ID es válido
                if (idProducto <= 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID válido.");
                    return;
                }

                // Crear un objeto Producto con la información obtenida
                Producto producto = new Producto(idProducto, descripcion, origen);

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
    }
}
