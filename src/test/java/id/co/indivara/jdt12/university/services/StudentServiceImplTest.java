package id.co.indivara.jdt12.university.services;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Student;
import id.co.indivara.jdt12.university.repositories.StudentRepository;
import id.co.indivara.jdt12.university.services.interfaces.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    @Test
    public void createStudent_ValidData_Success() {
        // Arrange
        Student student = new Student();
        student.setStudentName("John Doe");
        student.setEmail("john.doe@example.com");
        student.setGender("Male");

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // Act
        Student createdStudent = studentServiceImpl.createStudent(student);

        // Assert
        assertNotNull(createdStudent);
        assertEquals("John Doe", createdStudent.getStudentName());
        assertEquals("john.doe@example.com", createdStudent.getEmail());
        assertEquals("Male", createdStudent.getGender());

        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    public void getAllStudents_ReturnsListOfStudents_Success() {
        // Arrange
        Student student1 = new Student();
        student1.setStudentName("John Doe");
        student1.setEmail("john.doe@example.com");
        student1.setGender("Male");

        Student student2 = new Student();
        student2.setStudentName("Jane Smith");
        student2.setEmail("jane.smith@example.com");
        student2.setGender("Female");

        List<Student> studentList = Arrays.asList(student1, student2);

        when(studentRepository.findAll()).thenReturn(studentList);

        // Act
        List<Student> retrievedStudents = studentServiceImpl.fetchStudentList();

        // Assert
        assertNotNull(retrievedStudents);
        assertEquals(2, retrievedStudents.size());
        assertEquals("John Doe", retrievedStudents.get(0).getStudentName());
        assertEquals("Jane Smith", retrievedStudents.get(1).getStudentName());

        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void getStudentById_ExistingId_ReturnsStudent() throws ResourceNotFoundException {
        // Arrange
        String studentId = "123456";
        Student student = new Student();
        student.setStudentId(studentId);
        student.setStudentName("John Doe");
        student.setEmail("john.doe@example.com");
        student.setGender("Male");

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        // Act
        Student retrievedStudent = studentServiceImpl.findById(studentId).get();

        // Assert
        assertNotNull(retrievedStudent);
        assertEquals(studentId, retrievedStudent.getStudentId());
        assertEquals("John Doe", retrievedStudent.getStudentName());
        assertEquals("john.doe@example.com", retrievedStudent.getEmail());
        assertEquals("Male", retrievedStudent.getGender());

        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    public void updateStudentById_ExistingId_Success() throws ResourceNotFoundException {
        // Arrange
        String studentId = "123456";
        Student existingStudent = new Student();
        existingStudent.setStudentId(studentId);
        existingStudent.setStudentName("John Doe");
        existingStudent.setEmail("john.doe@example.com");
        existingStudent.setGender("Male");

        Student updatedStudent = new Student();
        updatedStudent.setStudentId(studentId);
        updatedStudent.setStudentName("Jane Smith");
        updatedStudent.setEmail("jane.smith@example.com");
        updatedStudent.setGender("Female");

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);

        // Act
        Student result = studentServiceImpl.updateStudent(updatedStudent, studentId);

        // Assert
        assertNotNull(result);
        assertEquals(studentId, result.getStudentId());
        assertEquals("Jane Smith", result.getStudentName());
        assertEquals("jane.smith@example.com", result.getEmail());
        assertEquals("Female", result.getGender());

        verify(studentRepository, times(1)).findById(studentId);
        verify(studentRepository, times(1)).save(any(Student.class));
    }

}
