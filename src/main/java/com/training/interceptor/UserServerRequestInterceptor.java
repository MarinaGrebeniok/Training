package com.training.interceptor;

import com.training.interceptor.utils.InterceptorUtils;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import lombok.extern.log4j.Log4j2;

import java.text.MessageFormat;
import java.util.UUID;

@Log4j2
public class UserServerRequestInterceptor extends InterceptorUtils implements ServerInterceptor {
    private static final String REQUEST_ID_MESSAGE_TEMPLATE = "RequestId: {0}";

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        String idHeader = UUID.randomUUID().toString();

        putRequestIdHeader(headers, idHeader);
        log.info(MessageFormat.format(REQUEST_ID_MESSAGE_TEMPLATE, idHeader));

        return next.startCall(call, headers);
    }
}
