package org.godzilla5hrimp.quizlet;

import gg.jte.CodeResolver;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.TemplateOutput;
import gg.jte.output.StringOutput;
import gg.jte.resolve.DirectoryCodeResolver;
import io.javalin.Javalin;
import org.godzilla5hrimp.quizlet.service.answer.Answer;
import org.godzilla5hrimp.quizlet.service.question.Question;

import com.zaxxer.hikari.HikariConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        CodeResolver codeResolver = new DirectoryCodeResolver(Path.of("/home/0c01/Projects/Quizlet/src/main/resources/templates/")); // This is the directory where your .jte files are located.
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html); // Two choices: Plain or Html
        Javalin app = Javalin.create().start(7000);
        HikariConfig config = new HikariConfig(""); //TODO: set up a HikariCP property file        
        config.setUsername(System.getenv().get("quizletClient"));
        config.setPassword(System.getenv().get("quizletClientPsw"));
        Question question = new Question("How are you today?", "");
        List<Answer> answerList = Arrays.asList(new Answer("Good", true), new Answer("Bad", false), new Answer("So-so", false), new Answer("Never Better", false));
        //String file = "/home/hillayer/IdeaProjects/Quizlet/src/main/resources/templates/quizRoundScreen.json";
        //String json = Arrays.toString(Files.readAllBytes(Paths.get(file)));
        Map<String, Object> params = new HashMap<>();
        params.put("question", question);
        params.put("answerList", answerList);
        //params.put("jsonEditorSchema", json);
        app.get("/", ctx -> {
            TemplateOutput output = new StringOutput();
            templateEngine.render("quizRound.jte", params, output);
            ctx.result(output.toString());
            ctx.contentType(String.valueOf(ContentType.Html));
        });
    }
}