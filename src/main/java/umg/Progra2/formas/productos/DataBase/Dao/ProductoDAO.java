package umg.Progra2.formas.productos.DataBase.Dao;

import umg.Progra2.formas.productos.DataBase.DB.DBConnection;
import umg.Progra2.formas.productos.DataBase.Model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private static final String SELECT_PRODUCTOS_BY_PRECIO = "SELECT * FROM tb_producto WHERE precio < 100;";
    private static final String INSERT_PRODUCTO_SQL = "INSERT INTO tb_producto (descripcion, origen, precio, cantidad) VALUES (?, ?, ?, ?)";
    private static final String DELETE_PRODUCTO_SQL = "DELETE FROM tb_producto WHERE id_producto = ?";
    private static final String SELECT_ALL_PRODUCTOS = "SELECT * FROM tb_producto ORDER BY origen, descripcion, precio, cantidad";
    private static final String SELECT_PRODUCTO_BY_ID = "SELECT id_producto, descripcion, origen, precio, cantidad FROM tb_producto WHERE id_producto = ?";
    private static final String UPDATE_PRODUCTO_SQL = "UPDATE tb_producto SET descripcion = ?, origen = ?, precio = ?, cantidad = ? WHERE id_producto = ?";

    // Nuevas consultas para reportes
    private static final String SELECT_PRODUCTOS_EXISTENCIA_MENOR_30 = "SELECT * FROM tb_producto WHERE cantidad < 30;";
    private static final String SELECT_PRODUCTOS_PRECIO_ENTRE_200_400 = "SELECT * FROM tb_producto WHERE precio BETWEEN 200 AND 400;";
    private static final String SELECT_PRODUCTOS_ORDENADOS_POR_PRECIO_DESC = "SELECT * FROM tb_producto ORDER BY precio DESC;";
    private static final String SELECT_PRODUCTOS_ORDENADOS_POR_EXISTENCIAS_ASC = "SELECT * FROM tb_producto ORDER BY cantidad ASC;";

    // Método para insertar un producto
    public void insertProducto(Producto producto) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTO_SQL)) {
            preparedStatement.setString(1, producto.getDescripcion());
            preparedStatement.setString(2, producto.getOrigen());
            preparedStatement.setInt(3, producto.getPrecio());
            preparedStatement.setInt(4, producto.getCantidad());
            preparedStatement.executeUpdate();
        }
    }

    // Método para seleccionar un producto por ID
    public Producto selectProducto(int idProducto) throws SQLException {
        Producto producto = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTO_BY_ID)) {
            preparedStatement.setInt(1, idProducto);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descripcion = rs.getString("descripcion");
                String origen = rs.getString("origen");
                int precio = rs.getInt("precio");
                int cantidad = rs.getInt("cantidad");
                producto = new Producto(idProducto, descripcion, origen, precio, cantidad);
            }
        }
        return producto;
    }

    // Método para seleccionar todos los productos
    public List<Producto> selectAllProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTOS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                String descripcion = rs.getString("descripcion");
                String origen = rs.getString("origen");
                int precio = rs.getInt("precio");
                int cantidad = rs.getInt("cantidad");
                productos.add(new Producto(idProducto, descripcion, origen, precio, cantidad));
            }
        }
        return productos;
    }

    // Método para eliminar un producto
    public boolean deleteProducto(int idProducto) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCTO_SQL)) {
            preparedStatement.setInt(1, idProducto);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    // Método para actualizar un producto
    public boolean updateProducto(Producto producto) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCTO_SQL)) {
            preparedStatement.setString(1, producto.getDescripcion());
            preparedStatement.setString(2, producto.getOrigen());
            preparedStatement.setInt(3, producto.getPrecio());
            preparedStatement.setInt(4, producto.getCantidad());
            preparedStatement.setInt(5, producto.getIdProducto());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    // Método para seleccionar productos por existencia menor a 30
    public List<Producto> selectProductosExistenciaMenor30() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTOS_EXISTENCIA_MENOR_30)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                String descripcion = rs.getString("descripcion");
                String origen = rs.getString("origen");
                int precio = rs.getInt("precio");
                int cantidad = rs.getInt("cantidad");
                productos.add(new Producto(idProducto, descripcion, origen, precio, cantidad));
            }
        }
        return productos;
    }

    // Método para seleccionar productos con precio entre 200 y 400
    public List<Producto> selectProductosPrecioEntre200Y400() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTOS_PRECIO_ENTRE_200_400)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                String descripcion = rs.getString("descripcion");
                String origen = rs.getString("origen");
                int precio = rs.getInt("precio");
                int cantidad = rs.getInt("cantidad");
                productos.add(new Producto(idProducto, descripcion, origen, precio, cantidad));
            }
        }
        return productos;
    }

    // Método para seleccionar productos ordenados por precio de mayor a menor
    public List<Producto> selectProductosOrdenadosPorPrecioDesc() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTOS_ORDENADOS_POR_PRECIO_DESC)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                String descripcion = rs.getString("descripcion");
                String origen = rs.getString("origen");
                int precio = rs.getInt("precio");
                int cantidad = rs.getInt("cantidad");
                productos.add(new Producto(idProducto, descripcion, origen, precio, cantidad));
            }
        }
        return productos;
    }

    // Método para seleccionar productos ordenados por existencias de menor a mayor
    public List<Producto> selectProductosOrdenadosPorExistenciasAsc() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTOS_ORDENADOS_POR_EXISTENCIAS_ASC)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                String descripcion = rs.getString("descripcion");
                String origen = rs.getString("origen");
                int precio = rs.getInt("precio");
                int cantidad = rs.getInt("cantidad");
                productos.add(new Producto(idProducto, descripcion, origen, precio, cantidad));
            }
        }
        return productos;
    }

    // Método para seleccionar productos por precio
    public List<Producto> selectProductosByPrecio() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTOS_BY_PRECIO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                String descripcion = rs.getString("descripcion");
                String origen = rs.getString("origen");
                int precio = rs.getInt("precio");
                int cantidad = rs.getInt("cantidad");
                productos.add(new Producto(idProducto, descripcion, origen, precio, cantidad));
            }
        }
        return productos;
    }

    // Método para eliminar un producto si su precio es Q0.00
    public boolean deleteProductoSiPrecioCero(int idProducto) throws SQLException {
        // Primero, obtenemos el producto para verificar su precio
        Producto producto = selectProducto(idProducto);
        if (producto != null && producto.getPrecio() == 0) {
            // Si el precio es Q0.00, se procede a eliminarlo
            return deleteProducto(idProducto);
        } else {
            // El producto no existe o su precio no es Q0.00
            System.out.println("No se puede eliminar el producto. Debe tener un precio de Q0.00.");
            return false;
        }
    }
}