package vqiz.report.dev.teammlg.Teammlg.utils;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import vqiz.report.dev.teammlg.Teammlg.report;

public class Reportutil {
    public ProxiedPlayer player;
    public ProxiedPlayer target;
    public String reason;
    public void sendtoteammeber(){
        for (ProxiedPlayer user : ProxyServer.getInstance().getPlayers()) {
            if (!user.hasPermission("report.admin")){
                continue;
            }
            if (report.login.getString(user.getUniqueId().toString(), "UUID", "LOGIN").equalsIgnoreCase("TRUE")){
                user.sendMessage(report.getInstance().configuration.getString("prefix") + " §8§m-------------------");
                user.sendMessage(report.getInstance().configuration.getString("prefix") + " §7Target : §6" + target.getName());
                user.sendMessage(report.getInstance().configuration.getString("prefix") + " §7From : §6" + player.getName());
                user.sendMessage(report.getInstance().configuration.getString("prefix") + " §7Reason : §6" + reason);
                TextComponent component = new TextComponent("§c[Delete]");
                component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reportdelete " + target.getUniqueId().toString()));
                TextComponent c = new TextComponent("§a[Teleport]");
                c.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/server " + target.getServer().getInfo().getName()));
                TextComponent base = new TextComponent( report.getInstance().configuration.getString("prefix") + " ");
                base.addExtra(component);
                user.sendMessage(base);
                TextComponent base1 = new TextComponent( report.getInstance().configuration.getString("prefix") + " ");
                base1.addExtra(c);
                user.sendMessage(base1);
                user.sendMessage(report.getInstance().configuration.getString("prefix") + " §8§m-------------------");
            }
        }
    }
}
