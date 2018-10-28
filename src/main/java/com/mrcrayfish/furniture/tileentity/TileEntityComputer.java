package com.mrcrayfish.furniture.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityComputer extends TileEntity implements IInventory
{
	private ItemStack paySlot = null;
	public int stockNum = 0;
	private boolean isTrading = false;

	@Override
	public int getSizeInventory()
	{
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return this.paySlot;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		if (this.paySlot != null)
		{
			if (this.paySlot.stackSize <= amount)
			{
				ItemStack stack = this.paySlot;
				this.paySlot = null;
				this.markDirty();
				return stack;
			}
			ItemStack stack = this.paySlot.splitStack(amount);
			if (this.paySlot.stackSize == 0)
				this.paySlot = null;
			this.markDirty();
			return stack;
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		if (this.paySlot != null)
		{
			ItemStack stack = this.paySlot;
			this.paySlot = null;
			return stack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		// TODO gamerforEA code start
		if (stack != null && stack.stackSize <= 0)
			stack = null;
		// TODO gamerforEA code end

		this.paySlot = stack;
		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
			stack.stackSize = this.getInventoryStackLimit();
		this.markDirty();
	}

	public String getInvName()
	{
		return "Computer";
	}

	public void takeEmeraldFromSlot(int price)
	{
		if (this.paySlot != null)
		{
			this.paySlot.stackSize -= price;

			// TODO gamerforEA code start
			if (this.paySlot.stackSize <= 0)
				this.paySlot = null;
			// TODO gamerforEA code end
		}

		this.markDirty();
	}

	public void clearInventory()
	{
		this.paySlot = null;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		this.stockNum = par1NBTTagCompound.getInteger("StockNum");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("StockNum", this.stockNum);
	}

	public void setBrowsingInfo(int stockNum)
	{
		this.stockNum = stockNum;
	}

	public int getBrowsingInfo()
	{
		return this.stockNum;
	}

	public void setTrading(boolean isUsing)
	{
		this.isTrading = isUsing;
	}

	public boolean isTrading()
	{
		return this.isTrading;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void updateContainingBlockInfo()
	{
		super.updateContainingBlockInfo();
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
	}

	@Override
	public void openInventory()
	{
	}

	@Override
	public void closeInventory()
	{
		this.setTrading(false);
	}

	@Override
	public void invalidate()
	{
		this.updateContainingBlockInfo();
		super.invalidate();
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return false;
	}

	@Override
	public String getInventoryName()
	{
		return "Computer";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}
}
