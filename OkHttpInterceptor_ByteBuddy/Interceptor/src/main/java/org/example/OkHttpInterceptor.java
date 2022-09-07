package org.example;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import okhttp3.Request;
import okhttp3.Response;

import java.util.concurrent.Callable;

public class OkHttpInterceptor {

    public static Response doProceed(
            @SuperCall
            Callable<Response> client,
            @AllArguments
            Object[] args) throws Exception {
        try {

            System.out.println("Inside OkhttpInterceptor doProceed");

            Request request = (Request) args[0];
            Response response = client.call();

//            new Response(request,).body()
            return response;
        } catch (Exception e) {
            System.out.println("exception " + e);
        }
        return null;
    }
}
