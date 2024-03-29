package org.example.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {

    private String branch_id;
    private String branch_location;
    private String branch_contact;
    private String branch_avl;
}
