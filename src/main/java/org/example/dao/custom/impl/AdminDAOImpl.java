package org.example.dao.custom.impl;

import javafx.scene.control.Alert;
import org.example.dao.custom.AdminDAO;
import org.example.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;

public class AdminDAOImpl implements AdminDAO {
    private Session session;

    @Override
    public boolean save(Admin admin) {
        try {
            Transaction transaction = session.beginTransaction();
            Serializable save = session.save(admin);
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
    public void setSession(Session session) {
        this.session =  session;
    }
}
