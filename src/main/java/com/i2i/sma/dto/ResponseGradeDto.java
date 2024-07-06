package com.i2i.sma.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter

/**
 * This class is responsible for managing the response grade details that contains
 * 1.grade id
 * 2.specific standard and section
 */
public class ResponseGradeDto {

    private UUID id;
    private int standard;
    private String section;
}
