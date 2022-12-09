package project.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Posts {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    @lombok.experimental.Tolerate
    public Posts(Integer userId, Integer id, String title, String body){
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @lombok.experimental.Tolerate
    public Posts(Integer userId, String title, String body){
        this.userId = userId;
        this.title = title;
        this.body = body;
    }
}
