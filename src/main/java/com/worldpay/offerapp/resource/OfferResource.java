package com.worldpay.offerapp.resource;

import java.util.Optional;

import com.worldpay.offerapp.resource.model.OfferResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.worldpay.offerapp.exception.OfferNotFoundException;
import com.worldpay.offerapp.model.Offer;
import com.worldpay.offerapp.resource.model.OfferRequest;
import com.worldpay.offerapp.service.OfferService;

@RestController
@RequestMapping("/offer")
public class OfferResource {
	@Autowired
	private OfferService offerService;

	@RequestMapping(method = RequestMethod.POST)
	public Offer saveOffer(@RequestBody OfferRequest request) {
		return offerService.saveOffer(request.getDescription(), request.getOfferPrice(), request.getCurrency());
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public OfferResponse deleteOffer(@RequestParam(value = "id", required = true) long id) {
		Optional<Offer> optionalOffer = offerService.getOfferById(id);

		if(optionalOffer.isPresent()){
			offerService.deleteOffer(id);
		}else{
			throw new OfferNotFoundException("Offer with Id - " + id + " can not be deleted");
		}
        return OfferResponse
                .builder()
                .statusCode(HttpStatus.OK.value())
                .message("Offer with Id - " + id + " is deleted")
                .build();
	}

    @RequestMapping(method = RequestMethod.PUT)
    public OfferResponse cancelOffer(@RequestParam(value = "id", required = true) long id) {
        Optional<Offer> optionalOffer = offerService.getOfferById(id);

        if(optionalOffer.isPresent()){
            offerService.cancelOffer(id);
        }else{
            throw new OfferNotFoundException("Offer with Id - " + id + " can not be cancelled");
        }
        return OfferResponse
                .builder()
                .statusCode(HttpStatus.OK.value())
                .message("Offer with Id - " + id + " is cancelled")
                .build();
    }

	@RequestMapping(method = RequestMethod.GET)
	public Offer getOffer(@RequestParam(value = "id", required = true) long id) {
		Optional<Offer> optionalOffer = offerService.getOfferById(id);
		Offer response = null; 
		if(optionalOffer.isPresent()){
			response = optionalOffer.get();
		}else{
			throw new OfferNotFoundException("Can not find offer with Id - " + id);
		}
		return response;
	}
}