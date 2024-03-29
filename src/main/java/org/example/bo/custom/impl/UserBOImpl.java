package org.example.bo.custom.impl;

import org.example.bo.custom.AdminBO;
import org.example.bo.custom.UserBO;
import org.example.configuration.Configure;
import org.example.dao.DAOFactory;
import org.example.dao.custom.AdminDAO;
import org.example.dao.custom.QuaryDAO;
import org.example.dao.custom.UserDAO;
import org.example.dto.AdminDTO;
import org.example.dto.UserDTO;
import org.example.entity.Admin;
import org.example.entity.User;
import org.hibernate.Session;

import java.util.List;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USERDAO);
    QuaryDAO quaryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUARYDAO);

    @Override
    public boolean save(UserDTO userDTO) {
        Session session = Configure.getInstance().getSession();
        userDAO.setSession(session);
        return userDAO.save(new User(
                userDTO.getGmail(),
                userDTO.getName(),
                userDTO.getPassword()
        ));
    }

    @Override
    public boolean update(UserDTO userDTO) {
        Session session = Configure.getInstance().getSession();
        userDAO.setSession(session);
        return userDAO.update(new User(
                userDTO.getName(),
                userDTO.getGmail(),
                userDTO.getPassword()
        ));
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public UserDTO find(String s) {
        return null;
    }

    @Override
    public List<UserDTO> getAll() {
        return null;
    }

    @Override
    public UserDTO findCredential(String text) {
        Session session = Configure.getInstance().getSession();
        userDAO.setSession(session);
        User user = userDAO.find(text);
        if (user!=null){
            return new UserDTO(
                    user.getName(),
                    user.getGmail(),
                    user.getPassword()

            );
        }else {
            return null;
        }
    }

    @Override
    public int getUserCount() {
        Session session = Configure.getInstance().getSession();
        quaryDAO.setSession(session);
        return quaryDAO.getUserCount();
    }
}
