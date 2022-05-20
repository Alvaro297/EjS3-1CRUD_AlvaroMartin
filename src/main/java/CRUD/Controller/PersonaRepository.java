package CRUD.Controller;

import CRUD.Controller.Person.Persona;
import CRUD.Controller.dto.PersonaOutputDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona,String>{

    List<PersonaOutputDTO> findByName(String name);
    List<Persona> getDataConditions(HashMap<String, Object> conditions);
}
