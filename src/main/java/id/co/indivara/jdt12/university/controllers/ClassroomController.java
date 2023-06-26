package id.co.indivara.jdt12.university.controllers;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Classroom;
import id.co.indivara.jdt12.university.models.dtos.CreateClassroomDto;
import id.co.indivara.jdt12.university.services.interfaces.ClassroomService;
import id.co.indivara.jdt12.university.services.interfaces.LecturerService;
import id.co.indivara.jdt12.university.services.interfaces.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
@RequestMapping(value = "/classroom")
@RestController
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    @PostMapping("/")
    public ResponseEntity<Classroom> createClassroom(@Valid @RequestBody CreateClassroomDto createClassroomDto) throws ResourceNotFoundException {
        return new ResponseEntity<>(classroomService.initiateClassroom(createClassroomDto), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity <List<Classroom>> allClassrooms(){
        return new ResponseEntity<>(classroomService.findAll(),HttpStatus.OK);
    }


    @GetMapping("/{classroomId}/")
    public ResponseEntity<Optional<Classroom>> getClassroomById(@Valid @PathVariable("classroomId") String classroomId) throws ResourceNotFoundException {
        return new ResponseEntity<>(classroomService.findById(classroomId), HttpStatus.OK);
    }

}
