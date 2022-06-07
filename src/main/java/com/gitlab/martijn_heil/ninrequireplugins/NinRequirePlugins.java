package com.gitlab.martijn_heil.ninrequireplugins;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class NinRequirePlugins extends JavaPlugin {

	@Override
	public void onEnable() {
		this.saveDefaultConfig();

		this.getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
			List<String> requiredPlugins = this.getConfig().getStringList("required_plugins");
			for (String pluginName : requiredPlugins) {
				Plugin plugin = this.getServer().getPluginManager().getPlugin(pluginName);
				if (plugin == null || !plugin.isEnabled()) {
					getLogger().severe("Could not find required plugin '" + pluginName + "'! Shutting down server!");
					this.getServer().shutdown();
				}
			}
		}, 0);
	}
}
