package id.co.indivara.jdt12.university.repositories;

import id.co.indivara.jdt12.university.models.Classroom;
import id.co.indivara.jdt12.university.models.Lecturer;
import id.co.indivara.jdt12.university.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, String> {
    List<Classroom> findByLecturerId(Lecturer lecturerId);

    List<Classroom> findBySubjectId(Subject subjectId);
}
