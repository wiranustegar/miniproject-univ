package id.co.indivara.jdt12.university.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Student;
import id.co.indivara.jdt12.university.services.interfaces.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @Autowired
    MockMvc mockMvc;

    private String adminAuth = "Basic YWRtaW46aW5pYWRtaW4=";
    private String studentAuth = "Basic c3R1ZGVudDppbmlzdHVkZW50";

    //------ POSTIVE CASE -------
    @Test
    void createStudentTest() throws Exception {
        Student student = new Student("11111", "tegar", 1010, "tegar@gmail.com", "MALE");
        when(studentService.createStudent(student)).thenReturn(student);
        mockMvc.perform(post("/student/")
                        .header(HttpHeaders.AUTHORIZATION, adminAuth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isCreated());
    }


    @Test
    void getAllStudentsTest() throws Exception {
        List<Student> students = Arrays.asList(new Student("11111", "tegar", 1010, "tegar@gmail.com", "MALE"),
                new Student("11112", "Tes", 1011, "test@gmail.com", "FEMALE")) ;

        when(studentService.fetchStudentList()).thenReturn(students);
        mockMvc.perform(get("/student/")
                        .header(HttpHeaders.AUTHORIZATION, adminAuth)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studentName").value("tegar"));
    }

    @Test
    void getStudentByIdTest() throws Exception {
        Student student = new Student("11111", "tegar", 1010, "tegar@gmail.com", "MALE");
        when(studentService.findById("11111")).thenReturn(Optional.of(student));
        mockMvc.perform(get("/student/{studentId}/", 11111)
                        .header(HttpHeaders.AUTHORIZATION, studentAuth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentName").value("tegar"));
    }

    @Test
    void deleteStudent() throws Exception {
        Student student = new Student("11111", "tegar", 1010, "tegar@gmail.com", "MALE");
        doNothing().when(studentService).deleteStudent("11111");
        mockMvc.perform(delete("/student/{studentId}/", 11111)
                        .header(HttpHeaders.AUTHORIZATION, adminAuth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk());
    }

    //------- NEGATIVE CASE ---------
    @Test
    void getStudentByIdNotFoundTest() throws Exception {
        Student student = new Student("111212", "tegar", 1010, "tegar@gmail.com", "MALE");
        when(studentService.findById("111215")).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get("/student/{studentId}/", 111215)
                        .header(HttpHeaders.AUTHORIZATION, adminAuth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isNotFound());
    }
}