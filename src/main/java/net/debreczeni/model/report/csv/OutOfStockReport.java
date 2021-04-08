package net.debreczeni.model.report.csv;

import net.debreczeni.model.Book;
import net.debreczeni.model.report.Report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * Generate a <b>CSV</b> file with the books that are out-of-stock
 */
public class OutOfStockReport implements Report {
    @Override
    public String toFile(String filename) {
        final List<Book> books = bookController.getAll().stream().filter(book -> book.getQuantity() == 0).collect(Collectors.toList());
        final File file = new File(RESOURCE_PATH + filename + ".csv");
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("id,title,author,genre,quantity,price\n");
            for (Book book : books) {
                writer.write(book + "\n");
            }
            writer.flush();
            return file.getAbsolutePath();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Couldn't generate CSV report file", e);
            return null;
        }
    }
}
