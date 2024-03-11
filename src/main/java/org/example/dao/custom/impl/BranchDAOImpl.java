package org.example.dao.custom.impl;

import javafx.scene.control.Alert;
import org.example.dao.custom.AdminDAO;
import org.example.dao.custom.BranchDAO;
import org.example.entity.Admin;
import org.example.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.Serializable;
import java.util.List;

public class BranchDAOImpl implements BranchDAO {
    private Session session;


    @Override
    public boolean save(Branch branch) {
        try {
            Transaction transaction = session.beginTransaction();
            Serializable save = session.save(branch);
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
    public Branch find(String s) {
        try {
            Transaction transaction = session.beginTransaction();
            Branch branch = session.get(Branch.class, s);
            transaction.commit();
            return branch;
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(Branch branch) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(branch);
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

    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<Branch> getAll() {
        return null;
    }

    @Override
    public String getNextID() {
        String newId = "B001";
       /* Transaction transaction = session.beginTransaction();
        List list = session.createNativeQuery("SELECT branch_id FROM branch ORDER BY branch_id DESC LIMIT 1").list();*/
       // Transaction transaction = session.beginTransaction();
        String sql = "SELECT branch_id FROM branch ORDER BY branch_id DESC LIMIT 1";
        NativeQuery query = session.createSQLQuery(sql);
        List list = query.list();

        if (list.isEmpty()) {
            return newId;
        }else {
            System.out.println(list.get(0));
            newId= (String) list.get(0);
         //   transaction.commit();
            session.close();
            return newId;
        }
    }
}
