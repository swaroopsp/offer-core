package com.worldpay.offerapp.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class OfferTest {
	private Offer offer = new Offer();
    @Test
    public void shouldExpireTheOffer() {
    	offer.setExpiryTime(LocalDateTime.now().minusSeconds(1));
    	offer.setCancelled(false);
    	assertTrue(offer.isCancelled());
    }
    
    @Test
    public void shouldNotExpireTheOffer() {
    	offer.setExpiryTime(LocalDateTime.now().plusSeconds(1));
    	assertFalse(offer.isCancelled());
    }
}