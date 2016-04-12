package me.timos.thuanle.bbcli.action;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by thuanle on 8/29/15.
 */
public class ApiV1RequestCreator {
    static final String BITBUCKET_API_1_0_BASE = "https://api.bitbucket.org/1.0/";
    static final String BITBUCKET_API_BASE_INVITATIONS = BITBUCKET_API_1_0_BASE + "invitations/";
    static final String BITBUCKET_API_BASE_REPOSITORIES = BITBUCKET_API_1_0_BASE + "repositories/";

    private static final String HEADER_AUTHORIZATION = "Authorization";

    public static Request getInvitationUserToRepository(String credential, String authorLogin, String repoSlug, String invitationEmail, String permission) {
        String url = BITBUCKET_API_BASE_INVITATIONS + authorLogin + "/" + repoSlug + "/" + invitationEmail;

        RequestBody body = new FormBody.Builder()
                .add("permission", permission)
                .build();

        return new Request.Builder()
                .url(url)
                .header(HEADER_AUTHORIZATION, credential)
                .post(body)
                .build();
    }


//    public static Request createIssueRequest(String credential, Issue issue, String repoAuthorId, String repoId) {
//        String url = BITBUCKET_API_BASE_REPOSITORIES + repoAuthorId + "/" + repoId + "/issues";
//
//        RequestBody body = new FormEncodingBuilderHelper()
//                .add("status", issue.getStatus())
//                .add("priority", issue.getPriority())
//                .add("title", issue.getTitle())
////                .add("responsible", requestNewIssueBuilder.getResponsible())
//                .add("content", issue.getContent())
//                .add("kind", issue.getMetadata().getKind())
//                .build();
//
//        return new Request.Builder()
//                .url(url)
//                .header(InternalConstants.HEADER_AUTHORIZATION, credential)
//                .post(body)
//                .build();
//    }
}
