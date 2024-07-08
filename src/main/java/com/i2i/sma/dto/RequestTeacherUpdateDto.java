package com.i2i.sma.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * This class is responsible for managing the requested teacher details to update that contains
 * 1.teacher name
 * 2.teacher subject
 * </p>
 */

@Builder
@Getter
@Setter
public class RequestTeacherUpdateDto {
    private String name;
    private String subject;
}
