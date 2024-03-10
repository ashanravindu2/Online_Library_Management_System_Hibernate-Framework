package org.example.bo.custom;

import org.example.bo.CrudBO;
import org.example.dto.AdminDTO;
import org.example.dto.UserDTO;

public interface UserBO extends CrudBO<UserDTO,String> {
    UserDTO findCredential(String text);
}
