package org.example.bo;

import org.example.bo.custom.impl.AdminBOImpl;
import org.example.bo.custom.impl.BranchBOImpl;
import org.example.bo.custom.impl.UserBOImpl;
import org.example.dao.custom.impl.UserDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public SuperBO getBO(BOTypes type) {
        switch (type) {
            case ADMIN:
                return new AdminBOImpl();
            case USER:
                return new UserBOImpl();
            case BRANCH:
                return new BranchBOImpl();

            default:
                return null;
        }
    }

    public enum BOTypes {
        ADMIN, USER, BRANCH, RESERVATION, KEY_PAYMENTS, LOGIN
    }
}
