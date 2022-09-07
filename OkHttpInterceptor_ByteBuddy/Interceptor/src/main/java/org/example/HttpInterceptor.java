package org.example;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HttpInterceptor implements Interceptor{

    @NotNull
    @Override
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        String reqBody = getRequestBody(request);

        System.out.println("Inside HttpInterceptor before next()");

        Response response = chain.proceed(request);

        System.out.println("Inside HttpInterceptor after next()");

        return response;
    }

    private String getRequestBody(Request request) {
        if (request.body() != null) {
            try {
                final Request copy = request.newBuilder().build();
                final Buffer buffer = new Buffer();
                copy.body().writeTo(buffer);
                return buffer.readUtf8();
            } catch (final IOException e) {
                System.out.println("Failed to stringify request body");
            }
        }
        return "";
    }

    private String getResponseBody(Response response) throws IOException {
        final BufferedSource source = response.body().source();
        source.request(Integer.MAX_VALUE);
        ByteString snapshot = source.getBuffer().snapshot();
        return snapshot.utf8();
    }

}
