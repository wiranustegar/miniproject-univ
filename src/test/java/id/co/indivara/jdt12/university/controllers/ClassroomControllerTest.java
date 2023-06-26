package id.co.indivara.jdt12.university.controllers;

import id.co.indivara.jdt12.university.services.interfaces.ClassroomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClassroomController.class)
class ClassroomControllerTest {

    @MockBean
    private ClassroomService classroomService;

    @Autowired
    MockMvc mockMvc;

    private String adminAuth = "Basic YWRtaW46aW5pYWRtaW4=";


    //------ POSITIVE CASE ------
    @Test
    void createClassroom() throws Exception {
        String createClassroomJson = "{\"lecturerId\":\"4028b88188e675340188e67fb4e10006\",\"subjectId\":\"4028b88188e64df50188e64e7d050000\",\"period\":\"SUMMER\"}";
        ResultActions resultActions = mockMvc.perform(post("/classroom/")
                        .header(HttpHeaders.AUTHORIZATION, adminAuth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createClassroomJson))
                .andExpect(status().isCreated());
    }

    @Test
    void allClassrooms() throws Exception {
        String getClassrooms = "[{\"classroomIdectId\":\"4028b88188ec06da0188ec0777050000\",\"subjectCode\":\"SUB66\",\"subjectName\":\"Science\"},\"period\":\"SUMMER\"},{\"classroomId\":\"4028b88188ec1e280188ec210e100001\",\"classroomCode\":\"CRM641\",\"subjectId\":{\"subjectId\":\"4028b88188e64df50188e64e7d050000\",\"subjectCode\":\"M01\",\"subjectName\":\"Math\"},\"period\":\"SUMMER\"},]";
        ResultActions resultActions = mockMvc.perform(get("/classroom/")
                        .header(HttpHeaders.AUTHORIZATION, adminAuth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getClassrooms))
                .andExpect(status().isOk());
    }


    //---- NEGATIVE CASE ------

    @Test
    void createClassroomBadRequestTest() throws Exception {
        String createClassroomJson = "{\"reportId\":\"4028b88188ee20690188ee24d1d30003\",\"quizTest\":70,\"midTest\":80,\"finalTest\":90}";
        ResultActions resultActions = mockMvc.perform(post("/classroom/")
                        .header(HttpHeaders.AUTHORIZATION, adminAuth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createClassroomJson))
                .andExpect(status().isBadRequest());
    }

}