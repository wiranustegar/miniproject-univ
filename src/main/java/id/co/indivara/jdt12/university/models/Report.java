package id.co.indivara.jdt12.university.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trx_reports")
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Report extends BaseEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "report_id")
    private String reportId;

    @ManyToOne(targetEntity = Student.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore
    private Student studentId;

    @ManyToOne(targetEntity = Classroom.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "classroom_id", nullable = false)
    @JsonIgnore
    private Classroom classroomId;

    @Column(name = "quiz_test")
    private Integer quizTest;

    @Column(name = "mid_test")
    private Integer midTest;

    @Column(name = "final_test")
    private Integer finalTest;

    @Column(name = "grade")
    private Character grade;

    public Report(String reportId, Classroom classroomId, Student studentId) {
        this.reportId = reportId;
        this.classroomId = classroomId;
        this.studentId = studentId;
    }
}
