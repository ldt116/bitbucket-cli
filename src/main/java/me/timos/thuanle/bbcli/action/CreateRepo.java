package me.timos.thuanle.bbcli.action;
import me.timos.thuanle.bbcli.Action;
import me.timos.thuanle.bbcli.Configuration;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class CreateRepo extends BaseBatchAction implements Action {
    public static final String ACTION_STRING = "createRepo";

    public static final String FIELD_REPO_ID = "repoId";
    public static final String FIELD_SCM = "scm";
    public static final String FIELD_IS_PRIVATE = "isPrivate";

    public static final String SCM_GIT = "git";
    public static final String SCM_HG = "git";

    public static final String IS_PRIVATE_FALSE = "false";
    public static final String IS_PRIVATE_TRUE = "true";

    private final String mCredential;

    public CreateRepo(Configuration config) {
        super(config);
        mCredential = Credentials.basic(config.getUser(), config.getPass());
    }

    @Override
    protected void performSingleAction(int recordIndex) throws IOException {
        Configuration config = getConfig();

        String repoId = config.getRecordField(recordIndex, FIELD_REPO_ID);
        String scm = config.getRecordField(recordIndex, FIELD_SCM, SCM_GIT);
        String isPrivate = config.getRecordField(recordIndex, FIELD_IS_PRIVATE, IS_PRIVATE_TRUE);

        performCreateRepo(config.getUser(), repoId, scm, isPrivate);
    }

    private void performCreateRepo(String user, String repoId, String scm, String isPrivate) throws IOException {
        System.out.format("%s: {repo: %s, scm: %s, isPrivate: %s}\n", ACTION_STRING, repoId, scm, isPrivate);

        OkHttpClient client = new OkHttpClient();
        Request request = ApiRequestCreator.requestCreateRepository(user, mCredential, repoId, scm, isPrivate);
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
