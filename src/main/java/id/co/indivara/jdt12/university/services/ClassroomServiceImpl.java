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
import java.util.Random;

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

        Lecturer lecturerRequest = lecturerService.findById(createClassroomDto.getLecturerId()).orElseThrow(() -> new ResourceNotFoundException("LecturerId not found"));
        Subject subjectRequest = subjectService.findById(createClassroomDto.getSubjectId()).orElseThrow(() -> new ResourceNotFoundException("SubjectId not found"));

        //CHECK EXISTENCE REGISTERED LECTURER TO SUBJECT IN CLASSROOMS TABLE
        List<Classroom> lecturerExist = classroomRepository.findByLecturerId(lecturerRequest);
        List<Classroom> subjectExist = classroomRepository.findBySubjectId(subjectRequest);

        if (!lecturerExist.isEmpty() && !subjectExist.isEmpty()){
            throw new RuntimeException("Lecturer is already registered as lecturer in that subject");
        } else {
            Classroom classroomInput = createClassroomDto.toInitiateClassroom()
                    .setLecturerId(lecturerRequest)
                    .setSubjectId(subjectRequest)
                    .setPeriod(createClassroomDto.getPeriod());

            Random random = new Random();
            classroomInput.setClassroomCode("CRM"+random.nextInt(1000));

            Classroom createdClassroom = create(classroomInput);

            return createdClassroom;
        }

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

    @Override
    public List<Classroom> findClassroomByLecturerId(Lecturer lecturerId) {
        List<Classroom> classroomList = classroomRepository.findByLecturerId(lecturerId);
        return classroomList;
    }

    @Override
    public List<Classroom> findClassroomBySubjectId(Subject subjectId) {
        List<Classroom> classroomList = classroomRepository.findBySubjectId(subjectId);
        return classroomList;
    }
}
