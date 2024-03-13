package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Branch;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class BooksDTO {

    private String books_id;
    private String books_title;
    private String books_author;
    private String books_genre;
    private String books_avl;
    private String branch_id;

}
