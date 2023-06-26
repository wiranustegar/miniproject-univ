package id.co.indivara.jdt12.university.controllers;

import id.co.indivara.jdt12.university.services.interfaces.ReportService;
import id.co.indivara.jdt12.university.services.interfaces.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReportControllerTest {

    @MockBean
    private ReportService reportService;

    @Autowired
    MockMvc mockMvc;

    private String adminAuth = "Basic YWRtaW46aW5pYWRtaW4=";
    private String lecturerAuth = "Basic bGVjdHVyZXI6aW5pbGVjdHVyZXI=";

    //----- POSITIVE CASE -------
    @Test
    void testRegisterStudentSuccess() throws Exception {

        String registerStudentJson = "{\"studentId\":\"4028b88188ee20690188ee2131bf0000\",\"classroomId\":\"4028b88188ee20690188ee23d7e00002\"}";
        ResultActions resultActions = mockMvc.perform(post("/report/register/")
                        .header(HttpHeaders.AUTHORIZATION, adminAuth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerStudentJson))
                .andExpect(status().isCreated());

    }

    @Test
    void testInputRecordAchievement() throws Exception {

        String inputRecord = "{\"reportId\":\"4028b88188ee20690188ee24d1d30003\",\"quizTest\":70,\"midTest\":80,\"finalTest\":90}";
        ResultActions resultActions = mockMvc.perform(put("/report/input-record/")
                        .header(HttpHeaders.AUTHORIZATION, lecturerAuth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputRecord))
                .andExpect(status().isOk());
    }

    //------- NEGATIVE CASE --------

    @Test
    void registerStudentBadRequestTest() throws Exception {

        String registerStudentJson = "{\"classroomId\":\"4028b88188ee20690188ee23d7e00002\"}";
        ResultActions resultActions = mockMvc.perform(post("/report/register/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, adminAuth)
                        .content(registerStudentJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void inputRecordAchievementBadRequestTest() throws Exception {

        String inputRecord = "{\"reportId\":\"4028b88188ee20690188ee24d1d30003\"midTest\":80,\"finalTest\":90}";
        ResultActions resultActions = mockMvc.perform(put("/report/input-record/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, lecturerAuth)
                        .content(inputRecord))
                .andExpect(status().isBadRequest());
    }

}
