package id.co.indivara.jdt12.university.services;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Student;
import id.co.indivara.jdt12.university.repositories.StudentRepository;
import id.co.indivara.jdt12.university.services.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        Random random = new Random();
        student.setStudentRegNumber(random.nextInt(10000));
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> findById(String studentId) throws ResourceNotFoundException {
        return Optional.ofNullable(studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("student id " + studentId + " not found")));
    }

    @Override
    public List<Student> fetchStudentList() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student, String studentId) throws ResourceNotFoundException {
        Student stud = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("student id " +studentId+ " not found"));

        stud.setStudentName(student.getStudentName());
        stud.setEmail(student.getEmail());
        stud.setGender(student.getGender());

        return studentRepository.save(stud);
    }

    @Override
    public void deleteStudent(String studentId) throws ResourceNotFoundException {
        Student stud = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("student id "+ studentId + " not found"));

        studentRepository.deleteById(stud.getStudentId());
    }
}
