package id.co.indivara.jdt12.university.services.interfaces;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

    //GET "/subject/{subjectId}"
    Optional<Subject> findById(String subjectId);

    //POST "/subject/"
    Subject createSubject(Subject subject);

    //GET "/subject/"
    List<Subject> fetchSubjectList();

    //PUT "/subject/{subjectId}"
    Subject updateSubject(Subject subject, String subjectId) throws ResourceNotFoundException;

    //DELETE "/subject/{subjectId}"
    void deleteSubject(String subjectId) throws ResourceNotFoundException;
}
