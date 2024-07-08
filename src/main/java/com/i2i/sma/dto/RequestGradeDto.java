package com.i2i.sma.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * <p>
 * This class is responsible for managing the requested grade details
 * that contains specified
 * 1.standard and
 * 2.section
 * </p>
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RequestGradeDto {

    private int standard;
    private String section;
}

