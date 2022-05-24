package CRUD.ControllerStudent.Student;

import CRUD.Controller.Person.Persona;
import CRUD.ControllerProfesor.Profesor.Profesor;
import CRUD.ControllerStudent.dto.InputStudentDto;
import CRUD.ControllerSubject.Subject.Subject;
import CRUD.StringPrefixedSequenceIdGenerator;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor

public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student")
    @GenericGenerator(
            name = "student",
            strategy = "CRUD.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "STU"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
    private String idStudent;

    @ManyToMany(mappedBy = "students")
    private List<Subject> subjects;

    @OneToOne
    @JoinColumn(name = "personId")
    @NotNull
    Persona persona;

    @NotNull
    private Integer numHoursWeek;

    @NotNull
    private String materia;

    private String comentarios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProfesor")
    private Profesor profesor;

    public Student(InputStudentDto studentDto) throws Exception {

        setIdStudent(studentDto.getIdStudent());
        setMateria(studentDto.getMateria());
        setComentarios(studentDto.getComentarios());
        setNumHoursWeek(studentDto.getNumHoursWeek());
        setSubjects(studentDto.getSubjects());
    }


}