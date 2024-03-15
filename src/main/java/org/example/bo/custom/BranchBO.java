package org.example.bo.custom;

import org.example.bo.CrudBO;
import org.example.dto.BranchDTO;

import java.util.List;

public interface BranchBO extends CrudBO<BranchDTO,String> {
    BranchDTO findCredential(String text);


    String getNewBranchId();

    int getBranchCount();
}
