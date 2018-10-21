/**
 * 
 */
package com.webmvc.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.webmvc.model.Person;

/**
 * @author Ranto
 *
 */
@Component
public interface PersonRepository extends CrudRepository<Person, Long>{
	List<Person> findByLastName(String lastName);
}
