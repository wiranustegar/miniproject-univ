package id.co.indivara.jdt12.university.services;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Classroom;
import id.co.indivara.jdt12.university.models.Lecturer;
import id.co.indivara.jdt12.university.models.Subject;
import id.co.indivara.jdt12.university.models.dtos.CreateClassroomDto;
import id.co.indivara.jdt12.university.repositories.ClassroomRepository;
import id.co.indivara.jdt12.university.services.interfaces.ClassroomService;
import id.co.indivara.jdt12.university.services.interfaces.LecturerService;
import id.co.indivara.jdt12.university.services.interfaces.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassroomServiceImpl implements ClassroomService {


    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private SubjectService subjectService;

    @Override
    public Classroom create(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    @Override
    public Classroom initiateClassroom(CreateClassroomDto createClassroomDto) throws ResourceNotFoundException {

        Lecturer lect = lecturerService.findById(createClassroomDto.getLecturerId()).orElseThrow(() -> new ResourceNotFoundException("LecturerId not found"));
        Subject subj = subjectService.findById(createClassroomDto.getSubjectId()).orElseThrow(() -> new ResourceNotFoundException("LecturerId not found"));

        Classroom classroomInput = createClassroomDto.toInitiateClassroom()
                .setLecturerId(lect)
                .setSubjectId(subj);

        Classroom createdClassroom = create(classroomInput);

        return createdClassroom;

    }

    @Override
    public List<Classroom> findAll() {
        List<Classroom> classrooms = new ArrayList<>();

        classroomRepository.findAll().forEach(classrooms :: add);

        return classrooms;
    }

    @Override
    public Optional<Classroom> findById(String classroomId) throws ResourceNotFoundException {
        return Optional.ofNullable(classroomRepository.findById(classroomId).orElseThrow(() -> new ResourceNotFoundException("classroom id " + classroomId + " not found")));
    }
}
