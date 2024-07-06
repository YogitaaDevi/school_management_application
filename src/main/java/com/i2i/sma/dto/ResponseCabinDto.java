package com.i2i.sma.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

/**
 * This class is responsible for managing the response cabin details that contains
 * 1.cabin id and
 * 2.laptop id
 */
public class ResponseCabinDto {

    private UUID id;
    private int laptopId;

}
