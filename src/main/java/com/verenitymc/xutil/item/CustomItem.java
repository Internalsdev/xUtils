package com.verenitymc.xutil.item;

import com.verenitymc.xutil.Core;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mat Superclass for both the physical items and the inventory items
 * Just takes basic item info that would be needed for both, seems weird now and
 * useless But you might see where I'm going with this.
 */
public class CustomItem {

	private Material material;
	private List<String> lore;
	private String name;
	private short dataValue;
	private short durability;

	private ItemStack finalItemStack;

	/***
	 * @param name Name of item, not that hard, can be left blank
	 * @param material material, can't be null
	 * @param lore explanatory, can be null or empty though
	 * @param dataValue Specific data value for item.
	 * @param durability Used for custom models if we ever need them, can usually just be set to 0
	 */
	public CustomItem(String name, Material material, List<String> lore, short dataValue, short durability) {
		this.name = ChatColor.translateAlternateColorCodes('&', name);
		this.material = material;
		this.lore = lore;
		this.dataValue = dataValue;
		this.durability = durability;
		generateItemStack();
	}

	public CustomItem(ItemStack item) {
		finalItemStack = item;
	}

	private void generateItemStack() {
		ItemStack itemStack = new ItemStack(material, 1, dataValue);
		ItemMeta im = itemStack.getItemMeta();
		im.setDisplayName(name);
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		if (lore != null && lore.size() > 0) {
			List<String> newlores = new ArrayList<String>();

			for (String string : lore) {
				newlores.add(ChatColor.translateAlternateColorCodes('&', string));
			}

			im.setLore(newlores);
		}

		if (durability > 0) {
			itemStack.setDurability(durability);
			im.spigot().setUnbreakable(true);
			im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		}

		itemStack.setItemMeta(im);

		finalItemStack = itemStack;

	}

	public ItemStack getItemStack() {
		return finalItemStack;
	}

	/**
	 * Set the item as glowing or not.
	 * Might act differently in a physical sense, but inventory just needs to be updated
	 * if used in an inventory using the "update" method.
	 * @param glowing - if they want their item as glowing.
	 */
	public void setGlowing(boolean glowing)
	{
		if(glowing)
		{
			getItemStack().addUnsafeEnchantment(Core.getGlowEffect(), 1);
		}
		else
		{
			getItemStack().removeEnchantment(Core.getGlowEffect());
		}
	}

	protected boolean isCustomModel() {
		return durability > 0;
	}

}
