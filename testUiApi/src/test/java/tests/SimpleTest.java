package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import framework.apiutils.BaseRequest;
import framework.apiutils.BaseResponse;
import framework.baseutils.ConfigManager;
import framework.driver.DriverSingleton;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import project.forms.CommentsResourcesForm;
import project.forms.HomePage;
import project.forms.PostsResourcesForm;
import project.models.Comments;
import project.models.Posts;
import project.utils.ConvertManager;
import project.utils.RandomManager;

import java.util.*;

public class SimpleTest extends BaseTest {
    @Test
    public void testingGetRequests() throws JsonProcessingException {
        int loggerStep = 0;
        Logger.getLogger(SimpleTest.class).info("Testing Get requests. Step " + ++loggerStep +
                " : click the link /posts from UI " +
                "and checking the size of the received posts list and the id of the first post");
        HomePage homePage = HomePage.getInstance();
        assert homePage.isOpen() : "Home page is not open";
        Logger.getLogger(SimpleTest.class).info("Home page is open");
        Integer numberOfPosts = homePage.getNumberOfPosts();
        homePage.clickPostsLink();
        PostsResourcesForm postsResourcesForm = PostsResourcesForm.getInstance();
        assert postsResourcesForm.isOpen() : "Posts resources form is not open";
        Logger.getLogger(SimpleTest.class).info("Posts resources form is open");
        String postsData = postsResourcesForm.getText();
        List<Posts> postsListUI = ConvertManager.convertJsonPosts(postsData);
        Assert.assertEquals(postsListUI.size(), numberOfPosts,
                "The number of Posts elements is not equal to the one specified in the resources");
        Integer postId = postsListUI.get(0).getId();
        Assert.assertEquals(postId, 1, "The id of the first Post is not equal to 1");
        DriverSingleton.back();

        Logger.getLogger(SimpleTest.class).info("Testing Get requests. Step " + ++loggerStep +
                " : sending a GET request using endpoint " +
                "/posts from API and comparing the list of posts received from API with the list received from UI");
        Response getPosts = BaseRequest.get(ConfigManager.readerStr("endPointPosts", "endpointdata.json"));
        Assert.assertEquals(BaseResponse.getStatusCode(getPosts), HttpStatus.SC_OK, "Status cod is not 'OK'");
        List<Posts> postsListAPI = BaseResponse.getListOfObjectsFromJsonPath(Posts.class, getPosts, "");
        Assert.assertEquals(postsListAPI, postsListUI,
                "The list of Posts received from API not equals to the list of Posts received from UI");

        Logger.getLogger(SimpleTest.class).info("Testing Get requests. Step " + ++loggerStep +
                " : sending a GET request from API using " +
                "endpoint /posts/outsideIdValue where outsideIdValue is an id out of bounds posts id " +
                "and checking the status code is 'NOT FOUND'");
        Integer postOutsideId = postsListUI.get(postsListUI.size() - 1).getId() + 1;
        Response getPostOutsideId = BaseRequest.get(String.format(ConfigManager.readerStr("endPointPostsWithId",
                "endpointdata.json"), postOutsideId));
        Assert.assertEquals(BaseResponse.getStatusCode(getPostOutsideId), HttpStatus.SC_NOT_FOUND,
                "Status cod is not 'NOT FOUND'");

        Logger.getLogger(SimpleTest.class).info("Testing Get requests. Step " + ++loggerStep +
                " : click the link /comments from UI " +
                "and checking the size of the received comments list");
        Integer numberOfComments = homePage.getNumberOfComments();
        homePage.clickCommentsLink();
        CommentsResourcesForm commentsResourcesForm = CommentsResourcesForm.getInstance();
        assert commentsResourcesForm.isOpen() : "Comments resources form is not open";
        Logger.getLogger(SimpleTest.class).info("Comments resources form is open");
        String commentsData = commentsResourcesForm.getText();
        List<Comments> commentsListUI = ConvertManager.convertJsonComments(commentsData);
        Assert.assertEquals(commentsListUI.size(), numberOfComments,
                "The number of Comments elements is not equal to the one specified in the resources");
        DriverSingleton.back();

        Logger.getLogger(SimpleTest.class).info("Testing Get requests. Step " + ++loggerStep +
                " : sending a GET request using endpoint /comments from API " +
                "and comparing the list of comments received from API with the list received from UI");
        Response getComments = BaseRequest.get(ConfigManager.readerStr("endPointComments", "endpointdata.json"));
        Assert.assertEquals(BaseResponse.getStatusCode(getComments), HttpStatus.SC_OK, "Status cod is not 'OK'");
        List<Comments> commentsListAPI = BaseResponse.getListOfObjectsFromJsonPath(Comments.class, getComments, "");
        Assert.assertEquals(commentsListAPI, commentsListUI,
                "The list of Comments received from API not equals to the list of Comments received from UI");

        Logger.getLogger(SimpleTest.class).info("Testing Get requests. Step " + ++loggerStep +
                " : sending a GET request from API using " +
                "endpoint /comments/outsideIdValue where outsideIdValue is an id out of bounds comments id " +
                "and checking the status code is 'NOT FOUND'");
        Integer commentOutsideId = commentsListUI.get(commentsListUI.size() - 1).getId() + 1;
        System.out.println(commentOutsideId);
        Response getCommentOutsideId = BaseRequest.get(String.format(ConfigManager.readerStr("endPointCommentsWithId",
                "endpointdata.json"), commentOutsideId));
        Assert.assertEquals(BaseResponse.getStatusCode(getCommentOutsideId), HttpStatus.SC_NOT_FOUND,
                "Status cod is not 'NOT FOUND'");
    }

    @Test
    public void testingPostRequests(){
        int loggerStep = 0;
        Logger.getLogger(SimpleTest.class).info("Testing Post requests. Step " + ++loggerStep +
                " : sending a POST request with a random title, body and userId " + "using endpoint /posts from API ");
        HomePage homePage = HomePage.getInstance();
        assert homePage.isOpen() : "Home page is not open";
        Logger.getLogger(SimpleTest.class).info("Home page is open");
        Response getPosts = BaseRequest.get(ConfigManager.readerStr("endPointPosts", "endpointdata.json"));
        Assert.assertEquals(BaseResponse.getStatusCode(getPosts), HttpStatus.SC_OK, "Status cod is not 'OK'");
        List userIdList = BaseResponse.getListFromBody(getPosts, "userId");
        int randomUserId = Integer.parseInt (RandomManager.getRandomListItem(userIdList));
        String randomTitle = RandomManager.getRandomText(
                ConfigManager.readerLong("minSizeTitle", "testdata.json").intValue(),
                ConfigManager.readerLong("maxSizeTitle", "testdata.json").intValue());
        String randomBody = RandomManager.getRandomText(
                ConfigManager.readerLong("minSizeBody", "testdata.json").intValue(),
                ConfigManager.readerLong("maxSizeBody", "testdata.json").intValue());
        Response postPost = BaseRequest.postJson(ConfigManager.readerStr("endPointPosts", "endpointdata.json"),
                new Posts(randomUserId, randomTitle, randomBody));
        Assert.assertEquals(BaseResponse.getStatusCode(postPost), HttpStatus.SC_CREATED, "Status cod is not 'CREATED'");
        Posts createdPost = BaseResponse.getClass(Posts.class, postPost);
        Assert.assertTrue(createdPost.getTitle().equals(randomTitle) & createdPost.getBody().equals(randomBody) &
                createdPost.getUserId().equals(randomUserId), "The received post data is match to the sent one");
        Assert.assertNotEquals(createdPost.getId(), null, "The received post id is not null");

        Logger.getLogger(SimpleTest.class).info("Testing Post requests. Step " + ++loggerStep +
                " : sending a POST request with a random name, email, body and " +
                "postId using endpoint /comments from API ");
        Response getComments = BaseRequest.get(ConfigManager.readerStr("endPointComments", "endpointdata.json"));
        Assert.assertEquals(BaseResponse.getStatusCode(getPosts), HttpStatus.SC_OK, "Status cod is not 'OK'");
        List postIdList = BaseResponse.getListFromBody(getComments, "postId");
        int randomPostId = Integer.parseInt(RandomManager.getRandomListItem(postIdList));
        String randomName = RandomManager.getRandomText(
                ConfigManager.readerLong("minSizeName", "testdata.json").intValue(),
                ConfigManager.readerLong("maxSizeName", "testdata.json").intValue());
        String randomEmail = RandomManager.getRandomEmail();
        String randomBodyNew = RandomManager.getRandomText(
                ConfigManager.readerLong("minSizeBody", "testdata.json").intValue(),
                ConfigManager.readerLong("maxSizeBody", "testdata.json").intValue());
        Response postComment = BaseRequest.postJson(ConfigManager.readerStr("endPointComments", "endpointdata.json"),
                new Comments(randomPostId, randomName, randomEmail, randomBodyNew));
        Assert.assertEquals(BaseResponse.getStatusCode(postComment), HttpStatus.SC_CREATED, "Status cod is not 'CREATED'");
        Comments createdComment = BaseResponse.getClass(Comments.class, postComment);
        Assert.assertTrue(createdComment.getPostId().equals(randomPostId) &
                        createdComment.getBody().equals(randomBodyNew) & createdComment.getEmail().equals(randomEmail) &
                        createdComment.getName().equals(randomName), "The received comment data is match to the sent one");
        Assert.assertNotEquals(createdComment.getId(), null, "The received comment id is not null");
    }
}