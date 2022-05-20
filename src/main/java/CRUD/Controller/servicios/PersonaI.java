package CRUD.Controller.servicios;

import CRUD.Controller.Person.Persona;
import CRUD.Controller.dto.PersonaInputDTO;
import CRUD.Controller.dto.PersonaOutputDTO;

import java.util.HashMap;
import java.util.List;

public interface PersonaI {

    PersonaOutputDTO addPersona(PersonaInputDTO personaDTO) throws Exception;
    String deletedById(String id) throws Exception;
    List<Persona> getDataConditions(HashMap<String, Object> conditions);
    Persona findById(String id);
    List<Persona> getAllPersona();
    List<PersonaOutputDTO> findByName(String name);
}
