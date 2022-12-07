package exercise.controllers;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.javalin.core.validation.JavalinValidation;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.Validator;
import io.javalin.http.Handler;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;
import java.util.Map;

public final class UserController {

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
                .id.equalTo(id)
                .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {
        // BEGIN
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        Validator<String> firstNameValidator = ctx.formParamAsClass("firstName", String.class)
                .check(it -> !it.isEmpty(), "Firstname shouldn't be empty");
        Validator<String> lastNameValidator = ctx.formParamAsClass("lastName", String.class)
                .check(it -> !it.isEmpty(), "Lastname shouldn't be empty");
        Validator<String> passwordValidator = ctx.formParamAsClass("password", String.class)
                .check(StringUtils::isNumeric, "Password should be numeric only")
                .check(it -> it.length() > 3, "Password length should be more than 3");
        Validator<String> emailValidator = ctx.formParamAsClass("email", String.class)
                .check(it -> EmailValidator.getInstance().isValid(it), "Email should be valid");

        Map<String, List<ValidationError<? extends Object>>> errors = JavalinValidation.collectErrors(
                firstNameValidator,
                lastNameValidator,
                emailValidator,
                passwordValidator);

        if (!errors.isEmpty()) {
            ctx.status(422);
            ctx.attribute("errors", errors);
            ctx.attribute("user", new User(firstName, lastName, email, password));
            ctx.render("/users/new.html");
            return;
        }
        new User(firstName, lastName, email, password).save();
        ctx.sessionAttribute("flash", "User created successfully");
        ctx.redirect("/users");
        // END
    };
}
