//package id.co.indivara.jdt12.university.controllers;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
//import id.co.indivara.jdt12.university.models.Student;
//import id.co.indivara.jdt12.university.services.interfaces.StudentService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(StudentController.class)
//class StudentControllerTest {
//
//    @MockBean
//    StudentService studentService;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    void createStudentTest() throws Exception {
//        Student student = new Student(99L, "Saidi", "saidi@gmail.com", "FEMALE");
//        when(studentService.createStudent(student)).thenReturn(student);
//        mockMvc.perform(post("/student")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(student)))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void getAllStudentsTest() throws Exception {
//        List<Student> students = Arrays.asList(new Student(99L, "Saidi", "saidi@gmail.com", "FEMALE"),
//                new Student(100L, "Budy", "Budy@gmail.com", "MALE")) ;
//
//        when(studentService.fetchStudentList()).thenReturn(students);
//        mockMvc.perform(get("/student")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].studentName").value("Saidi"));
//    }
//
//    @Test
//    void getStudentByIdTest() throws Exception {
//        Student student = new Student(99L, "Saidi", "saidi@gmail.com", "FEMALE");
//        when(studentService.findById(99L)).thenReturn(Optional.of(student));
//        mockMvc.perform(get("/student/{studentId}", 99L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(student)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.studentName").value("Saidi"));
//    }
//
//    @Test
//    void updateStudent() throws Exception {
//        Student student = new Student(99L, "Saidi", "saidi@gmail.com", "FEMALE");
//        when(studentService.updateStudent(student, 99L)).thenReturn(student);
//        mockMvc.perform(put("/{studentId}", 99L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(student)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void deleteStudent() throws Exception {
//        Student student = new Student(99L, "Saidi", "saidi@gmail.com", "FEMALE");
//        doNothing().when(studentService).deleteStudent(99L);
//        mockMvc.perform(delete("/student/{studentId}", 99L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(student)))
//                .andExpect(status().isOk());
//    }
//
//    //NEGATIVE CASE
//    @Test
//    void getStudentByIdNotFoundTest() throws Exception {
//        Student student = new Student(99L, "Saidi", "saidi@gmail.com", "FEMALE");
//        when(studentService.findById(98L)).thenThrow(ResourceNotFoundException.class);
//        mockMvc.perform(get("/student/{studentId}", 98L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(student)))
//                .andExpect(status().isNotFound());
//    }
//}