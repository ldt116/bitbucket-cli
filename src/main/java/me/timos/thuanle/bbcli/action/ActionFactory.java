package me.timos.thuanle.bbcli.action;

import me.timos.thuanle.bbcli.Action;
import me.timos.thuanle.bbcli.Configuration;

/**
 * Created by thuanle on 4/12/16.
 */
public class ActionFactory {
    public static Action createAction(String actionString, Configuration config) {
        if (actionString == null) {
            return null;
        }
        if (InviteToRepo.ACTION_STRING.equals(actionString)) {
            return new InviteToRepo(config);
        }
        return null;
    }
}
