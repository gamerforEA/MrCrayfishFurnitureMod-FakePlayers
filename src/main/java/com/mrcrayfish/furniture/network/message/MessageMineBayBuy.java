package com.mrcrayfish.furniture.network.message;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.api.RecipeData;
import com.mrcrayfish.furniture.api.Recipes;
import com.mrcrayfish.furniture.gui.containers.ContainerComputer;
import com.mrcrayfish.furniture.tileentity.TileEntityComputer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MessageMineBayBuy implements IMessage, IMessageHandler<MessageMineBayBuy, IMessage>
{
	private int itemNum;
	private int x;
	private int y;
	private int z;
	private boolean shouldClear;

	public MessageMineBayBuy()
	{
	}

	public MessageMineBayBuy(int itemNum, int x, int y, int z, boolean shouldClear)
	{
		this.itemNum = itemNum;
		this.x = x;
		this.y = y;
		this.z = z;
		this.shouldClear = shouldClear;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.itemNum = buf.readInt();
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.shouldClear = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.itemNum);
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
		buf.writeBoolean(this.shouldClear);
	}

	@Override
	public IMessage onMessage(MessageMineBayBuy message, MessageContext ctx)
	{
		EntityPlayerMP player = ctx.getServerHandler().playerEntity;
		World world = player.worldObj;
		int x = message.x;
		int y = message.y;
		int z = message.z;

		/* TODO gamerforEA code replace, old code:
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileEntityComputer)
		{
			TileEntityComputer tileComputer = (TileEntityComputer) tile;
			ItemStack buySlot = tileComputer.getStackInSlot(0);
			RecipeData[] data = Recipes.getMineBayItems();
			int price = data[message.itemNum].getPrice();
			if (buySlot == null)
				return null;

			if (message.shouldClear)
				tileComputer.clearInventory();
			else
				tileComputer.takeEmeraldFromSlot(price);

			EntityItem entityItem = new EntityItem(player.worldObj, player.posX, player.posY + 1.0D, player.posZ, data[message.itemNum].getInput().copy());
			player.worldObj.spawnEntityInWorld(entityItem);
			player.triggerAchievement(FurnitureAchievements.buyItem);
		} */
		Container openContainer = player.openContainer;
		if (openContainer instanceof ContainerComputer)
		{
			ContainerComputer containerComputer = (ContainerComputer) openContainer;
			TileEntityComputer tileComputer = null;
			if (containerComputer.computerInventory instanceof TileEntityComputer)
			{
				tileComputer = (TileEntityComputer) containerComputer.computerInventory;
				if (x != tileComputer.xCoord || y != tileComputer.yCoord || z != tileComputer.zCoord)
					tileComputer = null;
			}
			else if (world.blockExists(x, y, z))
			{
				TileEntity tile = world.getTileEntity(x, y, z);
				if (tile instanceof TileEntityComputer)
					tileComputer = (TileEntityComputer) tile;
			}

			if (tileComputer != null)
			{
				ItemStack buySlot = tileComputer.getStackInSlot(0);
				if (buySlot == null || buySlot.stackSize <= 0)
					return null;

				RecipeData recipeData = Recipes.getMineBayItems()[message.itemNum];
				int price = recipeData.getPrice();
				if (buySlot.stackSize < price)
					return null;

				ItemStack currency = recipeData.getCurrency();
				if (currency == null || currency.stackSize <= 0 || !buySlot.isItemEqual(currency))
					return null;

				tileComputer.takeEmeraldFromSlot(price);
				EntityItem entityItem = new EntityItem(player.worldObj, player.posX, player.posY + 1.0D, player.posZ, recipeData.getInput().copy());
				player.worldObj.spawnEntityInWorld(entityItem);
				player.triggerAchievement(FurnitureAchievements.buyItem);
			}
		}
		// TODO gamerforEA code end

		return null;
	}
}
