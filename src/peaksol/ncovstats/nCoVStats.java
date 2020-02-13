package peaksol.ncovstats;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class nCoVStats extends JavaPlugin {
	@Override
	public void onEnable() {
		this.getLogger().info("Enabled nCoVStats.");
	}

	@Override
	public void onDisable() {
		this.getLogger().info("Disabled nCoVStats.");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if(args.length < 1) {
					try {
						sender.sendMessage("§6正在获取数据中...");
						String overall = utils.get("https://lab.ahusmart.com/nCoV/api/overall", "");
						JsonParser parser_overall = new JsonParser();
						JsonObject obj = (JsonObject) parser_overall.parse(overall);
						JsonObject results = obj.get("results").getAsJsonArray().get(0).getAsJsonObject();
						sender.sendMessage(new String[] {
							"§b§l目前, 全国新冠肺炎统计信息如下:",
							"§c全国确诊: " + results.get("confirmedCount").getAsString(),
							"§e疑似病例: " + results.get("suspectedCount").getAsString(),
							"§a治愈人数: " + results.get("curedCount").getAsString(),
							"§7死亡人数: " + results.get("deadCount").getAsString()
						});
					}
					catch (IOException e) {
						sender.sendMessage("§c无法从接口获取信息.");
					}
					catch (Exception e) {
						sender.sendMessage("§c无法解析接口数据.");
					}
				}
				else {
					try {
						if(utils.map.containsKey(args[0])) args[0] = utils.map.get(args[0]); // 别名
						sender.sendMessage("§6正在获取数据中...");
						String area = utils.get("https://lab.ahusmart.com/nCoV/api/area?province=",  args[0]);
						JsonParser parser_area = new JsonParser();
						JsonObject obj = (JsonObject) parser_area.parse(area);
						JsonObject results = obj.get("results").getAsJsonArray().get(0).getAsJsonObject();
						sender.sendMessage(new String[] {
							"§b§l目前, " + args[0] + "新冠肺炎统计信息如下:",
							"§c确诊病例: " + results.get("confirmedCount").getAsString(),
							"§e疑似病例: " + results.get("suspectedCount").getAsString(),
							"§a治愈人数: " + results.get("curedCount").getAsString(),
							"§7死亡人数: " + results.get("deadCount").getAsString()}
						);	
					}
					catch (IOException e) {
						sender.sendMessage("§c无法从接口获取信息.");
					}
					catch (IndexOutOfBoundsException e) {
						sender.sendMessage("§c输入的区域名无效.");
					}
					catch (Exception e) {
						sender.sendMessage("§c无法解析接口数据.");
					}
				}
			}
		}.runTaskAsynchronously(this);
		return true;
	}
}