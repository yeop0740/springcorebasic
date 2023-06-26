package com.example.demo.singleton;

public class SingletonService {

    public static final SingletonService instance = new SingletonService();

    private SingletonService() {
    }

    public static SingletonService getInstance() {
        return instance;
    }

    private void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
