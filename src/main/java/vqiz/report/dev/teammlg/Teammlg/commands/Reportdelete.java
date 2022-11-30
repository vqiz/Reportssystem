package vqiz.report.dev.teammlg.Teammlg.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import vqiz.report.dev.teammlg.Teammlg.report;

public class Reportdelete extends Command {
    public Reportdelete() {
        super("reportdelete", "report.admin");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("report.admin")){
            if (report.reports.dataexist("TARGETUUID",args[0])){
                ProxiedPlayer player = ((ProxiedPlayer) sender);
                report.sendwebhook(report.getInstance().configuration, player.getName() + " deleted the report : id " + args[0]);
                report.reports.pdelete("TARGETUUID", args[0]);
                sender.sendMessage(report.getInstance().configuration.getString("prefix") + " §cYou deletet this report !");


            }else {
                sender.sendMessage(report.getInstance().configuration.getString("prefix") + " §cReport already deleted !");
            }

        }
    }
}
