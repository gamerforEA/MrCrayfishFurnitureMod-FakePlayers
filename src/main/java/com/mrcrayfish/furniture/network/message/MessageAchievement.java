package com.mrcrayfish.furniture.network.message;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class MessageAchievement implements IMessage, IMessageHandler<MessageAchievement, IMessage>
{
	private String achievementName;

	public MessageAchievement()
	{
	}

	public MessageAchievement(String achievementName)
	{
		this.achievementName = achievementName;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.achievementName = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, this.achievementName);
	}

	@Override
	public IMessage onMessage(MessageAchievement message, MessageContext ctx)
	{
		// TODO gamerforEA code clear:
		// FurnitureAchievements.triggerAchievement(ctx.getServerHandler().playerEntity, message.achievementName);
		return null;
	}
}
