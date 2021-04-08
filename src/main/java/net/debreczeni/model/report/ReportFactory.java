package net.debreczeni.model.report;

public class ReportFactory {
    private ReportFactory() {
    }

    public static Report getReport(ReportType reportType) {
        switch (reportType) {
            case OUT_OF_STOCK_CSV:
                return new net.debreczeni.model.report.csv.OutOfStockReport();
            case OUT_OF_STOCK_PDF:
                return new net.debreczeni.model.report.pdf.OutOfStockReport();
            default:
                return null;
        }
    }

    public enum ReportType {
        OUT_OF_STOCK_PDF,
        OUT_OF_STOCK_CSV,
    }
}
