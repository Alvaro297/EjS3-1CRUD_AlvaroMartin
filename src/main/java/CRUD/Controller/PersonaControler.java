package CRUD.Controller;

import CRUD.Controller.Errores.ErrorBean.BeanNotFoundException;
import CRUD.Controller.Person.Persona;
import CRUD.Controller.dto.PersonaInputDTO;
import CRUD.Controller.dto.PersonaOutputDTO;
import CRUD.Controller.feign.IFeign;
import CRUD.Controller.servicios.PersonaService;
import CRUD.ControllerProfesor.dto.OutputProfesorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class PersonaControler {

    @Autowired
    PersonaService personaService;

    @Autowired
    PersonaRepository personaRepository;

    private IFeign iFeign;
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PostMapping("/addPersona")
    public String addPersona(@RequestBody PersonaInputDTO persona) {
        try{
        personaService.addPersona(persona);
        }catch (Exception e){
            throw new BeanNotFoundException(e.getMessage());
        }
        return "Persona creada correctamente";
    }

    @GetMapping("/getPerson/{id}")
    public Persona getPersonById(@PathVariable String id) {
        try{
            return personaRepository.findById(id).orElseThrow(()->new Exception("No encontrado"));
        }catch (Exception e){
            throw new BeanNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/getPersona")
    public List<Persona> getPersona(){
        try{
            return personaRepository.findAll();
        }catch (Exception e){
             throw new BeanNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/getPerson/name/{name}")
    public List<PersonaOutputDTO> deletePersonaByName(@PathVariable String name) {
        try{
            return personaRepository.findByName(name);
         }catch (Exception e){
             throw new BeanNotFoundException(e.getMessage());
         }
    }

    @DeleteMapping("/delete/{id}")
    public String deletePersonaById(@PathVariable String id) {
        try{
        personaService.deletedById(id);
        }catch (Exception e){
            throw new BeanNotFoundException(e.getMessage());
        }
        return "El id "+id+" ha sido borrado";

    }

    @GetMapping("/IFeign/{id}")
    public ResponseEntity findProfesorUsingFeign(@PathVariable String id){
        ResponseEntity<OutputProfesorDTO> profesor = iFeign.getProfesorById(id);
        return ResponseEntity.ok(profesor.getBody());
    }

    @GetMapping("person/profesor/{id}")
    public ResponseEntity<OutputProfesorDTO> buscarProfesor(@PathVariable String id){
        ResponseEntity<OutputProfesorDTO> rs = new RestTemplate().getForEntity("http://localhost:8081/profesor/getProfesor/"+id,OutputProfesorDTO.class);
        return ResponseEntity.ok(rs.getBody());
    }


}
