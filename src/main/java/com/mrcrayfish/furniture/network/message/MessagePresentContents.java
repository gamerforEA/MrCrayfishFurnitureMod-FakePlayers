package com.mrcrayfish.furniture.network.message;

import com.gamerforea.mrcrayfishfurniture.EventConfig;
import com.gamerforea.mrcrayfishfurniture.ModUtils;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.gui.containers.ContainerPresent;
import com.mrcrayfish.furniture.gui.inventory.InventoryPresent;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;

public class MessagePresentContents implements IMessage, IMessageHandler<MessagePresentContents, IMessage>
{
	private ItemStack envelope;

	public MessagePresentContents()
	{
	}

	public MessagePresentContents(ItemStack envelope)
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
	public IMessage onMessage(MessagePresentContents message, MessageContext ctx)
	{
		EntityPlayerMP player = ctx.getServerHandler().playerEntity;

		/* TODO gamerforEA code replace, old code:
		ItemStack presentStack = message.envelope;
		if (presentStack.getItem() == MrCrayfishFurnitureMod.itemPresentRed)
		{
			ItemStack signedPresentInPacket = new ItemStack(MrCrayfishFurnitureMod.itemPresentRed);
			signedPresentInPacket.stackTagCompound = presentStack.stackTagCompound;
			signedPresentInPacket.setTagInfo("Author", new NBTTagString(player.getCommandSenderName()));
			signedPresentInPacket.setStackDisplayName(EnumChatFormatting.RED + "Wrapped Present");
			presentStack.stackTagCompound.getTag("Present");
			player.setCurrentItemOrArmor(0, signedPresentInPacket);
		}
		else
		{
			ItemStack signedPresentInPacket = new ItemStack(MrCrayfishFurnitureMod.itemPresentGreen);
			signedPresentInPacket.stackTagCompound = presentStack.stackTagCompound;
			signedPresentInPacket.setTagInfo("Author", new NBTTagString(player.getCommandSenderName()));
			signedPresentInPacket.setStackDisplayName(EnumChatFormatting.GREEN + "Wrapped Present");
			player.setCurrentItemOrArmor(0, signedPresentInPacket);
		} */
		String playerName = player.getCommandSenderName();
		ItemStack presentStack = message.envelope;
		Item presentItem = presentStack.getItem() == MrCrayfishFurnitureMod.itemPresentRed ? MrCrayfishFurnitureMod.itemPresentRed : MrCrayfishFurnitureMod.itemPresentGreen;

		boolean success = false;

		ItemStack itemInHand = player.getCurrentEquippedItem();
		if (itemInHand != null && itemInHand.getItem() == presentItem && player.openContainer instanceof ContainerPresent)
		{
			IInventory inventory = ((ContainerPresent) player.openContainer).presentInventory;
			if (inventory instanceof InventoryPresent)
			{
				ItemStack signedPresentInPacket = new ItemStack(presentItem);
				signedPresentInPacket.stackTagCompound = presentStack.stackTagCompound;
				String displayName = (presentItem == MrCrayfishFurnitureMod.itemPresentRed ? EnumChatFormatting.RED : EnumChatFormatting.GREEN) + "Wrapped Present";
				signedPresentInPacket.setStackDisplayName(displayName);
				signedPresentInPacket.setTagInfo("Author", new NBTTagString(playerName));

				InventoryPresent inventoryPresent = (InventoryPresent) inventory;
				NBTTagCompound nbt = itemInHand.hasTagCompound() ? (NBTTagCompound) itemInHand.getTagCompound().copy() : new NBTTagCompound();
				inventoryPresent.writeToNBT(nbt);
				nbt.setString("Author", playerName);

				ItemStack signedPresent = new ItemStack(presentItem);
				signedPresent.setTagCompound(nbt);
				signedPresent.setStackDisplayName(displayName);
				if (ItemStack.areItemStacksEqual(signedPresent, signedPresentInPacket))
				{
					success = true;
					player.setCurrentItemOrArmor(0, signedPresent);
				}
			}
		}

		if (!success && EventConfig.debug)
			ModUtils.LOGGER.info("Player {} try to dupe with Present (item) by packet hack", playerName);
		// TODO gamerforEA code end

		return null;
	}
}
