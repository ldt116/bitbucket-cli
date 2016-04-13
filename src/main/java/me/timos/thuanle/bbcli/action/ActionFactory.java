package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Action;
import me.timos.thuanle.bbcli.Configuration;

public class ActionFactory {
    public static Action createAction(String actionString, Configuration config) {
        if (actionString == null) {
            return null;
        }
        if (InviteToRepo.ACTION_STRING.equals(actionString)) {
            return new InviteToRepo(config);
        }
        if (CreateRepo.ACTION_STRING.equals(actionString)) {
            return new CreateRepo(config);
        }

        return null;
    }
}
