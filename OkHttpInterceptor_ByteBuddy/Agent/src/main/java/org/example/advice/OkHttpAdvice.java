package org.example.advice;

import net.bytebuddy.asm.Advice;
import okhttp3.OkHttpClient;
import org.example.HttpInterceptor;

public class OkHttpAdvice {

    @Advice.OnMethodEnter
    static void enterMethods(@Advice.Origin String method, @Advice.Argument(0) OkHttpClient okHttpClient) throws Exception {
        okHttpClient.interceptors().add(new HttpInterceptor());
        System.out.println("inside OkHttpAdvice => OkHttpClient: " + okHttpClient);
        System.out.println("Inside onMethodEnter advice-> " + method);
    }

    @Advice.OnMethodExit
    static void exitMethods(@Advice.Origin String method) throws Exception {
        System.out.println("Inside OnMethodExit advice -> " + method);
    }

}
