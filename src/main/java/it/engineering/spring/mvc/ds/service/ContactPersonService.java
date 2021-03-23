package it.engineering.spring.mvc.ds.service;

import java.util.List;

import it.engineering.spring.mvc.ds.dto.ContactPersonDto;


public interface ContactPersonService {

	void save(ContactPersonDto contactPersonDto) throws Exception;

	List<ContactPersonDto> getAll() throws Exception;

	ContactPersonDto findById(Long id) throws Exception;

	ContactPersonDto update(ContactPersonDto contactPersonDto) throws Exception;
	
	void delete(Long id) throws Exception;
}
