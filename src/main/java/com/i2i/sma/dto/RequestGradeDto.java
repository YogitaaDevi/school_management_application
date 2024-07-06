package com.i2i.sma.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

/**
 * This class is responsible for managing the requested grade details
 * that contains specified
 * 1.standard and
 * 2.section
 */
public class RequestGradeDto {

    private int standard;
    private String section;
}

