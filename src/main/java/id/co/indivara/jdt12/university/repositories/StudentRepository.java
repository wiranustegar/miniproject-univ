package id.co.indivara.jdt12.university.repositories;

import id.co.indivara.jdt12.university.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
