package com.parcialpapeleria.app.utilities;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.parcialpapeleria.app.entity.Producto;
import com.parcialpapeleria.app.entity.Venta;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component()
public class ListarVentasPdf extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Obtiene la fecha actual
		Date fechaActual = new Date();

		// Formatea la fecha en un formato legible, por ejemplo, "dd/MM/yyyy"
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFormateada = sdf.format(fechaActual);

		// Establece el nombre del archivo PDF
		String fileName = "ReporteCompras.pdf"; // Cambia el nombre del archivo a lo que desees
		response.setHeader("Content-Disposition", "inline; filename=" + fileName);

		@SuppressWarnings("unchecked")
		List<Venta> listadoVentas = (List<Venta>) model.get("ventas");

		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-10, -10, 40, 10);
		document.open();

		// Define un estilo de fuente personalizado para el encabezado de la empresa
		Font fuenteEmpresa = FontFactory.getFont("Helvetica", 12, Font.BOLD, Color.BLACK);
		fuenteEmpresa.setStyle(Font.UNDERLINE); // Agrega subrayado
		fuenteEmpresa.setFamily("Arial"); // Cambia la familia de la fuente
		fuenteEmpresa.setStyle(Font.ITALIC); // Agrega cursiva

		PdfPTable tablaEncabezado = new PdfPTable(2); // Cambia el número de columnas a 2 para dividir el título del
														// texto
		tablaEncabezado.setSpacingBefore(20); // Espacio antes de la tabla
		tablaEncabezado.setSpacingAfter(20); // Espacio después de la tabla
		tablaEncabezado.getDefaultCell().setBorder(Rectangle.BOX); // Agrega bordes a las celdas

		// Agrega la información de la empresa en la parte superior izquierda
		PdfPCell cellTitulo = new PdfPCell(new Phrase("NEGOCIO:", fuenteEmpresa)); // Título
		cellTitulo.setBackgroundColor(new Color(241, 196, 15));
		cellTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellTitulo.setPadding(6);
		tablaEncabezado.addCell(cellTitulo);

		PdfPCell cellValor = new PdfPCell(new Phrase("Papeleria Spectrum", fuenteEmpresa)); // Valor
		cellValor.setBackgroundColor(new Color(255, 255, 255));
		cellValor.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellValor.setPadding(6);
		tablaEncabezado.addCell(cellValor);

		cellTitulo = new PdfPCell(new Phrase("DIRECCIÓN:", fuenteEmpresa)); // Título
		cellTitulo.setBackgroundColor(new Color(241, 196, 15));
		cellTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellTitulo.setPadding(6);
		tablaEncabezado.addCell(cellTitulo);

		cellValor = new PdfPCell(new Phrase("CALLE. 9 #23 - 60", fuenteEmpresa)); // Valor
		cellValor.setBackgroundColor(new Color(255, 255, 255));
		cellValor.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellValor.setPadding(6);
		tablaEncabezado.addCell(cellValor);

		cellTitulo = new PdfPCell(new Phrase("CONTACTO:", fuenteEmpresa)); // Título
		cellTitulo.setBackgroundColor(new Color(241, 196, 15));
		cellTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellTitulo.setPadding(6);
		tablaEncabezado.addCell(cellTitulo);

		cellValor = new PdfPCell(new Phrase("+57 (315) 3281327", fuenteEmpresa)); // Valor
		cellValor.setBackgroundColor(new Color(255, 255, 255));
		cellValor.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellValor.setPadding(6);
		tablaEncabezado.addCell(cellValor);

		cellTitulo = new PdfPCell(new Phrase("EMAIL:", fuenteEmpresa)); // Título
		cellTitulo.setBackgroundColor(new Color(241, 196, 15));
		cellTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellTitulo.setPadding(6);
		tablaEncabezado.addCell(cellTitulo);

		cellValor = new PdfPCell(new Phrase("ecommerce@papeleriaspectrum.com", fuenteEmpresa)); // Valor
		cellValor.setBackgroundColor(new Color(255, 255, 255));
		cellValor.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellValor.setPadding(6);
		tablaEncabezado.addCell(cellValor);

		cellTitulo = new PdfPCell(new Phrase("FECHA:", fuenteEmpresa)); // Título
		cellTitulo.setBackgroundColor(new Color(241, 196, 15));
		cellTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellTitulo.setPadding(6);
		tablaEncabezado.addCell(cellTitulo);

		cellValor = new PdfPCell(new Phrase(fechaFormateada, fuenteEmpresa)); // Valor
		cellValor.setBackgroundColor(new Color(255, 255, 255));
		cellValor.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellValor.setPadding(6);
		tablaEncabezado.addCell(cellValor);

		
		document.add(tablaEncabezado);

		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda = null;

		Font fuenteTitulo = FontFactory.getFont("COURIER", 20, Color.WHITE);

		celda = new PdfPCell(new Phrase(" -- REGISTRO DE COMPRAS -- ", fuenteTitulo));fuenteTitulo.setStyle(Font.BOLD);
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(216, 55, 90));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celda.setPadding(30);

		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);

		PdfPTable tablaClientes = new PdfPTable(3);

		Font fuenteCeldas = FontFactory.getFont("VERDANA", 12); // Define una fuente más pequeña
		Font fuenteTitulos = FontFactory.getFont("VERDANA", 12, Color.BLACK); fuenteTitulos.setStyle(Font.BOLD);// Fuente para los títulos

		// Agrega los títulos de las columnas centrados
		PdfPCell cell = new PdfPCell(new Phrase("ID", fuenteTitulos));

		cell = new PdfPCell(new Phrase("REFERENCIA", fuenteTitulos));
		cell.setPadding(10);
		cell.setBackgroundColor(new Color(241, 196, 15));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tablaClientes.addCell(cell);

		cell = new PdfPCell(new Phrase("FECHA", fuenteTitulos));
		cell.setPadding(10);
		cell.setBackgroundColor(new Color(241, 196, 15));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tablaClientes.addCell(cell);

		cell = new PdfPCell(new Phrase("PRODUCTOS", fuenteTitulos));
		cell.setPadding(10);
		cell.setBackgroundColor(new Color(241, 196, 15));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tablaClientes.addCell(cell);

		listadoVentas.forEach(venta -> {
			PdfPCell cell2 = new PdfPCell(new Phrase(venta.getId(), fuenteCeldas));

			cell2 = new PdfPCell(new Phrase(venta.getNumeroref(), fuenteCeldas));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell2.setPadding(5); // Establece el relleno interno
			tablaClientes.addCell(cell2);

			cell2 = new PdfPCell(new Phrase(venta.getFecha(), fuenteCeldas));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell2.setPadding(5); // Establece el relleno interno
			tablaClientes.addCell(cell2);

			List<Producto> productos = venta.getProducto();
			StringBuilder nombresProductos = new StringBuilder();

			for (Producto producto : productos) {
			    nombresProductos.append(producto.getDescripcion()).append(", ");
			}

			// Eliminar la coma adicional al final, si es necesario
			if (nombresProductos.length() > 0) {
			    nombresProductos.deleteCharAt(nombresProductos.length() - 2);
			}

			cell2 = new PdfPCell(new Phrase(nombresProductos.toString(), fuenteCeldas));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell2.setPadding(25); // Establece el relleno interno
			tablaClientes.addCell(cell2);
			
			

		});

		document.add(tablaTitulo);
		document.add(tablaClientes);

	}

}
