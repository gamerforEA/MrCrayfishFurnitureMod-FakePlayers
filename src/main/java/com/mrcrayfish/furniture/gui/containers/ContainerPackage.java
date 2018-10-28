package com.mrcrayfish.furniture.gui.containers;

import com.gamerforea.mrcrayfishfurniture.ContainerItem;
import com.mrcrayfish.furniture.gui.slots.SlotPackage;
import com.mrcrayfish.furniture.items.IMail;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

// TODO gamerforEA extend ContainerItem, old class: Container
public class ContainerPackage extends ContainerItem
{
	private int numRows;

	// TODO gamerforEA code start
	public final IInventory packageInventory;

	@Override
	protected boolean isValidItem(ItemStack stack)
	{
		return stack.getItem() instanceof IMail;
	}
	// TODO gamerforEA code end

	public ContainerPackage(IInventory playerInventory, IInventory packageInventory)
	{
		// TODO gamerforEA code start
		super((InventoryPlayer) playerInventory);
		this.packageInventory = packageInventory;
		// TODO gamerforEA code end

		packageInventory.openInventory();
		int var3 = (this.numRows - 4) * 18;
		this.addSlotToContainer(new SlotPackage(packageInventory, 0, 62, 15));
		this.addSlotToContainer(new SlotPackage(packageInventory, 1, 62, 33));
		this.addSlotToContainer(new SlotPackage(packageInventory, 2, 80, 15));
		this.addSlotToContainer(new SlotPackage(packageInventory, 3, 80, 33));
		this.addSlotToContainer(new SlotPackage(packageInventory, 4, 98, 15));
		this.addSlotToContainer(new SlotPackage(packageInventory, 5, 98, 33));

		for (int var4 = 0; var4 < 3; ++var4)
		{
			for (int var5 = 0; var5 < 9; ++var5)
			{
				this.addSlotToContainer(new Slot(playerInventory, var5 + var4 * 9 + 9, 8 + var5 * 18, 103 + var4 * 18 + var3 + 53));
			}
		}

		for (int var4 = 0; var4 < 9; ++var4)
		{
			this.addSlotToContainer(new Slot(playerInventory, var4, 8 + var4 * 18, 161 + var3 + 53));
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		// TODO gamerforEA code replace, old code:
		// return true;
		return super.canInteractWith(player);
		// TODO gamerforEA code end
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot)
	{
		// TODO gamerforEA code start
		if (slot == this.getCurrentItemSlot())
			return null;
		if (!this.canInteractWith(player))
			return null;
		// TODO gamerforEA code end

		ItemStack var3 = null;
		Slot var4 = (Slot) this.inventorySlots.get(slot);
		if (var4 != null && var4.getHasStack() && var4 instanceof SlotPackage)
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			if (slot < 6)
			{
				if (!this.mergeItemStack(var5, 6, this.inventorySlots.size(), true))
					return null;
			}
			else if (!this.mergeItemStack(var5, 0, 6, false))
				return null;

			if (var5.stackSize == 0)
				var4.putStack(null);
			else
				var4.onSlotChanged();
		}

		return var3;
	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
	{
		super.onContainerClosed(par1EntityPlayer);
	}
}
