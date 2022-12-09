package project.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import project.models.Comments;
import project.models.Posts;

import java.util.Arrays;
import java.util.List;

public class ConvertManager {
    public static List<Posts> convertJsonPosts(String resultJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(resultJson, Posts[].class));
    }

    public static List<Comments> convertJsonComments(String resultJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(resultJson, Comments[].class));
    }
}