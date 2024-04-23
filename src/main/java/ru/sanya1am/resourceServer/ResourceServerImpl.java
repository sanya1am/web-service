package ru.sanya1am.resourceServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sanya1am.resources.ResourceService;
import ru.sanya1am.resources.TestResource;

public class ResourceServerImpl implements ResourceServer {
    private TestResource resource;
    static final Logger logger = LogManager.getLogger(ResourceServerImpl.class.getName());

    public ResourceServerImpl() {
    }

    @Override
    public String getName() {
        return resource.getName();
    }

    @Override
    public int getAge() {
        return resource.getAge();
    }

    public String getNameClass() {
        return resource.getClass().toString();
    }

    @Override
    public void readResource(String path) {
        resource = ResourceService.instance().getResource(path, TestResource.class);
        logger.info("Resource loaded");
    }
}
