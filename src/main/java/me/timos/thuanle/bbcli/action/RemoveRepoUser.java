package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Action;
import me.timos.thuanle.bbcli.Configuration;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class RemoveRepoUser extends BaseBatchAction implements Action {
    public static final String ACTION_STRING = "removeRepoUser";

    public static final String FIELD_REPO_ID = "repoId";
    public static final String FIELD_EMAIL = "email";

    private final String mCredential;

    public RemoveRepoUser(Configuration config) {
        super(config);
        mCredential = Credentials.basic(config.getUser(), config.getPass());
    }

    @Override
    protected void performSingleAction(int recordIndex) throws IOException {
        Configuration config = getConfig();

        String repoId = config.getRecordField(recordIndex, FIELD_REPO_ID);
        String email = config.getRecordField(recordIndex, FIELD_EMAIL);

        performRemove(config.getUser(), repoId, email);
    }

    private void performRemove(String user, String repoId, String email) throws IOException {
        System.out.format("%s: {repo: %s, email: %s}\n", ACTION_STRING, repoId, email);

        OkHttpClient client = new OkHttpClient();
        Request request = ApiRequestCreator.requestRemoveUserFromRepository(user, mCredential, repoId, email);
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
