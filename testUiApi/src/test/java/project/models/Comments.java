package project.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comments {
    private Integer postId;
    private Integer id;
    private String name;
    private String email;
    private String body;

    @lombok.experimental.Tolerate
    public Comments(Integer postId, Integer id, String name, String email, String body){
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    @lombok.experimental.Tolerate
    public Comments(Integer postId, String name, String email, String body){
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
    }
}
