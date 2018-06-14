package com.mrcrayfish.furniture.network.message;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;

public class MessagePresent implements IMessage, IMessageHandler<MessagePresent, IMessage>
{
	private ItemStack present;
	private int x;
	private int y;
	private int z;

	public MessagePresent()
	{
	}

	public MessagePresent(ItemStack present, int x, int y, int z)
	{
		this.present = present;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.present = ByteBufUtils.readItemStack(buf);
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeItemStack(buf, this.present);
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
	}

	@Override
	public IMessage onMessage(MessagePresent message, MessageContext ctx)
	{
		/* TODO gamerforEA clear:
		EntityPlayerMP player = ctx.getServerHandler().playerEntity;
		InventoryPresent presentInventory = new InventoryPresent(player, message.present);
		String author = NBTHelper.getString(message.present, "Author");
		TileEntity tile = player.worldObj.getTileEntity(message.x, message.y, message.z);
		if (tile instanceof TileEntityPresent)
		{
			TileEntityPresent tilePresent = (TileEntityPresent) tile;

			for (int i = 0; i < presentInventory.getSizeInventory(); ++i)
			{
				tilePresent.setContents(i, presentInventory.getStackInSlot(i));
			}

			tilePresent.setOwner(author);
		} */
		return null;
	}
}
