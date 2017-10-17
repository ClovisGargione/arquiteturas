/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.validation.ValidationFeature;

/**
 *
 * @author clovis
 */
@ApplicationPath("/")
public class ApplicationJAXRS extends ResourceConfig{
     public ApplicationJAXRS(){
        // Register resources and providers using package-scanning.
        packages("services");

        register(MoxyJsonFeature.class);
        register(MultiPartFeature.class);
        register(ValidationFeature.class);
    }
}
