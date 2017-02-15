package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Action;
import me.timos.thuanle.bbcli.Configuration;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class UpdateRepo extends BaseBatchAction implements Action {

    public static final String ACTION_STRING = "updateRepo";

    public UpdateRepo(Configuration config) {
        super(config);
    }

    @Override
    protected void performSingleAction(Map<String, String> params) throws IOException {
        Param param = new Param(getConfig(), params);

        System.out.format("%s: %s}\n",
                ACTION_STRING, param.toString());

        OkHttpClient client = new OkHttpClient();
        Request request = ApiRequestCreator.requestUpdateRepository(mCredential, param);
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    public static class Param extends AbstractActionParam {
        //        public static final String FIELD_HAS_ISSUES = "has_issues";
        public static final String HAS_ISSUES_FALSE = "false";

        public Param(Configuration config, Map<String, String> params) {
            super(config, params);

//            hasIssues = params.getOrDefault(FIELD_HAS_ISSUES, HAS_ISSUES_FALSE);
        }
    }
}
