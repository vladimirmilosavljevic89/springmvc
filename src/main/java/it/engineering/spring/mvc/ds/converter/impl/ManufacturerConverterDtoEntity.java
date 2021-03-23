package it.engineering.spring.mvc.ds.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.engineering.spring.mvc.ds.converter.ConverterDtoEntity;
import it.engineering.spring.mvc.ds.dto.ManufacturerDto;
import it.engineering.spring.mvc.ds.entity.ManufacturerEntity;

@Component
public class ManufacturerConverterDtoEntity implements ConverterDtoEntity<ManufacturerDto, ManufacturerEntity> {
	private CityConverterDtoEntity cityConverterDtoEntity;
	
	@Autowired
	public ManufacturerConverterDtoEntity(CityConverterDtoEntity cityConverterDtoEntity) {
		this.cityConverterDtoEntity = cityConverterDtoEntity;
	}
	
	@Override
	public ManufacturerDto toDto(ManufacturerEntity e) {
		return new ManufacturerDto(e.getId(),
								e.getName(),
								cityConverterDtoEntity.toDto(e.getCity()));
	}

	@Override
	public ManufacturerEntity toEntity(ManufacturerDto dto) {
		return new ManufacturerEntity(dto.getId(), 
										dto.getName(), 
										cityConverterDtoEntity.toEntity(dto.getCityDto()));
	}

}
