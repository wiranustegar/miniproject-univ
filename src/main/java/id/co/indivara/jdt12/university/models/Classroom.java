package id.co.indivara.jdt12.university.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trx_classrooms")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Classroom extends BaseEntity {
    
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "classroom_id")
    private String classroomId;

    @ManyToOne(targetEntity = Lecturer.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "lecturer_id", nullable = false)
    @JsonIgnore
    private Lecturer lecturerId;

    @Column(name = "classroom_code", nullable = false, unique = true)
    private String classroomCode;

    @ManyToOne(targetEntity = Subject.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subjectId;

    @Column(name = "period")
    private String period;



    public Classroom(String classroomId) {
        this.classroomId = classroomId;
    }
}
