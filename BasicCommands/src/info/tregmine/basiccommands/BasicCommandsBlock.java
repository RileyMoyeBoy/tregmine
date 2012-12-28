package info.tregmine.basiccommands;


import info.tregmine.api.TregminePlayer;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
//import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
//import org.bukkit.event.entity.EntityChangeBlockEvent;
//import org.bukkit.event.hanging.HangingEvent;
//import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
//import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.meta.FireworkMeta;

public class BasicCommandsBlock implements Listener {
	private final BasicCommands plugin;

	public BasicCommandsBlock(BasicCommands instance) {
		this.plugin = instance;
		plugin.getServer();
	}

	@EventHandler 
	public void fireWorkButton(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			TregminePlayer player = this.plugin.tregmine.getPlayer(event.getPlayer());
			Block block = event.getClickedBlock();
			
			if (info.tregmine.api.math.Checksum.block(block) == -1845477288) {

				if (!this.plugin.fireWorkEffect.containsKey(player.getName())) {
					this.plugin.fireWorkEffect.put(player.getName(), FireworkEffect.builder());
				}
				
				if (this.plugin.fireWorkEffect.containsKey(player.getName())) {
					this.plugin.fireWorkEffect.get(player.getName()).withColor(Color.WHITE);
					player.sendMessage(ChatColor.AQUA + "You have now added White color");
				}
				
			}

			if (info.tregmine.api.math.Checksum.block(block) == -337925479) {

				if (!this.plugin.fireWorkEffect.containsKey(player.getName())) {
					this.plugin.fireWorkEffect.put(player.getName(), FireworkEffect.builder());
				}
				
				if (this.plugin.fireWorkEffect.containsKey(player.getName())) {
					this.plugin.fireWorkEffect.get(player.getName()).withColor(Color.AQUA);
					player.sendMessage(ChatColor.AQUA + "You have now added Aqua color");
				}
				
			}

			
			Location loc = player.getLocation();
            Firework f1 = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            FireworkMeta meta = f1.getFireworkMeta();
            meta.setPower(1);
            meta.addEffect(this.plugin.fireWorkEffect.get(player.getName()).build());
            f1.setFireworkMeta(meta);

			
		}
	}
	
	
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player player = event.getPlayer();
			Block block = event.getClickedBlock();
			Location loc = block.getLocation();

			if (player.getItemInHand().getType() == Material.BOOK) {

				player.sendMessage(ChatColor.DARK_AQUA + "Type: " + ChatColor.AQUA + block.getType().toString() + " ("+ ChatColor.BLUE + block.getType().getId() + ChatColor.DARK_AQUA + ")" );
				player.sendMessage(ChatColor.DARK_AQUA +"Data: " + ChatColor.AQUA + (int) block.getData() );
				player.sendMessage(
						ChatColor.RED +"X" +
								ChatColor.WHITE + ", " +
								ChatColor.GREEN + "Y" +
								ChatColor.WHITE + ", " +
								ChatColor.BLUE + "Z" +
								ChatColor.WHITE + ": " +
								ChatColor.RED + loc.getBlockX() +
								ChatColor.WHITE + ", " +
								ChatColor.GREEN + loc.getBlockY() +
								ChatColor.WHITE + ", " +
								ChatColor.BLUE + loc.getBlockZ()
						);

				try {
					player.sendMessage(ChatColor.DARK_AQUA +"Biome: " + ChatColor.AQUA + block.getBiome().toString() );
				} catch (Exception e) {
					player.sendMessage(ChatColor.DARK_AQUA +"Biome: " + ChatColor.AQUA + "NULL" );
				}


				//				player.sendMessage(ChatColor.DARK_AQUA +"Light: " + ChatColor.AQUA + (int) block.getFace(BlockFace.UP).getLightLevel() );
				player.sendMessage(ChatColor.DARK_AQUA +"Hash2d: " + ChatColor.AQUA + info.tregmine.api.math.Checksum.flat(block) );
				player.sendMessage(ChatColor.DARK_AQUA +"Hash3d: " + ChatColor.AQUA + info.tregmine.api.math.Checksum.block(block) );
				plugin.log.info("BlockHash2d: " +  info.tregmine.api.math.Checksum.flat(block) );
				plugin.log.info("BlockHash3d: " +  info.tregmine.api.math.Checksum.block(block) );
				plugin.log.info("POS: " +  loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ());
			}
		}
	}    
}
