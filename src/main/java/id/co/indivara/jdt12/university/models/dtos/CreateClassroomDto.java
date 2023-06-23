package id.co.indivara.jdt12.university.models.dtos;

import id.co.indivara.jdt12.university.models.Classroom;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateClassroomDto {
//    @NotNull(message = "Classroom id is required to be created")
//    private String classroomId;

    @NotNull(message = "lecturerId is required")
    private String lecturerId;

    @NotNull(message = "subjectId is required")
    private String subjectId;

    @NotNull(message = "classroom code is required")
    private String classroomCode;

    @NotNull(message = "period is required")
    private String period;

    public Classroom toInitiateClassroom(){
        return new Classroom()
                .setClassroomCode(classroomCode)
                .setPeriod(period);
    }

}
