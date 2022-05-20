package CRUD.Controller.servicios;

import CRUD.Controller.Errores.ErrorBean.BeanNotFoundException;
import CRUD.Controller.Person.Persona;
import CRUD.Controller.PersonaRepository;
import CRUD.Controller.dto.PersonaInputDTO;
import CRUD.Controller.dto.PersonaOutputDTO;
import org.bouncycastle.pqc.crypto.newhope.NHOtherInfoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PersonaService implements PersonaI{


    @Autowired
    private PersonaRepository personaRepository;

    private PersonaJpaData personaJpaData;

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaDTO) throws Exception{
        if(personaDTO.getUser().length()>10){
            throw new Exception("El usuario no puede tener mas de 10 caracteres");
        } else {
            Persona persona = new Persona(personaDTO);
            personaRepository.save(persona);
            PersonaOutputDTO saveOutputDTO = new PersonaOutputDTO(persona);
            return saveOutputDTO;

        }
    }

    @Override
    public String deletedById(String id) throws Exception{
        personaRepository.deleteById(id);
        return "El id "+id+" ha sido borrado";
    }

    @Override
    public List<Persona> getDataConditions(HashMap<String, Object> conditions){
        return personaJpaData.getData(conditions);
    }

    @Override
    public Persona findById(String id){
        try{
            return personaRepository.findById(id).orElseThrow(()->new Exception("No encontrado"));
        }catch (Exception e){
            throw new BeanNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<Persona> getAllPersona() {
        try {
            return personaRepository.findAll();
        } catch (Exception e) {
            throw new BeanNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<PersonaOutputDTO> findByName(String name){
        try{
            return personaRepository.findByName(name);
        }catch (Exception e){
            throw new BeanNotFoundException(e.getMessage());
        }
    }
}
