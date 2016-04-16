package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Action;
import me.timos.thuanle.bbcli.Configuration;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class UpdateRepoUserPermission extends BaseBatchAction implements Action {
    public static final String ACTION_STRING = "updateRepoUserPermission";

    public static final String FIELD_REPO_ID = "repoId";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PERMISSION = "permission";

    private final String mCredential;

    public UpdateRepoUserPermission(Configuration config) {
        super(config);
        mCredential = Credentials.basic(config.getUser(), config.getPass());
    }

    @Override
    protected void performSingleAction(int recordIndex) throws IOException {
        Configuration config = getConfig();

        String repoId = config.getRecordField(recordIndex, FIELD_REPO_ID);
        String email = config.getRecordField(recordIndex, FIELD_EMAIL);
        String permission = config.getRecordField(recordIndex, FIELD_PERMISSION);

        performUpdatePermission(config.getUser(), repoId, email, permission);
    }

    private void performUpdatePermission(String user, String repoId, String email, String permission) throws IOException {
        System.out.format("%s: {repo: %s, email: %s, permission: %s}\n", ACTION_STRING, repoId, email, permission);

        OkHttpClient client = new OkHttpClient();
        Request request = ApiRequestCreator.requestUpdateUserPermissionInRepository(user, mCredential, repoId, email, permission);
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
