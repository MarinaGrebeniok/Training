package com.training;

import com.training.controllers.UserControllersImpl;
import com.training.interceptor.UserServerRequestInterceptor;
import com.training.interceptor.UserServerResponseInterceptor;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        io.grpc.Server server = ServerBuilder.forPort(8090)
                .addService(
                        ServerInterceptors.intercept(
                                new UserControllersImpl(),
                                new UserServerRequestInterceptor(),
                                new UserServerResponseInterceptor())
                ).build();

        server.start();

        System.out.println("Server started");
        server.awaitTermination();
    }
}