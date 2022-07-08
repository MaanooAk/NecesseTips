package maanooak.tips;

import necesse.engine.commands.CommandLog;
import necesse.engine.commands.ModularChatCommand;
import necesse.engine.commands.PermissionLevel;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;

public class TipChatCommand extends ModularChatCommand {

    public TipChatCommand() {
        super("tip", "Show a tip", PermissionLevel.USER, false);
    }

    @Override
    public void runModular(Client client, Server server, ServerClient serverClient, Object[] args, String[] errors, CommandLog commandLog) {
        commandLog.add("§7Tip:§0 " + Tips.next());
    }

}
