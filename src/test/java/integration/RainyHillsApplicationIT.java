package integration;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.task.rainyhills.application.RestActivator;
import com.task.rainyhills.controller.CalculateVolumeRequest;
import com.task.rainyhills.controller.RainyHillsController;
import com.task.rainyhills.service.RainyHillsVolumeService;
import com.task.rainyhills.service.impl.RainyHillsVolumeServiceImpl;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by Valeriy Shtanko on 2018-Jan-21, 20:15
 */
@RunWith(Arquillian.class)
@Ignore("Ignored because of fail with 'java.lang.RuntimeException: Could not create new instance of class org.jboss.arquillian.test.impl.EventTestRunnerAdaptor'")
public class RainyHillsApplicationIT {
    @Drone
    WebDriver browser;

    @Before
    public void setUp() {
    }

    @Deployment(testable = false)
    public static Archive<?> deploy() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                                   .addClass(RestActivator.class)
                                   .addClass(RainyHillsController.class)
                                   .addClass(RainyHillsVolumeService.class)
                                   .addClass(RainyHillsVolumeServiceImpl.class)
                                   .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml") ;

        System.out.println(war.toString(true));

        return war;
    }

    @Test
    @Ignore
    public void testApplicationInfoSuccess() {
        browser.navigate().to("http://localhost:8080/rainy-hills/calculate");
        assertThat(browser.getPageSource()).contains("Hello, I am Rainy Hills application");
    }

    @Inject
    RainyHillsController rainyHillsController;

    @Test
    @Ignore
    public void test1Post() {
        assertEquals("{ \"volume\": 2 }", rainyHillsController.calculateVolume(new CalculateVolumeRequest(new int[] {3,1,3})));
    }
}