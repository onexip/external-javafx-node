package com.onexip.externaljavafxnode;

public abstract class ExternalContent_AC {

    abstract public void setPositionBQ(double x, double y);

    abstract public void setSizeBQ(double x, double y);

    abstract public void setCloseRequestBQ();

    abstract public void setIconifiedBQ(boolean iconified);

    abstract public void init();

    abstract public void uiChanged(ExternalNodeJob job);
}
