package com.mrcrayfish.furniture.network.message;

import com.gamerforea.mrcrayfishfurniture.EventConfig;
import com.gamerforea.mrcrayfishfurniture.ModUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class MessageTakeWater implements IMessage, IMessageHandler<MessageTakeWater, IMessage>
{
	private int x;
	private int y;
	private int z;

	public MessageTakeWater()
	{
	}

	public MessageTakeWater(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
	}

	@Override
	public IMessage onMessage(MessageTakeWater message, MessageContext ctx)
	{
		EntityPlayerMP player = ctx.getServerHandler().playerEntity;

		// TODO gamerforEA code replace, old code:
		// player.worldObj.setBlockToAir(message.x, message.y, message.z);
		if (EventConfig.debug)
			ModUtils.LOGGER.info("Player {} try to set AIR at {}:{}:{}:{} by packet hack", player.getCommandSenderName(), message.x, message.y, message.z, player.worldObj.getWorldInfo().getWorldName());
		// TODO gamerforEA code end

		return null;
	}
}
