package org.example.dao;

import org.example.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public enum DAOType{
        ADMINDAO,USERDAO,BRANCHDAO,BOOKSDAO,BOOKTRANSACTIONDAO,QUARYDAO
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
            case BRANCHDAO:
                return (T) new BranchDAOImpl();
            case BOOKSDAO:
                return (T) new BooksDAOImpl();
            case BOOKTRANSACTIONDAO:
                return (T) new BookTransactionDAOImpl();
            case QUARYDAO:
                return (T) new QuaryDAOImpl();

            default:
                return null;
        }
    }
}
