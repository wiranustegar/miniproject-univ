package id.co.indivara.jdt12.university.services;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Lecturer;
import id.co.indivara.jdt12.university.repositories.LecturerRepository;
import id.co.indivara.jdt12.university.services.interfaces.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerServiceImpl implements LecturerService {

    @Autowired
    private LecturerRepository lecturerRepository;

    @Override
    public Optional<Lecturer> findById(String lecturerId) throws ResourceNotFoundException {
        return Optional.ofNullable(lecturerRepository.findById(lecturerId).orElseThrow(() -> new ResourceNotFoundException("lecturer id " + lecturerId + " not found")));
    }

    @Override
    public Lecturer createLecturer(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    @Override
    public List<Lecturer> fetchLecturerList() {
        return lecturerRepository.findAll();
    }

    @Override
    public Lecturer updateLecturer(Lecturer lecturer, String lecturerId) throws ResourceNotFoundException {
        Lecturer lect = lecturerRepository.findById(lecturerId).orElseThrow(()->new ResourceNotFoundException("lecturer id " + lecturerId + " not found"));

        lect.setLecturerName(lecturer.getLecturerName());
        lect.setEmail(lecturer.getEmail());
        lect.setSpecialization(lecturer.getSpecialization());
        lect.setGender(lecturer.getGender());

        return lecturerRepository.save(lect);
    }

    @Override
    public void deleteLecturer(String lecturerId) throws ResourceNotFoundException {
        Lecturer lect =  lecturerRepository.findById(lecturerId).orElseThrow(()->new ResourceNotFoundException("lecturer id " + lecturerId + " not found"));

        lecturerRepository.deleteById(lect.getLecturerId());
    }
}
