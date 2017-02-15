package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Action;
import me.timos.thuanle.bbcli.Configuration;

public class ActionFactory {
    public static Action createAction(String actionString, Configuration config) {
        switch (actionString) {
            case CreateRepo.ACTION_STRING:
                return new CreateRepo(config);
            case InviteRepoUser.ACTION_STRING:
                return new InviteRepoUser(config);
            case RemoveRepoUser.ACTION_STRING:
                return new RemoveRepoUser(config);
            case UpdateRepoUserPermission.ACTION_STRING:
                return new UpdateRepoUserPermission(config);
            case UpdateRepo.ACTION_STRING:
                return new UpdateRepo(config);
            case CreateIssue.ACTION_STRING:
                return new CreateIssue(config);
        }
        return null;
    }
}
