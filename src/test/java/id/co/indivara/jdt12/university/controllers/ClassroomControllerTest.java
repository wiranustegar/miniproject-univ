package id.co.indivara.jdt12.university.controllers;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Classroom;
import id.co.indivara.jdt12.university.models.dtos.CreateClassroomDto;
import id.co.indivara.jdt12.university.services.interfaces.ClassroomService;
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

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;


class ClassroomControllerTest {

    @Mock
    private ClassroomService classroomService;

    @InjectMocks
    private ClassroomController classroomController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createClassroom_ValidCreateClassroomDto_Success() throws ResourceNotFoundException {
        CreateClassroomDto createClassroomDto = new CreateClassroomDto();
        Classroom expectedClassroom = new Classroom();

        when(classroomService.initiateClassroom(createClassroomDto)).thenReturn(expectedClassroom);

        ResponseEntity<Classroom> response = classroomController.createClassroom(createClassroomDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedClassroom, response.getBody());
    }

    @Test
    void allClassrooms_ReturnsListOfClassrooms_Success() {
        List<Classroom> expectedClassrooms = new ArrayList<>();
        expectedClassrooms.add(new Classroom());
        expectedClassrooms.add(new Classroom());

        when(classroomService.findAll()).thenReturn(expectedClassrooms);

        ResponseEntity<List<Classroom>> response = classroomController.allClassrooms();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedClassrooms, response.getBody());
    }

    @Test
    void getClassroomById_ValidClassroomId_Success() throws ResourceNotFoundException {
        String classroomId = "123";
        Optional<Classroom> expectedClassroom = Optional.of(new Classroom());

        when(classroomService.findById(classroomId)).thenReturn(expectedClassroom);

        ResponseEntity<Optional<Classroom>> response = classroomController.getClassroomById(classroomId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedClassroom, response.getBody());
    }
}
