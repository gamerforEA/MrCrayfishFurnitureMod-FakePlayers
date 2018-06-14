package com.mrcrayfish.furniture.network.message;

import com.gamerforea.mrcrayfishfurniture.EventConfig;
import com.gamerforea.mrcrayfishfurniture.ModUtils;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.gui.containers.ContainerPackage;
import com.mrcrayfish.furniture.gui.inventory.InventoryPackage;
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

public class MessagePackage implements IMessage, IMessageHandler<MessagePackage, IMessage>
{
	private ItemStack package_;

	public MessagePackage()
	{
	}

	public MessagePackage(ItemStack package_)
	{
		this.package_ = package_;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.package_ = ByteBufUtils.readItemStack(buf);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeItemStack(buf, this.package_);
	}

	@Override
	public IMessage onMessage(MessagePackage message, MessageContext ctx)
	{
		EntityPlayerMP player = ctx.getServerHandler().playerEntity;
		String playerName = player.getCommandSenderName();

		ItemStack signedMailInPacket = new ItemStack(MrCrayfishFurnitureMod.itemPackageSigned);
		signedMailInPacket.stackTagCompound = message.package_.stackTagCompound;
		signedMailInPacket.setTagInfo("Author", new NBTTagString(playerName));
		signedMailInPacket.setStackDisplayName("Mail");

		// TODO gamerforEA code replace, old code:
		// player.setCurrentItemOrArmor(0, signedMailInPacket);
		boolean success = false;

		ItemStack itemInHand = player.getCurrentEquippedItem();
		if (itemInHand != null && itemInHand.getItem() == MrCrayfishFurnitureMod.itemPackage && player.openContainer instanceof ContainerPackage)
		{
			IInventory inventory = ((ContainerPackage) player.openContainer).packageInventory;
			if (inventory instanceof InventoryPackage)
			{
				InventoryPackage inventoryPackage = (InventoryPackage) inventory;
				NBTTagCompound nbt = itemInHand.hasTagCompound() ? (NBTTagCompound) itemInHand.getTagCompound().copy() : new NBTTagCompound();
				inventoryPackage.writeToNBT(nbt);
				nbt.setString("Author", playerName);

				ItemStack signedMail = new ItemStack(MrCrayfishFurnitureMod.itemPackageSigned);
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
			ModUtils.LOGGER.info("Player {} try to dupe with Package by packet hack", playerName);
		// TODO gamerforEA code end

		return null;
	}
}
