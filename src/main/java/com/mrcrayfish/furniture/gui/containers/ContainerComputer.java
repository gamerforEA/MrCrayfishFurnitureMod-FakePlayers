package com.mrcrayfish.furniture.gui.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerComputer extends Container
{
	// TODO gamerforEA code start
	public final IInventory computerInventory;
	// TODO gamerforEA code end

	public ContainerComputer(IInventory playerInventory, IInventory computerInventory)
	{
		// TODO gamerforEA code start
		this.computerInventory = computerInventory;
		// TODO gamerforEA code end

		computerInventory.openInventory();
		this.addSlotToContainer(new Slot(computerInventory, 0, 119, 40));

		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, j * 18 + 8, i * 18 + 95));
			}
		}

		for (int i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(playerInventory, i, i * 18 + 8, 153));
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		// TODO gamerforEA code replace, old code:
		// return true;
		return this.computerInventory.isUseableByPlayer(player);
		// TODO gamerforEA code end
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber)
	{
		ItemStack prevStack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotNumber);
		if (slot != null && slot.getHasStack())
		{
			ItemStack stack = slot.getStack();
			prevStack = stack.copy();
			if (slotNumber == 0)
			{
				if (!this.mergeItemStack(stack, 1, this.inventorySlots.size(), true))
					return null;
			}
			else if (!this.mergeItemStack(stack, 0, 1, false))
				return null;

			if (stack.stackSize == 0)
				slot.putStack(null);
			else
				slot.onSlotChanged();
		}

		return prevStack;
	}

	@Override
	public void onContainerClosed(EntityPlayer player)
	{
		super.onContainerClosed(player);
	}
}
