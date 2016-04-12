package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Configuration;

import java.io.IOException;

/**
 * Created by thuanle on 4/12/16.
 */
public abstract class BaseAction {
    private Configuration mConfig;

    public BaseAction(Configuration inputs) {
        mConfig = inputs;
    }

    public Configuration getConfig() {
        return mConfig;
    }

    public void perform() throws IOException {
        int size = getConfig().countRecords();
        for (int i = 0; i < size; i++) {
            performSingleAction(i);
        }
    }

    protected abstract void performSingleAction(int recordIndex) throws IOException;
}
