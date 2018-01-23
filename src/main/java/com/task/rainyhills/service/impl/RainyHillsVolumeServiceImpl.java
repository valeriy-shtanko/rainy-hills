package com.task.rainyhills.service.impl;

import javax.enterprise.context.ApplicationScoped;

import com.task.rainyhills.service.RainyHillsVolumeService;

/**
 * Created by Valeriy Shtanko on 2018-Jan-20, 12:10
 */
@ApplicationScoped
public class RainyHillsVolumeServiceImpl implements RainyHillsVolumeService {
    /**
     * Calculates the volume of water which remained between hills after the rain.
     *
     * @param hills - hills landscape
     *
     * @return Calculated volume of water.
     */
    public int calculateVolume(int[] hills) {
        if (hills.length <= 2) {
            return 0;
        }

        int[] leftMax  = new int[hills.length];
        int[] rightMax = new int[hills.length];

        leftMax[0] = 0;
        rightMax[rightMax.length - 1] = 0;

        int tempMaxLeft   = hills[0];
        int tempMaxRight  = hills[hills.length - 1];

        int rightMaxIndex = rightMax.length - 1;

        for(int i = 1; i < hills.length; i ++) {
            // Pass from left to right in search for local maximums
            leftMax[i] = tempMaxLeft;

            if (tempMaxLeft < hills[i]) {
                tempMaxLeft = hills[i];
            }

            // Pass from right to left in search for local maximums
            rightMaxIndex--;

            rightMax[rightMaxIndex] = tempMaxRight;

            if (tempMaxRight < hills[rightMaxIndex]) {
                tempMaxRight = hills[rightMaxIndex];
            }
        }

        // Calculate water volume
        int result = 0;
        int hillHeight;
        int minHeight;

        for(int i = 0; i < hills.length; i++) {
            hillHeight = hills[i];
            minHeight = Math.min(leftMax[i], rightMax[i]);

            if ( minHeight > hillHeight) {
                result += minHeight - hillHeight;
            }
        }

        return result;
    }
}
