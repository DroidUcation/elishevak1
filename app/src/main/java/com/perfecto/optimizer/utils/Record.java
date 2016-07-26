package com.perfecto.optimizer.utils;

/**
 * Created by elishevak on 7/18/2016.
 */
public class Record {
    private double share;
    private String image;
    private String deviceName;

    public Record(double share, String image, String deviceName) {
        this.share = share;
        this.image = image;
        this.deviceName = deviceName;
    }

    public double getShare() {
        return share;
    }

    public String getImage() {
        return image;
    }

    public String getDeviceName() {
        return deviceName;
    }
}
