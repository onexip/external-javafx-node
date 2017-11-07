package com.onexip.externaljavafxnode;

import java.util.concurrent.LinkedBlockingDeque;

public abstract class ExternalThreadedContent extends ExternalContent_AC {


    private PositionJob positionJob = null;
    private SizeJob sizeJob;
    private ClosingJob closeJob;
    private IconifiedJob iconifiedJob;

    protected LinkedBlockingDeque<ExternalNodeJob> queue = null;


    public ExternalThreadedContent() {

    }

    @Override
    public void init() {

        queue = new LinkedBlockingDeque<ExternalNodeJob>(20);

        new Thread(() -> {
            while (true) {
                getBlockingQ();
            }
        }).start();

    }

    public void addBlockingQ(ExternalNodeJob job) {
        // add Data in queue

        if (job instanceof PositionJob) {
            queue.remove(this.positionJob);        //remove all position jobs
            this.positionJob = (PositionJob) job;
        } else if (job instanceof SizeJob) {
            queue.remove(this.sizeJob);        //remove all position jobs
            this.sizeJob = (SizeJob) job;
        } else if (job instanceof ClosingJob) {
            queue.remove(this.closeJob);        //remove all closing jobs
            this.closeJob = (ClosingJob) job;
        } else if (job instanceof IconifiedJob) {
            queue.remove(this.iconifiedJob);   //remove all iconifing jobs
            this.iconifiedJob = (IconifiedJob) job;
        }
        queue.add(job);

    }

    // Get Data out of BlockingQueue, run Set functions if Value Modified
    public void getBlockingQ() {
        ExternalNodeJob job = null;
        try {
            job = queue.take();
            if (job instanceof PositionJob) {
                setPositionBQ(((PositionJob) job).x, ((PositionJob) job).y);
            } else if (job instanceof SizeJob) {
                setSizeBQ(((SizeJob) job).width, ((SizeJob) job).height);
            } else if (job instanceof IconifiedJob) {
                setIconifiedBQ(((IconifiedJob) job).iconifiedstate);
            } else if (job instanceof ClosingJob) {
                setCloseRequestBQ();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    public void uiChanged(ExternalNodeJob job) {
        addBlockingQ(job);
    }

}
