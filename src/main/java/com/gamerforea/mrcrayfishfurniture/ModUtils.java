package com.gamerforea.mrcrayfishfurniture;

import com.gamerforea.eventhelper.util.FastUtils;
import com.mojang.authlib.GameProfile;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public final class ModUtils
{
	public static final Logger LOGGER = LogManager.getLogger("MrCrayfishFurniture");
	public static final GameProfile profile = new GameProfile(UUID.fromString("abcda36e-a03d-4318-8d49-aa8971abab03"), "[MrCrayfishFurniture]");
	private static FakePlayer player = null;

	public static FakePlayer getModFake(World world)
	{
		if (player == null)
			player = FastUtils.getFake(world, profile);
		else
			player.worldObj = world;

		return player;
	}
}
