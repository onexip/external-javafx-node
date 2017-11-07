package com.onexip.externaljavafxnode;

public class PositionJob implements ExternalNodeJob{

    int x;
    int y;

    public PositionJob(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
