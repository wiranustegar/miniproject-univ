package id.co.indivara.jdt12.university.services.interfaces;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    //POST "/student/"
    Student createStudent(Student student);

    //GET "/student/{studentId}"
    Optional<Student> findById(String studentId) throws ResourceNotFoundException;

    //GET "/student/"
    List<Student> fetchStudentList();

    //PUT "/student/{studentId}"
    Student updateStudent(Student student, String studentId) throws ResourceNotFoundException;

    //DELETE "/student/{studentId}"
    void deleteStudent(String studentId) throws ResourceNotFoundException;
}
