package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.UserDao;
import models.LoginDTO;
import models.Users;
import ninja.Result;
import ninja.Results;

/**
 * Created by vlad on 12/21/14.
 */
@Singleton
public class RegistrationController {
    @Inject
    UserDao userDao;

    public Result parseUser(Users person) {
        if (!validate(person) && !userDao.createUser(person))
            return Results.json().render(new LoginDTO(person.username,false));
        return Results.json().render(new LoginDTO(person.username,true));

    }

    private boolean validate(Users users){
        return true;
    }
}
