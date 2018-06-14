package com.mrcrayfish.furniture.gui.containers;

import com.mrcrayfish.furniture.gui.slots.SlotEnvelope;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEnvelope extends Container
{
	private int numRows;

	// TODO gamerforEA code start
	public final IInventory envelopeInventory;
	// TODO gamerforEA code end

	public ContainerEnvelope(IInventory playerInventory, IInventory envelopeInventory)
	{
		// TODO gamerforEA code start
		this.envelopeInventory = envelopeInventory;
		// TODO gamerforEA code end

		envelopeInventory.openInventory();
		int var3 = (this.numRows - 4) * 18;
		this.addSlotToContainer(new SlotEnvelope(envelopeInventory, 0, 71, 18));
		this.addSlotToContainer(new SlotEnvelope(envelopeInventory, 1, 89, 18));

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
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack var3 = null;
		Slot var4 = (Slot) this.inventorySlots.get(par2);
		if (var4 != null && var4.getHasStack() && var4 instanceof SlotEnvelope)
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			if (par2 < 2)
			{
				if (!this.mergeItemStack(var5, 2, this.inventorySlots.size(), true))
					return null;
			}
			else if (!this.mergeItemStack(var5, 0, 2, false))
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
