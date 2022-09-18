package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    private Transaction transaction = null;
    private final SessionFactory sessionFactory = getSessionFactory();

    public UserDaoHibernateImpl() {

    }
    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            String SQL = "CREATE TABLE IF NOT EXISTS User " +
                    " (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " lastName VARCHAR(255) , " +
                    " age INT)";
            session.createSQLQuery(SQL).addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }
    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS User").addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
       try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }

    }

        @Override
        public void removeUserById ( long id){
            try (Session session = sessionFactory.getCurrentSession()) {
                transaction = session.beginTransaction();
                session.delete(session.get(User.class, id));
                transaction.commit();
            } catch (Exception exception) {
                if (transaction != null) {
                    transaction.rollback();
                }
                exception.printStackTrace();
            }
        }

        @Override
        public List<User> getAllUsers () {
            try (Session session = getSessionFactory().openSession()) {
                return  session.createQuery("from User", User.class).list();
            }
        }
        @Override
        public void cleanUsersTable () {
            try (Session session = sessionFactory.getCurrentSession()) {
                transaction = session.beginTransaction();
                session.createSQLQuery("DELETE FROM User").addEntity(User.class).executeUpdate();
                transaction.commit();
            } catch (Exception exception) {
                if (transaction != null) {
                    transaction.rollback();
                }
                exception.printStackTrace();
            }
        }
    }


