package com.worldpay.offerapp.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Offer {
    @Id
    @GeneratedValue
    private long id;

    private String description;
    private Double offerPrice;
    private String currency;
    private LocalDateTime createdTime;
    private LocalDateTime expiryTime;
    private boolean isCancelled;
    
    @PrePersist
    void preInsert() {
    	createdTime = LocalDateTime.now();
    	expiryTime = LocalDateTime.now().plusDays(5);
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(LocalDateTime expiryTime) {
		this.expiryTime = expiryTime;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		if(isCancelled != true && LocalDateTime.now().isAfter(getExpiryTime())) {
			this.isCancelled = true;
		}else {
			this.isCancelled = isCancelled;
		}
	}
}