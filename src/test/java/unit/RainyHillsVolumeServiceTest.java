package unit;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.task.rainyhills.service.impl.RainyHillsVolumeServiceImpl;

import static org.junit.Assert.assertEquals;

/**
 * Created by Valeriy Shtanko on 2018-Jan-20, 12:20
 */
public class RainyHillsVolumeServiceTest {
    private RainyHillsVolumeServiceImpl service;

    @Before
    public void setUp() {
        service = new RainyHillsVolumeServiceImpl();
    }

    @Test
    public void testSimple() {
        assertEquals(0, service.calculateVolume(new int[] {0}));
        assertEquals(0, service.calculateVolume(new int[] {2,2}));
        assertEquals(0, service.calculateVolume(new int[] {3,3,3}));
        assertEquals(2, service.calculateVolume(new int[] {3,1,3}));
        assertEquals(2, service.calculateVolume(new int[] {1,1,3,2,3,2,3,1,1}));
    }

    @Test
    public void testComplex() {
        Integer preCalculateValue = 28;
        Integer volume = service.calculateVolume(new int[] {3, 1, 2, 7, 5, 9, 4, 3, 1, 4, 3, 7, 6, 1, 4});

        assertEquals(preCalculateValue.intValue(), volume.intValue());
    }

    @Test
    public void testBigArray() {
        int[] input = new Random().ints(15_000_000, 0, 100 + 1).toArray();

        calculate(input);
    }

    @Test
    public void testVeryBigArray() {
        int[] input = new Random().ints(100_000_000, 0, 100 + 1).toArray();

        calculate(input);
    }

    @Test
    @Ignore("Ignored because of 'java.lang.OutOfMemoryError: Java heap space'")
    public void testHugeArray() {
        int[] input = new Random().ints(250_000_000, 0, 100 + 1).toArray();

        calculate(input);
    }

    private int calculate(int[] input) {
        Instant start = Instant.now();
        int volume = service.calculateVolume(input);
        Instant end = Instant.now();

        System.out.println(String.format("Hills Number: %d, Water Volume: %d, Duration: %d milliseconds\n",
                                         input.length, volume, Duration.between(start, end).toMillis()));

        return volume;
    }
}
