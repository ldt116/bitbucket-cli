package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Action;
import me.timos.thuanle.bbcli.Configuration;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class InviteToRepo extends BaseBatchAction implements Action {
    public static final String ACTION_STRING = "inviteToRepo";

    public static final String FIELD_REPO_ID = "repoId";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PERMISSION = "permission";

    public static final String PERMISSION_READ = "read";
    public static final String PERMISSION_WRITE = "write";
    public static final String PERMISSION_ADMIN = "admin";

    private final String mCredential;

    public InviteToRepo(Configuration config) {
        super(config);
        mCredential = Credentials.basic(config.getUser(), config.getPass());
    }

    @Override
    protected void performSingleAction(int recordIndex) throws IOException {
        Configuration config = getConfig();

        String repoId = config.getRecordField(recordIndex, FIELD_REPO_ID);
        String email = config.getRecordField(recordIndex, FIELD_EMAIL);
        String permission = config.getRecordField(recordIndex, FIELD_PERMISSION, PERMISSION_READ);

        performInvitation(config.getUser(), repoId, email, permission);
    }

    private void performInvitation(String user, String repoId, String email, String permission) throws IOException {
        System.out.format("%s: {repo: %s, email: %s, permission: %s}\n", ACTION_STRING, repoId, email, permission);

        OkHttpClient client = new OkHttpClient();
        Request request = ApiRequestCreator.requestInvitationUserToRepository(user, mCredential, repoId, email, permission);
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
