package id.co.indivara.jdt12.university.services;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Lecturer;
import id.co.indivara.jdt12.university.repositories.LecturerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class LecturerServiceImplTest {

    @Mock
    private LecturerRepository lecturerRepository;

    @InjectMocks
    private LecturerServiceImpl lecturerService;

    @Test
    public void createLecturer_ValidData_Success() {
        // Arrange
        Lecturer lecturer = new Lecturer();
        lecturer.setLecturerId("123456");
        lecturer.setLecturerName("John Doe");
        lecturer.setSpecialization("Computer Science");
        lecturer.setEmail("john.doe@example.com");
        lecturer.setGender("Male");

        when(lecturerRepository.save(lecturer)).thenReturn(lecturer);

        // Act
        Lecturer result = lecturerService.createLecturer(lecturer);

        // Assert
        assertNotNull(result);
        assertEquals("123456", result.getLecturerId());
        assertEquals("John Doe", result.getLecturerName());
        assertEquals("Computer Science", result.getSpecialization());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals("Male", result.getGender());

        verify(lecturerRepository, times(1)).save(lecturer);
    }

    @Test
    public void getAllLecturers_ExistingLecturers_ReturnsListOfLecturers() {
        // Arrange
        Lecturer lecturer1 = new Lecturer();
        lecturer1.setLecturerId("123456");
        lecturer1.setLecturerName("John Doe");
        lecturer1.setSpecialization("Computer Science");
        lecturer1.setEmail("john.doe@example.com");
        lecturer1.setGender("Male");

        Lecturer lecturer2 = new Lecturer();
        lecturer2.setLecturerId("789012");
        lecturer2.setLecturerName("Jane Smith");
        lecturer2.setSpecialization("Mathematics");
        lecturer2.setEmail("jane.smith@example.com");
        lecturer2.setGender("Female");

        List<Lecturer> lecturerList = new ArrayList<>();
        lecturerList.add(lecturer1);
        lecturerList.add(lecturer2);

        when(lecturerRepository.findAll()).thenReturn(lecturerList);

        // Act
        List<Lecturer> result = lecturerService.fetchLecturerList();

        // Assert
        assertEquals(2, result.size());
        assertEquals("123456", result.get(0).getLecturerId());
        assertEquals("John Doe", result.get(0).getLecturerName());
        assertEquals("Computer Science", result.get(0).getSpecialization());
        assertEquals("john.doe@example.com", result.get(0).getEmail());
        assertEquals("Male", result.get(0).getGender());

        assertEquals("789012", result.get(1).getLecturerId());
        assertEquals("Jane Smith", result.get(1).getLecturerName());
        assertEquals("Mathematics", result.get(1).getSpecialization());
        assertEquals("jane.smith@example.com", result.get(1).getEmail());
        assertEquals("Female", result.get(1).getGender());

        verify(lecturerRepository, times(1)).findAll();

    }

    @Test
    public void getLecturerById_ExistingId_ReturnsLecturer() throws ResourceNotFoundException {
        // Arrange
        String lecturerId = "123456";

        Lecturer existingLecturer = new Lecturer();
        existingLecturer.setLecturerId(lecturerId);
        existingLecturer.setLecturerName("John Doe");
        existingLecturer.setSpecialization("Computer Science");
        existingLecturer.setEmail("john.doe@example.com");
        existingLecturer.setGender("Male");

        when(lecturerRepository.findById(lecturerId)).thenReturn(Optional.of(existingLecturer));

        // Act
        Lecturer result = lecturerService.findById(lecturerId).get();

        // Assert
        assertEquals(lecturerId, result.getLecturerId());
        assertEquals("John Doe", result.getLecturerName());
        assertEquals("Computer Science", result.getSpecialization());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals("Male", result.getGender());

        verify(lecturerRepository, times(1)).findById(lecturerId);
    }

    @Test
    public void updateLecturer_ExistingLecturer_Success() throws ResourceNotFoundException {
        // Arrange
        String lecturerId = "123456";

        Lecturer existingLecturer = new Lecturer();
        existingLecturer.setLecturerId(lecturerId);
        existingLecturer.setLecturerName("John Doe");
        existingLecturer.setSpecialization("Computer Science");
        existingLecturer.setEmail("john.doe@example.com");
        existingLecturer.setGender("Male");

        Lecturer updatedLecturer = new Lecturer();
        updatedLecturer.setLecturerId(lecturerId);
        updatedLecturer.setLecturerName("Jane Smith");
        updatedLecturer.setSpecialization("Mathematics");
        updatedLecturer.setEmail("jane.smith@example.com");
        updatedLecturer.setGender("Female");

        when(lecturerRepository.findById(lecturerId)).thenReturn(Optional.of(existingLecturer));
        when(lecturerRepository.save(updatedLecturer)).thenReturn(updatedLecturer);

        // Act
        Lecturer result = lecturerService.updateLecturer(updatedLecturer, lecturerId);

        // Assert
        assertEquals(lecturerId, result.getLecturerId());
        assertEquals("Jane Smith", result.getLecturerName());
        assertEquals("Mathematics", result.getSpecialization());
        assertEquals("jane.smith@example.com", result.getEmail());
        assertEquals("Female", result.getGender());

        verify(lecturerRepository, times(1)).findById(lecturerId);
        verify(lecturerRepository, times(1)).save(updatedLecturer);
    }


}