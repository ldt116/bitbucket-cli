package me.timos.thuanle.bbcli.action;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * <p>This is the Connector for Bitbucket</p>
 *
 * <p>@see <a href="https://confluence.atlassian.com/bitbucket/version-1-423626337.html">
 *     Bitbucket Cloud REST APIs v1</a></p>
 */
public class ApiRequestCreator {
    private static final String BITBUCKET_API_1_BASE = "https://api.bitbucket.org/1.0/";
    private static final String BITBUCKET_API_1_BASE_INVITATIONS = BITBUCKET_API_1_BASE + "invitations/";

    private static final String BITBUCKET_API_2_BASE = "https://api.bitbucket.org/2.0/";
    private static final String BITBUCKET_API_2_BASE_REPOSITORIES = BITBUCKET_API_2_BASE + "repositories/";

    private static final String HEADER_AUTHORIZATION = "Authorization";

    private static Request buildRequest(String credential, String url, RequestBody body) {
        return new Request.Builder()
                .url(url)
                .header(HEADER_AUTHORIZATION, credential)
                .post(body)
                .build();
    }

    public static Request requestInvitationUserToRepository(String authorLogin, String credential, String repoSlug, String invitationEmail, String permission) {
        String url = BITBUCKET_API_1_BASE_INVITATIONS + authorLogin + "/" + repoSlug + "/" + invitationEmail;

        RequestBody body = new FormBody.Builder()
                .add("permission", permission)
                .build();

        return buildRequest(credential, url, body);
    }

    public static Request requestCreateRepository(String userLogin, String credential, String repoSlug, String scm, String isPrivate) {
        String url = BITBUCKET_API_2_BASE_REPOSITORIES + userLogin + "/" + repoSlug;

        RequestBody body = new FormBody.Builder()
                .add("scm", scm)
                .add("name", repoSlug)
                .add("is_private", isPrivate)
                .build();

        return buildRequest(credential, url, body);
    }
}
