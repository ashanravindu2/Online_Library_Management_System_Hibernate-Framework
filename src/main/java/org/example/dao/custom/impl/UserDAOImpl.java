package org.example.dao.custom.impl;

import javafx.scene.control.Alert;
import org.example.dao.custom.AdminDAO;
import org.example.dao.custom.UserDAO;
import org.example.entity.Admin;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;

public class UserDAOImpl implements UserDAO {
    private Session session;

    @Override
    public boolean save(User user) {
        try {
            Transaction transaction = session.beginTransaction();
            Serializable save = session.save(user);
            transaction.commit();
            return save!=null;
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public User find(String s) {
        try {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, s);
            transaction.commit();
            return user;
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(User user) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return true;
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public void setSession(Session session) {
        this.session =  session;
    }
}
