package me.timos.thuanle.bbcli.action;

import com.google.gson.Gson;
import me.timos.thuanle.bbcli.Configuration;

import java.util.Map;

/**
 * Created by thuanle on 2/14/17.
 */
public abstract class AbstractActionParam {

    public final String author;
    public final String repoId;

    public AbstractActionParam(Configuration config, Map<String, String> params) {
        author = ParamActionHelper.getAuthor(config, params);
        repoId = ParamActionHelper.getRepo(params);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
