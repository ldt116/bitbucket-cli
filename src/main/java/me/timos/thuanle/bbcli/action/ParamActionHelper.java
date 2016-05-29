package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Configuration;

import java.util.Map;

/**
 * Created by thuanle on 5/25/16.
 */
public class ParamActionHelper {
    public static final String FIELD_ORGANIZATION = "org";
    public static final String FIELD_REPO_ID = "repoId";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PERMISSION = "permission";

    public static final String getAuthor(Configuration config, Map<String, String> params) {
        String user = config.getUser();
        return params.getOrDefault(FIELD_ORGANIZATION, user);
    }

    public static String getRepo(Map<String, String> params) {
        return params.get(FIELD_REPO_ID);
    }

    public static String getEmail(Map<String, String> params) {
        return params.get(FIELD_EMAIL);
    }

    public static String getPermission(Map<String, String> params, String defaultValue) {
        return params.getOrDefault(FIELD_PERMISSION, defaultValue);
    }

    public static String getPermission(Map<String, String> params) {
        return params.get(FIELD_PERMISSION);
    }
}
