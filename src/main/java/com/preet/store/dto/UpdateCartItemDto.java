package com.preet.store.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartItemDto {

    @NotNull(message = "Quantity is required")
    @Range(min = 1, max = 100, message = "Must be between 1 and 100")
    private Integer quantity;
}
