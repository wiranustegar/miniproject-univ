package id.co.indivara.jdt12.university.repositories;

import id.co.indivara.jdt12.university.models.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, String> {
}
