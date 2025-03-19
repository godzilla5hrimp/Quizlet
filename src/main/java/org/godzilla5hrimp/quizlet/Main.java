package org.godzilla5hrimp.quizlet;

import io.javalin.Javalin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.spi.PersistenceProvider;
import lombok.extern.slf4j.Slf4j;
import gg.jte.CodeResolver;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.TemplateOutput;
import gg.jte.output.StringOutput;
import gg.jte.resolve.DirectoryCodeResolver;

import org.flywaydb.core.Flyway;
import org.godzilla5hrimp.quizlet.controllers.QuizController;
import org.godzilla5hrimp.quizlet.db.DBEntityManager;
import org.godzilla5hrimp.quizlet.service.answer.Answer;
import org.godzilla5hrimp.quizlet.service.question.Question;
import org.godzilla5hrimp.quizlet.service.quiz.Quiz;
import org.godzilla5hrimp.quizlet.service.quizSession.QuizSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

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
        Quiz quiz = new Quiz("firstQuiz");
        String dbHost = System.getenv("PGHOST");
        String dbPort = System.getenv("PGPORT");
        String dbName = System.getenv("POSTGRES_DB");
        String dbUser = System.getenv("POSTGRES_USER");
        String dbPass = System.getenv("POSTGRES_PASSWORD");
        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("jakarta.persistence.jdbc.driver", "org.postgresql.Driver");
        jpaProperties.put("jakarta.persistence.jdbc.url", System.getenv("DATABASE_URL"));
        jpaProperties.put("jakarta.persistence.jdbc.user", System.getenv("POSTGRES_USER"));
        jpaProperties.put("jakarta.persistence.jdbc.password", System.getenv("POSTGRES_PASSWORD"));
        jpaProperties.put("hibernate.hbm2ddl.auto", "update"); // or "create-drop"
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.format_sql", "true");
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit", jpaProperties);
        // EntityManager em = emf.createEntityManager();
        QuizController quizController = new QuizController(app);
        // trying out JPA persistance
        Question question = new Question("How are you today?", "");
        List<Answer> answerList = Arrays.asList(new Answer("Good", true), new Answer("Bad", false), new Answer("So-so", false), new Answer("Never Better", false));
        Map<String, Object> params = new HashMap<>();
        params.put("question", question);
        params.put("answerList", answerList);
        params.put("quiz", quiz);
        final String currentDomain = System.getenv("RAILWAY_PUBLIC_DOMAIN");
        params.put("currentDomain", currentDomain);
        //params.put("jsonEditorSchema", json);
        //QuizSession quizSession = new QuizSession();
        //params.put("quizSession", quizSession);
        Flyway flyway = Flyway.configure()
            .dataSource("jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName, dbUser, dbPass)
            .load();
        flyway.migrate();
        System.out.println("Migration successful!");
        // TODO: move to controllers
        // app.ws("/quiz/{id}", ws -> {
        //     ws.onConnect(ctx -> { 
        //         System.out.println("Connected user");
        //         ctx.enableAutomaticPings(10, TimeUnit.SECONDS, null);
        //         UUID userId = UUID.randomUUID();
        //         ctx.send("welcome");
        //         ctx.attribute("userId", userId.toString());
        //         if (quizSession.userConnected(ctx.attribute("userId"), ctx)) {
        //             ctx.enableAutomaticPings(10, TimeUnit.MILLISECONDS, null);
        //         }
        // });
        //     ws.onMessage(ctx -> {
        //         System.out.println("user connected:" + ctx.message());
        //     });

        //     ws.onClose(ctx -> {
        //         quizSession.userDisconected(ctx.attribute("userId"));
        //         System.out.println("user disconnected");
        //     });
        //     ws.onError(ctx -> {
        //         if (ctx.error() instanceof ClosedChannelException) {
        //             quizSession.userDisconected(ctx.attribute("userId"));
        //         }
        //     });
        // });
        // app.get("/", ctx -> {
        //     TemplateOutput output = new StringOutput();
        //     templateEngine.render("welcome.jte", params, output);
        //     ctx.result(output.toString());
        //     ctx.contentType(String.valueOf(ContentType.Html));
        // });
        // app.put("/quiz/{quizId}", ctx -> {
        //     JsonObject quizConfig = new Gson().fromJson(ctx.body(), JsonObject.class);
        // });
    }

}