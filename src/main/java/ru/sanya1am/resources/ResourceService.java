package ru.sanya1am.resources;

import ru.sanya1am.sax.ReadXMLFileSAX;

public class ResourceService {
    private static ResourceService instance;
    private ResourceService() {
    }

    public static ResourceService instance() {
        if (instance == null) {
            instance = new ResourceService();
        }
        return instance;
    }

    public <T> T getResource(String path, Class<T> clazz) {
        return clazz.cast(ReadXMLFileSAX.readXML(path));
    }
}
