package com.mrcrayfish.furniture.gui.containers;

import com.mrcrayfish.furniture.items.IMail;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPresent extends Container
{
	private int numRows;
	private IInventory present;

	// TODO gamerforEA code start
	public final IInventory presentInventory;
	// TODO gamerforEA code end

	public ContainerPresent(IInventory playerInventory, IInventory presentInventory)
	{
		// TODO gamerforEA code start
		this.presentInventory = presentInventory;
		// TODO gamerforEA code end

		this.present = presentInventory;
		presentInventory.openInventory();
		int var3 = (this.numRows - 4) * 18;
		this.addSlotToContainer(new Slot(presentInventory, 0, 71, 16));
		this.addSlotToContainer(new Slot(presentInventory, 1, 89, 16));
		this.addSlotToContainer(new Slot(presentInventory, 2, 71, 34));
		this.addSlotToContainer(new Slot(presentInventory, 3, 89, 34));

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
	public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
	{
		this.present.closeInventory();
		return super.slotClick(par1, par2, par3, par4EntityPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack var3 = null;
		Slot var4 = (Slot) this.inventorySlots.get(par2);
		if (var4 != null && var4.getHasStack())
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			if (var5.getItem() instanceof IMail)
				return null;

			if (par2 < 4)
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
