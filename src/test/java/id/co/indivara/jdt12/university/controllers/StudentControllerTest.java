package id.co.indivara.jdt12.university.controllers;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Student;
import id.co.indivara.jdt12.university.services.interfaces.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createStudent_ValidStudent_Success() {
        Student student = new Student();
        Student expectedStudent = new Student();

        when(studentService.createStudent(student)).thenReturn(expectedStudent);

        ResponseEntity<Student> response = studentController.createStudent(student);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedStudent, response.getBody());
    }

    @Test
    void getAllStudents_ReturnsListOfStudents_Success() {
        List<Student> expectedStudents = new ArrayList<>();
        expectedStudents.add(new Student());
        expectedStudents.add(new Student());

        when(studentService.fetchStudentList()).thenReturn(expectedStudents);

        ResponseEntity<List<Student>> response = studentController.getAllStudents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedStudents, response.getBody());
    }

    @Test
    void getStudentById_ValidStudentId_Success() throws ResourceNotFoundException {
        String studentId = "123";
        Optional<Student> expectedStudent = Optional.of(new Student());

        when(studentService.findById(studentId)).thenReturn(expectedStudent);

        ResponseEntity<Optional<Student>> response = studentController.getStudentById(studentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedStudent, response.getBody());
    }

    @Test
    void updateStudent_ValidStudentAndStudentId_Success() throws ResourceNotFoundException {
        String studentId = "123";
        Student student = new Student();
        Student expectedStudent = new Student();

        when(studentService.updateStudent(student, studentId)).thenReturn(expectedStudent);

        ResponseEntity<Student> response = studentController.updateStudent(student, studentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedStudent, response.getBody());
    }

    @Test
    void deleteStudent_ValidStudentId_Success() throws ResourceNotFoundException {
        String studentId = "123";

        ResponseEntity<Student> response = studentController.deleteStudent(studentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(studentService, times(1)).deleteStudent(studentId);
    }
}