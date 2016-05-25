package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Configuration;

import java.util.Map;

/**
 * Created by thuanle on 5/25/16.
 */
public abstract class BaseBatchOrgAction extends BaseBatchAction {
    public static final String FIELD_ORGANIZATION = "org";

    public BaseBatchOrgAction(Configuration config) {
        super(config);
    }


    protected String getAuthor(Map<String, String> params) {
        String user = getConfig().getUser();
        return params.getOrDefault(FIELD_ORGANIZATION, user);
    }
}
