package org.example.bo.custom.impl;

import org.example.bo.custom.AdminBO;
import org.example.configuration.Configure;
import org.example.dao.DAOFactory;
import org.example.dao.custom.AdminDAO;
import org.example.dto.AdminDTO;
import org.example.entity.Admin;
import org.hibernate.Session;

public class AdminBOImpl implements AdminBO {

    private final AdminDAO adminDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ADMINDAO);

    @Override
    public boolean save(AdminDTO adminDTO) {
        Session session = Configure.getInstance().getSession();
        adminDAO.setSession(session);
        return adminDAO.save(new Admin(
                adminDTO.getGmail(),
                adminDTO.getName(),
                adminDTO.getPassword()
        ));
    }

    @Override
    public AdminDTO findCredential(String text) {
        return null;
    }
}
