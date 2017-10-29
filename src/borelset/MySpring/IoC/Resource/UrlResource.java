package borelset.MySpring.IoC.Resource;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class UrlResource implements Resource{
    private URL Location;

    public UrlResource(URL location) {
        Location = location;
    }

    @Override
    public InputStream getInputStream() {
        InputStream inputStream = null;
        try{
            URLConnection urlConnection = Location.openConnection();
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
        }catch (Exception e){
            e.printStackTrace();
        }
        return inputStream;
    }
}
