package com.onexip.externaljavafxnode;


public abstract class ExternalContent extends ExternalContent_AC {


    @Override
    public void uiChanged(ExternalNodeJob job) {
        if (job instanceof PositionJob) {
            this.setPositionBQ(((PositionJob) job).x, ((PositionJob) job).y);
        }
        else if (job instanceof SizeJob) {
            this.setSizeBQ(((SizeJob) job).width, ((SizeJob) job).height);
        }
        else if(job instanceof IconifiedJob){
            this.setIconifiedBQ(((IconifiedJob) job).iconifiedstate);
        }
        else if(job instanceof ClosingJob){
            this.setCloseRequestBQ();
        }
    }
}
