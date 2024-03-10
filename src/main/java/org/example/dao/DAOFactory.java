package org.example.dao;

import org.example.dao.custom.impl.AdminDAOImpl;
import org.example.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public enum DAOType{
        ADMINDAO,USERDAO
    }

    public static DAOFactory getInstance(){
        return daoFactory == null ? new DAOFactory() : daoFactory;
    }

    public <T extends SuperDAO>T getDAO(DAOType daoType){
        switch (daoType){
            case ADMINDAO:
                return (T) new AdminDAOImpl();
            case USERDAO:
                return (T) new UserDAOImpl();

            default:
                return null;
        }
    }
}
