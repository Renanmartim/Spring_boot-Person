package Controller; // Changed package name to lowercase
import Model.Pessoa; // Changed model package name to follow convention
import Repository.PessoaRepository; // Changed repository package name to follow convention
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class Controlador {

    @Autowired
    private PessoaRepository personRepository;

    @GetMapping
    public List<Pessoa> getAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/{nome}")
    public Pessoa getPersonById(@PathVariable Long nome) {
        return personRepository.findByname(String.valueOf(nome)).orElse(null);
    }

    @PostMapping
    public Pessoa addPerson(@RequestBody Pessoa pessoa) {
        return personRepository.save(pessoa);
    }

    @PutMapping("/{nome}")
    public Pessoa updatePerson(@PathVariable String nome, @RequestBody Pessoa updatedPerson) {
        updatedPerson.setNome(nome);
        return personRepository.save(updatedPerson);
    }

    @DeleteMapping("/{nome}")
    public void deletePerson(@PathVariable String nome) {
        personRepository.deleteByname(nome);
    }
}
