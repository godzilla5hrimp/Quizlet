package org.godzilla5hrimp.quizlet.service.quiz;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.util.JSONPObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quiz {
    private String id;
    private List<String> questionIdList;
    private Date creationDate;
    private Date updateDate;
    private String userCreate;
    private String userUpdate;
    private Long version;
    private JSONPObject jsonQuizExport;

    public Quiz(final String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String exportJson() {
        return jsonQuizExport.toString();
    }
}
