package com.mrcrayfish.furniture.gui.inventory;

import com.mrcrayfish.furniture.items.IMail;
import com.mrcrayfish.furniture.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.UUID;

public class InventoryPresent extends InventoryBasic
{
	protected EntityPlayer playerEntity;

	// TODO gamerforEA code replace, old code:
	// protected static ItemStack present;
	protected ItemStack present;
	// TODO gamerforEA code end

	protected boolean reading = false;
	protected String uniqueID = "";

	public InventoryPresent(EntityPlayer player, ItemStack present)
	{
		super("Present", false, getInventorySize());
		this.playerEntity = player;
		this.present = present;
		if (!this.hasInventory())
		{
			this.uniqueID = UUID.randomUUID().toString();
			this.createInventory();
		}

		this.loadInventory();
	}

	@Override
	public void markDirty()
	{
		super.markDirty();
		if (!this.reading)
			this.saveInventory();

	}

	@Override
	public void openInventory()
	{
		this.loadInventory();
	}

	@Override
	public void closeInventory()
	{
		this.saveInventory();
	}

	protected static int getInventorySize()
	{
		return 4;
	}

	protected boolean hasInventory()
	{
		return NBTHelper.hasTag(this.present, "Present");
	}

	protected void createInventory()
	{
		this.writeToNBT();
	}

	protected void setNBT()
	{
		for (ItemStack itemStack : this.playerEntity.inventory.mainInventory)
		{
			if (itemStack != null && itemStack.getItem() instanceof IMail)
			{
				NBTTagCompound nbt = itemStack.getTagCompound();
				if (nbt != null && nbt.getCompoundTag("Present").getString("UniqueID").equals(this.uniqueID))
				{
					itemStack.setTagCompound(this.present.getTagCompound());
					break;
				}
			}
		}

	}

	public void loadInventory()
	{
		this.readFromNBT();
	}

	public void saveInventory()
	{
		this.writeToNBT();
		this.setNBT();
	}

	public String getSender()
	{
		return NBTHelper.getString(this.present, "Author");
	}

	protected void readFromNBT()
	{
		this.reading = true;
		NBTTagCompound nbt = NBTHelper.getCompoundTag(this.present, "Present");
		if ("".equals(this.uniqueID))
		{
			this.uniqueID = nbt.getString("UniqueID");
			if ("".equals(this.uniqueID))
				this.uniqueID = UUID.randomUUID().toString();
		}

		NBTTagList itemList = (NBTTagList) NBTHelper.getCompoundTag(this.present, "Present").getTag("Items");

		for (int i = 0; i < itemList.tagCount(); ++i)
		{
			NBTTagCompound slotEntry = itemList.getCompoundTagAt(i);
			int j = slotEntry.getByte("Slot") & 255;
			if (j >= 0 && j < this.getSizeInventory())
				this.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(slotEntry));
		}

		this.reading = false;
	}

	protected void writeToNBT()
	{
		NBTTagList itemList = new NBTTagList();

		for (int i = 0; i < this.getSizeInventory(); ++i)
		{
			ItemStack stackInSlot = this.getStackInSlot(i);
			if (stackInSlot != null)
			{
				NBTTagCompound slotEntry = new NBTTagCompound();
				slotEntry.setByte("Slot", (byte) i);
				stackInSlot.writeToNBT(slotEntry);
				itemList.appendTag(slotEntry);
			}
		}

		NBTTagCompound inventoryNbt = new NBTTagCompound();
		inventoryNbt.setTag("Items", itemList);
		inventoryNbt.setString("UniqueID", this.uniqueID);
		NBTHelper.setCompoundTag(this.present, "Present", inventoryNbt);
	}

	// TODO gamerforEA code start
	public void writeToNBT(NBTTagCompound nbt)
	{
		NBTTagList itemList = new NBTTagList();

		for (int i = 0; i < this.getSizeInventory(); ++i)
		{
			ItemStack stackInSlot = this.getStackInSlot(i);
			if (stackInSlot != null)
			{
				NBTTagCompound slotEntry = new NBTTagCompound();
				slotEntry.setByte("Slot", (byte) i);
				stackInSlot.writeToNBT(slotEntry);
				itemList.appendTag(slotEntry);
			}
		}

		NBTTagCompound inventoryNbt = new NBTTagCompound();
		inventoryNbt.setTag("Items", itemList);
		inventoryNbt.setString("UniqueID", this.uniqueID);
		nbt.setTag("Present", inventoryNbt);
	}
	// TODO gamerforEA code end
}
