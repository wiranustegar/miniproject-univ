package id.co.indivara.jdt12.university.services;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Subject;
import id.co.indivara.jdt12.university.repositories.SubjectRepository;
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
class SubjectServiceImplTest {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectServiceImpl subjectService; // Use the implementation class here

    @Test
    public void createSubject_ValidSubject_Success() {
        // Arrange

        String subjectId = "1234";

        Subject savedSubject = new Subject();
        savedSubject.setSubjectId(subjectId);
        savedSubject.setSubjectCode("abc");
        savedSubject.setSubjectName("Math");

        when(subjectRepository.save(any(Subject.class))).thenReturn(savedSubject);

        // Act
        Subject createdSubject = subjectService.createSubject(savedSubject);

        // Assert
        assertNotNull(createdSubject);
        assertEquals(savedSubject.getSubjectId(), createdSubject.getSubjectId());
        assertEquals(savedSubject.getSubjectCode(), createdSubject.getSubjectCode());
        assertEquals(savedSubject.getSubjectName(), createdSubject.getSubjectName());

        verify(subjectRepository, times(1)).save(any(Subject.class));
    }

    @Test
    public void getAllSubjects_SubjectsExist_Success() {
        // Arrange
        Subject subject1 = new Subject();
        subject1.setSubjectId("1");
        subject1.setSubjectCode("SUB123");
        subject1.setSubjectName("Mathematics");

        Subject subject2 = new Subject();
        subject2.setSubjectId("2");
        subject2.setSubjectCode("SUB456");
        subject2.setSubjectName("Science");

        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(subject1);
        subjectList.add(subject2);

        when(subjectRepository.findAll()).thenReturn(subjectList);

        // Act
        List<Subject> result = subjectService.fetchSubjectList();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(subject1));
        assertTrue(result.contains(subject2));

        verify(subjectRepository, times(1)).findAll();
    }

    @Test
    public void getSubjectById_ExistingId_Success() {
        // Arrange
        String subjectId = "123456";

        Subject subject = new Subject();
        subject.setSubjectId(subjectId);
        subject.setSubjectCode("SUB123");
        subject.setSubjectName("Mathematics");

        when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));

        // Act
        Optional<Subject> result = subjectService.findById(subjectId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(subjectId, result.get().getSubjectId());
        assertEquals("SUB123", result.get().getSubjectCode());
        assertEquals("Mathematics", result.get().getSubjectName());

        verify(subjectRepository, times(1)).findById(subjectId);
    }

    @Test
    public void updateSubjectById_ExistingId_Success() throws ResourceNotFoundException {
        // Arrange
        String subjectId = "123456";

        Subject existingSubject = new Subject();
        existingSubject.setSubjectId(subjectId);
        existingSubject.setSubjectCode("SUB123");
        existingSubject.setSubjectName("Mathematics");

        Subject updatedSubject = new Subject();
        updatedSubject.setSubjectId(subjectId);
        updatedSubject.setSubjectCode("SUB789");
        updatedSubject.setSubjectName("Physics");

        when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(existingSubject));
        when(subjectRepository.save(existingSubject)).thenReturn(updatedSubject);

        // Act
        Subject result = subjectService.updateSubject(updatedSubject, subjectId);

        // Assert
        assertNotNull(result);
        assertEquals(subjectId, result.getSubjectId());
        assertEquals("SUB789", result.getSubjectCode());
        assertEquals("Physics", result.getSubjectName());

        verify(subjectRepository, times(1)).findById(subjectId);
        verify(subjectRepository, times(1)).save(existingSubject);
    }

    //------ NEGATIVE CASE ------------
    @Test
    public void getSubjectById_NonExistingId_ReturnsEmptyOptional() {
        // Arrange
        String subjectId = "123456";

        when(subjectRepository.findById(subjectId)).thenReturn(Optional.empty());

        // Act
        Optional<Subject> result = subjectService.findById(subjectId);

        // Assert
        assertNotNull(result);
        assertFalse(result.isPresent());

        verify(subjectRepository, times(1)).findById(subjectId);
    }



}