package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Action;
import me.timos.thuanle.bbcli.Configuration;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class UpdateRepoUserPermission extends BaseBatchAction implements Action {
    public static final String ACTION_STRING = "updateRepoUserPermission";

    public static final String FIELD_REPO_ID = "repoId";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PERMISSION = "permission";


    public UpdateRepoUserPermission(Configuration config) {
        super(config);
    }


    @Override
    protected void performSingleAction(Map<String, String> params) throws IOException {
        String user = getConfig().getUser();
        String repoId = params.get(FIELD_REPO_ID);
        String email = params.get(FIELD_EMAIL);
        String permission = params.get(FIELD_PERMISSION);

        System.out.format("%s: {repo: %s, email: %s, permission: %s}\n", ACTION_STRING, repoId, email, permission);

        OkHttpClient client = new OkHttpClient();
        Request request = ApiRequestCreator.requestUpdateUserPermissionInRepository(user, mCredential, repoId, email, permission);
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
