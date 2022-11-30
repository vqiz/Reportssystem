package vqiz.report.dev.teammlg.Teammlg.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import vqiz.report.dev.teammlg.Teammlg.report;

import java.util.UUID;

public class Reportscommand extends Command {
    public Reportscommand() {
        super("reports", "report.admin");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("report.admin")){
            if (  report.reports.getStringList("TARGETUUID").isEmpty()){
                sender.sendMessage(report.getInstance().configuration.getString("prefix") + " §7No open reports !");
            }
            try {
                report.reports.getStringList("TARGETUUID").forEach(s -> {
                    try {
                        ProxiedPlayer user = ((ProxiedPlayer) sender);
                        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(UUID.fromString(s));
                        if (player == null) {
                            user.sendMessage(report.getInstance().configuration.getString("prefix") + " §8§m-------------------");
                            user.sendMessage(report.getInstance().configuration.getString("prefix") + " §7Target : §6" + "§cOFFLINE");
                            user.sendMessage(report.getInstance().configuration.getString("prefix") + " §7From : §6" + "§cOFFLINE");
                            user.sendMessage(report.getInstance().configuration.getString("prefix") + " §7Reason : §6" + report.reports.getString(player.getUniqueId().toString(), "TARGETUUID", "REASON"));
                            user.sendMessage(report.getInstance().configuration.getString("prefix") + " §7Time : §6" + report.reports.getString(player.getUniqueId().toString(), "TARGETUUID", "TIMESTEMP"));
                            TextComponent component = new TextComponent("§c[Delete]");
                            component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reportdelete " + player.getUniqueId().toString()));
                            user.sendMessage(component);
                            user.sendMessage(report.getInstance().configuration.getString("prefix") + " §8§m-------------------");
                            return;
                        }
                        user.sendMessage(report.getInstance().configuration.getString("prefix") + " §8§m-------------------");
                        user.sendMessage(report.getInstance().configuration.getString("prefix") + " §7Target : §6" + player.getName());
                        user.sendMessage(report.getInstance().configuration.getString("prefix") + " §7From : §6" + player.getName());
                        user.sendMessage(report.getInstance().configuration.getString("prefix") + " §7Reason : §6" + report.reports.getString(player.getUniqueId().toString(), "TARGETUUID", "REASON"));
                        user.sendMessage(report.getInstance().configuration.getString("prefix") + " §7Time : §6" + report.reports.getString(player.getUniqueId().toString(), "TARGETUUID", "TIMESTEMP"));
                        TextComponent component = new TextComponent("§c[Delete]");
                        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reportdelete " + player.getUniqueId().toString()));
                        TextComponent c = new TextComponent("§a[Teleport]");
                        c.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/server " + player.getServer().getInfo().getName()));
                        TextComponent base = new TextComponent( report.getInstance().configuration.getString("prefix")+ " " );
                        base.addExtra(component);
                        user.sendMessage(base);
                        TextComponent base1 = new TextComponent(" " + report.getInstance().configuration.getString("prefix")+ " ");
                        base1.addExtra(c);
                        user.sendMessage(base1);
                        user.sendMessage(report.getInstance().configuration.getString("prefix") + " §8§m-------------------");


                    }catch (Exception e) {

                    e.printStackTrace();
                    }
                });

            }catch (Exception e){
                e.printStackTrace();
            }



        }
    }
}
