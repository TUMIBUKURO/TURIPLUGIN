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
	int point=0;

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
				item.getType() == Material.STICK && point == 0) {
			point=1;
			List<String> blockpoint = new ArrayList<String>();
			blockpoint.add(String.valueOf(x));
			blockpoint.add(String.valueOf(y));







		}

	}






}
