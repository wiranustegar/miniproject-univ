package id.co.indivara.jdt12.university.services.interfaces;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Lecturer;

import java.util.List;
import java.util.Optional;

public interface LecturerService  {

    //GET "/lecturer/{lecturerId}"
    Optional<Lecturer> findById(String lecturerId) throws ResourceNotFoundException;

    //POST "/lecturer/"
    Lecturer createLecturer(Lecturer lecturer);

    //GET "/lecturer/"
    List<Lecturer> fetchLecturerList();

    //PUT "/lecturer/{lecturerId}"
    Lecturer updateLecturer(Lecturer lecturer, String lecturerId) throws ResourceNotFoundException;

    //DELETE "/lecturer/{lecturerId}"
    void deleteLecturer(String lecturerId) throws ResourceNotFoundException;
}
