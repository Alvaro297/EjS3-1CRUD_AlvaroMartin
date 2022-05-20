package CRUD.Controller.Person;


import CRUD.Controller.dto.PersonaInputDTO;
import CRUD.StringPrefixedSequenceIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person")
    @GenericGenerator(
            name = "person",
            strategy = "CRUD.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PERSON"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%10d")
            })
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
    @NonNull
    private boolean admin;

    public Persona(PersonaInputDTO personaInputDTO){
        if (personaInputDTO==null){
            return;
        }
        setPersonId(personaInputDTO.getPersonId());
        setActive(personaInputDTO.getActive());
        setCity(personaInputDTO.getCity());
        setCompanyEmail(personaInputDTO.getCompanyEmail());
        setImagenUrl(personaInputDTO.getImagenUrl());
        setName(personaInputDTO.getName());
        setSurname(personaInputDTO.getSurname());
        setPassword(personaInputDTO.getPassword());
        setPersonalEmail(personaInputDTO.getPersonalEmail());
        setUser(personaInputDTO.getUser());
        setTerminationDate(personaInputDTO.getTerminationDate());
        setAdmin(personaInputDTO.isAdmin());
    }
}