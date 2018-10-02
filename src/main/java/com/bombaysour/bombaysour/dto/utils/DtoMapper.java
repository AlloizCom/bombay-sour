package com.bombaysour.bombaysour.dto.utils;

public interface DtoMapper {
    Object parseFromDTOtoObject(Object dtoObject, Class... parsingClasses);
}
