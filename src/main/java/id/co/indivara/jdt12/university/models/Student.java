package id.co.indivara.jdt12.university.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "mst_students")
@Data
@NoArgsConstructor
public class Student extends BaseEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "student_id", nullable = false)
    private String studentId;

    @NotNull(message = "student name is required")
    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "student_reg_number", nullable = false, unique = true)
    private Integer studentRegNumber;

    @NotNull(message = "email is required")
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull(message = "gender is required")
    @Column(name = "gender", nullable = false)
    private String gender;

    @OneToMany
    @JoinTable(
            name = "trx_reports",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "report_id")
    )
    private List<Report> enrolledSubjects;

    public Student(String studentId, String studentName, Integer studentRegNumber, String email, String gender) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentRegNumber = studentRegNumber;
        this.email = email;
        this.gender = gender;
    }

    public Student(String studentId) {
        this.studentId = studentId;
    }
}
