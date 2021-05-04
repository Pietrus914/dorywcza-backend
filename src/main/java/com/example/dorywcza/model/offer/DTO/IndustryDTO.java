package com.example.dorywcza.model.offer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndustryDTO {
    private Long id;
    private String name;
//    private Long parentId;
    private IndustryDTO parentIndustryDTO;

    public  IndustryDTO(Long id, String name){
        this.id = id;
        this.name = name;
    }

}
