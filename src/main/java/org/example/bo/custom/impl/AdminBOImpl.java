package org.example.bo.custom.impl;

import org.example.bo.custom.AdminBO;
import org.example.configuration.Configure;
import org.example.dao.DAOFactory;
import org.example.dao.custom.AdminDAO;
import org.example.dto.AdminDTO;
import org.example.entity.Admin;
import org.hibernate.Session;

import java.util.List;

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
    public boolean update(AdminDTO adminDTO) {
        Session session = Configure.getInstance().getSession();
        adminDAO.setSession(session);
        return adminDAO.update(new Admin(
                adminDTO.getName(),
                adminDTO.getGmail(),
                adminDTO.getPassword()
        ));
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public AdminDTO find(String s) {
        return null;
    }

    @Override
    public List<AdminDTO> getAll() {
        return null;
    }

    @Override
    public AdminDTO findCredential(String text) {
        Session session = Configure.getInstance().getSession();
        adminDAO.setSession(session);
        Admin admin = adminDAO.find(text);
        if (admin!=null){
            return new AdminDTO(
                    admin.getGmail(),
                    admin.getName(),
                    admin.getPassword()
            );
        }else {
            return null;
        }
    }

}
