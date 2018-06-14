package com.mrcrayfish.furniture;

import com.gamerforea.mrcrayfishfurniture.EventConfig;
import com.google.common.collect.UnmodifiableIterator;
import com.mrcrayfish.furniture.api.IRecipeRegistry;
import com.mrcrayfish.furniture.api.RecipeRegistry;
import com.mrcrayfish.furniture.api.RecipeRegistryComm;
import com.mrcrayfish.furniture.api.Recipes;
import com.mrcrayfish.furniture.blocks.*;
import com.mrcrayfish.furniture.entity.EntitySittableBlock;
import com.mrcrayfish.furniture.gui.GuiHandler;
import com.mrcrayfish.furniture.handler.*;
import com.mrcrayfish.furniture.items.*;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import com.mrcrayfish.furniture.tileentity.*;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import java.lang.reflect.Method;

@Mod(modid = "cfm",
	 name = "MrCrayfish\'s Furniture Mod",
	 version = "3.4.8",
	 guiFactory = "com.mrcrayfish.furniture.gui.GuiFactory")
public class MrCrayfishFurnitureMod
{
	@Instance("cfm")
	public static MrCrayfishFurnitureMod instance = new MrCrayfishFurnitureMod();
	@SidedProxy(clientSide = "com.mrcrayfish.furniture.proxy.ClientProxy",
				serverSide = "com.mrcrayfish.furniture.proxy.CommonProxy")
	public static CommonProxy proxy;
	private InputHandler keyHandler;
	private GuiHandler guiHandler = new GuiHandler();
	public static boolean hasDisplayedOnce = false;
	public static Item itemTableWood;
	public static Item itemTableStone;
	public static Item itemChairWood;
	public static Item itemChairStone;
	public static Item itemCabinet;
	public static Item itemBedsideCabinet;
	public static Item itemCoffeeTableWood;
	public static Item itemCoffeeTableStone;
	public static Item itemFridge;
	public static Item itemCoolPack;
	public static Item itemCouch;
	public static Item itemBlinds;
	public static Item itemCurtains;
	public static Item itemOven;
	public static Item itemOvenRangehood;
	public static Item itemFlesh;
	public static Item itemCookedFlesh;
	public static Item itemHedgeBasic;
	public static Item itemHedgeSpruce;
	public static Item itemHedgeBirch;
	public static Item itemHedgeJungle;
	public static Item itemBirdBath;
	public static Item itemStonePath;
	public static Item itemWhiteFence;
	public static Item itemTap;
	public static Item itemMailBox;
	public static Item itemHammer;
	public static Item itemEnvelope;
	public static Item itemEnvelopeSigned;
	public static Item itemPackage;
	public static Item itemPackageSigned;
	public static Item itemTV;
	public static Item itemComputer;
	public static Item itemPrinter;
	public static Item itemInkCartridge;
	public static Item itemStereo;
	public static Item itemElectricFence;
	public static Item itemCeilingLight;
	public static Item itemDoorBell;
	public static Item itemFireAlarm;
	public static Item itemLamp;
	public static Item itemToilet;
	public static Item itemBasin;
	public static Item itemWallCabinet;
	public static Item itemBath;
	public static Item itemShower;
	public static Item itemShowerHead;
	public static Item itemBin;
	public static Item itemToaster;
	public static Item itemMicrowave;
	public static Item itemBlender;
	public static Item itemWashingMachine;
	public static Item itemDishWasher;
	public static Item itemCounterDoored;
	public static Item itemCounterSink;
	public static Item itemKitchenCabinet;
	public static Item itemPlate;
	public static Item itemCookieJar;
	public static Item itemBarStool;
	public static Item itemChoppingBoard;
	public static Item itemKnife;
	public static Item itemCup;
	public static Item itemDrink;
	public static Item itemSoap;
	public static Item itemSoapyWater;
	public static Item itemSuperSoapyWater;
	public static Item itemBreadSlice;
	public static Item itemToast;
	public static Item itemRecipeBook;
	public static Item itemPresentRed;
	public static Item itemPresentGreen;
	public static Item itemTree;
	public static Item itemCrayfish;
	public static Item itemDollar;
	public static Block coffeeTableWood;
	public static Block coffeeTableStone;
	public static Block tableWood;
	public static Block tableStone;
	public static Block chairWood;
	public static Block chairStone;
	public static Block freezer;
	public static Block fridge;
	public static Block cabinet;
	public static Block bedsideCabinet;
	public static Block couch;
	public static Block blinds;
	public static Block blindsClosed;
	public static Block curtains;
	public static Block curtainsClosed;
	public static Block oven;
	public static Block ovenOverhead;
	public static Block hedge;
	public static Block birdBath;
	public static Block stonePath;
	public static Block whiteFence;
	public static Block tap;
	public static Block mailBox;
	public static Block TV;
	public static Block computer;
	public static Block printer;
	public static Block electricFence;
	public static Block doorBell;
	public static Block stereo;
	public static Block fireAlarmOff;
	public static Block fireAlarmOn;
	public static Block ceilingLightOff;
	public static Block ceilingLightOn;
	public static Block lampOn;
	public static Block lampOff;
	public static Block toilet;
	public static Block basin;
	public static Block bath1;
	public static Block bath2;
	public static Block showerBottom;
	public static Block showerTop;
	public static Block showerHeadOff;
	public static Block showerHeadOn;
	public static Block wallCabinet;
	public static Block bin;
	public static Block toaster;
	public static Block microwave;
	public static Block blender;
	public static Block washingMachine;
	public static Block dishWasher;
	public static Block counterDoored;
	public static Block counterSink;
	public static Block kitchenCabinet;
	public static Block cup;
	public static Block plate;
	public static Block cookieJar;
	public static Block barStool;
	public static Block choppingBoard;
	public static Block present;
	public static Block tree;
	public static Block string;
	public static Block hey;
	public static Block nyan;
	public static Block pattern;
	public static Block yellowGlow;
	public static Block whiteGlass;
	public static CreativeTabs tabFurniture = new FurnitureTab(CreativeTabs.getNextID(), "tabFurniture");

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		// TODO gamerforEA code start
		EventConfig.init();
		// TODO gamerforEA code end

		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		lampOn = new BlockLampOn(Material.glass).setBlockName("lampon").setLightLevel(1.0F).setHardness(0.75F).setStepSound(Block.soundTypeCloth);
		lampOff = new BlockLampOff(Material.glass).setBlockName("lampoff").setHardness(0.75F).setStepSound(Block.soundTypeCloth);
		coffeeTableWood = new BlockCoffeeTable(Material.wood).setBlockName("coffetablewood").setHardness(1.0F).setStepSound(Block.soundTypeWood);
		coffeeTableStone = new BlockCoffeeTable(Material.rock).setBlockName("coffetablestone").setHardness(1.5F).setStepSound(Block.soundTypeStone);
		tableWood = new BlockTable(Material.wood).setBlockName("tablewood").setHardness(1.0F).setStepSound(Block.soundTypeWood);
		tableStone = new BlockTable(Material.rock).setBlockName("tablestone").setHardness(1.5F).setStepSound(Block.soundTypeStone);
		chairWood = new BlockChair(Material.wood).setBlockName("chairwood").setHardness(1.0F).setStepSound(Block.soundTypeWood);
		chairStone = new BlockChair(Material.rock).setBlockName("chairstone").setHardness(1.5F).setStepSound(Block.soundTypeStone);
		freezer = new BlockFreezer().setBlockName("freezer").setHardness(2.0F).setStepSound(Block.soundTypeMetal);
		fridge = new BlockFridge().setBlockName("fridge").setHardness(2.0F).setStepSound(Block.soundTypeStone);
		cabinet = new BlockCabinet(Material.wood).setBlockName("cabinet").setHardness(1.0F).setStepSound(Block.soundTypeWood);
		couch = new BlockCouch(Material.cloth).setBlockName("couch").setHardness(0.5F).setStepSound(Block.soundTypeCloth);
		blinds = new BlockWindowDecoration(Material.wood).setBlockName("blindon").setHardness(0.5F).setStepSound(Block.soundTypeWood);
		blindsClosed = new BlockWindowDecorationClosed(Material.glass).setBlockName("blindoff").setHardness(0.5F).setStepSound(Block.soundTypeWood);
		curtains = new BlockWindowDecoration(Material.cloth).setBlockName("curtainon").setHardness(0.5F).setStepSound(Block.soundTypeCloth);
		curtainsClosed = new BlockWindowDecorationClosed(Material.cloth).setBlockName("curtainoff").setHardness(0.5F).setStepSound(Block.soundTypeCloth);
		bedsideCabinet = new BlockBedsideCabinet(Material.wood).setBlockName("bedsidecabinet").setHardness(1.0F).setStepSound(Block.soundTypeWood);
		oven = new BlockOven().setBlockName("oven").setHardness(1.0F).setStepSound(Block.soundTypeMetal);
		ovenOverhead = new BlockOvenOverhead(Material.rock).setBlockName("ovenoverhead").setHardness(0.5F).setStepSound(Block.soundTypeMetal).setLightLevel(0.5F);
		hedge = new BlockHedge().setBlockName("hedge").setHardness(0.2F).setStepSound(Block.soundTypeGrass);
		birdBath = new BlockBirdBath(Material.rock).setBlockName("birdbath").setHardness(1.0F).setStepSound(Block.soundTypeStone);
		stonePath = new BlockStonePath(Material.rock).setBlockName("stonepath").setHardness(0.75F).setStepSound(Block.soundTypeStone);
		whiteFence = new BlockWhiteFence(Material.wood).setBlockName("whitefence").setHardness(1.0F).setStepSound(Block.soundTypeWood);
		tap = new BlockTap(Material.rock).setBlockName("tap").setHardness(0.5F).setStepSound(Block.soundTypeStone);
		mailBox = new BlockMailBox(Material.wood).setBlockName("mailbox").setStepSound(Block.soundTypeWood);
		TV = new BlockTV(Material.wood).setBlockName("tv").setStepSound(Block.soundTypeWood);
		computer = new BlockComputer(Material.iron).setHardness(1.0F).setBlockName("computer").setStepSound(Block.soundTypeAnvil);
		printer = new BlockPrinter(Material.iron).setHardness(1.0F).setBlockName("printer").setStepSound(Block.soundTypeAnvil);
		electricFence = new BlockElectricFence().setHardness(1.0F).setBlockName("electricfence").setStepSound(Block.soundTypeAnvil);
		doorBell = new BlockDoorBell(Material.wood).setBlockName("doorbell").setStepSound(Block.soundTypeWood);
		fireAlarmOff = new BlockFireAlarm(Material.rock, false).setHardness(0.5F).setBlockName("firealarmoff").setStepSound(Block.soundTypeStone);
		fireAlarmOn = new BlockFireAlarm(Material.rock, true).setHardness(0.5F).setBlockName("firealarmon").setStepSound(Block.soundTypeStone);
		ceilingLightOff = new BlockCeilingLight(Material.glass, false).setHardness(0.5F).setBlockName("ceilinglightoff").setStepSound(Block.soundTypeGlass);
		ceilingLightOn = new BlockCeilingLight(Material.glass, true).setHardness(0.5F).setBlockName("ceilinglighton").setStepSound(Block.soundTypeGlass);
		stereo = new BlockStereo(Material.wood).setBlockName("stereo").setHardness(1.0F).setStepSound(Block.soundTypeWood);
		toilet = new BlockToilet(Material.rock).setHardness(1.0F).setBlockName("toilet").setStepSound(Block.soundTypeStone);
		basin = new BlockBasin(Material.rock).setHardness(1.0F).setBlockName("basin").setStepSound(Block.soundTypeStone);
		wallCabinet = new BlockWallCabinet(Material.rock).setHardness(1.0F).setBlockName("wallcabinet").setStepSound(Block.soundTypeStone);
		bath1 = new BlockBath(Material.rock).setHardness(1.0F).setBlockName("bath1").setStepSound(Block.soundTypeStone);
		bath2 = new BlockBath(Material.rock).setHardness(1.0F).setBlockName("bath2").setStepSound(Block.soundTypeStone);
		showerBottom = new BlockShower(Material.rock).setHardness(1.0F).setBlockName("showerbottom").setStepSound(Block.soundTypeStone);
		showerTop = new BlockShower(Material.rock).setHardness(1.0F).setBlockName("showertop").setStepSound(Block.soundTypeStone);
		showerHeadOff = new BlockShowerHead(Material.rock).setHardness(1.0F).setBlockName("showerheadoff").setStepSound(Block.soundTypeStone);
		showerHeadOn = new BlockShowerHead(Material.rock).setHardness(1.0F).setBlockName("showerheadon").setStepSound(Block.soundTypeStone);
		bin = new BlockBin(Material.rock).setBlockName("bin").setStepSound(Block.soundTypeAnvil).setHardness(0.5F);
		tree = new BlockTree(Material.wood).setBlockName("tree").setStepSound(Block.soundTypeWood).setLightLevel(0.3F).setHardness(0.5F);
		present = new BlockPresent(Material.cloth).setBlockName("present").setStepSound(Block.soundTypeCloth).setHardness(0.5F);
		toaster = new BlockToaster(Material.wood).setBlockName("toaster").setStepSound(Block.soundTypeAnvil).setHardness(0.5F);
		microwave = new BlockMicrowave(Material.wood).setBlockName("microwave").setStepSound(Block.soundTypeAnvil).setHardness(0.5F);
		washingMachine = new BlockWashingMachine(Material.rock).setBlockName("washingmachine").setStepSound(Block.soundTypeAnvil).setHardness(0.5F);
		cookieJar = new BlockCookieJar(Material.glass).setBlockName("cookiejar").setStepSound(Block.soundTypeGlass).setHardness(0.5F);
		blender = new BlockBlender(Material.glass).setBlockName("blender").setStepSound(Block.soundTypeGlass).setHardness(0.5F);
		cup = new BlockCup(Material.glass).setBlockName("cup").setStepSound(Block.soundTypeGlass).setHardness(0.1F);
		plate = new BlockPlate(Material.glass).setBlockName("plate").setStepSound(Block.soundTypeGlass).setHardness(0.5F);
		counterDoored = new BlockCounter(Material.wood).setBlockName("counterdoored").setStepSound(Block.soundTypeWood).setHardness(0.5F);
		counterSink = new BlockCounterSink(Material.wood).setBlockName("countersink").setStepSound(Block.soundTypeWood).setHardness(0.5F);
		dishWasher = new BlockDishwasher(Material.rock).setBlockName("dishwasher").setStepSound(Block.soundTypeAnvil).setHardness(0.5F);
		kitchenCabinet = new BlockCabinetKitchen(Material.wood).setBlockName("kitchencabinet").setStepSound(Block.soundTypeWood).setHardness(0.5F);
		choppingBoard = new BlockChoppingBoard(Material.wood).setBlockName("choppingboard").setStepSound(Block.soundTypeWood).setHardness(0.5F);
		barStool = new BlockBarStool(Material.wood).setBlockName("barstool").setStepSound(Block.soundTypeWood).setHardness(0.5F);
		hey = new BlockTVAnimation().setBlockName("hey").setBlockTextureName("cfm:hey");
		nyan = new BlockTVAnimation().setBlockName("nyan").setBlockTextureName("cfm:nyan");
		pattern = new BlockTVAnimation().setBlockName("pattern").setBlockTextureName("cfm:pattern");
		yellowGlow = new BlockTVAnimation().setBlockName("yellowGlow").setBlockTextureName("yellowGlow").setBlockTextureName("cfm:yellowglow");
		whiteGlass = new BlockTVAnimation().setBlockName("whiteGlass").setBlockTextureName("cfm:whiteglass");
		itemTableWood = new ItemBlockCustom(tableWood, 1).setUnlocalizedName("itemtablewood").setTextureName("cfm:itemtablewood").setCreativeTab(tabFurniture);
		itemTableStone = new ItemBlockCustom(tableStone, 2).setUnlocalizedName("itemtablestone").setTextureName("cfm:itemtablestone").setCreativeTab(tabFurniture);
		itemChairWood = new ItemFurniturePlacer(chairWood).setUnlocalizedName("itemchairwood").setTextureName("cfm:itemchairwood").setCreativeTab(tabFurniture);
		itemChairStone = new ItemFurniturePlacer(chairStone).setUnlocalizedName("itemchairstone").setTextureName("cfm:itemchairstone").setCreativeTab(tabFurniture);
		itemCabinet = new ItemFurniturePlacer(cabinet).setUnlocalizedName("itemcabinet").setTextureName("cfm:itemcabinet").setCreativeTab(tabFurniture);
		itemCoffeeTableWood = new ItemBlockCustom(coffeeTableWood, 1).setUnlocalizedName("itemcoffeetablewood").setTextureName("cfm:itemcoffeetablewood").setCreativeTab(tabFurniture);
		itemCoffeeTableStone = new ItemBlockCustom(coffeeTableStone, 2).setUnlocalizedName("itemcoffeetablestone").setTextureName("cfm:itemcoffeetablestone").setCreativeTab(tabFurniture);
		itemFridge = new ItemFridge().setUnlocalizedName("itemfridge").setTextureName("cfm:itemfridge").setCreativeTab(tabFurniture);
		itemCouch = new ItemFurniturePlacer(couch).setUnlocalizedName("itemcouch").setTextureName("cfm:itemcouchwhite").setCreativeTab(tabFurniture);
		itemBlinds = new ItemFurniturePlacer(blinds).setUnlocalizedName("itemblinds").setTextureName("cfm:itemblinds").setCreativeTab(tabFurniture);
		itemCurtains = new ItemFurniturePlacer(curtains).setUnlocalizedName("itemcurtains").setTextureName("cfm:itemcurtains").setCreativeTab(tabFurniture);
		itemLamp = new ItemBlockCustom(lampOff, 0).setUnlocalizedName("itemlamp").setTextureName("cfm:itemlamp").setCreativeTab(tabFurniture);
		itemBedsideCabinet = new ItemFurniturePlacer(bedsideCabinet).setUnlocalizedName("itembedsidecabinet").setTextureName("cfm:itembedsidecabinet").setCreativeTab(tabFurniture);
		itemCoolPack = new ItemFurniture("itemcoolpack").setUnlocalizedName("itemcoolpack").setTextureName("cfm:itemcoolpack").setCreativeTab(tabFurniture);
		itemOven = new ItemFurniturePlacer(oven).setUnlocalizedName("itemoven").setTextureName("cfm:itemoven").setCreativeTab(tabFurniture);
		itemOvenRangehood = new ItemFurniturePlacer(ovenOverhead).setUnlocalizedName("itemovenoverhead").setTextureName("cfm:itemovenoverhead").setCreativeTab(tabFurniture);
		itemFlesh = new ItemFurnitureFood(1, 2, false).setUnlocalizedName("itemflesh").setTextureName("cfm:itemflesh").setCreativeTab(tabFurniture);
		itemCookedFlesh = new ItemFurnitureFood(4, 4, false).setUnlocalizedName("itemfleshcooked").setTextureName("cfm:itemfleshcooked").setCreativeTab(tabFurniture);
		itemHedgeBasic = new ItemBlockCustom(hedge, 0).setUnlocalizedName("itemhedgebasic").setTextureName("cfm:itemhedgebasic").setCreativeTab(tabFurniture);
		itemHedgeSpruce = new ItemBlockCustom(hedge, 1).setUnlocalizedName("itemhedgespruce").setTextureName("cfm:itemhedgespruce").setCreativeTab(tabFurniture);
		itemHedgeBirch = new ItemBlockCustom(hedge, 2).setUnlocalizedName("itemhedgebirch").setTextureName("cfm:itemhedgebirch").setCreativeTab(tabFurniture);
		itemHedgeJungle = new ItemBlockCustom(hedge, 3).setUnlocalizedName("itemhedgejungle").setTextureName("cfm:itemhedgejungle").setCreativeTab(tabFurniture);
		itemBirdBath = new ItemBlockCustom(birdBath, 0).setUnlocalizedName("itembirdbath").setTextureName("cfm:itembirdbath").setCreativeTab(tabFurniture);
		itemStonePath = new ItemBlockCustom(stonePath, 2).setUnlocalizedName("itemstonepath").setTextureName("cfm:itemstonepath").setCreativeTab(tabFurniture);
		itemWhiteFence = new ItemBlockCustom(whiteFence, 0).setUnlocalizedName("itemwhitefence").setTextureName("cfm:itemwhitefence").setCreativeTab(tabFurniture);
		itemTap = new ItemFurniturePlacer(tap).setUnlocalizedName("itemtap").setTextureName("cfm:itemtap").setCreativeTab(tabFurniture);
		itemMailBox = new ItemFurniturePlacer(mailBox).setUnlocalizedName("itemmailbox").setTextureName("cfm:itemmailbox").setCreativeTab(tabFurniture);
		itemEnvelope = new ItemEnvelope().setUnlocalizedName("itemenvelope").setTextureName("cfm:itemenvelope").setCreativeTab(tabFurniture).setMaxStackSize(1);
		itemEnvelopeSigned = new ItemEnvelopeSigned().setUnlocalizedName("itemenvelopesigned").setTextureName("cfm:itemenvelope").setMaxStackSize(1);
		itemPackage = new ItemPackage().setUnlocalizedName("itempackage").setTextureName("cfm:itempackage").setCreativeTab(tabFurniture).setMaxStackSize(1);
		itemPackageSigned = new ItemPackageSigned().setUnlocalizedName("itempackagesigned").setTextureName("cfm:itempackage").setMaxStackSize(1);
		itemHammer = new ItemHammer().setUnlocalizedName("itemhammer").setTextureName("cfm:itemhammer").setCreativeTab(tabFurniture);
		itemTV = new ItemFurniturePlacer(TV).setUnlocalizedName("itemtv").setTextureName("cfm:itemtv").setCreativeTab(tabFurniture);
		itemComputer = new ItemFurniturePlacer(computer).setUnlocalizedName("itemcomputer").setTextureName("cfm:itemcomputer").setCreativeTab(tabFurniture);
		itemPrinter = new ItemFurniturePlacer(printer).setUnlocalizedName("itemprinter").setTextureName("cfm:itemprinter").setCreativeTab(tabFurniture);
		itemInkCartridge = new ItemFurniture("iteminkcartridge").setUnlocalizedName("iteminkcartridge").setTextureName("cfm:iteminkcartridge").setCreativeTab(tabFurniture);
		itemElectricFence = new ItemBlockCustom(electricFence, 0).setUnlocalizedName("itemelectricfence").setTextureName("cfm:itemelectricfence").setCreativeTab(tabFurniture);
		itemFireAlarm = new ItemFireAlarm(fireAlarmOff).setUnlocalizedName("itemfirealarm").setTextureName("cfm:itemfirealarm").setCreativeTab(tabFurniture);
		itemCeilingLight = new ItemFireAlarm(ceilingLightOff).setUnlocalizedName("itemceilinglight").setTextureName("cfm:itemceilinglight").setCreativeTab(tabFurniture);
		itemDoorBell = new ItemFurniturePlacer(doorBell).setUnlocalizedName("itemdoorbell").setTextureName("cfm:itemdoorbell").setCreativeTab(tabFurniture);
		itemStereo = new ItemFurniturePlacer(stereo).setUnlocalizedName("itemstereo").setTextureName("cfm:itemstereo").setCreativeTab(tabFurniture);
		itemToilet = new ItemFurniturePlacer(toilet).setUnlocalizedName("itemtoilet").setTextureName("cfm:itemtoilet").setCreativeTab(tabFurniture);
		itemBasin = new ItemFurniturePlacer(basin).setUnlocalizedName("itembasin").setTextureName("cfm:itembasin").setCreativeTab(tabFurniture);
		itemWallCabinet = new ItemFurniturePlacer(wallCabinet).setUnlocalizedName("itemwallcabinet").setTextureName("cfm:itemwallcabinet").setCreativeTab(tabFurniture);
		itemBath = new ItemBath().setUnlocalizedName("itembath").setTextureName("cfm:itembath").setCreativeTab(tabFurniture);
		itemShower = new ItemFurniturePlacer(showerBottom).setUnlocalizedName("itemshower").setTextureName("cfm:itemshower").setCreativeTab(tabFurniture);
		itemShowerHead = new ItemFurniturePlacer(showerHeadOff).setUnlocalizedName("itemshowerhead").setTextureName("cfm:itemshowerhead").setCreativeTab(tabFurniture);
		itemBin = new ItemFurniturePlacer(bin).setUnlocalizedName("itembin").setTextureName("cfm:itembin").setCreativeTab(tabFurniture);
		itemPresentRed = new ItemPresent().setUnlocalizedName("itempresentred").setTextureName("cfm:itempresentred").setCreativeTab(tabFurniture).setMaxStackSize(1);
		itemPresentGreen = new ItemPresent().setUnlocalizedName("itempresentgreen").setTextureName("cfm:itempresentgreen").setCreativeTab(tabFurniture).setMaxStackSize(1);
		itemTree = new ItemFurniturePlacer(tree).setUnlocalizedName("itemtree").setTextureName("cfm:itemtree").setCreativeTab(tabFurniture);
		itemToaster = new ItemFurniturePlacer(toaster).setUnlocalizedName("itemtoaster").setTextureName("cfm:itemtoaster").setCreativeTab(tabFurniture);
		itemMicrowave = new ItemFurniturePlacer(microwave).setUnlocalizedName("itemmicrowave").setTextureName("cfm:itemmicrowave").setCreativeTab(tabFurniture);
		itemWashingMachine = new ItemFurniturePlacer(washingMachine).setUnlocalizedName("itemwashingmachine").setTextureName("cfm:itemwashingmachine").setCreativeTab(tabFurniture);
		itemCookieJar = new ItemBlockCustom(cookieJar, 0).setUnlocalizedName("itemcookiejar").setTextureName("cfm:itemcookiejar").setCreativeTab(tabFurniture);
		itemBlender = new ItemFurniturePlacer(blender).setUnlocalizedName("itemblender").setTextureName("cfm:itemblender").setCreativeTab(tabFurniture);
		itemPlate = new ItemFurniturePlacer(plate).setUnlocalizedName("itemplate").setTextureName("cfm:itemplate").setCreativeTab(tabFurniture);
		itemCounterDoored = new ItemFurniturePlacer(counterDoored).setUnlocalizedName("itemkitchencounter").setTextureName("cfm:itemkitchencounter").setCreativeTab(tabFurniture);
		itemCounterSink = new ItemFurniturePlacer(counterSink).setUnlocalizedName("itemkitchencountersink").setTextureName("cfm:itemkitchencountersink").setCreativeTab(tabFurniture);
		itemDishWasher = new ItemFurniturePlacer(dishWasher).setUnlocalizedName("itemdishwasher").setTextureName("cfm:itemdishwasher").setCreativeTab(tabFurniture);
		itemKitchenCabinet = new ItemFurniturePlacer(kitchenCabinet).setUnlocalizedName("itemkitchencabinet").setTextureName("cfm:itemkitchencabinet").setCreativeTab(tabFurniture);
		itemChoppingBoard = new ItemFurniturePlacer(choppingBoard).setUnlocalizedName("itemchoppingboard").setTextureName("cfm:itemchoppingboard").setCreativeTab(tabFurniture);
		itemBarStool = new ItemBlockCustom(barStool, 15).setUnlocalizedName("itembarstool").setTextureName("cfm:itembarstool").setCreativeTab(tabFurniture);
		itemBreadSlice = new ItemFood(2, false).setUnlocalizedName("itembreadslice").setTextureName("cfm:itembreadslice").setCreativeTab(tabFurniture);
		itemToast = new ItemFood(4, false).setUnlocalizedName("itemtoast").setTextureName("cfm:itemtoast").setCreativeTab(tabFurniture);
		itemKnife = new ItemSword(ToolMaterial.STONE).setUnlocalizedName("itemknife").setTextureName("cfm:itemknife").setMaxStackSize(1).setCreativeTab(tabFurniture);
		itemCup = new ItemCup(false).setUnlocalizedName("itemcup").setCreativeTab(tabFurniture);
		itemDrink = new ItemCup(true).setUnlocalizedName("itemdrink");
		itemSoap = new Item().setUnlocalizedName("itemsoap").setTextureName("cfm:itemsoap").setCreativeTab(tabFurniture);
		itemSoapyWater = new Item().setUnlocalizedName("itemsoapwater").setTextureName("cfm:itemsoapwater").setMaxStackSize(1).setContainerItem(Items.bucket).setCreativeTab(tabFurniture);
		itemSuperSoapyWater = new Item().setUnlocalizedName("itemsupersoapwater").setTextureName("cfm:itemsupersoapwater").setMaxStackSize(1).setContainerItem(Items.bucket).setCreativeTab(tabFurniture);
		itemRecipeBook = new ItemRecipeBook().setUnlocalizedName("itemrecipebook").setTextureName("cfm:itemrecipebook").setMaxStackSize(1).setCreativeTab(tabFurniture);
		itemCrayfish = new Item().setUnlocalizedName("itemcrayfish").setMaxStackSize(1).setTextureName("cfm:crayfish");
		itemDollar = new Item().setUnlocalizedName("itemmoney").setMaxStackSize(1).setTextureName("cfm:money");
		GameRegistry.registerBlock(lampOn, lampOn.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(lampOff, lampOff.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(coffeeTableWood, coffeeTableWood.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(coffeeTableStone, coffeeTableStone.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(tableWood, tableWood.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(tableStone, tableStone.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(chairWood, chairWood.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(chairStone, chairStone.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(freezer, freezer.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(fridge, fridge.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(cabinet, cabinet.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(couch, couch.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(blinds, blinds.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(blindsClosed, blindsClosed.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(curtains, curtains.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(curtainsClosed, curtainsClosed.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(bedsideCabinet, bedsideCabinet.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(oven, oven.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(ovenOverhead, ovenOverhead.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(hedge, hedge.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(birdBath, birdBath.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(stonePath, stonePath.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(whiteFence, whiteFence.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(tap, tap.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(mailBox, mailBox.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(TV, TV.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(computer, computer.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(printer, printer.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(electricFence, electricFence.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(doorBell, doorBell.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(fireAlarmOff, fireAlarmOff.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(fireAlarmOn, fireAlarmOn.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(ceilingLightOff, ceilingLightOff.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(ceilingLightOn, ceilingLightOn.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(stereo, stereo.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(toilet, toilet.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(basin, basin.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(wallCabinet, wallCabinet.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(bath1, bath1.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(bath2, bath2.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(showerBottom, showerBottom.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(showerTop, showerTop.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(showerHeadOff, showerHeadOff.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(showerHeadOn, showerHeadOn.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(bin, bin.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(present, present.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(tree, tree.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(toaster, toaster.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(microwave, microwave.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(washingMachine, washingMachine.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(cookieJar, cookieJar.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(blender, blender.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(plate, plate.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(cup, cup.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(counterDoored, counterDoored.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(counterSink, counterSink.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(dishWasher, dishWasher.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(kitchenCabinet, kitchenCabinet.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(choppingBoard, choppingBoard.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(barStool, barStool.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(hey, hey.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(nyan, nyan.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(pattern, pattern.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(yellowGlow, yellowGlow.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(whiteGlass, whiteGlass.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(itemTableWood, "ItemTableWood");
		GameRegistry.registerItem(itemTableStone, "ItemTableStone");
		GameRegistry.registerItem(itemChairWood, "ItemChairWood");
		GameRegistry.registerItem(itemChairStone, "ItemChairStone");
		GameRegistry.registerItem(itemCabinet, "ItemCabinet");
		GameRegistry.registerItem(itemBedsideCabinet, "ItemBesideCabinet");
		GameRegistry.registerItem(itemCoffeeTableWood, "ItemCoffeeTableWood");
		GameRegistry.registerItem(itemCoffeeTableStone, "ItemCoffeeTableStone");
		GameRegistry.registerItem(itemFridge, "ItemFridge");
		GameRegistry.registerItem(itemCoolPack, "ItemCoolPack");
		GameRegistry.registerItem(itemCouch, "ItemCouch");
		GameRegistry.registerItem(itemBlinds, "ItemBlinds");
		GameRegistry.registerItem(itemCurtains, "ItemCurtains");
		GameRegistry.registerItem(itemOven, "ItemOven");
		GameRegistry.registerItem(itemOvenRangehood, "ItemOvenRangehood");
		GameRegistry.registerItem(itemFlesh, "ItemFlesh");
		GameRegistry.registerItem(itemCookedFlesh, "ItemCookedFlesh");
		GameRegistry.registerItem(itemHedgeBasic, "ItemHedgeBasic");
		GameRegistry.registerItem(itemHedgeSpruce, "ItemHedgeSpruce");
		GameRegistry.registerItem(itemHedgeBirch, "ItemHedgeBirch");
		GameRegistry.registerItem(itemHedgeJungle, "ItemHedgeJungle");
		GameRegistry.registerItem(itemBirdBath, "ItemBirdBath");
		GameRegistry.registerItem(itemStonePath, "ItemStonePath");
		GameRegistry.registerItem(itemWhiteFence, "ItemWhiteFence");
		GameRegistry.registerItem(itemTap, "ItemTap");
		GameRegistry.registerItem(itemMailBox, "ItemMailBox");
		GameRegistry.registerItem(itemHammer, "ItemHammer");
		GameRegistry.registerItem(itemEnvelope, "ItemEnvelope");
		GameRegistry.registerItem(itemEnvelopeSigned, "ItemEnvelopeSigned");
		GameRegistry.registerItem(itemPackage, "ItemPackage");
		GameRegistry.registerItem(itemPackageSigned, "ItemPackageSigned");
		GameRegistry.registerItem(itemTV, "ItemTV");
		GameRegistry.registerItem(itemComputer, "ItemComputer");
		GameRegistry.registerItem(itemPrinter, "ItemPrinter");
		GameRegistry.registerItem(itemInkCartridge, "ItemInkCartridge");
		GameRegistry.registerItem(itemStereo, "ItemStereo");
		GameRegistry.registerItem(itemElectricFence, "ItemElectricFence");
		GameRegistry.registerItem(itemCeilingLight, "ItemCeilingLight");
		GameRegistry.registerItem(itemDoorBell, "ItemDoorBell");
		GameRegistry.registerItem(itemFireAlarm, "ItemFireAlarm");
		GameRegistry.registerItem(itemLamp, "ItemLamp");
		GameRegistry.registerItem(itemToilet, "ItemToilet");
		GameRegistry.registerItem(itemBasin, "ItemBasin");
		GameRegistry.registerItem(itemWallCabinet, "ItemWallCabinet");
		GameRegistry.registerItem(itemBath, "ItemBath");
		GameRegistry.registerItem(itemShower, "ItemShower");
		GameRegistry.registerItem(itemShowerHead, "ItemShowerHead");
		GameRegistry.registerItem(itemBin, "ItemBin");
		GameRegistry.registerItem(itemPresentRed, "ItemPresentRed");
		GameRegistry.registerItem(itemPresentGreen, "ItemPresentGreen");
		GameRegistry.registerItem(itemTree, "itemTree");
		GameRegistry.registerItem(itemToaster, "ItemToaster");
		GameRegistry.registerItem(itemMicrowave, "ItemMicrowave");
		GameRegistry.registerItem(itemWashingMachine, "ItemWashingMachine");
		GameRegistry.registerItem(itemCookieJar, "ItemCookieJar");
		GameRegistry.registerItem(itemBlender, "ItemBlender");
		GameRegistry.registerItem(itemPlate, "ItemPlate");
		GameRegistry.registerItem(itemCounterDoored, "ItemCounterDoored");
		GameRegistry.registerItem(itemCounterSink, "ItemCounterSink");
		GameRegistry.registerItem(itemDishWasher, "ItemDishWasher");
		GameRegistry.registerItem(itemKitchenCabinet, "ItemKitchenCabinet");
		GameRegistry.registerItem(itemChoppingBoard, "ItemChoppingBoard");
		GameRegistry.registerItem(itemBarStool, "ItemBarStall");
		GameRegistry.registerItem(itemBreadSlice, "ItemBreadSlice");
		GameRegistry.registerItem(itemToast, "ItemToast");
		GameRegistry.registerItem(itemKnife, "ItemKnife");
		GameRegistry.registerItem(itemCup, "ItemCup");
		GameRegistry.registerItem(itemDrink, "ItemDrink");
		GameRegistry.registerItem(itemSoap, "ItemSoap");
		GameRegistry.registerItem(itemSoapyWater, "ItemSoapyWater");
		GameRegistry.registerItem(itemSuperSoapyWater, "ItemSuperSoapyWater");
		GameRegistry.registerItem(itemRecipeBook, "ItemRecipeBook");
		GameRegistry.registerItem(itemCrayfish, "ItemCrayfish");
		GameRegistry.registerItem(itemDollar, "ItemDollar");
		FurnitureAchievements.loadAchievements();
		FurnitureAchievements.registerPage();
		PacketHandler.init();
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		proxy.registerRenders();
		GameRegistry.registerTileEntity(TileEntityOven.class, "cfmOven");
		GameRegistry.registerTileEntity(TileEntityFridge.class, "cfmFridge");
		GameRegistry.registerTileEntity(TileEntityCabinet.class, "cfmCabinet");
		GameRegistry.registerTileEntity(TileEntityFreezer.class, "cfmFreezer");
		GameRegistry.registerTileEntity(TileEntityBedsideCabinet.class, "cfmBedsideCabinet");
		GameRegistry.registerTileEntity(TileEntityMailBox.class, "cfmMailBox");
		GameRegistry.registerTileEntity(TileEntityComputer.class, "cfmComputer");
		GameRegistry.registerTileEntity(TileEntityPrinter.class, "cfmPrinter");
		GameRegistry.registerTileEntity(TileEntityTV.class, "cfmTV");
		GameRegistry.registerTileEntity(TileEntityStereo.class, "cfmStereo");
		GameRegistry.registerTileEntity(TileEntityPresent.class, "cfmPresent");
		GameRegistry.registerTileEntity(TileEntityBin.class, "cfmBin");
		GameRegistry.registerTileEntity(TileEntityWallCabinet.class, "cfmWallCabinet");
		GameRegistry.registerTileEntity(TileEntityBath.class, "cfmBath");
		GameRegistry.registerTileEntity(TileEntityBasin.class, "cfmBasin");
		GameRegistry.registerTileEntity(TileEntityShowerHead.class, "cfmShowerHead");
		GameRegistry.registerTileEntity(TileEntityCookieJar.class, "cfmCookieJar");
		GameRegistry.registerTileEntity(TileEntityPlate.class, "cfmPlate");
		GameRegistry.registerTileEntity(TileEntityCouch.class, "cfmCouch");
		GameRegistry.registerTileEntity(TileEntityToaster.class, "cfmToaster");
		GameRegistry.registerTileEntity(TileEntityChoppingBoard.class, "cfmChoppingBoard");
		GameRegistry.registerTileEntity(TileEntityBlender.class, "cfmBlender");
		GameRegistry.registerTileEntity(TileEntityMicrowave.class, "cfmMicrowave");
		GameRegistry.registerTileEntity(TileEntityWashingMachine.class, "cfmWashingMachine");
		GameRegistry.registerTileEntity(TileEntityDishwasher.class, "cfmDishwasher");
		GameRegistry.registerTileEntity(TileEntityCabinetKitchen.class, "cfmCabinetKitchen");
		GameRegistry.registerTileEntity(TileEntityCounterSink.class, "cfmCounterSink");
		GameRegistry.registerTileEntity(TileEntityCup.class, "cfmCup");
		EntityRegistry.registerModEntity(EntitySittableBlock.class, "MountableBlock", 0, this, 80, 1, false);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, this.guiHandler);
		GameRegistry.addRecipe(new ItemStack(itemTableWood, 1), "***", " * ", " * ", '*', Blocks.planks);
		GameRegistry.addRecipe(new ItemStack(itemTableStone, 1), "***", " * ", " * ", '*', Blocks.cobblestone);
		GameRegistry.addRecipe(new ItemStack(itemChairWood, 1), "*  ", "***", "* *", '*', Blocks.planks);
		GameRegistry.addRecipe(new ItemStack(itemChairStone, 1), "*  ", "***", "* *", '*', Blocks.cobblestone);
		GameRegistry.addRecipe(new ItemStack(itemCouch, 1), "***", "***", '*', new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addRecipe(new ItemStack(itemFridge, 1), "***", "*#*", "*@*", '*', Blocks.iron_block, '#', Blocks.chest, '@', Blocks.furnace);
		GameRegistry.addRecipe(new ItemStack(itemCabinet, 1), "***", "*@*", "***", '*', Blocks.planks, '@', Blocks.chest);
		GameRegistry.addRecipe(new ItemStack(itemCurtains, 2), "@@@", "* *", "@ @", '*', Items.gold_ingot, '@', new ItemStack(Blocks.wool, 1, 14));
		GameRegistry.addRecipe(new ItemStack(itemBlinds, 2), "***", "***", "***", '*', Items.stick);
		GameRegistry.addRecipe(new ItemStack(itemCoolPack, 2), "***", "*@*", "***", '*', Blocks.glass, '@', Items.water_bucket);
		GameRegistry.addRecipe(new ItemStack(itemCoffeeTableWood, 1), "***", "* *", '*', Blocks.planks);
		GameRegistry.addRecipe(new ItemStack(itemCoffeeTableStone, 1), "***", "* *", '*', Blocks.cobblestone);
		GameRegistry.addRecipe(new ItemStack(itemLamp, 2), "***", "*@*", " & ", '*', Blocks.wool, '@', Blocks.glowstone, '&', Blocks.obsidian);
		GameRegistry.addRecipe(new ItemStack(itemBedsideCabinet, 1), "***", "*@*", "*@*", '*', Blocks.planks, '@', Blocks.chest);
		GameRegistry.addRecipe(new ItemStack(itemOven, 1), "***", "*@*", "***", '*', Blocks.iron_block, '@', Blocks.furnace);
		GameRegistry.addRecipe(new ItemStack(itemOvenRangehood, 1), " * ", " * ", "*@*", '*', Items.iron_ingot, '@', Blocks.glowstone);
		GameRegistry.addRecipe(new ItemStack(itemHedgeBasic, 4), "***", "***", '*', new ItemStack(Blocks.leaves, 1, 0));
		GameRegistry.addRecipe(new ItemStack(itemHedgeSpruce, 4), "***", "***", '*', new ItemStack(Blocks.leaves, 1, 1));
		GameRegistry.addRecipe(new ItemStack(itemHedgeBirch, 4), "***", "***", '*', new ItemStack(Blocks.leaves, 1, 2));
		GameRegistry.addRecipe(new ItemStack(itemHedgeJungle, 4), "***", "***", '*', new ItemStack(Blocks.leaves, 1, 3));
		GameRegistry.addRecipe(new ItemStack(itemBirdBath, 1), "***", " * ", " * ", '*', Blocks.stone);
		GameRegistry.addRecipe(new ItemStack(itemStonePath, 8), "**", '*', Blocks.cobblestone);
		GameRegistry.addRecipe(new ItemStack(itemTap, 1), " @ ", "***", "  *", '*', Blocks.stone, '@', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(itemMailBox, 1), "*@*", "***", " * ", '*', Blocks.planks, '@', Items.book);
		GameRegistry.addRecipe(new ItemStack(itemEnvelope, 1), "**", '*', Items.paper);
		GameRegistry.addRecipe(new ItemStack(itemPackage, 1), "***", "***", '*', Items.paper);
		GameRegistry.addShapelessRecipe(new ItemStack(itemDoorBell, 1), Blocks.noteblock, Blocks.stone_button);
		GameRegistry.addShapelessRecipe(new ItemStack(itemWhiteFence, 2), Blocks.fence, new ItemStack(Items.dye, 1, 15));
		GameRegistry.addRecipe(new ItemStack(itemComputer, 1), "***", "*@*", "*&*", '*', Blocks.iron_block, '@', Blocks.glass_pane, '&', Items.redstone);
		GameRegistry.addRecipe(new ItemStack(itemPrinter, 1), "*@*", "&R&", "***", '*', Blocks.stone, '@', Items.paper, '&', Blocks.iron_block, 'R', Items.redstone);
		GameRegistry.addRecipe(new ItemStack(itemElectricFence, 8), "***", "*@*", "*#*", '*', Items.iron_ingot, '@', itemWhiteFence, '#', Blocks.redstone_torch);
		GameRegistry.addRecipe(new ItemStack(itemFireAlarm, 1), "*#*", "*@*", '*', Items.iron_ingot, '@', Blocks.noteblock, '#', Items.redstone);
		GameRegistry.addRecipe(new ItemStack(itemTV, 1), "***", "*@*", "*&*", '*', Blocks.log, '@', Blocks.glass_pane, '&', Items.redstone);
		GameRegistry.addRecipe(new ItemStack(itemStereo, 1), " * ", "NJN", '*', Items.iron_ingot, 'N', Blocks.noteblock, 'J', Blocks.jukebox);
		GameRegistry.addRecipe(new ItemStack(itemCeilingLight, 4), "O", "S", "G", 'O', Blocks.obsidian, 'S', Blocks.stone, 'G', Blocks.glowstone);
		GameRegistry.addRecipe(new ItemStack(itemInkCartridge, 2), "SSS", "SIS", "SSS", 'I', new ItemStack(Items.dye, 1, 0), 'S', Blocks.stone);
		GameRegistry.addRecipe(new ItemStack(itemTree, 1), " L ", "LLL", " P ", 'L', Blocks.leaves, 'P', Items.flower_pot);
		GameRegistry.addRecipe(new ItemStack(itemPresentRed, 1), "RRR", "RPR", "RRR", 'R', new ItemStack(Blocks.wool, 1, 14), 'P', itemPackage);
		GameRegistry.addRecipe(new ItemStack(itemPresentGreen, 1), "GGG", "GPG", "GGG", 'G', new ItemStack(Blocks.wool, 1, 13), 'P', itemPackage);
		GameRegistry.addRecipe(new ItemStack(itemToilet, 1), "QB ", "QQQ", " Q ", 'Q', Blocks.quartz_block, 'B', Blocks.stone_button);
		GameRegistry.addRecipe(new ItemStack(itemBasin, 1), "BIB", "QQQ", " Q ", 'Q', Blocks.quartz_block, 'B', Blocks.stone_button, 'I', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(itemWallCabinet, 1), "QQQ", "QCQ", "QQQ", 'Q', Blocks.quartz_block, 'C', Blocks.chest);
		GameRegistry.addRecipe(new ItemStack(itemShower, 1), "QGQ", "QGQ", "QGQ", 'Q', Blocks.quartz_block, 'G', Blocks.glass);
		GameRegistry.addRecipe(new ItemStack(itemBin, 1), "BSB", "I I", "III", 'B', Blocks.heavy_weighted_pressure_plate, 'S', Blocks.stone, 'I', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(itemBath, 1), "B  ", "Q Q", "QQQ", 'Q', Blocks.quartz_block, 'B', Blocks.stone_button);
		GameRegistry.addRecipe(new ItemStack(itemShowerHead, 1), "II ", " I ", "SSS", 'S', Blocks.stone, 'I', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(itemShowerHead, 1), "II ", " I ", "SSS", 'S', Blocks.stone, 'I', Items.iron_ingot);
		GameRegistry.addShapelessRecipe(new ItemStack(itemSoap), Items.clay_ball, new ItemStack(Items.dye, 1, 15), new ItemStack(Items.dye, 1, 12));
		GameRegistry.addShapelessRecipe(new ItemStack(itemSoapyWater), Items.water_bucket, itemSoap);
		GameRegistry.addRecipe(new ItemStack(itemSuperSoapyWater), "GGG", "GSG", "GGG", 'G', Items.gold_ingot, 'S', itemSoapyWater);
		GameRegistry.addRecipe(new ItemStack(itemToaster), "QBQ", "QPS", "WWW", 'Q', Blocks.quartz_block, 'B', Blocks.iron_bars, 'S', Blocks.stone, 'P', Blocks.piston, 'W', new ItemStack(Blocks.wool, 1, 15));
		GameRegistry.addRecipe(new ItemStack(itemMicrowave), "IIQ", "GGB", "IIQ", 'I', Items.iron_ingot, 'Q', Blocks.quartz_block, 'G', Blocks.glass_pane, 'B', Blocks.stone_button);
		GameRegistry.addRecipe(new ItemStack(itemBlender), "GBG", "GIG", "BBB", 'G', Blocks.glass_pane, 'B', new ItemStack(Blocks.wool, 1, 15), 'I', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(itemWashingMachine), "QQQ", "QGQ", "QFQ", 'Q', Blocks.quartz_block, 'G', Blocks.glass_pane, 'F', Blocks.furnace);
		GameRegistry.addRecipe(new ItemStack(itemDishWasher), "QQQ", "CBC", "CFC", 'Q', Blocks.quartz_block, 'G', Blocks.glass_pane, 'F', Blocks.furnace, 'C', new ItemStack(Blocks.stained_hardened_clay, 1, 9), 'B', Blocks.iron_bars);
		GameRegistry.addRecipe(new ItemStack(itemCounterDoored), "CCC", "QQQ", "QQQ", 'C', new ItemStack(Blocks.stained_hardened_clay, 1, 9), 'Q', Blocks.quartz_block);
		GameRegistry.addRecipe(new ItemStack(itemCounterSink), "CSC", "QQQ", "QQQ", 'C', new ItemStack(Blocks.stained_hardened_clay, 1, 9), 'S', itemBasin, 'Q', Blocks.quartz_block);
		GameRegistry.addRecipe(new ItemStack(itemKitchenCabinet), "QQQ", "HCH", "QQQ", 'Q', Blocks.quartz_block, 'C', Blocks.chest, 'H', new ItemStack(Blocks.stained_hardened_clay, 1, 9));
		GameRegistry.addRecipe(new ItemStack(itemPlate), "Q Q", " Q ", 'Q', Blocks.quartz_block);
		GameRegistry.addRecipe(new ItemStack(itemCookieJar), " W ", "G G", "GGG", 'W', new ItemStack(Blocks.wool, 1, 15), 'G', Blocks.glass_pane);
		GameRegistry.addRecipe(new ItemStack(itemBarStool), "WWW", "CCC", "Q Q", 'W', new ItemStack(Blocks.wool, 1, 0), 'C', new ItemStack(Blocks.stained_hardened_clay, 1, 9), 'Q', Blocks.quartz_block);
		GameRegistry.addRecipe(new ItemStack(itemChoppingBoard), "LLL", "SSS", "LLL", 'L', new ItemStack(Blocks.log, 1, 0), 'S', new ItemStack(Blocks.wooden_slab, 1, 0));
		GameRegistry.addRecipe(new ItemStack(itemKnife), "I ", " S", 'I', Blocks.stone, 'S', Items.stick);
		GameRegistry.addRecipe(new ItemStack(itemCup), "G G", "G G", "GGG", 'G', Blocks.glass_pane);
		GameRegistry.addSmelting(itemFlesh, new ItemStack(itemCookedFlesh), 0.05F);
		FMLCommonHandler.instance().bus().register(new CraftingHandler());
		FMLCommonHandler.instance().bus().register(new PlayerEvents());
		if (event.getSide() == Side.CLIENT)
		{
			this.keyHandler = new InputHandler();
			FMLCommonHandler.instance().bus().register(this.keyHandler);
		}
		else
			FMLCommonHandler.instance().bus().register(new SyncEvent());

		FMLInterModComms.sendMessage("Waila", "register", "com.mrcrayfish.furniture.util.WailaIconRegister.callbackRegister");
	}

	@EventHandler
	public void loadComplete(FMLPostInitializationEvent event)
	{
		RecipeRegistry.registerDefaultRecipes();
		RecipeRegistry.registerConfigRecipes();
		Recipes.addCommRecipesToLocal();
		Recipes.updateDataList();
		FishRegistry.registerNormal(new ItemStack(itemFlesh), 8);
		FishRegistry.registerLoot(new ItemStack(itemToast), 8);
		FishRegistry.registerLoot(new ItemStack(itemToilet), 5);
		FishRegistry.registerLoot(new ItemStack(itemPlate), 5);
		FishRegistry.registerLoot(new ItemStack(itemBlender), 5);
		FishRegistry.registerRare(new ItemStack(itemKnife), 2);
		FishRegistry.registerRare(new ItemStack(itemCrayfish), 2);
	}

	@EventHandler
	public void processIMC(IMCEvent event)
	{
		if (event.getMessages().size() > 0 && ConfigurationHandler.api_debug)
			System.out.println("RecipeAPI (InterModComm): Registering recipes from " + event.getMessages().size() + " mod(s).");

		UnmodifiableIterator var2 = event.getMessages().iterator();

		while (var2.hasNext())
		{
			IMCMessage imcMessage = (IMCMessage) var2.next();
			if (imcMessage.isStringMessage() && imcMessage.key.equalsIgnoreCase("register"))
				this.register(imcMessage.getStringValue(), imcMessage.getSender());
		}

	}

	public void register(String method, String modid)
	{
		String[] data = method.split("\\.");
		String methodName = data[data.length - 1];
		String className = method.substring(0, method.length() - methodName.length() - 1);
		String modName = Loader.instance().getIndexedModList().get(modid).getName();

		try
		{
			Class clazz = Class.forName(className);
			Method registerMethod = clazz.getDeclaredMethod(methodName, IRecipeRegistry.class);
			registerMethod.invoke(null, RecipeRegistryComm.getInstance(modName));
		}
		catch (ClassNotFoundException var9)
		{
			var9.printStackTrace();
		}
		catch (NoSuchMethodException var10)
		{
			var10.printStackTrace();
		}
		catch (Exception var11)
		{
			var11.printStackTrace();
		}

	}
}
