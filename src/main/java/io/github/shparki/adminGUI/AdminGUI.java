package io.github.shparki.adminGUI;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;


public class AdminGUI extends JavaPlugin implements Listener{
	
	public static Inventory mainMenu = Bukkit.createInventory(null, 9, "Admin GUI - Main Menu");
	public static Inventory timeMenu = Bukkit.createInventory(null, 9, "Admin GUI - Time Menu");
	public static Inventory gamemodeMenu = Bukkit.createInventory(null, 9, "Admin GUI - Gamemode Menu");
	public static Inventory warpMenu = Bukkit.createInventory(null, 9, "Admin GUI - Warp Menu");
	
	
	public static ItemStack prevMenuIcon = new ItemStack(Material.EYE_OF_ENDER);
	public static ItemStack closeMenuIcon = new ItemStack(Material.BONE);
	
	public static ItemStack setTimeIcon = new ItemStack(Material.WATCH);
	public static ItemStack setTimeMorningIcon = new ItemStack(Material.GOLD_BLOCK);
	public static ItemStack setTimeNoonIcon = new ItemStack(Material.GLOWSTONE);
	public static ItemStack setTimeNightIcon = new ItemStack(Material.OBSIDIAN);
	
	public static ItemStack setGamemodeIcon = new ItemStack(Material.DIAMOND);
	public static ItemStack setGamemodeAdventureIcon = new ItemStack(Material.LEATHER_HELMET);
	public static ItemStack setGamemodeSurvivalIcon = new ItemStack(Material.DIAMOND_HELMET);
	public static ItemStack setGamemodeCreativeIcon = new ItemStack(Material.JACK_O_LANTERN);
	
	public static ItemStack viewHomesIcon = new ItemStack(Material.BEACON);
	public static ItemStack setHome1Icon = new ItemStack(Material.IRON_INGOT);
	public static ItemStack tpHome1Icon = new ItemStack(Material.IRON_BLOCK);
	public static ItemStack setHome2Icon = new ItemStack(Material.GOLD_INGOT);
	public static ItemStack tpHome2Icon = new ItemStack(Material.GOLD_BLOCK);
	
	public static ItemStack announceIcon = new ItemStack(Material.BOOK_AND_QUILL);
	
	public static Location home1 = null, home2 = null;
	
	
	
	public static void makeIcon (ItemStack icon, String title, String desc){
		ItemMeta itemMeta = icon.getItemMeta();
		ArrayList<String> itemLore = new ArrayList<String>();
		itemLore.add(desc);
		
		itemMeta.setDisplayName(title);
		itemMeta.setLore(itemLore);
		
		icon.setItemMeta(itemMeta);
	}
	public static void initIcons(){
		makeIcon(prevMenuIcon, "Return", "As one man once said that one time: \"Just. Leave.\"");
		makeIcon(closeMenuIcon, "Close", "Bye Felicia");
		
		makeIcon(setTimeIcon, "Set Time", "Turn to the brighter side of things");
		makeIcon(setTimeMorningIcon, "Morning", "Dammmmnnn Sun. Back at it again with burning my retinas");
		makeIcon(setTimeNoonIcon, "Noon", "Time to wake up");
		makeIcon(setTimeNightIcon, "Midnight", "Come to the darkside");
		
		makeIcon(setGamemodeIcon, "Set Gamemode", "Make life easier on yourself");
		makeIcon(setGamemodeAdventureIcon, "Adventure", "For when you want to punch things and not see results");
		makeIcon(setGamemodeSurvivalIcon, "Survival", "For when you want to waste 48 hours on a dirt hut");
		makeIcon(setGamemodeCreativeIcon, "Creative", "For when you want to waste 48 hours on a diamond hut. Yay dimaondsss");
	
		makeIcon(viewHomesIcon, "View Homes", "View all of your 2 homes");
		makeIcon(setHome1Icon, "Set Home 1", "You kinda don't have a Home 1...");
		makeIcon(tpHome1Icon, "Teleport to Home 1", "Baby lock them doors and turn them lights down low...");
		makeIcon(setHome2Icon, "Set Home 2", "You kinda don't have a Home 2...");
		makeIcon(tpHome2Icon, "Teleport to Home 2", "Sssshh... Home 1 will never find out");
		makeIcon(announceIcon, "Issue Server Announcement", "Just incase you didn't hear me every 5 minutes for the past 3 hours");
	}
	
	public static void createMainMenu(){
		mainMenu.clear();
		
		mainMenu.setItem(0, prevMenuIcon);
		mainMenu.setItem(2, setTimeIcon);
		mainMenu.setItem(3, setGamemodeIcon);
		mainMenu.setItem(4, viewHomesIcon);
		mainMenu.setItem(6, announceIcon);		
		mainMenu.setItem(8, closeMenuIcon);
	}
	public static void createTimeMenu(){
		timeMenu.clear();
		
		timeMenu.setItem(0, prevMenuIcon);
		timeMenu.setItem(3, setTimeMorningIcon);
		timeMenu.setItem(4, setTimeNoonIcon);
		timeMenu.setItem(5, setTimeNightIcon);		
		timeMenu.setItem(8, closeMenuIcon);
	}
	public static void createGamemodeMenu(){
		gamemodeMenu.clear();
		
		gamemodeMenu.setItem(0, prevMenuIcon);
		gamemodeMenu.setItem(3, setGamemodeAdventureIcon);
		gamemodeMenu.setItem(4, setGamemodeSurvivalIcon);
		gamemodeMenu.setItem(5, setGamemodeCreativeIcon);		
		gamemodeMenu.setItem(8, closeMenuIcon);
	}
	public static void createWarpMenu(){
		warpMenu.clear();
		
		warpMenu.setItem(0, prevMenuIcon);
		warpMenu.setItem(2, setHome1Icon);
		warpMenu.setItem(3, tpHome1Icon);
		warpMenu.setItem(5, setHome2Icon);
		warpMenu.setItem(6, tpHome2Icon);		
		warpMenu.setItem(8, closeMenuIcon);
	}

	static { 
		initIcons();
		
		createMainMenu();
		createTimeMenu();
		createGamemodeMenu();
		createWarpMenu();
	}
	
	
	@Override
	public void onEnable(){ 
		getServer().getPluginManager().registerEvents(this, this);
	}
	@Override 
	public void onDisable(){
		HandlerList.unregisterAll((Listener)this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (cmd.getName().equalsIgnoreCase("admingui")){
			if (sender instanceof Player){
				Player player = (Player) sender;
				player.closeInventory();
				player.openInventory(mainMenu);
				return true;
			}
		}
		return false;
	}
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		String invName = e.getInventory().getName();
		
		if (invName.equals(mainMenu.getName())){
			handleMainMenu(e);
		} else if (invName.equals(timeMenu.getName())){
			handleTimeMenu(e);
		} else if (invName.equals(gamemodeMenu.getName())){
			handleGamemodeMenu(e);
		} else if (invName.equals(warpMenu.getName())){
			handleWarpMenu(e);
		}
	}

	public void handleMainMenu(InventoryClickEvent e){
		Player player = (Player) e.getWhoClicked();
		ItemStack iconClicked = e.getCurrentItem();
		
		
		if (iconClicked.equals(prevMenuIcon)){
			e.setCancelled(true);
			player.sendMessage("Oh silly steve, there is no returning from here");
		} else if (iconClicked.equals(setTimeIcon)){
			e.setCancelled(true);
			player.closeInventory();
			player.openInventory(timeMenu);
		} else if (iconClicked.equals(setGamemodeIcon)){
			e.setCancelled(true);
			player.closeInventory();
			player.openInventory(gamemodeMenu);
		} else if (iconClicked.equals(viewHomesIcon)){
			e.setCancelled(true);
			player.closeInventory();
			createWarpMenu();
			player.openInventory(warpMenu);
		} else if (iconClicked.equals(announceIcon)){
			e.setCancelled(true);
			Bukkit.broadcastMessage(player.getDisplayName() + " thinks they are better than all of you combined. You are all welcome.");
		} else if (iconClicked.equals(closeMenuIcon)){
			e.setCancelled(true);
			player.closeInventory();
			player.sendMessage("So long, farewell. Hope to never see you again. Hastalabyebye");
		} else { 
			allHailBacon(player);
		}
	}
	public void handleTimeMenu(InventoryClickEvent e){
		Player player = (Player) e.getWhoClicked();
		ItemStack iconClicked = e.getCurrentItem();
		World world = player.getWorld();
		
		if (iconClicked.equals(prevMenuIcon)){
			e.setCancelled(true);
			player.closeInventory();
			player.openInventory(mainMenu);
		} else if (iconClicked.equals(setTimeMorningIcon)){
			e.setCancelled(true);
			world.setTime(23000);
		} else if (iconClicked.equals(setTimeNoonIcon)){
			e.setCancelled(true);
			world.setTime(8000);
		} else if (iconClicked.equals(setTimeNightIcon)){
			e.setCancelled(true);
			world.setTime(16000);
		} else if (iconClicked.equals(closeMenuIcon)){
			e.setCancelled(true);
			player.closeInventory();
			player.sendMessage("So long, farewell. Hope to never see you again. Hastalabyebye");
		} else { allHailBacon(player); }
	}
	public void handleGamemodeMenu(InventoryClickEvent e){
		Player player = (Player) e.getWhoClicked();
		ItemStack iconClicked = e.getCurrentItem();
		
		if (iconClicked.equals(prevMenuIcon)){
			e.setCancelled(true);
			player.closeInventory();
			player.openInventory(mainMenu);
		} else if (iconClicked.equals(setGamemodeAdventureIcon)){
			e.setCancelled(true);
			player.closeInventory();
			player.setGameMode(GameMode.ADVENTURE);
			player.openInventory(gamemodeMenu);
		} else if (iconClicked.equals(setGamemodeSurvivalIcon)){
			e.setCancelled(true);
			player.closeInventory();
			player.setGameMode(GameMode.SURVIVAL);
			player.openInventory(gamemodeMenu);
		} else if (iconClicked.equals(setGamemodeCreativeIcon)){
			e.setCancelled(true);
			player.closeInventory();
			player.setGameMode(GameMode.CREATIVE);
			player.openInventory(gamemodeMenu);
		} else if (iconClicked.equals(closeMenuIcon)){
			e.setCancelled(true);
			player.closeInventory();
			player.sendMessage("So long, farewell. Hope to never see you again. Hastalabyebye");
		} else { allHailBacon(player); }
	}
	public void handleWarpMenu(InventoryClickEvent e){
		Player player = (Player) e.getWhoClicked();
		ItemStack iconClicked = e.getCurrentItem();
		
		if (iconClicked.equals(prevMenuIcon)){
			e.setCancelled(true);
			player.closeInventory();
			player.openInventory(mainMenu);
		} if (iconClicked.equals(setHome1Icon)){
			e.setCancelled(true);
			player.closeInventory();
			home1 = player.getLocation();
			makeIcon(setHome1Icon, "Set Home 1", 
					"Baby come home to (" + home1.getBlockX() + ", " + home1.getBlockY() + ", " + home1.getBlockZ() + ")");
			player.sendMessage(
					"Home 1 saved to (" + home1.getBlockX() + ", " + home1.getBlockY() + ", " + home1.getBlockZ() + "). I Hope you're happy.");
			createWarpMenu();
			player.openInventory(warpMenu);
		} else if (iconClicked.equals(tpHome1Icon)){
			e.setCancelled(true);
			if (home1 != null){
				player.teleport(home1);
				player.openInventory(warpMenu);
			} else {
				player.sendMessage("You don't have a home 1. Shocker.");
			}
		} else if (iconClicked.equals(setHome2Icon)){
			e.setCancelled(true);
			player.closeInventory();
			home2 = player.getLocation();
			makeIcon(setHome2Icon, "Set Home 2", 
					"Baby come home to (" + home2.getBlockX() + ", " + home2.getBlockY() + ", " + home2.getBlockZ() + ")");
			player.sendMessage(
					"Home 2 saved to (" + home2.getBlockX() + ", " + home2.getBlockY() + ", " + home2.getBlockZ() + "). I Hope you're happy.");
			createWarpMenu();
			player.openInventory(warpMenu);
		} else if (iconClicked.equals(tpHome2Icon)){
			e.setCancelled(true);
			if (home2 != null){
				player.teleport(home2);
				player.openInventory(warpMenu);
			} else {
				player.sendMessage("You don't have a home 2. Shocker.");
			}
		} else if (iconClicked.equals(closeMenuIcon)){
			e.setCancelled(true);
			player.closeInventory();
			player.sendMessage("So long, farewell. Hope to never see you again. Hastalabyebye");
		} else { allHailBacon(player); }
	}
	
	public void allHailBacon(Player player){
		//Used for debug; Kept because it's life does have meaning
		
		player.sendMessage("ALL HAIL BACON!");
		player.getInventory().addItem(new ItemStack(Material.GRILLED_PORK, 1));
	}
	
}
