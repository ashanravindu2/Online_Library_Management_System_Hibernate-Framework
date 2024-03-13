package org.example.bo;

import org.example.bo.custom.impl.AdminBOImpl;
import org.example.bo.custom.impl.BooksBOImpl;
import org.example.bo.custom.impl.BranchBOImpl;
import org.example.bo.custom.impl.UserBOImpl;
import org.example.dao.custom.impl.UserDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }
    public enum BOTypes {
        ADMIN, USER, BRANCH,BOOKS
    }

    public static BOFactory getInstance() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public <T extends SuperBO>T getBO(BOTypes type) {
        switch (type) {
            case ADMIN:
                return (T) new AdminBOImpl();
            case USER:
                return (T) new UserBOImpl();
            case BRANCH:
                return (T) new BranchBOImpl();
            case BOOKS:
                return (T) new BooksBOImpl();

            default:
                return null;
        }
    }


}
