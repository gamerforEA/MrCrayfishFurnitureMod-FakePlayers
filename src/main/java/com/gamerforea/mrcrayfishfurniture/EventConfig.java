package com.gamerforea.mrcrayfishfurniture;

import com.gamerforea.eventhelper.util.FastUtils;
import net.minecraftforge.common.config.Configuration;

public final class EventConfig
{
	public static boolean debug = true;

	public static void init()
	{
		try
		{
			Configuration cfg = FastUtils.getConfig("MrCrayfishFurniture");
			String c = Configuration.CATEGORY_GENERAL;
			debug = cfg.getBoolean("debug", c, debug, "Вывод отладочной информации в лог");
			cfg.save();
		}
		catch (Throwable throwable)
		{
			System.err.println("Failed load config. Use default values.");
			throwable.printStackTrace();
		}
	}
}
