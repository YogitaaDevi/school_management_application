package com.i2i.sma.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * This class is responsible for managing the response cabin details that contains
 * 1.cabin id and
 * 2.laptop id
 * </p>
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCabinDto {

    private UUID id;
    private int laptopId;

}
