package umg.Progra2.formas.productos.Reportes;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import umg.Progra2.formas.productos.DataBase.Model.Producto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Stream;

public class PdfReport {
    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLDITALIC);
    private static final Font HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

    public void generateProductReport(List<Producto> productos, String outputPath) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(outputPath));
        document.open();

        addTitle(document);
        addProductTable(document, productos);

        document.close();
    }

    private void addTitle(Document document) throws DocumentException {
        Paragraph title = new Paragraph("Nayeli Melissa Urrutia Orellana 0905-23-5575", TITLE_FONT);

        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);
    }

    private void addProductTable(Document document, List<Producto> productos) throws DocumentException {
        PdfPTable table = new PdfPTable(6); // 6 columnas para id, descripción, origen, precio, cantidad y total
        table.setWidthPercentage(100);
        addTableHeader(table);
        addRows(table, productos);
        addTotals(table, productos); // Llamar al método para agregar totales
        document.add(table);
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("ID", "Descripción", "Origen", "Precio", "Cantidad", "Total")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.PINK);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle, HEADER_FONT));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, List<Producto> productos) {
        for (Producto producto : productos) {
            table.addCell(new Phrase(String.valueOf(producto.getIdProducto()), NORMAL_FONT));
            table.addCell(new Phrase(producto.getDescripcion(), NORMAL_FONT));
            table.addCell(new Phrase(producto.getOrigen(), NORMAL_FONT));
            DecimalFormat df = new DecimalFormat("#.00"); // Formato con 2 decimales
            table.addCell(new Phrase("Q. " + df.format(producto.getPrecio()), NORMAL_FONT));
            table.addCell(new Phrase(String.valueOf(producto.getCantidad()), NORMAL_FONT));

            // Calcular y agregar el Total
            double total = producto.getPrecio() * producto.getCantidad();
            table.addCell(new Phrase("Q. " + df.format(total), NORMAL_FONT)); // Agregamos el total a la fila
        }
    }

    private void addTotals(PdfPTable table, List<Producto> productos) {
        double totalGeneralPrecio = 0.0;
        int totalGeneralCantidad = 0;
        double totalGeneralTotal = 0.0;

        for (Producto producto : productos) {
            totalGeneralPrecio += producto.getPrecio(); // Sumar precio
            totalGeneralCantidad += producto.getCantidad(); // Sumar cantidad
            totalGeneralTotal += producto.getPrecio() * producto.getCantidad(); // Sumar total
        }

        DecimalFormat df = new DecimalFormat("#.00"); // Formato con 2 decimales

        // Agregar totales por grupo
        table.addCell(new Phrase("Total por Grupo:", HEADER_FONT));
        table.addCell(new Phrase("", NORMAL_FONT)); // Espacio vacío para la columna de descripción
        table.addCell(new Phrase("", NORMAL_FONT)); // Espacio vacío para la columna de origen
        table.addCell(new Phrase("Q. " + df.format(totalGeneralPrecio), NORMAL_FONT));
        table.addCell(new Phrase(String.valueOf(totalGeneralCantidad), NORMAL_FONT));
        table.addCell(new Phrase("Q. " + df.format(totalGeneralTotal), NORMAL_FONT));

        // Agregar línea en blanco
        table.addCell(new Phrase("", NORMAL_FONT));
        table.addCell(new Phrase("", NORMAL_FONT));
        table.addCell(new Phrase("", NORMAL_FONT));
        table.addCell(new Phrase("", NORMAL_FONT));
        table.addCell(new Phrase("", NORMAL_FONT));
        table.addCell(new Phrase("", NORMAL_FONT));

        // Agregar gran total
        table.addCell(new Phrase("Gran Total:", HEADER_FONT));
        table.addCell(new Phrase("", NORMAL_FONT)); // Espacio vacío para la columna de descripción
        table.addCell(new Phrase("", NORMAL_FONT)); // Espacio vacío para la columna de origen
        table.addCell(new Phrase("Q. " + df.format(totalGeneralPrecio), NORMAL_FONT));
        table.addCell(new Phrase(String.valueOf(totalGeneralCantidad), NORMAL_FONT));
        table.addCell(new Phrase("Q. " + df.format(totalGeneralTotal), NORMAL_FONT));
    }
}