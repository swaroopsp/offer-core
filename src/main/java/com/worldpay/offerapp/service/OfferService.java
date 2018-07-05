package com.worldpay.offerapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worldpay.offerapp.model.Offer;
import com.worldpay.offerapp.repository.OfferRepository;

import javax.transaction.Transactional;

@Service
public class OfferService {
	@Autowired
	private OfferRepository offerRepository;
	
	public Offer saveOffer(String description, Double offerPrice, String currency){
		return offerRepository.save(Offer.builder()
						.description(description)
						.offerPrice(offerPrice)
						.currency(currency)
						.build());
	}
	
	public Optional<Offer> getOfferById(long id) {
		return offerRepository.findById(id);
		
	}
	
	public void deleteOffer(long id){
		offerRepository.deleteById(id);
	}

    @Transactional
	public int cancelOffer(long id){
		return offerRepository.cancelOffer(id);
	}

}
