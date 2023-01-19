package com.training.interceptor;

import com.training.interceptor.utils.InterceptorUtils;
import io.grpc.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UserServerResponseInterceptor extends InterceptorUtils implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {

        return next.startCall(
                new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {
                    @Override
                    public void sendHeaders(Metadata responseHeaders) {
                        getRequestIdHeader(headers).ifPresent(header -> putRequestIdHeader(responseHeaders, header));

                        super.sendHeaders(responseHeaders);
                    }
                }, headers);
    }
}
