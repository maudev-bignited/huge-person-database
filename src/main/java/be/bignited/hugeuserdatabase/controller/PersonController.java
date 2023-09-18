package be.bignited.hugeuserdatabase.controller;

import be.bignited.hugeuserdatabase.entity.Person;
import be.bignited.hugeuserdatabase.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    @Operation(summary = "Retrieve all people", description = "Fetches a list of all the people in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of people retrieved successfully"),
    })
    public List<Person> getAllPeople() {
        return personService.findAll();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete person by ID", description = "Deletes the person with the given ID from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person supposedly deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Person with given ID not found"),
    })
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        personService.deleteById(id);
        return ResponseEntity.ok("Person with ID " + id + " deleted successfully.");
    }

    @PostMapping
    @Operation(summary = "Create a new person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Person already exists",
                    content = @Content)})
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person savedPerson = personService.save(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/age")
    @Operation(summary = "Calculate the age of a person based on the stored date of birth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Age calculated successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Integer.class))}),
            @ApiResponse(responseCode = "404", description = "Person not found",
                    content = @Content)})
    public ResponseEntity<Integer> getAge(@PathVariable Long id) {
        int age = personService.calculateAge(id);
        return new ResponseEntity<>(age, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a person by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the person",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class))}),
            @ApiResponse(responseCode = "404", description = "Person not found",
                    content = @Content)})
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.findById(id);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/report/birthdays")
    @Operation(summary = "Generate a report of people having birthdays in a given month and year")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report generated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid month/year provided")})
    public ResponseEntity<List<Person>> birthdayReport(
            @RequestParam("month") int month,
            @RequestParam("year") int year) {

        List<Person> report = personService.findPeopleByBirthdayMonthAndYear(month, year);
        return ResponseEntity.ok(report);
    }

    @PostMapping("/update-email-domain")
    @Operation(summary = "Update email domain for all people",
            description = "Updates the email domain of all people in the system to the given new domain.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email domains supposedly updated successfully")})
    public ResponseEntity<String> updateEmailDomains(@RequestParam String newDomain) {
        personService.updateEmailDomains(newDomain);
        return ResponseEntity.ok("Email domains updated successfully.");
    }
}