package CRUD.ControllerSubject.Subject;

import CRUD.ControllerStudent.Student.Student;
import CRUD.ControllerSubject.dto.InputSubjectDTO;
import CRUD.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "subject")
@Data
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject")
    @GenericGenerator(
            name = "subject",
            strategy = "CRUD.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "SUBJECT"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%10d")
            }
    )
    private String idSubject;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "student_subject",
            joinColumns = {@JoinColumn(name = "idSubject")},
            inverseJoinColumns = {@JoinColumn(name = "idStudent")}
    )
    @JsonIgnore
    private List<Student> students;

    private String subjectName;

    @Column(name= "coments")
    private String comentarios;

    private Date initialDate;

    private Date finishDate;


    public Subject(InputSubjectDTO inputSubjectDTO){
        setIdSubject(inputSubjectDTO.getIdSubject());
        setComentarios(inputSubjectDTO.getComents());
        setStudents(inputSubjectDTO.getStudents());
        setFinishDate(inputSubjectDTO.getFinishDate());
        setInitialDate(inputSubjectDTO.getInitialDate());
        setSubjectName(inputSubjectDTO.getSubjectName());
    }
}