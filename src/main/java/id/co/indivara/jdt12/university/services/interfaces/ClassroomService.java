package id.co.indivara.jdt12.university.services.interfaces;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Classroom;
import id.co.indivara.jdt12.university.models.dtos.CreateClassroomDto;

import java.util.List;
import java.util.Optional;

public interface ClassroomService {

    //for save CreateClassroomDto into Classroom
    Classroom create(Classroom classroom);

    //POST "/classroom/"
    Classroom initiateClassroom(CreateClassroomDto createClassroomDto) throws ResourceNotFoundException;

    //GET "/classroom/"
    List<Classroom> findAll();

    //GET "/classroom/{classroomId}"
    Optional<Classroom> findById(String classroomId) throws ResourceNotFoundException;

}
