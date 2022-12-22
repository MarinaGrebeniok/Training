package com.training.controllers;

import com.training.domain.User;
import com.training.grpc.UserServiceGrpc;
import com.training.grpc.UserServiceOuterClass;
import com.training.service.UserService;
import io.grpc.stub.StreamObserver;

public class UserControllersImpl extends UserServiceGrpc.UserServiceImplBase {
    UserService userServiceImpl = new UserService();

    @Override
    public void deleteUser(UserServiceOuterClass.DeleteUserRequest request,
                           StreamObserver<UserServiceOuterClass.DeleteUserResponse> responseObserver) {
        userServiceImpl.deleteUser(request);

        UserServiceOuterClass.DeleteUserResponse response = UserServiceOuterClass.DeleteUserResponse.newBuilder()
                .setMessage("User deleted")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateUser(UserServiceOuterClass.UpdateUserRequest request,
                           StreamObserver<UserServiceOuterClass.UpdateUserResponse> responseObserver) {
        User user = userServiceImpl.updateUser(request);

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
        User user = userServiceImpl.getUser(request);

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
        User buildUser = userServiceImpl.createUser(request);

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
