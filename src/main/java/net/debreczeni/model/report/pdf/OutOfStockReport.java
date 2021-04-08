package net.debreczeni.model.report.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import net.debreczeni.model.Book;
import net.debreczeni.model.report.Report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * Generate a <b>PDF</b> file with the books that are out-of-stock
 */
public class OutOfStockReport implements Report {
    private static final Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

    @Override
    public String toFile(String filename) {
        final List<Book> books = bookController.getAll().stream().filter(book -> book.getQuantity() == 0).collect(Collectors.toList());

        try {
            Document document = new Document();
            File file = new File(RESOURCE_PATH + filename + ".pdf");
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            document.addTitle("Out of stock books - Book Store");
            document.add(new Paragraph("id,title,author,genre,quantity,price", font));
            for (Book book : books) {
                document.add(new Paragraph(book.toString(), font));
            }

            document.close();
            return file.getAbsolutePath();
        } catch (DocumentException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Couldn't generate PDF report file", e);
            return null;
        }
    }
}
