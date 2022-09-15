package org.example;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;
import okhttp3.OkHttpClient;

import java.util.concurrent.Callable;

public class BuildInterceptor {

    public static okhttp3.OkHttpClient execute(
            @SuperCall
            Callable<okhttp3.OkHttpClient> client,
            @Origin String method, @This OkHttpClient.Builder builder) throws Exception {

        System.out.println("Build Interceptor was called for: " + method);
        builder.addInterceptor(new HttpInterceptor());
        return client.call();
    }
}
