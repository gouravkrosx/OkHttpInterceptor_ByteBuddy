package org.example;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.concurrent.Callable;

public class ConstructorInterceptor {

    public static void doProceed(
            @SuperCall
            Callable<?> client,
            @Argument(0)
            OkHttpClient okHttpClient, @Origin String method) throws Exception {

        System.out.println("Constructor Interceptor was called for: " + method);
        client.call();
//        okHttpClient.interceptors().add(new HttpInterceptor());
    }
}
