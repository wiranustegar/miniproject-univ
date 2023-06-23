//package id.co.indivara.jdt12.university.controllers;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import id.co.indivara.jdt12.university.models.Classroom;
//import id.co.indivara.jdt12.university.models.Report;
//import id.co.indivara.jdt12.university.models.Student;
//import id.co.indivara.jdt12.university.services.interfaces.ReportService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(ReportController.class)
//class ReportControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ReportService reportService;
//
//    @Test
//    void testRegisterStudentSuccess() throws Exception {
//
//        Report reportRequest = new Report(12L, new Classroom(), new Student());
//        when(reportService.register(reportRequest)).thenReturn(reportRequest);
//        mockMvc.perform(post("/report")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(reportRequest)))
//                .andExpect(status().isCreated());
//    }
//
//}
