package com.training.service;

import com.training.dao.UserDAO;
import com.training.domain.User;
import com.training.dto.UserDTO;
import com.training.exception.CommonException;

import static java.util.Objects.isNull;

public class UserService {
    private static final String USER_NOT_FOUND = "User not found";

    private final UserDAO userDao = new UserDAO();
    public void deleteUser(Integer userId) {

        User user = userDao.getUser(userId);

        if (isNull(user)) {
            throw new  CommonException(USER_NOT_FOUND);
        }

        new UserDAO().deleteUser(user);
    }

    public User updateUser(UserDTO userDTO) {
        User user = userDao.getUser(userDTO.getUserId());

        if (isNull(user)) {
            throw new CommonException(USER_NOT_FOUND);
        }

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());

        return new UserDAO().updateUser(user);
    }

    public User getUser(Integer userId) {
        User user = userDao.getUser(userId);

        if (isNull(user)) {
            throw new CommonException(USER_NOT_FOUND);
        }

        return user;
    }

    public User createUser(UserDTO userDTO) {
        User buildUser = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .age(userDTO.getAge())
                .build();

        return new UserDAO().saveUser(buildUser);
    }
}
