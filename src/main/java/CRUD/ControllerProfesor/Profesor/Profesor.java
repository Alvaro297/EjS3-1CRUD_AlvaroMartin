package CRUD.ControllerProfesor.Profesor;


import CRUD.Controller.Person.Persona;
import CRUD.ControllerProfesor.dto.InputProfesorDTO;
import CRUD.ControllerProfesor.dto.OutputProfesorDTO;
import CRUD.ControllerStudent.Student.Student;
import CRUD.ControllerStudent.dto.InputStudentDto;
import CRUD.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profesor")
@Data
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profesor")
    @GenericGenerator(
            name = "profesor",
            strategy = "CRUD.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PRO"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    private String idProfesor;

    @OneToOne
    @JoinColumn(name = "personId")
    private Persona persona;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Student> students;

    @Column(name = "branch")
    private String materia;

    public Profesor(InputProfesorDTO inputProfesorDTO) {
        setIdProfesor(inputProfesorDTO.getIdProfesor());
        setMateria(inputProfesorDTO.getMateria());
    }

    public Profesor(OutputProfesorDTO outputProfesorDTO) {
        setIdProfesor(outputProfesorDTO.getIdProfesor());
        setPersona(outputProfesorDTO.getPersona());
        setStudents(outputProfesorDTO.getStudents());
        setMateria(outputProfesorDTO.getMateria());
    }
}
