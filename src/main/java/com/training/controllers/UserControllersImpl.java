package com.training.controllers;

import com.training.domain.User;
import com.training.dto.UserDTO;
import com.training.grpc.UserServiceGrpc;
import com.training.grpc.UserServiceOuterClass;
import com.training.service.UserService;
import io.grpc.stub.StreamObserver;

public class UserControllersImpl extends UserServiceGrpc.UserServiceImplBase {
    UserService userServiceImpl = new UserService();

    @Override
    public void deleteUser(UserServiceOuterClass.DeleteUserRequest request,
                           StreamObserver<UserServiceOuterClass.DeleteUserResponse> responseObserver) {
        userServiceImpl.deleteUser(request.getUserId());

        UserServiceOuterClass.DeleteUserResponse response = UserServiceOuterClass.DeleteUserResponse.newBuilder()
                .setMessage("User deleted")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateUser(UserServiceOuterClass.UpdateUserRequest request,
                           StreamObserver<UserServiceOuterClass.UpdateUserResponse> responseObserver) {
        UserDTO userDTO = UserDTO.builder()
                .userId(request.getUserId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .build();

        User user = userServiceImpl.updateUser(userDTO);

        UserServiceOuterClass.UpdateUserResponse response = UserServiceOuterClass.UpdateUserResponse.newBuilder()
                .setUser(UserServiceOuterClass.User.newBuilder()
                        .setUserId(user.getUserId())
                        .setFirstName(user.getFirstName())
                        .setLastName(user.getLastName())
                        .setAge(user.getAge())
                        .build())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUser(UserServiceOuterClass.GetUserRequest request,
                        StreamObserver<UserServiceOuterClass.GetUserResponse> responseObserver) {
        User user = userServiceImpl.getUser(request.getUserId());

        UserServiceOuterClass.GetUserResponse response = UserServiceOuterClass.GetUserResponse.newBuilder()
                .setUser(UserServiceOuterClass.User.newBuilder()
                        .setUserId(user.getUserId())
                        .setFirstName(user.getFirstName())
                        .setLastName(user.getLastName())
                        .setAge(user.getAge())
                        .build())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createUser(UserServiceOuterClass.CreateUserRequest request,
                           StreamObserver<UserServiceOuterClass.CreateUserResponse> responseObserver) {
        UserDTO userDTO = UserDTO.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .build();

        User buildUser = userServiceImpl.createUser(userDTO);

        UserServiceOuterClass.CreateUserResponse response = UserServiceOuterClass.CreateUserResponse
                .newBuilder()
                .setUser(UserServiceOuterClass.User.newBuilder()
                        .setUserId(buildUser.getUserId())
                        .setFirstName(buildUser.getFirstName())
                        .setLastName(buildUser.getLastName())
                        .setAge(buildUser.getAge())
                        .build())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
