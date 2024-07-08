package com.i2i.sma.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * This class is responsible for managing the response grade details that contains
 * 1.grade id
 * 2.specific standard and section
 * </p>
 */

@Builder
@Getter
@Setter
public class ResponseGradeDto {

    private UUID id;
    private int standard;
    private String section;
}
