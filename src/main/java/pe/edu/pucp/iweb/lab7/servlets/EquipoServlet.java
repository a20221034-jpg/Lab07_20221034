package pe.edu.pucp.iweb.lab7.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Clases de OpenPDF (paquete com.lowagie.text):
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import pe.edu.pucp.iweb.lab7.dao.EquipoDao;
import pe.edu.pucp.iweb.lab7.dao.SedeDao;

@WebServlet(name = "EquipoServlet", urlPatterns = {"/equipos"})
public class EquipoServlet extends HttpServlet {

    private final SedeDao sedeDao = new SedeDao();
    private final EquipoDao equipoDao = new EquipoDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "listar";
        }

        int idSede = 0;
        String paramSede = request.getParameter("idsede");
        if (paramSede != null && !paramSede.isEmpty()) {
            idSede = Integer.parseInt(paramSede);
        }

        switch (accion) {
            case "pdf":
                generarPdf(request, response, idSede);
                break;
            default:
                listar(request, response, idSede);
                break;
        }
    }

    /** Ejercicio 3: listado con combobox. */
    private void listar(HttpServletRequest request, HttpServletResponse response, int idSede)
            throws ServletException, IOException {

        ArrayList<Sede> sedes = sedeDao.listar();
        ArrayList<EquipoRed> equipos = equipoDao.listarPorSede(idSede);

        request.setAttribute("sedes", sedes);
        request.setAttribute("equipos", equipos);
        request.setAttribute("idsedeSel", idSede);

        request.getRequestDispatcher("equipos.jsp").forward(request, response);
    }

    /** Ejercicio 4: reporte PDF descargable. */

    private void generarPdf(HttpServletRequest request, HttpServletResponse response, int idSede)
            throws IOException {

        ArrayList<EquipoRed> equipos = equipoDao.listarPorSede(idSede);

        String nombreSede;
        String archivo;
        if (idSede == 0) {
            nombreSede = "Todas las sedes";
            archivo = "inventario_todas.pdf";
        } else {
            Sede sede = sedeDao.buscarPorId(idSede);
            nombreSede = (sede != null) ? sede.getNombre() : "Sede";
            archivo = "inventario_" + nombreSede.toLowerCase() + ".pdf";
        }

        // Cabeceras de descarga
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + archivo + "\"");

        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, response.getOutputStream());
            doc.open();

            // Título
            Font fontTitulo = new Font(Font.HELVETICA, 16, Font.BOLD);
            doc.add(new Paragraph("Inventario de equipos - " + nombreSede, fontTitulo));

            // Fecha y hora de generación
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            doc.add(new Paragraph("Fecha de generacion: " + sdf.format(new Date())));
            doc.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(100);

            Font fontCab = new Font(Font.HELVETICA, 11, Font.BOLD);
            String[] cabeceras = {
                    "Modelo", "Tipo", "Sede", "Precio (S/)", "Cantidad", "Soporte mensual (S/)"
            };
            for (String c : cabeceras) {
                tabla.addCell(new PdfPCell(new Phrase(c, fontCab)));
            }

            double total = 0;
            for (EquipoRed e : equipos) {
                double soporte = e.calcularSoporteMensual(); // polimorfismo
                total += soporte;

                tabla.addCell(e.getModelo());
                tabla.addCell(e.getTipo());
                tabla.addCell(e.getSede().getNombre());
                tabla.addCell(String.format(Locale.US, "%.2f", e.getPrecio()));
                tabla.addCell(String.valueOf(e.getCantidad()));
                tabla.addCell(String.format(Locale.US, "%.2f", soporte));
            }

            PdfPCell celdaTotal = new PdfPCell(new Phrase("TOTAL", fontCab));
            celdaTotal.setColspan(5);
            celdaTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabla.addCell(celdaTotal);
            tabla.addCell(new PdfPCell(new Phrase(String.format(Locale.US, "%.2f", total), fontCab)));

            doc.add(tabla);
            doc.close();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }
}
