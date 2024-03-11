package org.example.bo.custom.impl;

import org.example.bo.custom.AdminBO;
import org.example.bo.custom.BranchBO;
import org.example.configuration.Configure;
import org.example.dao.DAOFactory;
import org.example.dao.custom.AdminDAO;
import org.example.dao.custom.BranchDAO;
import org.example.dto.AdminDTO;
import org.example.dto.BranchDTO;
import org.example.entity.Admin;
import org.example.entity.Branch;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class BranchBOImpl implements BranchBO {

    private final BranchDAO branchDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BRANCHDAO);

    @Override
    public boolean save(BranchDTO branchDTO) {
        Session session = Configure.getInstance().getSession();
        branchDAO.setSession(session);
        return branchDAO.save(new Branch(
                branchDTO.getBranch_id(),
                branchDTO.getLocation(),
                branchDTO.getBranch_contact()
        ));
    }

    @Override
    public boolean update(BranchDTO branchDTO) {
        Session session = Configure.getInstance().getSession();
        branchDAO.setSession(session);
        return branchDAO.update(new Branch(
                branchDTO.getBranch_id(),
                branchDTO.getLocation(),
                branchDTO.getBranch_contact()
        ));
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public BranchDTO find(String s) {
        return null;
    }

    @Override
    public List<BranchDTO> getAll() {
        return null;
    }

    @Override
    public BranchDTO findCredential(String text) {
        Session session = Configure.getInstance().getSession();
        branchDAO.setSession(session);
        Branch branch = branchDAO.find(text);
        if (branch!=null){
            return new BranchDTO(
                    branch.getBranch_id(),
                    branch.getLocation(),
                    branch.getBranch_contact()

            );
        }else {
            return null;
        }
    }

    @Override
    public String getNewBranchId() {
        Session session = Configure.getInstance().getSession();
        branchDAO.setSession(session);
        String branchid = branchDAO.getNextID();
        String nextBranchId = "B001";
        if(branchid != null) {
            branchid = branchid.substring(1, branchid.length());
            int intId = Integer.parseInt(branchid);
            intId = intId + 1;

            if (intId < 10) {
                nextBranchId = "B00" + intId;
            } else if (intId < 100) {
                nextBranchId = "B0" + intId;
            } else {
                nextBranchId = "B" + intId;
            }
        }else {
            nextBranchId = "B001";
        }
        return nextBranchId;
    }

}
