package org.example.advice;

import net.bytebuddy.asm.Advice;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.example.HttpInterceptor;


import java.lang.reflect.Constructor;

public class OkHttpAdvice {


    //Advices for constructor
    @Advice.OnMethodEnter
    static void enterMethods(@Advice.Origin Constructor constructor) throws Exception {
        System.out.println("Inside OnMethodEnter advice-> " + constructor);
    }

    @Advice.OnMethodExit
    static void exitMethods(@Advice.Origin Constructor constructor, @Advice.This OkHttpClient.Builder builder) throws Exception {
        System.out.println("Inside OnMethodExit advice -> " + constructor);

        HttpInterceptor httpInterceptor = new HttpInterceptor();
        builder.addInterceptor(httpInterceptor);
    }

    //Advices for methods
//    @Advice.OnMethodEnter
//    public static void enterMethods(@Advice.Origin Method method) {
////        okHttpClient.interceptors().add(new HttpInterceptor());
//        System.out.println("onEnter: method in the okhttpAdvice-> " + method);
//    }
//
//    @Advice.OnMethodExit
//    public static void exitMethods(@Advice.Origin Method method) throws Exception {
//        System.out.println("onExit: method in the okhttpAdvice-> " + method);
//    }

}
