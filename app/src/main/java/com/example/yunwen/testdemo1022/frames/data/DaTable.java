package com.example.yunwen.testdemo1022.frames.data;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //运用在接口、类枚举、注解
@Retention(RetentionPolicy.RUNTIME) //注解会在class字节码文件中存在，在运行时可以痛过反射获取到
public @interface DaTable {
    String value();
}
