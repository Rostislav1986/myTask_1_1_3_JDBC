package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Максим", "Синявский", (byte) 34);
        userService.saveUser("Марат", "Ачилов", (byte) 33);
        userService.saveUser("Игорь", "Старовойтов", (byte) 34);
        userService.saveUser("Леонид", "Качнов", (byte) 29);
        userService.saveUser("Татьяна", "Подкаменская", (byte) 26);
        userService.saveUser("Вячеслав", "Шигин", (byte) 22);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
    }

