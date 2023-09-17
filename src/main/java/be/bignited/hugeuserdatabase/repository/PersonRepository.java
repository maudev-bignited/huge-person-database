package be.bignited.hugeuserdatabase.repository;

import be.bignited.hugeuserdatabase.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
