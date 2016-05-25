package me.timos.thuanle.bbcli.action;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * <p>This is the Connector for Bitbucket.</p>
 *
 * @see <a href="https://confluence.atlassian.com/bitbucket/version-1-423626337.html">
 * Bitbucket Cloud REST APIs v1</a>
 * @see <a href="https://confluence.atlassian.com/bitbucket/version-2-423626329.html">
 * Bitbucket Cloud REST APIs v2</a>
 */
public class ApiRequestCreator {
    private static final String BITBUCKET_API_1_BASE = "https://api.bitbucket.org/1.0/";
    private static final String BITBUCKET_API_1_BASE_INVITATIONS = BITBUCKET_API_1_BASE + "invitations/";
    private static final String BITBUCKET_API_1_BASE_PRIVILEGES = BITBUCKET_API_1_BASE + "privileges/";

    private static final String BITBUCKET_API_2_BASE = "https://api.bitbucket.org/2.0/";
    private static final String BITBUCKET_API_2_BASE_REPOSITORIES = BITBUCKET_API_2_BASE + "repositories/";

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String SPLASH = "/";
    private static final MediaType MEDIA_TYPE_TEXT_PLAIN = MediaType.parse("text/plain");

    private static Request.Builder buildCommon(String credential, String url) {
        return new Request.Builder()
                .url(url)
                .header(HEADER_AUTHORIZATION, credential);
    }

    private static Request buildDelete(String credential, String url) {
        return buildCommon(credential, url)
                .delete()
                .build();
    }

    private static Request buildPost(String credential, String url, RequestBody body) {
        return buildCommon(credential, url)
                .post(body)
                .build();
    }

    private static Request buildPut(String credential, String url, RequestBody body) {
        return buildCommon(credential, url)
                .put(body)
                .build();
    }

    static Request requestCreateRepository(String userLogin, String credential, String repoSlug, String scm, String isPrivate) {
        String url = BITBUCKET_API_2_BASE_REPOSITORIES + userLogin + SPLASH + repoSlug;

        RequestBody body = new FormBody.Builder()
                .add("scm", scm)
                .add("name", repoSlug)
                .add("is_private", isPrivate)
                .build();

        return buildPost(credential, url, body);
    }

    static Request requestInvitationUserToRepository(String authorLogin, String credential, String repoSlug, String invitationEmail, String permission) {
        String url = BITBUCKET_API_1_BASE_INVITATIONS + authorLogin + SPLASH + repoSlug + SPLASH + invitationEmail;

        RequestBody body = new FormBody.Builder()
                .add("permission", permission)
                .build();

        return buildPost(credential, url, body);
    }

    static Request requestRemoveUserFromRepository(String authorLogin, String credential, String repoSlug, String privilegeAccount) {
        String url = BITBUCKET_API_1_BASE_PRIVILEGES + authorLogin + SPLASH + repoSlug + SPLASH + privilegeAccount;

        return buildDelete(credential, url);
    }

    static Request requestUpdateUserPermissionInRepository(String authorLogin, String credential, String repoSlug, String updateEmail, String permission) {
        String url = BITBUCKET_API_1_BASE_PRIVILEGES + authorLogin + SPLASH + repoSlug + SPLASH + updateEmail;

        RequestBody body = RequestBody.create(MEDIA_TYPE_TEXT_PLAIN, permission);
        return buildPut(credential, url, body);
    }
}
