package id.co.indivara.jdt12.university.controllers;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Lecturer;
import id.co.indivara.jdt12.university.services.interfaces.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/lecturer")
@RestController
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    @PostMapping("/")
    public ResponseEntity<Lecturer> createLecturer(@RequestBody Lecturer lecturer){
        return new ResponseEntity<>(lecturerService.createLecturer(lecturer), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Lecturer>> getAllLecturers(){
        return new ResponseEntity<>(lecturerService.fetchLecturerList(), HttpStatus.OK) ;
    }

    @GetMapping("/{lecturerId}/")
    public ResponseEntity<Optional<Lecturer>> getLecturerById(@Valid @PathVariable("lecturerId") String lecturerId) throws ResourceNotFoundException {
        return new ResponseEntity<>(lecturerService.findById(lecturerId),HttpStatus.OK);
    }

    @PutMapping("/{lecturerId}/")
    public ResponseEntity<Lecturer> updateLecturer(@Valid @RequestBody Lecturer lecturer, @PathVariable("lecturerId") String lecturerId) throws ResourceNotFoundException {
        return new ResponseEntity<>(lecturerService.updateLecturer(lecturer, lecturerId), HttpStatus.OK);
    }

    @DeleteMapping("/{lecturerId}/")
    public ResponseEntity<Lecturer> deleteLecturer(@PathVariable("lecturerId") String lecturerId) throws ResourceNotFoundException {
        lecturerService.deleteLecturer(lecturerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
