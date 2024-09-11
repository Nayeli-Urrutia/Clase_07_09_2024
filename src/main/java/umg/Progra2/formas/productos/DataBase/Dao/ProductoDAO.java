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

    private static final String INSERT_PRODUCTO_SQL = "INSERT INTO tb_producto (descripcion, origen) VALUES (?, ?)";
    private static final String SELECT_PRODUCTO_BY_ID = "SELECT id_producto, descripcion, origen FROM tb_producto WHERE id_producto = ?";
    private static final String SELECT_ALL_PRODUCTOS = "SELECT * FROM tb_producto";
    private static final String DELETE_PRODUCTO_SQL = "DELETE FROM tb_producto WHERE id_producto = ?";
    private static final String UPDATE_PRODUCTO_SQL = "UPDATE tb_producto SET descripcion = ?, origen = ? WHERE id_producto = ?";

    // Método para insertar un producto
    public void insertProducto(Producto producto) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTO_SQL)) {
            preparedStatement.setString(1, producto.getDescripcion());
            preparedStatement.setString(2, producto.getOrigen());
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
                producto = new Producto(idProducto, descripcion, origen);
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
                productos.add(new Producto(idProducto, descripcion, origen));
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
            preparedStatement.setInt(3, producto.getIdProducto());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}