package integration;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.jaxrs.JAXRSFraction;

import com.task.rainyhills.application.RestActivator;
import com.task.rainyhills.controller.RainyHillsController;
import com.task.rainyhills.service.RainyHillsVolumeService;
import com.task.rainyhills.service.impl.RainyHillsVolumeServiceImpl;

import io.restassured.RestAssured;

//import org.wildfly.swarm.arquillian.CreateSwarm;

/**
 * Created by Valeriy Shtanko on 2018-Jan-25, 22:25
 */
@RunWith(Arquillian.class)
@Ignore
public class RainyHillApplicationEndToEndIT {
    @Before
    public static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/rainy-hills/calculate";
    }

    @Deployment(testable = false)
    public static Archive<?> deploy() {
        JAXRSArchive jar = ShrinkWrap.create(JAXRSArchive.class)
                                     .addClass(RestActivator.class)
                                     .addClass(RainyHillsController.class)
                                     .addClass(RainyHillsVolumeService.class)
                                     .addClass(RainyHillsVolumeServiceImpl.class)
                                     .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml") ;

        System.out.println();
        System.out.println(jar.toString(true));
        System.out.println();

        return jar;
    }

    //@CreateSwarm
    public static Swarm newContainer() throws Exception {
        return new Swarm()
            .fraction(new JAXRSFraction())
            .withProfile("application-path");
    }

    @Test
    public void test2_IsCalculateEndpointAvailable() {
        RestAssured.expect()
                   .statusCode(200)
                   .given()
                   .get();
    }
}
