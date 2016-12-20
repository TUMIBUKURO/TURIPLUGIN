package turiplugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class TuripPlugin extends JavaPlugin implements Listener {
	int point1=0;
	int point2=0;
	@Override
	public void onEnable() {
	this.getServer().getPluginManager().registerEvents(this, this);}
	@EventHandler
	public void PlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		// クリック場所の特定
		int x = event.getClickedBlock().getX();
		int y = event.getClickedBlock().getY();


		if (player.isOp() && event.getAction() == Action.RIGHT_CLICK_BLOCK &&
				item.getType() == Material.STICK && point1 == 0) {
			point1=1;
			List<String> blockpoint1 = new ArrayList<String>();
			blockpoint1.add(String.valueOf(x));
			blockpoint1.add(String.valueOf(y));
			player.sendMessage(""+blockpoint1+"にX座標を"+x+"Y座標を"+y+"と記録しました");
			point1=0;
		}
		if (player.isOp() && event.getAction() == Action.LEFT_CLICK_BLOCK &&
				item.getType() == Material.STICK && point2 == 0) {
			point2=1;
			List<String> blockpoint2 = new ArrayList<String>();
			blockpoint2.add(String.valueOf(x));
			blockpoint2.add(String.valueOf(y));
			player.sendMessage(""+blockpoint2+"にX座標を"+x+"Y座標を"+y+"と記録しました");
			point2=0;
		}

	}


}




