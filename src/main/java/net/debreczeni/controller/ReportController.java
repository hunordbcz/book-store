package net.debreczeni.controller;

public class ReportController {
    private ReportController() {
    }

    public static ReportController getInstance() {
        return ReportController.Singleton.INSTANCE;
    }

    private static class Singleton {
        private static final ReportController INSTANCE = new ReportController();
    }
}
