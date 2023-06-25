package id.co.indivara.jdt12.university.services.interfaces;

import id.co.indivara.jdt12.university.exceptions.ResourceNotFoundException;
import id.co.indivara.jdt12.university.models.Classroom;
import id.co.indivara.jdt12.university.models.Report;
import id.co.indivara.jdt12.university.models.Student;
import id.co.indivara.jdt12.university.models.dtos.InputRecordAchievementDto;
import id.co.indivara.jdt12.university.models.dtos.RegisterStudentDto;

import java.util.ArrayList;
import java.util.List;


public interface ReportService {

    //for save RegisterStudentDto into Report
    Report save(Report report);

    //POST "/report/register/"
    Report registerStudent(RegisterStudentDto registerStudentDto) throws ResourceNotFoundException;

    //GET "/report"
    List<Report> findAll();

    //POST "/report/input-record/"
    Report inputRecordAchievement(InputRecordAchievementDto inputRecordAchievementDto) throws ResourceNotFoundException;

    //GET "/report/{classroomId}"
    ArrayList<Report> findByClassroomId(Classroom classroomId);

    ArrayList<Report> findByStudentId(Student studentId);


}
