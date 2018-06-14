package com.mrcrayfish.furniture.items;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.gui.inventory.InventoryPresent;
import com.mrcrayfish.furniture.tileentity.TileEntityPresent;
import com.mrcrayfish.furniture.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class ItemPresent extends Item implements IMail
{
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		if (par1ItemStack.hasTagCompound())
		{
			NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
			NBTTagString nbttagstring = (NBTTagString) nbttagcompound.getTag("Author");
			if (nbttagstring != null)
				par3List.add(EnumChatFormatting.GRAY + "from " + nbttagstring.func_150285_a_());
			else
				par3List.add(EnumChatFormatting.GRAY + "Unsigned");
		}

	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset)
	{
		if (world.getBlock(x, y, z).isSideSolid(world, x, y, z, ForgeDirection.UP))
		{
			int meta = 0;
			if (this == MrCrayfishFurnitureMod.itemPresentGreen)
				meta = 1;

			if (stack.hasTagCompound())
			{
				NBTTagCompound nbt = stack.getTagCompound();
				NBTTagString authorNbt = (NBTTagString) nbt.getTag("Author");
				if (authorNbt != null)
				{
					NBTTagList itemList = (NBTTagList) NBTHelper.getCompoundTag(stack, "Present").getTag("Items");
					if (itemList.tagCount() > 0)
					{
						world.setBlock(x, y + 1, z, MrCrayfishFurnitureMod.present, meta, 2);
						world.playSoundEffect((double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), MrCrayfishFurnitureMod.present.stepSound.func_150496_b(), (MrCrayfishFurnitureMod.present.stepSound.getVolume() + 1.0F) / 2.0F, MrCrayfishFurnitureMod.present.stepSound.getPitch() * 0.8F);

						/* TODO gamerforEA replace, old code:
						if (world.isRemote)
							PacketHandler.INSTANCE.sendToServer(new MessagePresent(stack, x, y + 1, z)); */
						if (!world.isRemote)
						{
							TileEntity tile = world.getTileEntity(x, y + 1, z);
							if (tile instanceof TileEntityPresent)
							{
								TileEntityPresent tilePresent = (TileEntityPresent) tile;

								InventoryPresent presentInventory = new InventoryPresent(player, stack);
								for (int i = 0; i < presentInventory.getSizeInventory(); ++i)
								{
									tilePresent.setContents(i, presentInventory.getStackInSlot(i));
								}

								tilePresent.setOwner(authorNbt.func_150285_a_());
							}
						}
						// TODO gamerforEA code end

						--stack.stackSize;
					}
					else if (world.isRemote)
						player.addChatMessage(new ChatComponentText("You some how have no items in the present. You cannot use this present."));
				}
				else if (world.isRemote)
					player.addChatMessage(new ChatComponentText("You need to sign it before you can place it"));
			}
			else if (world.isRemote)
				player.addChatMessage(new ChatComponentText("You need to sign it before you can place it"));
		}

		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par2World.isRemote)
			if (par1ItemStack.hasTagCompound())
			{
				NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
				NBTTagString nbttagstring = (NBTTagString) nbttagcompound.getTag("Author");
				if (nbttagstring == null)
					par3EntityPlayer.openGui(MrCrayfishFurnitureMod.instance, 9, par2World, 0, 0, 0);
				else if (nbttagstring.func_150285_a_().equals(""))
					par3EntityPlayer.addChatMessage(new ChatComponentText("You cannot unwrap the present once signed"));
			}
			else
				par3EntityPlayer.openGui(MrCrayfishFurnitureMod.instance, 9, par2World, 0, 0, 0);

		return par1ItemStack;
	}

	public static IInventory getInv(EntityPlayer par1EntityPlayer)
	{
		ItemStack present = par1EntityPlayer.getCurrentEquippedItem();
		InventoryPresent invPresent = null;
		if (present != null && present.getItem() instanceof ItemPresent)
			invPresent = new InventoryPresent(par1EntityPlayer, present);

		return invPresent;
	}
}
