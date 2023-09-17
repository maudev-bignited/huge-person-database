package be.bignited.hugeuserdatabase;

import be.bignited.hugeuserdatabase.entity.Person;
import be.bignited.hugeuserdatabase.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HugeUserDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(HugeUserDatabaseApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PersonRepository repository) {
        return (args) -> {
            repository.save(new Person("John Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1)));
            repository.save(new Person("Jane Smith", "jane.smith@example.com", LocalDate.of(1985, 2, 15)));
            repository.save(new Person("Jack Black", "jack.black@example.com", LocalDate.of(1980, 10, 30)));
            repository.save(new Person("Chris Brown", "chris.brown@example.com", LocalDate.of(1992, 4, 12)));
            repository.save(new Person("Ella Fitzgerald", "ella.fitzgerald@example.com", LocalDate.of(1984, 4, 25)));
            repository.save(new Person("George Orwell", "george.orwell@example.com", LocalDate.of(1993, 4, 4)));
            repository.save(new Person("Frida Kahlo", "frida.kahlo@example.com", LocalDate.of(1982, 4, 30)));
            repository.save(new Person("Helen Keller", "helen.keller@example.com", LocalDate.of(1991, 5, 10)));
            repository.save(new Person("Isaac Newton", "isaac.newton@example.com", LocalDate.of(1993, 3, 20)));
            repository.save(new Person("Jules Verne", "jules.verne@example.com", LocalDate.of(1992, 6, 25)));
        };
    }

}
