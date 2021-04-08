package net.debreczeni.controller;

import net.debreczeni.model.report.Report;
import net.debreczeni.model.report.ReportFactory;
import net.debreczeni.model.report.ReportFactory.ReportType;

public class ReportController {
    private ReportController() {
    }

    public static ReportController getInstance() {
        return ReportController.Singleton.INSTANCE;
    }

    public String generatePDF() {
        final Report report = ReportFactory.getReport(ReportType.OUT_OF_STOCK_PDF);
        if (report == null) {
            return null;
        }

        return report.toFile("out-of-stock-books");
    }

    public String generateCSV() {
        final Report report = ReportFactory.getReport(ReportType.OUT_OF_STOCK_CSV);
        if (report == null) {
            return null;
        }

        return report.toFile("out-of-stock-books");
    }

    private static class Singleton {
        private static final ReportController INSTANCE = new ReportController();
    }
}
