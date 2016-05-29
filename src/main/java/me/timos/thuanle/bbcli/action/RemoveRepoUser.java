package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Action;
import me.timos.thuanle.bbcli.Configuration;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class RemoveRepoUser extends BaseBatchAction implements Action {
    public static final String ACTION_STRING = "removeRepoUser";

    public RemoveRepoUser(Configuration config) {
        super(config);
    }

    @Override
    protected void performSingleAction(Map<String, String> params) throws IOException {
        String author = ParamActionHelper.getAuthor(getConfig(), params);
        String repoId = ParamActionHelper.getRepo(params);
        String email = ParamActionHelper.getEmail(params);

        System.out.format("%s: {repo: %s, email: %s}\n",
                ACTION_STRING, repoId, email);

        OkHttpClient client = new OkHttpClient();
        Request request = ApiRequestCreator.requestRemoveUserFromRepository(author, mCredential, repoId, email);
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
