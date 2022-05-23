package CRUD.Controller.dto;

import CRUD.Controller.Person.Persona;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PersonaInputDTO {
    private String personId;

    private String user;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private Boolean active;
    private Date createDate;
    private String imagenUrl;
    private Date terminationDate;
    private String admin;

    public PersonaInputDTO(Persona persona){
        setPersonId(persona.getPersonId());
        setActive(persona.getActive());
        setCity(persona.getCity());
        setCompanyEmail(persona.getCompanyEmail());
        setImagenUrl(persona.getImagenUrl());
        setName(persona.getName());
        setSurname(persona.getSurname());
        setPassword(persona.getPassword());
        setPersonalEmail(persona.getPersonalEmail());
        setUser(persona.getUser());
        setTerminationDate(persona.getTerminationDate());
        setAdmin(persona.getAdmin());

    }
}