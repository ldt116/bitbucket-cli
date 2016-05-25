package me.timos.thuanle.bbcli;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import me.timos.thuanle.bbcli.action.ActionFactory;

import java.io.File;
import java.io.IOException;

public class Main {
    public static final String USAGE_STRING =
            "Usage: bbcli --action <action> --file <file> --user <username> --pass <password>\n" +
                    "  --action <action>\n" +
                    "        Requested operation to be performed. Valid actions are listed at the end of this document.\n" +
                    "  --file <file>\n" +
                    "        The input data file in csv format.\n" +
                    "  --user <username>\n" +
                    "        Username of Bitbucket account.\n" +
                    "  --pass <password>\n" +
                    "        Password of Bitbucket account.\n" +
                    "\n" +
                    "Valid actions:\n" +
                    "  createRepo - Creates a new repository\n" +
                    "        Required parameters:\n" +
                    "            repoId          Repository identifier.\n" +
                    "        Optional parameters:\n" +
                    "            scm             The SCM type for the new repo. Either \"git\" (default) or \"hg\"\".\n" +
                    "            isPrivate       Whether the repository should be private (\"true\", default) or public (\"false\") .\n" +
                    "            org             The organization holds the repo.\n" +
                    "\n" +
                    "  inviteRepoUser - Invites a user to a repository\n" +
                    "        Required parameters:\n" +
                    "            repoId          Repository identifier.\n" +
                    "            email           The email recipient.\n" +
                    "        Optional parameters:\n" +
                    "            permission      The permission the recipient is granted. Either \"read\" (default), \"write\" or \"admin\".\n" +
                    "            org             The organization holds the repo.\n" +
                    "\n" +
                    "  removeRepoUser - Removes a user from a repository\n" +
                    "        Required parameters:\n" +
                    "            repoId          Repository identifier.\n" +
                    "            email           The email associated with removed account.\n" +
                    "        Optional parameters:\n" +
                    "            org             The organization holds the repo.\n" +
                    "\n" +
                    "  updateRepoUserPermission - Update a user permission in a repository\n" +
                    "        Required parameters:\n" +
                    "            repoId          Repository identifier.\n" +
                    "            email           The email recipient.\n" +
                    "            permission      The new permission the recipient is granted. Either \"read\", \"write\" or \"admin\".\n" +
                    "        Optional parameters:\n" +
                    "            org             The organization holds the repo.\n";

    public static final String PARAM_ACTION = "action";
    public static final String PARAM_FILE = "file";
    public static final String PARAM_PASS = "pass";
    public static final String PARAM_USER = "user";

    private String mActionString;
    private Configuration mConfig;

    private static Main initialArguments(String[] args) throws IOException {
        OptionParser parser = new OptionParser();
        OptionSpec<String> oAction = parser.accepts(PARAM_ACTION).withRequiredArg().ofType(String.class);
        OptionSpec<File> oFile = parser.accepts(PARAM_FILE).withRequiredArg().ofType(File.class);
        OptionSpec<String> oPass = parser.accepts(PARAM_PASS).withRequiredArg().ofType(String.class);
        OptionSpec<String> oUser = parser.accepts(PARAM_USER).withRequiredArg().ofType(String.class);

        OptionSet options = parser.parse(args);

        if (!options.has(oAction) ||
                !options.has(oFile) ||
                !options.has(oUser) ||
                !options.has(oPass)) {
            showArgsUsage();
            return null;
        }

        String user = options.valuesOf(oUser).get(0);
        String pass = options.valuesOf(oPass).get(0);
        String action = options.valuesOf(oAction).get(0);
        File input = options.valueOf(oFile);

        System.out.println("Initialize BitBucket with user " + user
                + " action " + action
                + " in " + input.getPath());

        Main main = new Main();
        main.mActionString = action;
        main.mConfig = new Configuration(user, pass, input);
        return main;
    }

    public static void main(String[] args) throws IOException {
        Main main = initialArguments(args);
        if (main == null) {
            return;
        }

        main.run();
    }

    private static void showArgsUsage() {
        System.out.println(USAGE_STRING);
    }

    private void run() throws IOException {
        Action action = ActionFactory.createAction(mActionString, mConfig);
        action.perform();
    }
}
