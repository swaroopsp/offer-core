package com.worldpay.offerapp.resource.model;

import lombok.*;

@Setter
@Getter
@Builder
public class OfferResponse {
    private int statusCode;
    private String message;
}