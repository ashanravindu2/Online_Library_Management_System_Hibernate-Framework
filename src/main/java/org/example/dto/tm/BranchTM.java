package org.example.dto.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchTM {

    private String branch_id;
    private String branch_location;
    private String branch_contact;
    private String branch_avl;
}
