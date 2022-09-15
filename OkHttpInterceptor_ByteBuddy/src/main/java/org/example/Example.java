package org.example;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Example {
    public static void main(String[] args) throws IOException {

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
