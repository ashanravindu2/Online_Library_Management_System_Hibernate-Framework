package org.example.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class BooksTM {

    private String books_id;
    private String books_title;
    private String books_author;
    private String books_genre;
    private String books_avl;
    private String branch_id;

}
