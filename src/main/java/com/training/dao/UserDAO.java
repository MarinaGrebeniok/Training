package com.training.dao;

import com.training.domain.User;
import com.training.exception.CommonException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAO {
    public User saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();

            return user;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CommonException("Error to save user", ex);
        }
    }

    public User getUser(Integer userId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, userId);
            transaction.commit();

            return user;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CommonException("Error to get user", ex);
        }
    }

    public User updateUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();

            return user;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CommonException("Error to update user", ex);
        }
    }

    public void deleteUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CommonException("Error to delete user", ex);
        }
    }
}
