/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package at.inclumedia.joker.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

import at.inclumedia.TheJoker;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.joker.inclumedia.at",
    ownerName = "backend.joker.inclumedia.at",
    packagePath=""
  )
)
public class MyEndpoint {

    @ApiMethod(name = "tellJoke")
    public MyBean tellJoke() {
        MyBean response = new MyBean();

        String joke = TheJoker.tellJoke();
        response.setData(joke);

        return response;
    }

}
