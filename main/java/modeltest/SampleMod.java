package modeltest;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = SampleMod.MODID, version = SampleMod.VERSION)
public class SampleMod
{
    public static final String MODID = "modeltest";
    public static final String VERSION = "1.0";

	@SidedProxy(
		clientSide = "modeltest.ClientProxy",
		serverSide = "modeltest.CommonProxy")
	public static CommonProxy proxy;

	public static SampleItem item;

	@EventHandler
	public void PreInit(FMLPreInitializationEvent evt)
	{
		item = new SampleItem();
		item.setUnlocalizedName(MODID + ":sample");	// システム名の登録
		item.setTextureName(MODID + ":sample");		// テクスチャの指定
		item.setCreativeTab(CreativeTabs.tabCombat);// 武器防具タブにアイテム追加
		GameRegistry.registerItem(item, "sample");

		this.proxy.registerRenderer();
	}

	@EventHandler
	public void init(FMLInitializationEvent evt)
	{

	}


	@EventHandler
	public void postInit(FMLPostInitializationEvent evt)
	{
	}
}
