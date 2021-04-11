package net.debreczeni.model.report;

import net.debreczeni.controller.BookController;

import java.util.logging.Logger;

public interface Report {
    String RESOURCE_PATH = "./";
    Logger LOGGER = Logger.getLogger(Report.class.getSimpleName());
    BookController bookController = BookController.getInstance();

    /**
     * @param filename the prefix for the filename
     * @return The absolute path of the created file
     * If null, then the file wasn't created
     */
    String toFile(String filename);
}
