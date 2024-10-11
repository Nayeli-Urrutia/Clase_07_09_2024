package umg.Progra2.formas.productos.DataBase.Model;


    public class Producto {
        private int idProducto;
        private String descripcion;
        private String origen;
        private double precio;
        private int cantidad;

        // Constructor vacío
        public Producto() {}

        // Constructor con parámetros
        public Producto(int idProducto, String descripcion, String origen,double precio, int cantidad) {
            this.idProducto = idProducto;
            this.descripcion = descripcion;
            this.origen = origen;
            this.precio = precio;
            this.cantidad = cantidad;
        }

        // Getters y Setters
        public int getIdProducto() {
            return idProducto;
        }

        public void setIdProducto(int idProducto) {
            this.idProducto = idProducto;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getOrigen() {
            return origen;
        }

        public void setOrigen(String origen) {
            this.origen = origen;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
    }

