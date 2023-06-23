package id.co.indivara.jdt12.university.services;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Classroom;
import id.co.indivara.jdt12.university.models.Report;
import id.co.indivara.jdt12.university.models.Student;
import id.co.indivara.jdt12.university.models.dtos.InputRecordAchievementDto;
import id.co.indivara.jdt12.university.models.dtos.RegisterStudentDto;
import id.co.indivara.jdt12.university.repositories.ClassroomRepository;
import id.co.indivara.jdt12.university.repositories.ReportRepository;
import id.co.indivara.jdt12.university.repositories.StudentRepository;
import id.co.indivara.jdt12.university.services.interfaces.ClassroomService;
import id.co.indivara.jdt12.university.services.interfaces.ReportService;
import id.co.indivara.jdt12.university.services.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private ClassroomRepository classroomRepository;


    @Override
    public Report save(Report report) {

        return reportRepository.save(report);
    }

    @Override
    public Report registerStudent(RegisterStudentDto registerStudentDto) throws ResourceNotFoundException {

        Student studentRequest = studentService.findById(registerStudentDto.getStudentId()).orElseThrow(() -> new ResourceNotFoundException("StudentId not found"));
        Classroom classroomRequest = classroomService.findById(registerStudentDto.getClassroomId()).orElseThrow(() -> new ResourceNotFoundException("StudentId not found"));

        Report reportInput = registerStudentDto.toRegisterStudent()
                    .setStudentId(studentRequest)
                    .setClassroomId(classroomRequest);

            Report registeredStudent = save(reportInput);

            return registeredStudent;


//        Student existingStudent = reportRepository.findById(registerStudentDto.getStudentId()).ge;
//        Classroom existingClassroom = classroomRepository.findById(registerStudentDto.getClassroomId()).get();
//
//        if (studentRequest.equals(existingStudent) && classroomRequest.equals(existingClassroom)){
//            throw new DuplicateKeyException("df");
//        } else {
//            Report reportInput = registerStudentDto.toRegisterStudent()
//                    .setStudentId(studentRequest)
//                    .setClassroomId(classroomRequest);
//
//            Report registeredStudent = save(reportInput);
//
//            return registeredStudent;
//        }

    }

    @Override
    public List<Report> findAll() {
        List<Report> reports = new ArrayList<>();
        reportRepository.findAll().forEach(reports::add);
        return reports;
    }

    @Override
    public Report inputRecordAchievement(InputRecordAchievementDto inputRecordAchievementDto) throws ResourceNotFoundException {

        Report updateReport = reportRepository.findById(inputRecordAchievementDto.getReportId()).orElseThrow(()->new ResourceNotFoundException("report id not found"));

        int count = (inputRecordAchievementDto.getQuizTest() + inputRecordAchievementDto.getMidTest() + inputRecordAchievementDto.getFinalTest()) / 3;
        char grade;

        if (count > 80){
            grade = 'A';
        } else if (count >= 75) {
            grade = 'B';
        } else if (count >= 70) {
            grade = 'C';
        } else if (count > 65) {
            grade = 'D';
        } else {
            grade = 'E';
        }

        //GET ALL RECORD FROM DTO
        Report inputRecordDto = inputRecordAchievementDto.toInputRecordAchievement()
                .setQuizTest(inputRecordAchievementDto.getQuizTest())
                .setMidTest(inputRecordAchievementDto.getMidTest())
                .setFinalTest(inputRecordAchievementDto.getFinalTest());

        //SAVE ALL FROM DTO TO REPORT
        updateReport.setQuizTest(inputRecordDto.getQuizTest())
                .setMidTest(inputRecordDto.getMidTest())
                .setFinalTest(inputRecordDto.getFinalTest())
                .setGrade(grade);

        Report inputRecord = save(updateReport);

        return inputRecord;
    }

    @Override
    public ArrayList<Report> findByClassroomId(Classroom classroomId) {
        ArrayList<Report> reports = (ArrayList<Report>) reportRepository.findByClassroomId(classroomId);
        return reports;
    }

}
