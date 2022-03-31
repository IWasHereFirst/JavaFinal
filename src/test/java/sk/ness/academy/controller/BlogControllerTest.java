package sk.ness.academy.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sk.ness.academy.domain.Comment;
import sk.ness.academy.dto.ArticleJ;
import sk.ness.academy.service.ArticleService;
import sk.ness.academy.service.AuthorService;
import sk.ness.academy.service.CommentService;

import javax.annotation.Resource;
import java.util.List;

import static io.restassured.RestAssured.given;

@SpringBootTest
class BlogControllerTest {

    static String url = "http://localhost:8080/";

    @Resource
    private ArticleService articleService;

    @Resource
    private CommentService commentService;

    @Resource
    private AuthorService authorService;


    @Test
    void getArticle() {
        int articleId = 0;
        List<ArticleJ> articleList = this.articleService.findAll();
        if(articleList.size() != 0) {
            articleId =  articleList.get(0).getId();
            given().when().get(url + "articles/" + articleId).then().statusCode(200);
        } else {
            given().when().get(url + "articles/" + articleId).then().statusCode(404);
        }
    }

    @Test
    void getAllArticles() {
        given().when().get(url + "articles").then().statusCode(200);
    }

    @Test
    void searchArticle() {
        String text = "";
        List<ArticleJ> articleList = this.articleService.findAll();
        if(articleList.size() != 0) {
            text =  articleList.get(0).getText();
            given().when().get(url + "articles/search/" + text).then().statusCode(200);
        } else {
            given().when().get(url + "articles/search/"  + text).then().statusCode(400);
        }
    }

    @Test
    void addArticle() {
        String input = "{" +
                "\"title\": \"Skusanie API\"," +
                "\"text\": \"Toto je len na test\"," +
                "\"author\": \"Daniel Danielowicz\"" +
                "}";
        given().contentType(ContentType.JSON).body(input).when().put(url + "articles")
                .then().assertThat().statusCode(200);
    }

    @Test
    void deleteArticle() {
        int articleId = 0;
        List<ArticleJ> articleList = this.articleService.findAll();
        if(articleList.size() != 0) {
            articleId =  articleList.get(0).getId();
            given().when().delete(url + "articles/" + articleId).then().statusCode(200);
        } else {
            given().when().delete(url + "articles/" + articleId).then().statusCode(404);
        }
    }

    @Test
    void getAllComments() {
        List<ArticleJ> articleList = this.articleService.findAll();
        if (articleList.size() != 0){
            int articleId = articleList.get(0).getId();
            List<Comment> commentList = this.commentService.findAll(articleId);
            if (commentList.size() != 0) {
                given().when().get(url + "articles/" + articleId + "/comments").then().statusCode(200);
            } else {
                given().when().get(url + "articles/" + articleId + "/comments").then().statusCode(404);
            }
        }
    }

    @Test
    void getComment() {
        List<ArticleJ> articleList = this.articleService.findAll();
        if (articleList.size() != 0){
            int articleId = articleList.get(0).getId();
            List<Comment> commentList = this.commentService.findAll(articleId);
            int commentId = 0;
            if (commentList.size() != 0) {
                commentId = commentList.get(0).getId();
                given().when().get(url + "articles/" + articleId + "/comments/" + commentId).then().statusCode(200);
            } else {
                given().when().get(url + "articles/" + articleId + "/comments/" + commentId).then().statusCode(404);
            }
        }
    }

    @Test
    void addComment() {
        String input = "{" +
                "\"author\": \"Testing comment\"," +
                "\"text\": \"Unit testing\"" +
                "}";
        int articleId = 0;
        List<ArticleJ> articleList = this.articleService.findAll();
        if(articleList.size() != 0) {
            articleId = articleList.get(0).getId();
            given().contentType(ContentType.JSON).body(input).when().put(url + "articles/" + articleId + "/comments")
                    .then().assertThat().statusCode(200);
        }
    }

    @Test
    void removeComment() {
    }

    @Test
    void removeAllComments() {
    }

    @Test
    void getAllAuthors() {
    }

    @Test
    void authorStats() {
    }
}