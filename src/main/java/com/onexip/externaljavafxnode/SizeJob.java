package com.onexip.externaljavafxnode;

public class SizeJob implements ExternalNodeJob {

    double width;
    double height;

    public SizeJob(double width, double height) {
        this.width = width;
        this.height = height;
    }
}
