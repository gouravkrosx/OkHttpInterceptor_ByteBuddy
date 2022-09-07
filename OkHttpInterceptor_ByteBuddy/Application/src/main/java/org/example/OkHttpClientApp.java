package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import okhttp3.*;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * Hello world!
 */
public class OkHttpClientApp {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello OkHttpClient!");

//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(new MyOkInterceptor())
//                .build();


        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient.Builder().build();


//        Request request = new Request.Builder()
//                .url("https://reqres.in/api/users/2")
//                .header("User-Agent", "A Baeldung Reader")
//                .build();

        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        Request request = new Request.Builder().method("POST", RequestBody.create(body.getBytes(StandardCharsets.UTF_8)))
                .url("https://reqres.in/api/users").addHeader("Gourav", "Kumar").build();


        Response response = client.newCall(request).execute();

        System.out.println("Response " + response);
        System.out.println("Body " + response.body().string());

    }
}
