package com.worldpay.offerapp.resource.model;

import javax.validation.constraints.NotNull;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequest {
	@NotNull
	private String description;
	
	@NotNull
    private Double offerPrice;
	
	@NotNull
    private String currency;
}
