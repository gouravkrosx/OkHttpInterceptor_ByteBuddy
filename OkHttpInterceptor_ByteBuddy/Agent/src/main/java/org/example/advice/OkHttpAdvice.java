package org.example.advice;

import net.bytebuddy.asm.Advice;

public class OkHttpAdvice {

    @Advice.OnMethodEnter
    static void enterMethods(@Advice.Origin String method) throws Exception {
        System.out.println("Inside onMethodEnter advice-> " + method);
    }

    @Advice.OnMethodExit
    static void exitMethods(@Advice.Origin String method) throws Exception {
        System.out.println("Inside OnMethodExit advice -> " + method);
    }

}
