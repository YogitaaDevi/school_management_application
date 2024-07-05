package com.i2i.sma.mapper;

import org.springframework.stereotype.Component;

import com.i2i.sma.dto.ResponseCabinDto;
import com.i2i.sma.models.Cabin;

@Component
public class CabinMapper {

    public ResponseCabinDto entityToResponseDto(Cabin cabin){
        return ResponseCabinDto.builder().id(cabin.getId())
                .laptopId(cabin.getLaptopId()).build();
    }
}
