package com.preet.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    private CartProductDto product;
    private int quantity;
    private BigDecimal totalPrice;

}
