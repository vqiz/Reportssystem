package vqiz.report.dev.teammlg.Teammlg.listener;

import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import vqiz.report.dev.teammlg.Teammlg.report;

public class Joinlistener implements Listener {
    @EventHandler
    public void onjoin(PostLoginEvent event){
        if (event.getPlayer().hasPermission("report.admin") && !report.login.dataexist("UUID", event.getPlayer().getUniqueId().toString())){
            report.login.insert("'"+ event.getPlayer().getUniqueId().toString() + "','TRUE'");

        }
    }
}
