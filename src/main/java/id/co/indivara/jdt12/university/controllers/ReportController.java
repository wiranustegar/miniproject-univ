package id.co.indivara.jdt12.university.controllers;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.AuthRequest;
import id.co.indivara.jdt12.university.models.Classroom;
import id.co.indivara.jdt12.university.models.Report;
import id.co.indivara.jdt12.university.models.Student;
import id.co.indivara.jdt12.university.models.dtos.InputRecordAchievementDto;
import id.co.indivara.jdt12.university.models.dtos.RegisterStudentDto;
import id.co.indivara.jdt12.university.services.interfaces.ClassroomService;
import id.co.indivara.jdt12.university.services.interfaces.ReportService;
import id.co.indivara.jdt12.university.services.interfaces.StudentService;
import id.co.indivara.jdt12.university.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequestMapping(value = "/report")
@RestController
public class ReportController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ReportService reportService;


    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }

    @PostMapping("/register/")
    public ResponseEntity<Report> registerStudent(@Valid @RequestBody RegisterStudentDto registerStudentDto) throws ResourceNotFoundException {
        return new ResponseEntity<>(reportService.registerStudent(registerStudentDto), HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<List<Report>> allReports(){
        return new ResponseEntity<>(reportService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/input-record/")
    public ResponseEntity<Report> inputRecordAchievement(@Valid @RequestBody InputRecordAchievementDto report) throws ResourceNotFoundException {
        return new ResponseEntity<>(reportService.inputRecordAchievement(report), HttpStatus.OK);
    }

    @GetMapping("/{classroomId}/")
    public ResponseEntity<List<Report>> findReportByClassroomId(@Valid @PathVariable("classroomId") Classroom classroomId){
        return new ResponseEntity<>(reportService.findByClassroomId(classroomId), HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/")
    public ResponseEntity<List<Report>> findReportByStudentId(@Valid @PathVariable("studentId") Student studentId){
        return new ResponseEntity<>(reportService.findByStudentId(studentId),HttpStatus.OK);
    }

}
