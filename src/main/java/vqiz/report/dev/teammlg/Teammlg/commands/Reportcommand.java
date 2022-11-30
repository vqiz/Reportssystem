package vqiz.report.dev.teammlg.Teammlg.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import vqiz.report.dev.teammlg.Teammlg.report;
import vqiz.report.dev.teammlg.Teammlg.utils.Reportutil;

import java.io.File;
import java.time.LocalDateTime;

public class Reportcommand extends Command {
    public Reportcommand() {
        super("report");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer player = ((ProxiedPlayer) sender);
        Configuration configuration = report.getInstance().configuration;






        if (args.length != 2){
            if (args.length == 1){
                if (args[0].equalsIgnoreCase("toggle")){
                    if (sender.hasPermission("report.admin")){
                        if (report.login.getString(player.getUniqueId().toString(), "UUID", "LOGIN").equalsIgnoreCase("TRUE")){
                            report.login.setString(player.getUniqueId().toString(), "UUID", "LOGIN", "FALSE");
                            sender.sendMessage(configuration.getString("prefix") + " §7Report notfication §cOFF");
                            return;
                        }
                        if (report.login.getString(player.getUniqueId().toString(), "UUID", "LOGIN").equalsIgnoreCase("FALSE")){
                            report.login.setString(player.getUniqueId().toString(), "UUID", "LOGIN", "TRUE");
                            sender.sendMessage(configuration.getString("prefix") + " §7Report notfication §aON");
                            return;
                        }
                    }
                }


            }
            sender.sendMessage(configuration.getString("prefix") + " §7Please user §6/report <user> <reason>");
            return;
        }
        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

        if (target == null){
            sender.sendMessage(configuration.getString("prefix") + " §7This user is not §conline §7!");
            return;
        }
        if (report.reports.dataexist("TARGETUUID", target.getUniqueId().toString())){
            sender.sendMessage(configuration.getString("prefix") + " §7This user has already a report !");
            return;
        }
        report.reports.insert("'"+ target.getUniqueId().toString() +"','"+ player.getUniqueId().toString() + "','" + args[1] + "','" + LocalDateTime.now() + "'");
        Reportutil util = new Reportutil();
        util.reason = args[1];
        util.player = player;
        util.target = target;
        util.sendtoteammeber();
        sender.sendMessage(configuration.getString("prefix") + " §7Succsessfully reported This Player !");



    }

}
