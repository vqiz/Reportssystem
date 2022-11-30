package vqiz.report.dev.teammlg.Teammlg;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import vqiz.report.dev.teammlg.Teammlg.commands.Reportcommand;
import vqiz.report.dev.teammlg.Teammlg.commands.Reportdelete;
import vqiz.report.dev.teammlg.Teammlg.commands.Reportscommand;
import vqiz.report.dev.teammlg.Teammlg.listener.Joinlistener;
import vqiz.report.dev.teammlg.Teammlg.mysql.DatabaseManager;
import vqiz.report.dev.teammlg.Teammlg.mysql.Table;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class report extends Plugin {
    public static DatabaseManager db;
    public static Table reports = new Table("report_teammlg", "TARGETUUID TEXT, BYUUID TEXT, REASON TEXT, TIMESTEMP TEXT");
    public static Table login = new Table("reportsystem_login", "UUID TEXT, LOGIN TEXT");
    public Configuration configuration;
    public static report instance;

    public static report getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        configuration = makeConfig();

        db = new DatabaseManager(configuration.getString("mysql.host"),configuration.getString("mysql.port"),configuration.getString("mysql.user"),configuration.getString("mysql.database"), configuration.getString("mysql.password"));
        db.Connect();
        reports.create(db);
        login.create(db);
        System.out.println("Enabled mysql otherwise there is an error !");
        ProxyServer.getInstance().getPluginManager().registerListener(this, new Joinlistener());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Reportscommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Reportdelete());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Reportcommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public Configuration makeConfig() {
        // Create plugin config folder if it doesn't exist
        if (!getDataFolder().exists()) {
            getLogger().info("Created config folder: " + getDataFolder().mkdir());
        }

        File configFile = new File(getDataFolder(), "config.yml");

        // Copy default config if it doesn't exist
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Configuration config = null;
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        addDefault(config, "mysql.host", "127.0.0.1");
        addDefault(config, "mysql.port", "3306");
    addDefault(config, "mysql.database", "DatenBank");
    addDefault(config, "mysql.user", "Username");
    addDefault(config, "mysql.password", "DatenbankPasswort");
    addDefault(config,"prefix", "§8[§6Report§8]");
    addDefault(config, "webhook", "https://discord.com/api/webhooks/947122674512052246/3W9AM0QbhTEdhmrUIdRhUE9mL0wq-Ro4n1zGf8ldVUdd0DzXizdPdABPGNiuODgLfcbD");

        try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, configFile);
        } catch (IOException e) {
        throw new RuntimeException(e);
        }

        return config;
        }



private String addDefault(Configuration conf, String path, String test1) {
        if (!conf.contains(path)) {
        conf.set(path, test1);
        }
        return conf.getString(path);
        }
        public static void sendwebhook(Configuration configuration, String content){
        DiscordWebhook webhook = new DiscordWebhook(configuration.getString("webhook"));
        webhook.setContent(content);
            try {
                webhook.execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
}
