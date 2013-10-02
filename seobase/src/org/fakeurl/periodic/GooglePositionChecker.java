package org.fakeurl.periodic;

import java.util.concurrent.TimeUnit;

/**
 */
public class GooglePositionChecker extends AbstractPeriodic{

    private long period = TimeUnit.MILLISECONDS.convert(12, TimeUnit.HOURS);

    public GooglePositionChecker() {
    }

    public GooglePositionChecker(long period) {
        this.period = period;
    }

    @Override
    protected void doAction() {
        //todo get sites with words from db for check position

        //todo check position for each site and word

        //todo set result into db
    }

    @Override
    protected long getPeriod() {
        return period;
    }
}
