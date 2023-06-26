package id.co.indivara.jdt12.university.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Lecturer;
import id.co.indivara.jdt12.university.services.interfaces.LecturerService;
import id.co.indivara.jdt12.university.services.interfaces.SubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LecturerController.class)
class LecturerControllerTest {

    @MockBean
    private LecturerService lecturerService;

    @Autowired
    MockMvc mockMvc;

    private String adminAuth = "Basic YWRtaW46aW5pYWRtaW4=";
    private String lecturerAuth = "Basic bGVjdHVyZXI6aW5pbGVjdHVyZXI=";


    @Test
    void createLecturerTest() throws Exception {
        Lecturer lecturer = new Lecturer("2222", "Elon", 321,"AI", "elon@gmail.com", "MALE");
        when(lecturerService.createLecturer(lecturer)).thenReturn(lecturer);
        mockMvc.perform(post("/lecturer/")
                        .header(HttpHeaders.AUTHORIZATION, adminAuth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(lecturer)))
                .andExpect(status().isCreated());

    }

    @Test
    void getAllLecturers() throws Exception {
        List<Lecturer> lecturers = Arrays.asList(new Lecturer("2222", "Elon", 321,"AI", "elon@gmail.com", "MALE"),
                new Lecturer("2223", "Akulecturer", 322,"Science", "hehe@gmail.com", "MALE"));

        when(lecturerService.fetchLecturerList()).thenReturn(lecturers);
        mockMvc.perform(get("/lecturer/")
                        .header(HttpHeaders.AUTHORIZATION, adminAuth)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lecturerName").value("Elon"))
                .andExpect(jsonPath("$[1].lecturerName").value("Akulecturer"));
    }

    @Test
    void getLecturerById() throws Exception {
        Lecturer lecturer = new Lecturer("2222", "Elon", 321,"AI", "elon@gmail.com", "MALE");
        when(lecturerService.findById("2222")).thenReturn(Optional.of(lecturer));
        mockMvc.perform(get("/lecturer/{lecturerId}/", 2222)
                        .header(HttpHeaders.AUTHORIZATION, lecturerAuth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(lecturer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lecturerName").value("Elon"));
    }

    @Test
    void deleteLecturer() throws Exception {
        Lecturer lecturer = new Lecturer("2222", "Elon", 321,"AI", "elon@gmail.com", "MALE");
        doNothing().when(lecturerService).deleteLecturer("2222");
        mockMvc.perform(delete("/lecturer/{lecturerId}/", 2222)
                        .header(HttpHeaders.AUTHORIZATION, adminAuth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(lecturer)))
                .andExpect(status().isOk());
    }
}