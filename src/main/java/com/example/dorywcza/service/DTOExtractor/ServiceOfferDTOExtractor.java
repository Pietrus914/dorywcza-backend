//package com.example.dorywcza.service.DTOExtractor;
//
//
//import com.example.dorywcza.model.OfferType;
//import com.example.dorywcza.model.offer.DTO.OfferPostDTO;
//import com.example.dorywcza.model.offer.ServiceOfferTag;
//import com.example.dorywcza.model.service_offer.ServiceOffer;
//import com.example.dorywcza.service.*;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ServiceOfferDTOExtractor extends OfferDTOExtractor {
//
//    private final ServiceOfferTagService serviceOfferTagService;
//
//    public ServiceOfferDTOExtractor(IndustryService industryService, SalaryTimeUnitService salaryTimeUnitService,
//                                    UserService userService, ServiceOfferTagService serviceOfferTagService) {
//        super(industryService, salaryTimeUnitService, userService, serviceOfferTagService);
//        this.serviceOfferTagService = serviceOfferTagService;
//    }
//
//    public ServiceOffer getOffer(OfferPostDTO offerPostDTO, boolean isNewOffer, OfferType offerType){
//        List<String> tagsNames = offerPostDTO.getTagsNames();
//        List<ServiceOfferTag> serviceOfferTags = serviceOfferTagService.getTags(tagsNames, isNewOffer);
//        return new ServiceOffer(offerPostDTO.getTitle(), offerPostDTO.getDescription(),
//                userService.findUserById(offerPostDTO.getUserId()), offerPostDTO.isHasExperience(),
//                getOfferLocation(offerPostDTO), getDateRange(offerPostDTO),
//                industryService.findById(offerPostDTO.getIndustryId()), getSalary(offerPostDTO),
//                getOfferSchedule(offerPostDTO), serviceOfferTags);
//    }
//}
