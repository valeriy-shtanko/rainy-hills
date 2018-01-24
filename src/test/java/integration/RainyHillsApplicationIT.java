package integration;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.task.rainyhills.application.RestActivator;
import com.task.rainyhills.controller.CalculateVolumeRequest;
import com.task.rainyhills.controller.RainyHillsController;
import com.task.rainyhills.service.RainyHillsVolumeService;
import com.task.rainyhills.service.impl.RainyHillsVolumeServiceImpl;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by Valeriy Shtanko on 2018-Jan-21, 20:15
 */
@RunWith(Arquillian.class)
public class RainyHillsApplicationIT {
    @Inject
    RainyHillsController rainyHillsController;

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

        System.out.println();
        System.out.println(war.toString(true));
        System.out.println();

        return war;
    }

    @Test
    public void testControllerCalculate() {
        assertThat( rainyHillsController.calculateVolume(new CalculateVolumeRequest(new int[] {3,1,3})))
            .contains("\"volume\": 2");
    }
}