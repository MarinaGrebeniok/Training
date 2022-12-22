package com.training.service;

import com.training.dao.UserDAO;
import com.training.domain.User;
import com.training.exception.CommonException;
import com.training.grpc.UserServiceOuterClass;

import static java.util.Objects.isNull;

public class UserService {
    private static final String USER_NOT_FOUND = "User not found";

    private UserDAO userDao = new UserDAO();
    public void deleteUser(UserServiceOuterClass.DeleteUserRequest request) {

        User user = userDao.getUser(request.getUserId());

        if (isNull(user)) {
            new CommonException(USER_NOT_FOUND);
        }

        new UserDAO().deleteUser(user);
    }

    public User updateUser(UserServiceOuterClass.UpdateUserRequest request) {
        User user = userDao.getUser(request.getUserId());

        if (isNull(user)) {
            new CommonException(USER_NOT_FOUND);
        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAge(request.getAge());

        return new UserDAO().updateUser(user);
    }

    public User getUser(UserServiceOuterClass.GetUserRequest request) {
        User user = userDao.getUser(request.getUserId());

        if (isNull(user)) {
            new CommonException(USER_NOT_FOUND);
        }

        return user;
    }

    public User createUser(UserServiceOuterClass.CreateUserRequest request) {
        User buildUser = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .build();

        return new UserDAO().saveUser(buildUser);
    }
}
