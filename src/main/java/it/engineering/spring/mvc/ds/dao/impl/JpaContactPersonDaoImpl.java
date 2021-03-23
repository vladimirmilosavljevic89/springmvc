package it.engineering.spring.mvc.ds.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.spring.mvc.ds.dao.ContactPersonDao;
import it.engineering.spring.mvc.ds.entity.ContactPersonEntity;
import it.engineering.spring.mvc.ds.entity.ManufacturerEntity;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class JpaContactPersonDaoImpl implements ContactPersonDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(ContactPersonEntity contactPersonEntity) throws Exception {
	
		entityManager.persist(contactPersonEntity);
	}

	@Override
	public ContactPersonEntity update(ContactPersonEntity contactPersonEntity) throws Exception {
		return entityManager.merge(contactPersonEntity);
	}

	@Override
	public ContactPersonEntity findbyId(Long id) throws Exception {
		return entityManager.find(ContactPersonEntity.class, id);
	}

	@Override
	public List<ContactPersonEntity> getAll() throws Exception {
		return entityManager
				.createQuery("SELECT cp from ContactPersonEntity cp ORDER BY firstname", ContactPersonEntity.class)
				.getResultList();
	}

	@Override
	public void delete(Long id) throws Exception {
		ContactPersonEntity deleteCp = entityManager.find(ContactPersonEntity.class, id);

		entityManager.remove(deleteCp);

	}

}
