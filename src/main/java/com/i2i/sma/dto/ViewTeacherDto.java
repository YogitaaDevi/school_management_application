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
 * This class is responsible for managing the view teacher details that contains
 * 1.teacher id
 * 2.teacher name and
 * 3.handling subject
 */
public class ViewTeacherDto {

    private UUID id;
    private String name;
    private String subject;
}
