package com.example.restful.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//        <A extends Annotation>	getAnnotation(Class<A> annotationClass)	该元素如果存在指定类型的注解，则返回这些注解，否则返回 null。
//        Annotation[]	getAnnotations()	返回此元素上存在的所有注解，包括从父类继承的
//        boolean	isAnnotationPresent(Class<? extends Annotation>  annotationClass)	如果指定类型的注解存在于此元素上，则返回 true，否则返回 false。
//        Annotation[]	getDeclaredAnnotations()	返回直接存在于此元素上的所有注解，注意，不包括父类的注解，调用者可以随意修改返回的数组；这不会对其他调用者返回的数组产生任何影响，没有则返回长度为0的数组

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
    String name() default "";
}
