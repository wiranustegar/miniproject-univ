package id.co.indivara.jdt12.university.controllers;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Subject;
import id.co.indivara.jdt12.university.services.interfaces.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/subject")
@RestController
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject){
        return new ResponseEntity<>(subjectService.createSubject(subject), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Subject>> getAllSubjects(){
        return new ResponseEntity<>(subjectService.fetchSubjectList(), HttpStatus.OK) ;
    }

    @PutMapping("/{subjectId}/")
    public ResponseEntity<Subject> updateSubject(@RequestBody Subject subject, @PathVariable("subjectId") String subjectId) throws ResourceNotFoundException {
        return new ResponseEntity<>(subjectService.updateSubject(subject, subjectId), HttpStatus.OK);
    }

    @DeleteMapping("/{subjectId}/")
    public ResponseEntity<Subject> deleteSubject(@PathVariable("subjectId") String subjectId) throws ResourceNotFoundException {
        subjectService.deleteSubject(subjectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
