package id.co.indivara.jdt12.university.models.dtos;

import id.co.indivara.jdt12.university.models.Report;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class InputRecordAchievementDto {

    @NotNull(message = "report id is required")
    private String reportId;

    @NotNull(message = "quiz test is required to fill")
    private Integer quizTest;

    @NotNull(message = "mid test is required to fill")
    private Integer midTest;

    @NotNull(message = "final test is required to fill")
    private Integer finalTest;

    public Report toInputRecordAchievement(){
        return new Report()
                .setQuizTest(quizTest)
                .setMidTest(midTest)
                .setFinalTest(finalTest);
    }
}
