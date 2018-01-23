package com.task.rainyhills.controller;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Valeriy Shtanko on 2018-Jan-21, 23:06
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CalculateVolumeRequest {
    @XmlElement(required=true)
    private int[] hills;

    public CalculateVolumeRequest() {
        this.hills = null;
    }

    public CalculateVolumeRequest(int[] hills) {
        this.hills = hills;
    }

    public int[] getHills() {
        return hills;
    }

    public void setHills(int[] hills) {
        this.hills = hills;
    }
}
