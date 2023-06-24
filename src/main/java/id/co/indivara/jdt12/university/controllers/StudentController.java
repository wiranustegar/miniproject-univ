package id.co.indivara.jdt12.university.controllers;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Student;
import id.co.indivara.jdt12.university.services.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudents(){
        return new ResponseEntity<>(studentService.fetchStudentList(), HttpStatus.OK) ;
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable("studentId") String studentId) throws ResourceNotFoundException {
        return new ResponseEntity<>(studentService.findById(studentId), HttpStatus.OK);
    }

    @PutMapping("{studentId}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("studentId") String studentId) throws ResourceNotFoundException {
        return new ResponseEntity<>(studentService.updateStudent(student, studentId), HttpStatus.OK);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("studentId") String studentId) throws ResourceNotFoundException {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
