package org.example;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import okhttp3.OkHttpClient;

import java.util.concurrent.Callable;
// to intercept newBuilder method of OkHttpClient
public class BuilderInterceptor {

    public static OkHttpClient.Builder execute(
            @SuperCall
            Callable<OkHttpClient.Builder> client,
            @Origin String method) throws Exception {

        System.out.println("New Builder Interceptor was called for: " + method);

        OkHttpClient.Builder builder = client.call();
        builder.addInterceptor(new HttpInterceptor());
        return builder;
    }
}
