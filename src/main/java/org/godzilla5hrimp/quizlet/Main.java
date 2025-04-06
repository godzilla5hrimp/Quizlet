package org.godzilla5hrimp.quizlet;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.flywaydb.core.Flyway;
import org.godzilla5hrimp.quizlet.controllers.AnswerController;
import org.godzilla5hrimp.quizlet.controllers.QuestionController;
import org.godzilla5hrimp.quizlet.controllers.QuizController;
import org.godzilla5hrimp.quizlet.controllers.UserController;

import gg.jte.CodeResolver;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) throws IOException {
        String DB_URL = System.getenv("DB_URL"); // Persistent H2 DB
        String USER = System.getenv("DB_USER");
        String PASSWORD = System.getenv("DB_PASS");
        initializeDatabase(DB_URL, USER, PASSWORD);
        CodeResolver codeResolver = new DirectoryCodeResolver(Path.of("src/main/jte")); // This is the directory where your .jte files are located.
        TemplateEngine.create(codeResolver, ContentType.Html);
        Javalin app = Javalin.create().start(7000);
        QuizController quizController = new QuizController(app);
        QuestionController questionController = new QuestionController(app);
        AnswerController answerController = new AnswerController(app);
        UserController userController = new UserController(app);
    }

    private static void initializeDatabase(final String DB_URL, final String USER, final String PASSWORD) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            
            Flyway flyway = Flyway.configure()
                .dataSource(DB_URL, USER, PASSWORD)
                .locations("classpath:db/migration") // Folder where Flyway looks for migrations
                .baselineOnMigrate(true)
                .load();

            flyway.migrate();
            
            System.out.println("✅ H2 Database Initialized with schema.sql");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}