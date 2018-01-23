package com.task.rainyhills.service;

/**
 * Created by Valeriy Shtanko on 2018-Jan-20, 12:12
 */
public interface RainyHillsVolumeService {
    /**
     * Calculates the volume of water which remained between hills after the rain.
     *
     * @param hills - hills landscape
     *
     * @return Calculated volume of water.
     */
    int calculateVolume(int[] hills);
}
