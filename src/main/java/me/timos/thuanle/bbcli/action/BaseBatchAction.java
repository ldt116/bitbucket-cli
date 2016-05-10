package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Action;
import me.timos.thuanle.bbcli.Configuration;

import java.io.IOException;
import java.util.Map;

public abstract class BaseBatchAction implements Action {
    private Configuration mConfig;

    public BaseBatchAction(Configuration config) {
        mConfig = config;
    }

    public Configuration getConfig() {
        return mConfig;
    }

    @Override
    public void perform() throws IOException {
        int size = getConfig().countRecords();
        for (int i = 0; i < size; i++) {
            performSingleAction(mConfig.getRecord(i));
        }
    }

    protected abstract void performSingleAction(Map<String, String> params) throws IOException;
}
