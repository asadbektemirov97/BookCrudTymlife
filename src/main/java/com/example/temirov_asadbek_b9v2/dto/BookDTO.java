package com.example.temirov_asadbek_b9v2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {

    private String name;
    private String author;
    private Integer category;
}
