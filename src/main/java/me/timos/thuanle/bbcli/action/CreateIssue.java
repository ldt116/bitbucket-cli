package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Action;
import me.timos.thuanle.bbcli.Configuration;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class CreateIssue extends BaseBatchAction implements Action {

    public static final String ACTION_STRING = "createIssue";

    public CreateIssue(Configuration config) {
        super(config);
    }

    @Override
    protected void performSingleAction(Map<String, String> params) throws IOException {
        Param param = new Param(getConfig(), params);

        System.out.format("%s: %s}\n",
                ACTION_STRING, param.toString());

        OkHttpClient client = new OkHttpClient();
        Request request = ApiRequestCreator.requestCreateIssue(mCredential, param);
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    public static class Param extends AbstractActionParam {
        public static final String FIELD_PRIORITY = "priority";
        public static final String FIELD_KIND = "kind";
        public static final String FIELD_TITLE = "title";
        public static final String FIELD_CONTENT = "content";


        public static final String PRIORITY_TRIVIAL = "trivial";
        public static final String PRIORITY_MINOR = "minor";
        public static final String PRIORITY_MAJOR = "major";
        public static final String PRIORITY_CRITICAL = "critical";
        public static final String PRIORITY_BLOCKER = "blocker";

        public static final String KIND_TASK = "task";

        final String priority;
        final String kind;
        final String title;
        final String content;


        public Param(Configuration config, Map<String, String> params) {
            super(config, params);

            priority = params.getOrDefault(FIELD_PRIORITY, PRIORITY_MAJOR);
            kind = params.getOrDefault(FIELD_KIND, KIND_TASK);
            title = params.getOrDefault(FIELD_TITLE, "");
            content = params.getOrDefault(FIELD_TITLE, "");
        }
    }
}
