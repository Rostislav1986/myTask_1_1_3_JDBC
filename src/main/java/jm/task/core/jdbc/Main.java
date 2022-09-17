package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser("Максим", "Синявский", (byte) 34);
        userDao.saveUser("Марат", "Ачилов", (byte) 33);
        userDao.saveUser("Игорь", "Старовойтов", (byte) 34);
        userDao.saveUser("Глеб", "Мантуров", (byte) 34);
        userDao.saveUser("Леонид", "Качнов", (byte) 29);
        userDao.saveUser("Татьяна", "Подкаменская", (byte) 26);
        userDao.saveUser("Вячеслав", "Шигин", (byte) 22);

        userDao.getAllUsers();

        /*   userDao.cleanUsersTable();

        //userDao.dropUsersTable();*/
    }
    }

