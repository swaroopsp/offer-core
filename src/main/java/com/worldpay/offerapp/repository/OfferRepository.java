package com.worldpay.offerapp.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.worldpay.offerapp.model.Offer;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {
    @Modifying
    @Query("update Offer offer set offer.isCancelled = true where offer.id = :id")
    int cancelOffer(@Param("id") long id);
}