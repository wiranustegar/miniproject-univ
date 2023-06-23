package id.co.indivara.jdt12.university.models.dtos;

import id.co.indivara.jdt12.university.models.Report;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterStudentDto {

    @NotNull(message = "studentId is required")
    private String studentId;

    @NotNull(message = "classroomId is required")
    private String classroomId;


    public Report toRegisterStudent(){
        return new Report();
    }

}
