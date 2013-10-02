package org.fakeurl.periodic;

/**
 */
public abstract class AbstractPeriodic implements Runnable{

    @Override
    public void run() {
        while(true){

            doAction();

            try {
                Thread.sleep(getPeriod());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    abstract protected void doAction();
    abstract protected long getPeriod();

}
