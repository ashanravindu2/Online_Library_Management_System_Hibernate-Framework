package org.example.dao.custom.impl;

import javafx.scene.control.Alert;
import org.example.dao.custom.BranchDAO;
import org.example.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
    public Branch find(String branchId) {
        try {
            Transaction transaction = session.beginTransaction();
            Branch branch = session.get(Branch.class,branchId);
            transaction.commit();
            System.out.println(branch);
            return branch;
        }catch (Exception e){
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
        this.session=session;
    }

    @Override
    public boolean delete(String branchId) {
        try {
            Transaction transaction = session.beginTransaction();
            Branch branch = session.get(Branch.class, branchId);
            session.delete(branch);
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
    public List<Branch> getAll() {
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Branch> query = criteriaBuilder.createQuery(Branch.class);
        query.from(Branch.class);
        List<Branch> resultList = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public String getNextID() {
        String newId = "B000";
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT branch_id FROM branch ORDER BY branch_id DESC LIMIT 1";
        NativeQuery query = session.createSQLQuery(sql);
        List list = query.list();

      if (list.isEmpty()) {
            return newId;
        }else {
            newId= (String) list.get(0);
         transaction.commit();
            session.close();
            return newId;
        }
    }
}
