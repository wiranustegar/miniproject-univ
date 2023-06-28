package id.co.indivara.jdt12.university.controllers;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Lecturer;
import id.co.indivara.jdt12.university.services.interfaces.LecturerService;
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

class LecturerControllerTest {

    @Mock
    private LecturerService lecturerService;

    @InjectMocks
    private LecturerController lecturerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createLecturer_ValidLecturer_Success() {
        Lecturer lecturer = new Lecturer();
        Lecturer expectedLecturer = new Lecturer();

        when(lecturerService.createLecturer(lecturer)).thenReturn(expectedLecturer);

        ResponseEntity<Lecturer> response = lecturerController.createLecturer(lecturer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedLecturer, response.getBody());
    }

    @Test
    void getAllLecturers_ReturnsListOfLecturers_Success() {
        List<Lecturer> expectedLecturers = new ArrayList<>();
        expectedLecturers.add(new Lecturer());
        expectedLecturers.add(new Lecturer());

        when(lecturerService.fetchLecturerList()).thenReturn(expectedLecturers);

        ResponseEntity<List<Lecturer>> response = lecturerController.getAllLecturers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedLecturers, response.getBody());
    }

    @Test
    void getLecturerById_ValidLecturerId_Success() throws ResourceNotFoundException {
        String lecturerId = "123";
        Optional<Lecturer> expectedLecturer = Optional.of(new Lecturer());

        when(lecturerService.findById(lecturerId)).thenReturn(expectedLecturer);

        ResponseEntity<Optional<Lecturer>> response = lecturerController.getLecturerById(lecturerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedLecturer, response.getBody());
    }

    @Test
    void updateLecturer_ValidLecturerAndLecturerId_Success() throws ResourceNotFoundException {
        String lecturerId = "123";
        Lecturer lecturer = new Lecturer();
        Lecturer expectedLecturer = new Lecturer();

        when(lecturerService.updateLecturer(lecturer, lecturerId)).thenReturn(expectedLecturer);

        ResponseEntity<Lecturer> response = lecturerController.updateLecturer(lecturer, lecturerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedLecturer, response.getBody());
    }

    @Test
    void deleteLecturer_ValidLecturerId_Success() throws ResourceNotFoundException {
        String lecturerId = "123";

        ResponseEntity<Lecturer> response = lecturerController.deleteLecturer(lecturerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(lecturerService, times(1)).deleteLecturer(lecturerId);
    }
}