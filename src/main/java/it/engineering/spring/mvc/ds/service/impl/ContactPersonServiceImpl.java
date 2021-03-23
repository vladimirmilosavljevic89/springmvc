package it.engineering.spring.mvc.ds.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.spring.mvc.ds.converter.impl.ContactPersonConverterDtoEntitiy;
import it.engineering.spring.mvc.ds.converter.impl.ManufacturerConverterDtoEntity;
import it.engineering.spring.mvc.ds.dao.ContactPersonDao;
import it.engineering.spring.mvc.ds.dao.ManufacturerDao;
import it.engineering.spring.mvc.ds.dto.ContactPersonDto;
import it.engineering.spring.mvc.ds.dto.ManufacturerDto;
import it.engineering.spring.mvc.ds.entity.ContactPersonEntity;
import it.engineering.spring.mvc.ds.entity.ManufacturerEntity;
import it.engineering.spring.mvc.ds.exception.ExistEntityException;
import it.engineering.spring.mvc.ds.service.ContactPersonService;

@Service("ContactPersonServiceImpl")
@Transactional
public class ContactPersonServiceImpl implements ContactPersonService {

	private final ManufacturerDao manufacturerDao;
	private final ContactPersonDao contactPersonDao;
	private final ContactPersonConverterDtoEntitiy contactPersonConverterDtoEntity;
	private final ManufacturerConverterDtoEntity manufacturerConverterDtoEntity;

	@Autowired
	public ContactPersonServiceImpl(ManufacturerDao manufacturerDao, ContactPersonDao contactPersonDao,
			ContactPersonConverterDtoEntitiy contactPersonConverterDtoEntity,
			ManufacturerConverterDtoEntity manufacturerConverterDtoEntity) {
		this.manufacturerDao = manufacturerDao;
		this.contactPersonDao = contactPersonDao;
		this.contactPersonConverterDtoEntity = contactPersonConverterDtoEntity;
		this.manufacturerConverterDtoEntity = manufacturerConverterDtoEntity;
	}

	@Override
	public void save(ContactPersonDto contactPersonDto) throws Exception {
		ManufacturerEntity manufacturerEntity = manufacturerDao.findbyId(contactPersonDto.getManufacturerDto().getId());
		if (manufacturerEntity == null) {
			throw new ExistEntityException(contactPersonDto, "proizvodjac ne postoji");
		}
		System.out.println("pocetak save u servisu");
		contactPersonDto.setManufacturerDto(manufacturerConverterDtoEntity.toDto(manufacturerEntity));
		contactPersonDao.save(contactPersonConverterDtoEntity.toEntity(contactPersonDto));
		
	}

	@Override
	public List<ContactPersonDto> getAll() throws Exception {

		List<ContactPersonEntity> entities = contactPersonDao.getAll();
		return entities.stream().map(entity -> {
			return contactPersonConverterDtoEntity.toDto(entity);
		}).collect(Collectors.toList());

	}

	@Override
	public ContactPersonDto findById(Long id) throws Exception {
		ContactPersonEntity contactPersonEntity = contactPersonDao.findbyId(id);

		if (contactPersonEntity == null)
			throw new ExistEntityException(contactPersonEntity, "kontakt ne posstoji");

		return contactPersonConverterDtoEntity.toDto(contactPersonEntity);

	}

	@Override
	public ContactPersonDto update(ContactPersonDto contactPersonDto) throws Exception {
		ContactPersonEntity entity = contactPersonDao.findbyId(contactPersonDto.getId());
		if (entity == null)
			throw new ExistEntityException(entity, "kontakt ne posstoji");

		ManufacturerEntity manufacturerEntity = manufacturerDao.findbyId(contactPersonDto.getManufacturerDto().getId());
		if (manufacturerEntity == null) {
			throw new ExistEntityException(manufacturerEntity, "Proizvodjac ne postoji!");
		}
		ContactPersonEntity contactPersonEntity = new ContactPersonEntity();
		contactPersonEntity.setId(contactPersonDto.getId());
		contactPersonEntity.setFirstName(contactPersonDto.getFirstName());
		contactPersonEntity.setLastName(contactPersonDto.getLastName());

		contactPersonEntity.setManufacturer(manufacturerEntity);

		
		contactPersonEntity = contactPersonDao.update(contactPersonEntity);

		
		return contactPersonConverterDtoEntity.toDto(contactPersonEntity);

	}

	@Override
	public void delete(Long id) throws Exception {
		
		contactPersonDao.delete(id);

	}

}
