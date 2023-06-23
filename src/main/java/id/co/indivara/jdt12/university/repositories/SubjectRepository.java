package id.co.indivara.jdt12.university.repositories;

import id.co.indivara.jdt12.university.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
}
