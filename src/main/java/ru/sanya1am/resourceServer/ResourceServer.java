package ru.sanya1am.resourceServer;

public interface ResourceServer {
    String getName();
    int getAge();
    String getNameClass();
    void readResource(String path);
}
