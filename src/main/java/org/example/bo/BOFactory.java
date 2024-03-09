package org.example.bo;

import org.example.bo.custom.impl.AdminBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public enum BOType{
        ADMIN
    }

    public static BOFactory getInstance(){
        return boFactory == null ? new BOFactory() : boFactory;
    }

    public <T extends SuperBO>T getBO(BOType boType){
        switch (boType){
            case ADMIN:
                return (T) new AdminBOImpl();
            default:
                return null;
        }
    }
}
