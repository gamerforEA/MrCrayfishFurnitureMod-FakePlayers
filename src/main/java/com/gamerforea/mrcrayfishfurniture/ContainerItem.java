package com.gamerforea.mrcrayfishfurniture;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;

public abstract class ContainerItem extends Container
{
	private static final String NBT_KEY_UID = "UID";
	private final int currentItem;
	private final ItemStack currentItemStack;
	private int currentItemSlot = -1;

	public ContainerItem(InventoryPlayer playerInventory)
	{
		this.currentItem = playerInventory.currentItem;
		this.currentItemStack = playerInventory.getStackInSlot(this.currentItem);
		if (this.currentItemStack != null && this.isValidItem(this.currentItemStack))
		{
			if (!this.currentItemStack.hasTagCompound())
				this.currentItemStack.setTagCompound(new NBTTagCompound());
			NBTTagCompound nbt = this.currentItemStack.getTagCompound();
			if (!nbt.hasKey(NBT_KEY_UID))
				nbt.setString(NBT_KEY_UID, UUID.randomUUID().toString());
		}
	}

	protected int getCurrentItemSlot()
	{
		return this.currentItemSlot;
	}

	protected abstract boolean isValidItem(ItemStack stack);

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		InventoryPlayer inventoryPlayer = player.inventory;
		int currentItem = inventoryPlayer.currentItem;
		return this.currentItem == currentItem && this.isSameItem(inventoryPlayer.getStackInSlot(currentItem));
	}

	@Override
	public ItemStack slotClick(int slot, int button, int buttonType, EntityPlayer player)
	{
		if (slot == this.currentItemSlot)
			return null;
		if (buttonType == 2 && button == this.currentItemSlot)
			return null;
		if (!this.canInteractWith(player))
			return null;
		return super.slotClick(slot, button, buttonType, player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot)
	{
		return slot == this.currentItemSlot || !this.canInteractWith(player) ? null : super.transferStackInSlot(player, slot);
	}

	public boolean isSameItem(ItemStack stack)
	{
		return isSameItemInventory(stack, this.currentItemStack);
	}

	private static boolean isSameItemInventory(ItemStack base, ItemStack comparison)
	{
		if (base == null || comparison == null)
			return false;

		if (base.getItem() != comparison.getItem())
			return false;

		if (!base.hasTagCompound() || !comparison.hasTagCompound())
			return false;

		String baseUID = base.getTagCompound().getString(NBT_KEY_UID);
		String comparisonUID = comparison.getTagCompound().getString(NBT_KEY_UID);
		return baseUID != null && baseUID.equals(comparisonUID);
	}
}
