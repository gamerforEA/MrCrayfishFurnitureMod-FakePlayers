package com.gamerforea.mrcrayfishfurniture;

import com.mrcrayfish.furniture.items.IMail;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public final class SlotPresent extends Slot
{
	public SlotPresent(IInventory inventory, int slotIndex, int x, int y)
	{
		super(inventory, slotIndex, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return stack == null || !(stack.getItem() instanceof IMail);
	}
}
