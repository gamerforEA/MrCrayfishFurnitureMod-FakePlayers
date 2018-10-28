package com.mrcrayfish.furniture.gui.containers;

import com.gamerforea.mrcrayfishfurniture.ContainerItem;
import com.mrcrayfish.furniture.gui.slots.SlotEnvelope;
import com.mrcrayfish.furniture.items.IMail;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

// TODO gamerforEA extend ContainerItem, old class: Container
public class ContainerEnvelope extends ContainerItem
{
	private int numRows;

	// TODO gamerforEA code start
	public final IInventory envelopeInventory;

	@Override
	protected boolean isValidItem(ItemStack stack)
	{
		return stack.getItem() instanceof IMail;
	}
	// TODO gamerforEA code end

	public ContainerEnvelope(IInventory playerInventory, IInventory envelopeInventory)
	{
		// TODO gamerforEA code start
		super((InventoryPlayer) playerInventory);
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
		if (var4 != null && var4.getHasStack() && var4 instanceof SlotEnvelope)
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			if (slot < 2)
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
