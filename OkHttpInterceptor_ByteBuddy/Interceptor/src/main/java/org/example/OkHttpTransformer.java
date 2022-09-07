package org.example;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.JavaModule;

import java.security.ProtectionDomain;

public class OkHttpTransformer implements AgentBuilder.Transformer {

    String interceptor;

    public OkHttpTransformer(String okhttpinterceptor) {
        this.interceptor = okhttpinterceptor;
    }

    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, ProtectionDomain protectionDomain) {

        ClassFileLocator.Compound compound = new ClassFileLocator.Compound(ClassFileLocator.ForClassLoader.of(classLoader),
                ClassFileLocator.ForClassLoader.ofSystemLoader());
        return builder.method(ElementMatchers.named("proceed").and(ElementMatchers.returns(ElementMatchers.named("okhttp3.Response"))))
                .intercept(MethodDelegation.to(TypePool.Default.of(compound).describe(interceptor).resolve()));
    }
}
