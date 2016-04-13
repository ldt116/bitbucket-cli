package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Configuration;

import java.io.IOException;

public abstract class BaseBatchAction {
    private Configuration mConfig;

    public BaseBatchAction(Configuration config) {
        mConfig = config;
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
