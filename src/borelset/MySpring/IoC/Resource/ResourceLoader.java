package borelset.MySpring.IoC.Resource;

import java.net.URL;

public class ResourceLoader {
    private Resource mResource;

    public Resource getResource(String location) {
        URL path = this.getClass().getClassLoader().getResource(location);
        mResource = new UrlResource(path);
        return mResource;
    }

    public void setResource(Resource resource) {
        mResource = resource;
    }
}
