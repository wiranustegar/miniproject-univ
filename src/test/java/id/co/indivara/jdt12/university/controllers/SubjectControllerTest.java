//package id.co.indivara.jdt12.university.controllers;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
//import id.co.indivara.jdt12.university.models.Subject;
//import id.co.indivara.jdt12.university.services.interfaces.SubjectService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(SubjectController.class)
//class SubjectControllerTest {
//    @MockBean
//    SubjectService subjectService;
//
//    @Autowired
//    MockMvc mockMvc;
//
//
//    @Test
//    void createSubjectTest() throws Exception {
//        Subject subject = new Subject(70L, "Math");
//        when(subjectService.createSubject(subject)).thenReturn(subject);
//        mockMvc.perform(post("/subject")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(subject)))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void getAllSubjects() throws Exception {
//        List<Subject> subjects = Arrays.asList(new Subject(70L, "Math"),
//                new Subject(71L, "Science")) ;
//        when(subjectService.fetchSubjectList()).thenReturn(subjects);
//        mockMvc.perform(get("/subject")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].subjectName").value("Math"));
//    }
//
//    @Test
//    void updateSubject() {
//    }
//
//    @Test
//    void deleteSubject() throws Exception {
//        Subject subject = new Subject(70L, "Math");
//        doNothing().when(subjectService).deleteSubject(70L);
//        mockMvc.perform(delete("/subject/{subjectId}", 70L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(subject)))
//                .andExpect(status().isOk());
//    }
//}