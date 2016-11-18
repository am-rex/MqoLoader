package modeltest;

import modeltest.modelloader.MQO_MetasequoiaObject;
import modeltest.modelloader.MQO_ModelLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ClientProxy extends CommonProxy
{
	public void registerRenderer()
	{
		AdvancedModelLoader.registerModelHandler(new MQO_ModelLoader());

		IModelCustom model =  AdvancedModelLoader.loadModel(new ResourceLocation(SampleMod.MODID, "models/sample.mqo"));

		MinecraftForgeClient.registerItemRenderer(SampleMod.item, new SampleItemRender((MQO_MetasequoiaObject) model));
	}
}
