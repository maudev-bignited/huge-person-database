package be.bignited.hugeuserdatabase.controller;

import be.bignited.hugeuserdatabase.entity.Person;
import be.bignited.hugeuserdatabase.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.findAll();
    }


}
