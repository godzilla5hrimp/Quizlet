package org.godzilla5hrimp.quizlet;

import io.javalin.Javalin;
import gg.jte.CodeResolver;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.TemplateOutput;
import gg.jte.output.StringOutput;
import gg.jte.resolve.DirectoryCodeResolver;

import org.flywaydb.core.Flyway;
import org.godzilla5hrimp.quizlet.service.answer.Answer;
import org.godzilla5hrimp.quizlet.service.question.Question;
import org.godzilla5hrimp.quizlet.service.quiz.Quiz;
import org.godzilla5hrimp.quizlet.service.quizSession.QuizSession;

import com.auth0.jwt.algorithms.Algorithm;

import com.zaxxer.hikari.HikariConfig;  

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) throws IOException {
        CodeResolver codeResolver = new DirectoryCodeResolver(Path.of("src/main/jte")); // This is the directory where your .jte files are located.
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html); // Two choices: Plain or Html
        Javalin app = Javalin.create().start(7000);
        Algorithm algorithm = Algorithm.HMAC256("very_secret");
        
        System.out.println(algorithm);
        HikariConfig config = new HikariConfig(""); //TODO: set up a HikariCP property file        
        config.setUsername(System.getenv().get("quizletClient"));
        config.setPassword(System.getenv().get("quizletClientPsw"));
        Quiz quiz = new Quiz("firstQuiz");
        //quiz.setQuestionIdList(Arrays.asList("1"));
        Question question = new Question("How are you today?", "");
        List<Answer> answerList = Arrays.asList(new Answer("Good", true), new Answer("Bad", false), new Answer("So-so", false), new Answer("Never Better", false));
        //String file = "/home/hillayer/IdeaProjects/Quizlet/src/main/resources/templates/quizRoundScreen.json";
        //String json = Arrays.toString(Files.readAllBytes(Paths.get(file)));
        Map<String, Object> params = new HashMap<>();
        params.put("question", question);
        params.put("answerList", answerList);
        params.put("quiz", quiz);
        final String currentDomain = System.getenv("RAILWAY_PUBLIC_DOMAIN");
        params.put("currentDomain", currentDomain);
        //params.put("jsonEditorSchema", json);
        QuizSession quizSession = new QuizSession("firstSession");
        quizSession.setQuizId("firstQuiz");
        params.put("quizSession", quizSession);
        Flyway flyway = Flyway.configure()
            .dataSource(System.getenv("DATABASE_PUBLIC_URL").toString(), 
                System.getenv().get("PGUSER").toString(), System.getenv("PGPASSWORD").toString())
            .load();
        flyway.migrate();
        System.out.println("Migration successful!");
        app.ws("/quiz/{id}", ws -> {
            ws.onConnect(ctx -> { 
                System.out.println("Connected user");
                ctx.enableAutomaticPings(10, TimeUnit.SECONDS, null);
                UUID userId = UUID.randomUUID();
                ctx.send("welcome");
                ctx.attribute("userId", userId.toString());
                if (quizSession.userConnected(ctx.attribute("userId"), ctx)) {
                    ctx.enableAutomaticPings(10, TimeUnit.MILLISECONDS, null);
                }
        });
            ws.onMessage(ctx -> {
                System.out.println("user connected:" + ctx.message());
            });

            ws.onClose(ctx -> {
                quizSession.userDisconected(ctx.attribute("userId"));
                System.out.println("user disconnected");
            });
            ws.onError(ctx -> {
                if (ctx.error() instanceof ClosedChannelException) {
                    quizSession.userDisconected(ctx.attribute("userId"));
                }
            });
        });
        app.get("/", ctx -> {
            TemplateOutput output = new StringOutput();
            templateEngine.render("quizRound.jte", params, output);
            ctx.result(output.toString());
            ctx.contentType(String.valueOf(ContentType.Html));
        });
    }

}