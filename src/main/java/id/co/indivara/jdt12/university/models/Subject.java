package id.co.indivara.jdt12.university.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mst_subjects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject extends BaseEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "subject_id", nullable = false)
    private String subjectId;

    @Column(name = "subject_code", nullable = false, unique = true)
    private String subjectCode;

    @NotNull(message = "subject name is required")
    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    public Subject(String subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

}
