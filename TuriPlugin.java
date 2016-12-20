package turiplugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TuriPlugin extends JavaPlugin implements Listener {
	List<String> blockpoint1 = new ArrayList<String>();
	List<String> blockpoint2 = new ArrayList<String>();
	FileConfiguration conf = getConfig();
	int point1 = 0;
	int point2 = 0;
	int count1 = conf.getInt("count1");

	@Override
	public void onEnable() {
		// コマンドの読み込み
		getCommand("setb").setExecutor(this);
		this.getServer().getPluginManager().registerEvents(this, this);
		// デフォルトのコンフィグを出力
		this.saveDefaultConfig();
	}

	@EventHandler
	public void PlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		// クリック場所の特定
		int x = event.getClickedBlock().getX();
		int z = event.getClickedBlock().getZ();
		MetadataValue blockpointx = new FixedMetadataValue(this, x);
		MetadataValue blockpointz = new FixedMetadataValue(this, z);

		if (player.isOp() && event.getAction() == Action.LEFT_CLICK_BLOCK && item.getType() == Material.STICK
				&& point1 == 0) {
			point1 = 1;
			player.setMetadata("blockpoint1", blockpointx);
			player.setMetadata("blockpoint2", blockpointz);
			player.sendMessage("blockpoint1" + "にX座標を" + x + "Z座標を" + z + "と記録しました");
			point1 = 0;

		}
		if (player.isOp() && event.getAction() == Action.RIGHT_CLICK_BLOCK && item.getType() == Material.STICK
				&& point2 == 0) {
			point2 = 1;
			player.setMetadata("blockpoint3", blockpointx);
			player.setMetadata("blockpoint4", blockpointz);
			player.sendMessage("blockpoint2" + "にX座標を" + x + "Z座標を" + z + "と記録しました");

			new BukkitRunnable() {
				public void run() {
					point2 = 0;
				}
			}.runTaskLater(this, 1);
		}

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		// TODO 自動生成されたメソッド・スタブ]
		// MapをListにいれる
		List<String> list = new ArrayList<String>();

		if (command.getName().equalsIgnoreCase("setb") && sender.isOp()) {
			if (!(sender instanceof Player)) {
				return false;
			}
			for (MetadataValue meta1 : player.getMetadata("blockpoint1")) {

				list.add(meta1.asString());




			}
			for (MetadataValue meta2 : player.getMetadata("blockpoint2")) {

				list.add(meta2.asString());

			}
			for (MetadataValue meta3 : player.getMetadata("blockpoint3")) {

				list.add(meta3.asString());
			}
			for (MetadataValue meta4 : player.getMetadata("blockpoint4")) {

				list.add(meta4.asString());

				this.saveConfig();
			}

				conf.set("blockpointconfig" + count1,list);
				count1 = count1 + 1;

				conf.set("count1", count1);
				sender.sendMessage("登録しました");

		}

		return true;
	}

}
