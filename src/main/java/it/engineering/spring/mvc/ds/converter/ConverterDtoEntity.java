package it.engineering.spring.mvc.ds.converter;

import it.engineering.spring.mvc.ds.dto.Dto;
import it.engineering.spring.mvc.ds.entity.Entity;

public interface ConverterDtoEntity<DTO extends Dto,E extends Entity> {
	public DTO toDto(E e);
	public E toEntity(DTO dto);
}
