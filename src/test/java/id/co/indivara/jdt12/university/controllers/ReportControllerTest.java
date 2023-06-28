package id.co.indivara.jdt12.university.controllers;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.AuthRequest;
import id.co.indivara.jdt12.university.models.Classroom;
import id.co.indivara.jdt12.university.models.Report;
import id.co.indivara.jdt12.university.models.Student;
import id.co.indivara.jdt12.university.models.dtos.InputRecordAchievementDto;
import id.co.indivara.jdt12.university.models.dtos.RegisterStudentDto;
import id.co.indivara.jdt12.university.services.interfaces.ReportService;
import id.co.indivara.jdt12.university.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class ReportControllerTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void generateToken_ValidAuthRequest_Success() throws Exception {
        AuthRequest authRequest = new AuthRequest("username", "password");
        String expectedToken = "generated-token";

        when(jwtUtil.generateToken(authRequest.getUserName())).thenReturn(expectedToken);

        String result = reportController.generateToken(authRequest);

        assertEquals(expectedToken, result);
        verify(authenticationManager, times(1)).authenticate(any());
    }

    @Test
    void registerStudent_ValidRegisterStudentDto_Success() throws ResourceNotFoundException {
        RegisterStudentDto registerStudentDto = new RegisterStudentDto();
        Report expectedReport = new Report();

        when(reportService.registerStudent(registerStudentDto)).thenReturn(expectedReport);

        ResponseEntity<Report> response = reportController.registerStudent(registerStudentDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedReport, response.getBody());
    }

    @Test
    void allReports_ReturnsListOfReports_Success() {
        List<Report> expectedReports = new ArrayList<>();
        expectedReports.add(new Report());
        expectedReports.add(new Report());

        when(reportService.findAll()).thenReturn(expectedReports);

        ResponseEntity<List<Report>> response = reportController.allReports();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedReports, response.getBody());
    }

    @Test
    void inputRecordAchievement_ValidInputRecordAchievementDto_Success() throws ResourceNotFoundException {
        InputRecordAchievementDto inputRecordAchievementDto = new InputRecordAchievementDto();
        Report expectedReport = new Report();

        when(reportService.inputRecordAchievement(inputRecordAchievementDto)).thenReturn(expectedReport);

        ResponseEntity<Report> response = reportController.inputRecordAchievement(inputRecordAchievementDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedReport, response.getBody());
    }

    @Test
    void findReportByClassroomId_ValidClassroomId_Success() {
        Classroom classroom = new Classroom();
        List<Report> expectedReports = new ArrayList<>();
        expectedReports.add(new Report());
        expectedReports.add(new Report());

        when(reportService.findByClassroomId(classroom)).thenReturn((ArrayList<Report>) expectedReports);

        ResponseEntity<List<Report>> response = reportController.findReportByClassroomId(classroom);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedReports, response.getBody());
    }

    @Test
    void findReportByStudentId_ValidStudentId_Success() {
        Student student = new Student();
        List<Report> expectedReports = new ArrayList<>();
        expectedReports.add(new Report());
        expectedReports.add(new Report());

        when(reportService.findByStudentId(student)).thenReturn((ArrayList<Report>) expectedReports);

        ResponseEntity<List<Report>> response = reportController.findReportByStudentId(student);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedReports, response.getBody());
    }
}
