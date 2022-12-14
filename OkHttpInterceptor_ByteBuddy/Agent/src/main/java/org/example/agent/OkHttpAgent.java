package org.example.agent;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.JavaModule;
import okhttp3.OkHttpClient;


import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class OkHttpAgent {
    public static void premain(String arg, Instrumentation instrumentation) {

        System.out.println("Hello from premain");

        // without using interceptor of okhttp3 library
//        final String okHttpInterceptor = "org.example.OkHttpInterceptor";

//        new AgentBuilder.Default()
////                .with(AgentBuilder.TypeStrategy.Default.REDEFINE).with(AgentBuilder.TypeStrategy.Default.REBASE)
//                .type(ElementMatchers.named("okhttp3.internal.http.RealInterceptorChain"))
////                .type(ElementMatchers.named("okhttp3.internal.connection.RealCall.enterNetworkInterceptorExchange"))
////                .transform(new OkHttpTransformer(okHttpInterceptor)).installOn(instrumentation);
//                .transform((builder, typeDescription, classLoader, module, protectionDomain) -> {
//
//                    System.out.println("Inside Transformer method");
//
//                    ClassFileLocator.Compound compound = new ClassFileLocator.Compound(ClassFileLocator.ForClassLoader.of(classLoader),
//                            ClassFileLocator.ForClassLoader.ofSystemLoader());
//                    return builder.method(ElementMatchers.named("proceed").and(ElementMatchers.returns(ElementMatchers.named("okhttp3.Response"))))
////                            .intercept(MethodDelegation.to(TypePool.Default.of(compound).describe(okHttpInterceptor).resolve()));
//                            .intercept(MethodDelegation.to(OkHttpInterceptor.class));
//                }).installOn(instrumentation);


// intercepting exchanger of okhttp

//        new AgentBuilder.Default().type(ElementMatchers.named("okhttp3.internal.connection.RealCall")).transform((builder, typeDescription, classLoader, javaModule, protectionDomain) -> {
//            System.out.println("Inside Transformer");
//            return builder.method(ElementMatchers.named("initExchange").and(ElementMatchers.returns(ElementMatchers.named("okhttp3.internal.connection.Exchange")))).intercept(MethodDelegation.to(OkHttpExchanger.class));
//        }).installOn(instrumentation);

// adding custom interceptor using bytebuddy

//        ByteBuddyAgent.install();
//        new ByteBuddy().redefine(OkHttpClient.Builder.class).method(named("addInterceptor")).intercept(MethodDelegation.to(HttpInterceptor.class)).make().load(OkHttpClient.Builder.class.getClassLoader());

//adding custom interceptor using constructor interception and bytebuddy agent

        //.andThen(SuperMethodCall.INSTANCE))
        //.with(AgentBuilder.TypeStrategy.Default.REBASE)


//        String name = OkHttpClient.Builder.class.getName();
//        String canonicalName = OkHttpClient.Builder.class.getCanonicalName();
//        String simpleName = OkHttpClient.Builder.class.getSimpleName();
//        String typeName = OkHttpClient.Builder.class.getTypeName();
//
//        System.out.println("------------------------------------------------------------");
//        System.out.println("name: " + name);
//        System.out.println("canonicalName: " + canonicalName);
//        System.out.println("simpleName: " + simpleName);
//        System.out.println("typeName: " + typeName);
//        System.out.println("------------------------------------------------------------");

// ------------------------------------- 1 --------------------------------------------
//new ByteBuddy().with(TypeValidation.DISABLED)

//        new AgentBuilder.Default()
////                .with(AgentBuilder.Listener.StreamWriting.toSystemOut())
//                .type(ElementMatchers.named("okhttp3.OkHttpClient$Builder")).transform((builder, typeDescription, classLoader, javaModule, protectionDomain) -> {
//                    System.out.println("Transformer: adding custom okhttpinterceptor using agent: " + typeDescription.getName());
//                    return builder.constructor(takesArgument(1, ElementMatchers.named("okhttp3.OkHttpClient")))
//                            .intercept(MethodDelegation.to(TypePool.Default.ofSystemLoader().describe("org.example.ConstructorInterceptor").resolve()).andThen(SuperMethodCall.INSTANCE));
//                }).installOn(instrumentation);


// ------------------------------------- 2 --------------------------------------------

        // Using Advice to intercept constructor of OkHttpClient
//        new AgentBuilder.Default()
//                .with(AgentBuilder.Listener.StreamWriting.toSystemOut())
//                .type(ElementMatchers.named("okhttp3.OkHttClient$Builder"))
//                .transform(new AgentBuilder.Transformer.ForAdvice()
//                        .advice(isConstructor().and(takesArgument(1,ElementMatchers.named("okhttp3.OkHttpClient"))), "org.example.advice.OkHttpAdvice"))
//                .installOn(instrumentation);


// ------------------------------------- 3 --------------------------------------------

        //different way to delegate the call to advices
//        new AgentBuilder.Default(new ByteBuddy().with(TypeValidation.DISABLED))
////                .with(AgentBuilder.Listener.StreamWriting.toSystemOut())
//                .type(named("okhttp3.OkHttpClient$Builder"))
//                .transform((builder, typeDescription, classLoader, javaModule, protectionDomain) -> {
//                    System.out.println("Inside Transformer (Advice)");
//                    return builder.constructor(any())
////                    return builder.constructor(any())
//                            .intercept(Advice.to(TypePool.Default.ofSystemLoader().describe("org.example.advice.OkHttpAdvice").resolve(), ClassFileLocator.ForClassLoader.ofSystemLoader()));
//                }).installOn(instrumentation);


//------------------------------------ 4 - working great ---------------------------------------------
        // intercepting build() method of OkHttpClient.Builder

//        new AgentBuilder.Default(new ByteBuddy().with(TypeValidation.DISABLED))
////                .with(AgentBuilder.Listener.StreamWriting.toSystemOut())
//                .type(named("okhttp3.OkHttpClient$Builder"))
//                .transform(((builder, typeDescription, classLoader, javaModule, protectionDomain) -> {
//                    System.out.println("Inside BuildInterceptor Transformer");
//                    return builder.method(ElementMatchers.named("build")).intercept(MethodDelegation.to(TypePool.Default.ofSystemLoader().describe("org.example.BuildInterceptor").resolve()));
//                })).installOn(instrumentation);

//------------------------------------ 5 - working great -----------------------------------------------

        // intercepting newBuilder() method of okhttpclient in order to add our own interceptor using the returned builder.

//        new AgentBuilder.Default(new ByteBuddy().with(TypeValidation.DISABLED))
////                .with(AgentBuilder.Listener.StreamWriting.toSystemOut())
//                .type(named("okhttp3.OkHttpClient"))
//                .transform((builder, typeDescription, classLoader, javaModule, protectionDomain) -> {
//                    System.out.println("Inside BuilderInterceptor Transformer");
//
//                    return builder.method(any().and(returns(OkHttpClient.Builder.class)))
//////                            .intercept(Advice.to(TypePool.Default.ofSystemLoader().describe("org.example.advice.OkHttpAdvice").resolve(), ClassFileLocator.ForClassLoader.ofSystemLoader()));
//                            .intercept(MethodDelegation.to(TypePool.Default.ofSystemLoader().describe("org.example.BuilderInterceptor").resolve()));
//                }).installOn(instrumentation);


//-------------------------------------------- 6 - working perfectly fine  -----------------------------------------------
//         intercepting constructor of OkHttpClient.Builder

//        new AgentBuilder.Default(new ByteBuddy().with(TypeValidation.DISABLED))
////                .with(AgentBuilder.Listener.StreamWriting.toSystemOut())
//                .type(named("okhttp3.OkHttpClient$Builder"))
//                .transform(((builder, typeDescription, classLoader, javaModule, protectionDomain) -> {
//                    System.out.println("Inside Transformer (Builder constructor)");
//                    return builder.constructor(isDefaultConstructor()).intercept(Advice.to(TypePool.Default.ofSystemLoader().describe("org.example.advice.OkHttpAdvice").resolve(), ClassFileLocator.ForClassLoader.ofSystemLoader()));
//                })).installOn(instrumentation);


//-------------------------------------- 7 ------------------------------------
// merging the transformer due to 3 different ways to create a OkHttpClient

//        OkHttpClient client = new OkHttpClient().newBuilder().build();  // if used constructor interceptor using advice in this case then it get called 2 times.

//        OkHttpClient client = new OkHttpClient.Builder().build();

//        OkHttpClient client = new OkHttpClient();

        new AgentBuilder.Default(new ByteBuddy().with(TypeValidation.DISABLED))
//                .with(AgentBuilder.Listener.StreamWriting.toSystemOut())
                .type(named("okhttp3.OkHttpClient$Builder"))
                .transform(((builder, typeDescription, classLoader, javaModule, protectionDomain) -> {
                    System.out.println("Inside Merged Transformer (Builder constructor)");
                    return builder
                            .constructor(isDefaultConstructor()).intercept(Advice.to(TypePool.Default.ofSystemLoader().describe("org.example.advice.OkHttpAdvice").resolve(), ClassFileLocator.ForClassLoader.ofSystemLoader()))
                            .method(ElementMatchers.named("build")).intercept(MethodDelegation.to(TypePool.Default.ofSystemLoader().describe("org.example.BuildInterceptor").resolve()));
                })).installOn(instrumentation);
    }
}
