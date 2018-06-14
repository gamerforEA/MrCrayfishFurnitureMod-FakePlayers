package com.mrcrayfish.furniture.network.message;

import com.gamerforea.mrcrayfishfurniture.EventConfig;
import com.gamerforea.mrcrayfishfurniture.ModUtils;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.gui.containers.ContainerEnvelope;
import com.mrcrayfish.furniture.gui.inventory.InventoryEnvelope;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;

public class MessageEnvelope implements IMessage, IMessageHandler<MessageEnvelope, IMessage>
{
	private ItemStack envelope;

	public MessageEnvelope()
	{
	}

	public MessageEnvelope(ItemStack envelope)
	{
		this.envelope = envelope;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.envelope = ByteBufUtils.readItemStack(buf);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeItemStack(buf, this.envelope);
	}

	@Override
	public IMessage onMessage(MessageEnvelope message, MessageContext ctx)
	{
		EntityPlayerMP player = ctx.getServerHandler().playerEntity;
		String playerName = player.getCommandSenderName();

		ItemStack signedMailInPacket = new ItemStack(MrCrayfishFurnitureMod.itemEnvelopeSigned);
		signedMailInPacket.stackTagCompound = message.envelope.stackTagCompound;
		signedMailInPacket.setTagInfo("Author", new NBTTagString(playerName));
		signedMailInPacket.setStackDisplayName("Mail");

		// TODO gamerforEA code replace, old code:
		// player.setCurrentItemOrArmor(0, signedMailInPacket);
		boolean success = false;

		ItemStack itemInHand = player.getCurrentEquippedItem();
		if (itemInHand != null && itemInHand.getItem() == MrCrayfishFurnitureMod.itemEnvelope && player.openContainer instanceof ContainerEnvelope)
		{
			IInventory inventory = ((ContainerEnvelope) player.openContainer).envelopeInventory;
			if (inventory instanceof InventoryEnvelope)
			{
				InventoryEnvelope inventoryEnvelope = (InventoryEnvelope) inventory;
				NBTTagCompound nbt = itemInHand.hasTagCompound() ? (NBTTagCompound) itemInHand.getTagCompound().copy() : new NBTTagCompound();
				inventoryEnvelope.writeToNBT(nbt);
				nbt.setString("Author", playerName);

				ItemStack signedMail = new ItemStack(MrCrayfishFurnitureMod.itemEnvelopeSigned);
				signedMail.setTagCompound(nbt);
				signedMail.setStackDisplayName("Mail");
				if (ItemStack.areItemStacksEqual(signedMail, signedMailInPacket))
				{
					success = true;
					player.setCurrentItemOrArmor(0, signedMail);
				}
			}
		}

		if (!success && EventConfig.debug)
			ModUtils.LOGGER.info("Player {} try to dupe with Envelope by packet hack", playerName);
		// TODO gamerforEA code end

		return null;
	}
}
