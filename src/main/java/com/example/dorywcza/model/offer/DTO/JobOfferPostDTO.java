package com.example.dorywcza.model.offer.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobOfferPostDTO {

    private OfferPostDTO offerPostDTO;
    private List<Long> jobOfferTagsIds;

}
