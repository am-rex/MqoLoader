package modeltest;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import modeltest.modelloader.MQO_MetasequoiaObject;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class SampleItemRender implements IItemRenderer
{
	MQO_MetasequoiaObject model;

	public SampleItemRender(MQO_MetasequoiaObject model)
	{
		this.model = model;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return
			type == ItemRenderType.EQUIPPED ||
			type == ItemRenderType.EQUIPPED_FIRST_PERSON ||
			type == ItemRenderType.ENTITY ||
			type == ItemRenderType.INVENTORY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		GL11.glPushMatrix();

		GL11.glEnable(GL11.GL_CULL_FACE);

		GL11.glShadeModel(GL11.GL_SMOOTH);

		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(SampleMod.MODID, "textures/sample.png"));

		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);

		switch(type)
		{
		case ENTITY:
			model.renderAll();
			break;
		case EQUIPPED:
			GL11.glTranslatef(0, 0.005F, -0.165F);
			GL11.glScalef(2,2,2);
			GL11.glRotatef(-10, 0, 0, 1);
			GL11.glRotatef( 90, 0,-1, 0);
			GL11.glRotatef(-50, 1, 0, 0);
			model.renderAll();
			break;
		case EQUIPPED_FIRST_PERSON:
			GL11.glTranslatef(0.3F, 0.5F, -0.5F);
			GL11.glScalef(2,2,2);
			GL11.glRotatef( 10, 0, 0, 1);
			GL11.glRotatef(160, 0, 1, 0);
			GL11.glRotatef(-10, 1, 0, 0);
			model.renderAll();
			break;
		case INVENTORY:
			GL11.glTranslatef(5, 5, 5);
			GL11.glScalef(2,2,2);
			model.renderAll();
			break;
		case FIRST_PERSON_MAP:
		default:
			break;
		}

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);

		GL11.glShadeModel(GL11.GL_FLAT);

		GL11.glPopMatrix();
	}
}
