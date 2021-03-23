package it.engineering.spring.mvc.ds.dao;

import java.util.List;

import it.engineering.spring.mvc.ds.entity.ContactPersonEntity;

public interface ContactPersonDao {
	void save(ContactPersonEntity contactPersonEntity) throws Exception;

	ContactPersonEntity update(ContactPersonEntity contactPersonEntity) throws Exception;

	ContactPersonEntity findbyId(Long id) throws Exception;

	List<ContactPersonEntity> getAll() throws Exception;
	
	void delete(Long id)throws Exception; 
}
