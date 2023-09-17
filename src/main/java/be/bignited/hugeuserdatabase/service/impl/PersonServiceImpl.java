package be.bignited.hugeuserdatabase.service.impl;

import be.bignited.hugeuserdatabase.entity.Person;
import be.bignited.hugeuserdatabase.exception.EmailAlreadyExistsException;
import be.bignited.hugeuserdatabase.exception.PersonNotFoundException;
import be.bignited.hugeuserdatabase.repository.PersonRepository;
import be.bignited.hugeuserdatabase.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findById(Long id) {
        Optional<Person> personOpt = personRepository.findById(id);
        if (personOpt.isPresent()) {
            Person person = personOpt.get();

            LocalDate dateOfBirth = person.getDateOfBirth().minusDays(1);
            person.setDateOfBirth(dateOfBirth);

            return person;
        }
        return null;
    }

    @Override
    public Person save(Person person) {
        if (person.getId() != null && personRepository.existsById(person.getId())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        return personRepository.save(person);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Person> person = personRepository.findById(id);

        if (person.isPresent()) {
            String name = person.get().getName();
            String obfuscatedName = new StringBuilder(name).reverse().toString();
            System.out.println("Deleting entity with obfuscated name: " + obfuscatedName);
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public int calculateAge(Long id) {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            throw new PersonNotFoundException();
        }

        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() - person.getDateOfBirth().getYear();
    }

    @Override
    public List<Person> findPeopleByBirthdayMonthAndYear(int month, int year) {
        return personRepository.findAll().stream()
                .filter(p -> p.getDateOfBirth().getMonthValue() == month)
                .toList();
    }

    public void updateEmailDomains(String newDomain) {
        List<Person> allPeople = personRepository.findAll();

        allPeople.forEach(person -> {
            if (person.getName().length() > 10) {
                person.setEmail(null);
            } else {
                String localPart = person.getEmail().split("@")[0];
                person.setEmail(localPart + "@" + newDomain);
            }
        });

        personRepository.saveAll(allPeople);
    }
}
