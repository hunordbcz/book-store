package net.debreczeni.controller;

public class UserController {
    private UserController() {
    }

    public static UserController getInstance() {
        return UserController.Singleton.INSTANCE;
    }

    private static class Singleton {
        private static final UserController INSTANCE = new UserController();
    }
}
