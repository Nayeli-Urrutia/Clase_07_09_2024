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

    // Método para obtener productos con precio menor a 100
    public List<Producto> obtenerProductosConPrecioMenorA100() throws SQLException {
        return productoDAO.selectProductosByPrecio(); // Llama al método del DAO
    }

    // Método para eliminar un producto
    public boolean eliminarProducto(int idProducto) throws SQLException {
        return productoDAO.deleteProducto(idProducto);
    }

    // Servicio para actualizar un producto
    public boolean actualizarProducto(Producto producto) throws SQLException {
        return productoDAO.updateProducto(producto);
    }

    // Nuevo método para eliminar productos con precio igual a 0
    public void eliminarProductosConPrecioCero() throws SQLException {
        List<Producto> productos = productoDAO.selectAllProductos(); // Obtener todos los productos
        for (Producto producto : productos) {
            if (producto.getPrecio() == 0) {
                productoDAO.deleteProducto(producto.getIdProducto()); // Eliminar producto
            }
        }
    }

    // Método para obtener productos con existencia menor a 30
    public List<Producto> obtenerProductosConExistenciaMenorA30() throws SQLException {
        return productoDAO.selectProductosExistenciaMenor30(); // Llama al método del DAO
    }

    // Método para obtener productos con precio entre 200 y 400
    public List<Producto> obtenerProductosConPrecioEntre200Y400() throws SQLException {
        return productoDAO.selectProductosPrecioEntre200Y400(); // Llama al método del DAO
    }

    // Método para obtener productos ordenados por precio de mayor a menor
    public List<Producto> obtenerProductosOrdenadosPorPrecioDesc() throws SQLException {
        return productoDAO.selectProductosOrdenadosPorPrecioDesc(); // Llama al método del DAO
    }

    // Método para obtener productos ordenados por existencias de menor a mayor
    public List<Producto> obtenerProductosOrdenadosPorExistenciasAsc() throws SQLException {
        return productoDAO.selectProductosOrdenadosPorExistenciasAsc(); // Llama al método del DAO
    }
}