package id.co.indivara.jdt12.university.services;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Subject;
import id.co.indivara.jdt12.university.repositories.SubjectRepository;
import id.co.indivara.jdt12.university.services.interfaces.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Optional<Subject> findById(String subjectId) {
        return subjectRepository.findById(subjectId);
    }

    @Override
    public Subject createSubject(Subject subject) {
        Random random = new Random();
        subject.setSubjectCode("SUB" + (random.nextInt(100)));
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> fetchSubjectList() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject updateSubject(Subject subject, String subjectId) throws ResourceNotFoundException {
        Subject subj = subjectRepository.findById(subjectId).orElseThrow(()->new ResourceNotFoundException("student id " +subjectId+ " not found"));

        subj.setSubjectName(subject.getSubjectName());

        return subjectRepository.save(subj);
    }

    @Override
    public void deleteSubject(String subjectId) throws ResourceNotFoundException {
        Subject subj = subjectRepository.findById(subjectId).orElseThrow(()->new ResourceNotFoundException("student id "+ subjectId + " not found"));

        subjectRepository.deleteById(subj.getSubjectId());
    }
}
