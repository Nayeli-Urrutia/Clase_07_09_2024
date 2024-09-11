package umg.Progra2.formas.productos.DataBase.Service;

import umg.Progra2.formas.productos.DataBase.Dao.ProductoDAO;
import umg.Progra2.formas.productos.DataBase.Model.Producto;

import java.sql.SQLException;
import java.util.List;

public class ProductoService {
    private ProductoDAO productoDAO = new ProductoDAO();

    // Servicio para agregar un nuevo producto
    public void agregarProducto(Producto producto) throws SQLException {
        productoDAO.insertProducto(producto);
    }

    // Servicio para obtener un producto por su ID
    public Producto obtenerProductoPorId(int idProducto) throws SQLException {
        return productoDAO.selectProducto(idProducto);
    }

    // Servicio para obtener todos los productos
    public List<Producto> obtenerTodosLosProductos() throws SQLException {
        return productoDAO.selectAllProductos();
    }

    // Servicio para eliminar un producto
    public boolean eliminarProducto(int idProducto) throws SQLException {
        return productoDAO.deleteProducto(idProducto);
    }

    // Servicio para actualizar un producto
    public boolean actualizarProducto(Producto producto) throws SQLException {
        return productoDAO.updateProducto(producto);
    }
}