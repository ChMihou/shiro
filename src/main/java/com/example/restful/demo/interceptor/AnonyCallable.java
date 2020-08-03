package com.example.restful.demo.interceptor;

import java.util.concurrent.Callable;

import java.util.concurrent.ExecutionException;

import java.util.concurrent.FutureTask;

//匿名类部类实现Callable接口创建子线程

public class AnonyCallable {


    public static void main(String[] args) {

        Callable<String> cl = new Callable<String>() {


            @Override

            public String call() throws Exception {

                System.out.println(Thread.currentThread().getName() + "正在行军~~~");

                System.out.println(Thread.currentThread().getName() + "遭遇敌军~~~");

                System.out.println(Thread.currentThread().getName() + "奋勇杀敌！！！！");

                return "战斗胜利，俘虏敌军50000人";

            }


        };

        FutureTask<String> ft = new FutureTask(cl);

        new Thread(ft, "李存孝部队").start();

        try {

            Thread.currentThread().setName("李存勖部队");

            Thread.sleep(3000);

            System.out.println(Thread.currentThread().getName() + "休整3000ms");

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        System.out.println(Thread.currentThread().getName() + "整顿完毕，等待友军消息...");

        try {

            String str = ft.get();

            System.out.println("李存勖部队得知友军消息为：" + str);

        } catch (InterruptedException | ExecutionException e) {

            e.printStackTrace();

        }

    }
}