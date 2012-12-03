package de.bdh.ks;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KSHelper 
{
	Main m;
	public KSHelper(Main main) {
		this.m = main;
	}
	
	public void getDeliver(Player p)
	{
		//Bearbeite die Delivery Tabelle ab
		//Und gib dem Spieler Geld/Items
	}
	
	public Map<Integer,Integer> getPrices(int material)
	{
		//Hole Liste von 
		return new HashMap<Integer,Integer>();
	}
	
	//Hole Maximale Angebotsmenge für Material
	public int getMaxAmount(int material)
	{
		return this.getMaxAmount(material, 999999999);
	}
	
	//Hole maximale Angebotsmenge für Material mit max. Preis
	public int getMaxAmount(int material, int maxPrice)
	{
		return 0;
	}
	
	//Füge Item in das AH ein
	public boolean enlistItem(int material, int amount)
	{
		return false;
	}
	
	//Kaufe Item, gibt zurück wieviele er wirklich gekauft hat
	public int buyItem(int material, int amount, int maxPrice)
	{
		return 0;
	}
	
	//Entferne Deliverys, welche über 30 Tage zurückliegen
	public void pruneDelivery()
	{
		
	}
	
	//Entferne Auktionen, die über 30 Tage alt sind
	public void pruneAuctions()
	{
		
	}
	
	//Setze in Abarbeitsungstabelle
	public boolean addDelivery(Player p, int money)
	{
		return false;
	}
	
	//Setze in Abarbeitungstabelle
	public boolean addDelivery(Player p, int typeid, int amount)
	{
		return false;
	}
	
	public boolean canbeSold(ItemStack i)
	{
		if(i != null)
		{
			//Keine benutzten Gegenstände verkaufbar
			if((i.getType().getMaxDurability() != -1 || i.getType() == Material.MAP) && i.getType().getMaxDurability() * 0.1f < i.getDurability())
				return false;
			//Keine Verzauberten Gegenstände verkaufbar
			if(i.getEnchantments().size() > 0)
				return false;
		}
		return true;
	}
	
	//Nimm Items vom Spieler
	public int takeItemsFromPlayer(Player p, int typeid, int amount)
	{
		int taken = 0;
		HashMap<Integer, ? extends ItemStack> stacks = p.getInventory().all(typeid);
		for (Entry<Integer, ? extends ItemStack> en : stacks.entrySet())
		{
			ItemStack stack = en.getValue();
			if(stack != null)
			{
				if(canbeSold(stack))
				{
					if(stack.getAmount() <= amount)
					{
						taken += stack.getAmount();
						amount -= stack.getAmount();
						stack.setType(Material.AIR);
						stack.setAmount(0);
					} else
					{
						stack.setAmount(stack.getAmount() - amount);
						taken += amount;
						amount = 0;
					}
				}
			}
			
			if(amount <= 0)
				return taken;
		}
		return taken;
	}
}
