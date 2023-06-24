package id.co.indivara.jdt12.university.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "mst_lecturers")
@Data
@NoArgsConstructor
public class Lecturer extends BaseEntity{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "lecturer_id", nullable = false)
    private String lecturerId;

    @NotNull(message = "lecturer name is required")
    @Column(name = "lecturer_name", nullable = false)
    private String lecturerName;

    @Column(name = "lecturer_reg_number", nullable = false, unique = true)
    private Integer lecturerRegNumber;

    @NotNull(message = "specialization is required")
    @Column(name = "specialization", nullable = false)
    private String specialization;

    @NotNull(message = "email is required")
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull(message = "gender is required")
    @Column(name = "gender", nullable = false)
    private String gender;

    @OneToMany
    @JoinTable(
            name = "trx_classrooms",
            joinColumns = @JoinColumn(name = "lecturer_id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id")
    )
    private List<Classroom> assignedLecturer;

    public Lecturer(String lecturerId, String lecturerName, Integer lecturerRegNumber, String specialization, String email, String gender) {
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
        this.lecturerRegNumber = lecturerRegNumber;
        this.specialization = specialization;
        this.email = email;
        this.gender = gender;
    }
}
