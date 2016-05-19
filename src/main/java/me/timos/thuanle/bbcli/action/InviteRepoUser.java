package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Action;
import me.timos.thuanle.bbcli.Configuration;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class InviteRepoUser extends BaseBatchAction implements Action {
    public static final String ACTION_STRING = "inviteRepoUser";

    public static final String FIELD_REPO_ID = "repoId";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PERMISSION = "permission";

    public static final String PERMISSION_READ = "read";
    public static final String PERMISSION_WRITE = "write";
    public static final String PERMISSION_ADMIN = "admin";

    public InviteRepoUser(Configuration config) {
        super(config);
    }

    @Override
    protected void performSingleAction(Map<String, String> params) throws IOException {
        String user = getConfig().getUser();
        String repoId = params.get(FIELD_REPO_ID);
        String email = params.get(FIELD_EMAIL);
        String permission = params.getOrDefault(FIELD_PERMISSION, PERMISSION_READ);

        System.out.format("%s: {repo: %s, email: %s, permission: %s}\n", ACTION_STRING, repoId, email, permission);

        OkHttpClient client = new OkHttpClient();
        Request request = ApiRequestCreator.requestInvitationUserToRepository(user, mCredential, repoId, email, permission);
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
