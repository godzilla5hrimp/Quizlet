package org.godzilla5hrimp.quizlet.controllers;

import java.util.List;
import java.util.UUID;

import org.godzilla5hrimp.quizlet.db.UserDao;
import org.godzilla5hrimp.quizlet.service.user.User;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.javalin.Javalin;
import io.javalin.http.Context;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserController {
    UserDao userDao;

    public UserController(final Javalin app) {
        this.userDao = new UserDao();
        app.post("/user/{userId}", this::saveUser);
        app.post("/user", this::updateUser);
        app.get("/user/all", this::getAll);
        app.get("/user/{userId}", this::getUser);
    }
    
    public void saveUser(final Context ctx) {
        // check for 
        User user = new Gson().fromJson(ctx.body(), User.class);
        if (!user.equals(null)) {
            userDao.saveUser(null);
            ctx.status(200);
            ctx.result("success");
        } else {
            ctx.status(500);
            ctx.result("internal server error");
        }
    }

    public void updateUser(final Context ctx) {
        // check for 
        User user = new Gson().fromJson(ctx.body(), User.class);
        if (!user.equals(null)) {
            userDao.updateUser(user);
            ctx.status(200);
            ctx.result("success");
        } else {
            ctx.status(500);
            ctx.result("internal server error");
        }
    }

    public void getUser(final Context ctx) {
        try {
            User result = userDao.getUser(UUID.fromString(ctx.pathParam("userId")));
            if (!result.equals(null)) {
                ctx.json(result);
            } else {
                JsonObject errObject = new JsonObject();
                errObject.add("error", new Gson().toJsonTree("error fetching user"));
                ctx.json(errObject);
            }
        } catch(Exception e) {
            log.error("error fetching quiz with exception {}", e);
        }
    }

    public void getAll(final Context ctx) {
        try {
            List<User> result = userDao.getAllUsers();
            if (!result.equals(null)) {
                ctx.json(result);
            } else {
                JsonObject errObject = new JsonObject();
                errObject.add("error", new Gson().toJsonTree("error fetching all users"));
                ctx.json(errObject);
            }
        } catch(Exception e) {
            log.error("error fetching all users {}", e);
        }
    }
}
