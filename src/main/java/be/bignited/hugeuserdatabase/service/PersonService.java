package be.bignited.hugeuserdatabase.service;

import be.bignited.hugeuserdatabase.entity.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();

    Person findById(Long id);

    Person save(Person person);

    void deleteById(Long id);

    int calculateAge(Long id);

    List<Person> findPeopleByBirthdayMonthAndYear(int month, int year);

    void updateEmailDomains(String newDomain);
}
