package be.eafcuccle.projint.backendfroland.persistence;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
