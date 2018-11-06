package com.example.yunwen.testdemo1022.frames.data;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) //字段、枚举的常量
@Retention(RetentionPolicy.RUNTIME) //注解会在class字节码文件中存在，在运行时可以痛过反射获取到
public @interface DbField {
    String value();
}
