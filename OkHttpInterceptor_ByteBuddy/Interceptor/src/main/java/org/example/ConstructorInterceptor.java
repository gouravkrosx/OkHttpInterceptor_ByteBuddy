package org.example;

import net.bytebuddy.implementation.bind.annotation.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.lang.reflect.Constructor;
import java.util.concurrent.Callable;

public class ConstructorInterceptor {

    public static void doProceed(
            @SuperCall
            Callable<?> client,
            @Origin Constructor constructor) throws Exception {

        System.out.println("Constructor Interceptor was called for: " + constructor);
        client.call();
//        builder.addInterceptor(new HttpInterceptor());
    }
}
