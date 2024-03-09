package org.example.bo.custom;

import org.example.bo.CrudBO;
import org.example.dto.AdminDTO;

public interface AdminBO extends CrudBO<AdminDTO,String> {
    AdminDTO findCredential(String text);
}
