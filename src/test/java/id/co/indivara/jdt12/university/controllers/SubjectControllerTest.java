package id.co.indivara.jdt12.university.controllers;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Subject;
import id.co.indivara.jdt12.university.services.interfaces.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SubjectControllerTest {

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private SubjectController subjectController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createSubject_ValidSubject_Success() {
        Subject subject = new Subject();
        Subject expectedSubject = new Subject();

        when(subjectService.createSubject(subject)).thenReturn(expectedSubject);

        ResponseEntity<Subject> response = subjectController.createSubject(subject);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedSubject, response.getBody());
    }

    @Test
    void getAllSubjects_ReturnsListOfSubjects_Success() {
        List<Subject> expectedSubjects = new ArrayList<>();
        expectedSubjects.add(new Subject());
        expectedSubjects.add(new Subject());

        when(subjectService.fetchSubjectList()).thenReturn(expectedSubjects);

        ResponseEntity<List<Subject>> response = subjectController.getAllSubjects();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedSubjects, response.getBody());
    }

    @Test
    void updateSubject_ValidSubjectAndSubjectId_Success() throws ResourceNotFoundException {
        String subjectId = "123";
        Subject subject = new Subject();
        Subject expectedSubject = new Subject();

        when(subjectService.updateSubject(subject, subjectId)).thenReturn(expectedSubject);

        ResponseEntity<Subject> response = subjectController.updateSubject(subject, subjectId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedSubject, response.getBody());
    }

    @Test
    void deleteSubject_ValidSubjectId_Success() throws ResourceNotFoundException {
        String subjectId = "123";

        ResponseEntity<Subject> response = subjectController.deleteSubject(subjectId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(subjectService, times(1)).deleteSubject(subjectId);
    }
}
