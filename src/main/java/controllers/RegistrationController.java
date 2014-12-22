package controllers;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.DeviceDao;
import dao.UserDao;
import models.Device;
import models.LoginDTO;
import models.UserDTO;
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
    @Inject
    DeviceDao deviceDao;
    public Result registerUser(UserDTO dto) {
        Users person = parceUser(dto);
        if (person !=null && !userDao.createUser(person)){
            Device device = parceDevice(dto, person);
            if (deviceDao.createDevice(device))
                return Results.ok();

        }
        return Results.badRequest();

    }

    private Device parceDevice(UserDTO dto, Users person) {
        return new Device(Lists.newArrayList(person.id),dto.lat,dto.lng);
    }

    private Users parceUser(UserDTO dto) {
        return validate(dto.login,dto.password,dto.fullname) ?
                new Users(dto.login,dto.password.hashCode(),dto.fullname, dto.info):null;
    }

    private boolean validate(String username, String password, String fullname){
        return username!=null && !username.isEmpty() && password!=null && !password.isEmpty() && fullname != null && !fullname.isEmpty();
    }
}
