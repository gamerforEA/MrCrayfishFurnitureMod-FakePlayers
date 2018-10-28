package com.mrcrayfish.furniture.gui.containers;

import com.gamerforea.mrcrayfishfurniture.ContainerItem;
import com.gamerforea.mrcrayfishfurniture.SlotPresent;
import com.mrcrayfish.furniture.items.IMail;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

// TODO gamerforEA extend ContainerItem, old class: Container
public class ContainerPresent extends ContainerItem
{
	private int numRows;
	private IInventory present;

	// TODO gamerforEA code start
	public final IInventory presentInventory;

	@Override
	protected boolean isValidItem(ItemStack stack)
	{
		return stack.getItem() instanceof IMail;
	}
	// TODO gamerforEA code end

	public ContainerPresent(IInventory playerInventory, IInventory presentInventory)
	{
		// TODO gamerforEA code start
		super((InventoryPlayer) playerInventory);
		this.presentInventory = presentInventory;
		// TODO gamerforEA code end

		this.present = presentInventory;
		presentInventory.openInventory();
		int var3 = (this.numRows - 4) * 18;

		// TODO gamerforEA use SlotPresent
		this.addSlotToContainer(new SlotPresent(presentInventory, 0, 71, 16));
		// TODO gamerforEA use SlotPresent
		this.addSlotToContainer(new SlotPresent(presentInventory, 1, 89, 16));
		// TODO gamerforEA use SlotPresent
		this.addSlotToContainer(new SlotPresent(presentInventory, 2, 71, 34));
		// TODO gamerforEA use SlotPresent
		this.addSlotToContainer(new SlotPresent(presentInventory, 3, 89, 34));

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
	public ItemStack slotClick(int slot, int button, int buttonType, EntityPlayer player)
	{
		// TODO gamerforEA code start
		if (slot == this.getCurrentItemSlot())
			return null;
		if (buttonType == 2 && button == this.getCurrentItemSlot())
			return null;
		if (!this.canInteractWith(player))
			return null;
		// TODO gamerforEA code end

		this.present.closeInventory();
		return super.slotClick(slot, button, buttonType, player);
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
		if (var4 != null && var4.getHasStack())
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			if (var5.getItem() instanceof IMail)
				return null;

			if (slot < 4)
			{
				if (!this.mergeItemStack(var5, 4, this.inventorySlots.size(), true))
					return null;
			}
			else if (!this.mergeItemStack(var5, 0, 4, false))
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
		this.present.closeInventory();
	}
}
