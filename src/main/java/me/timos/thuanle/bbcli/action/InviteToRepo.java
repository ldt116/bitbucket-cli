package me.timos.thuanle.bbcli.action;
import me.timos.thuanle.bbcli.Action;
import me.timos.thuanle.bbcli.Configuration;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by thuanle on 4/12/16.
 */
public class InviteToRepo extends BaseAction implements Action {
    public static final String ACTION_STRING = "inviteToRepo";

    public static final String FIELD_REPOID = "repoId";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PERMISSION = "permission";

    public static final String PERMISSION_READ = "read";
    private final String mCredential;


    public InviteToRepo(Configuration config) {
        super(config);
        mCredential = Credentials.basic(config.getUser(), config.getPass());
    }

    @Override
    protected void performSingleAction(int recordIndex) throws IOException {
        Configuration config = getConfig();

        String permission = PERMISSION_READ;
        if (config.hasField(FIELD_PERMISSION)) {
            permission = config.getRecordField(recordIndex, FIELD_PERMISSION);
        }
        String repoId = config.getRecordField(recordIndex, FIELD_REPOID);
        String email = config.getRecordField(recordIndex, FIELD_EMAIL);

        performInvitation(config.getUser(), repoId, email, permission);
    }

    private void performInvitation(String user, String repoId, String email, String permission) throws IOException {
        System.out.println(ACTION_STRING + ": " + email + " to repo " + repoId + " with permission " + permission);
        OkHttpClient client = new OkHttpClient();
        Request request = ApiV1RequestCreator.getInvitationUserToRepository(mCredential, user, repoId, email, permission);
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
