package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Action;
import me.timos.thuanle.bbcli.Configuration;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class CreateRepo extends BaseBatchAction implements Action {
    public static final String ACTION_STRING = "createRepo";

    public static final String FIELD_SCM = "scm";
    public static final String FIELD_IS_PRIVATE = "isPrivate";

    public static final String SCM_GIT = "git";
    public static final String SCM_HG = "hg";

    public static final String IS_PRIVATE_FALSE = "false";
    public static final String IS_PRIVATE_TRUE = "true";

    public CreateRepo(Configuration config) {
        super(config);
    }

    @Override
    protected void performSingleAction(Map<String, String> params) throws IOException {
        String author = ParamActionHelper.getAuthor(getConfig(), params);
        String repoId = ParamActionHelper.getRepo(params);
        String scm = params.getOrDefault(FIELD_SCM, SCM_GIT);
        String isPrivate = params.getOrDefault(FIELD_IS_PRIVATE, IS_PRIVATE_TRUE);

        System.out.format("%s: {repo: %s, scm: %s, isPrivate: %s}\n",
                ACTION_STRING, repoId, scm, isPrivate);

        OkHttpClient client = new OkHttpClient();
        Request request = ApiRequestCreator.requestCreateRepository(author, mCredential, repoId, scm, isPrivate);
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
