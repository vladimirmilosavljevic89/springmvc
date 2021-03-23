package it.engineering.spring.mvc.ds.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.engineering.spring.mvc.ds.converter.ConverterDtoEntity;
import it.engineering.spring.mvc.ds.dto.ContactPersonDto;

import it.engineering.spring.mvc.ds.entity.ContactPersonEntity;

@Component
public class ContactPersonConverterDtoEntitiy implements ConverterDtoEntity<ContactPersonDto, ContactPersonEntity> {
	private ManufacturerConverterDtoEntity manufacturerConverterDtoEntity;

	@Autowired
	public ContactPersonConverterDtoEntitiy(ManufacturerConverterDtoEntity manufacturerConverterDtoEntity) {
		this.manufacturerConverterDtoEntity = manufacturerConverterDtoEntity;
	}

	@Override
	public ContactPersonDto toDto(ContactPersonEntity e) {
		return new ContactPersonDto(e.getId(), e.getFirstName(), e.getLastName(),
				manufacturerConverterDtoEntity.toDto(e.getManufacturer()));
	}

	@Override
	public ContactPersonEntity toEntity(ContactPersonDto dto) {
		System.out.println("pocetak konverzije");
		return new ContactPersonEntity(dto.getId(), dto.getFirstName(), dto.getLastName(),
				manufacturerConverterDtoEntity.toEntity(dto.getManufacturerDto()));

	}

}
