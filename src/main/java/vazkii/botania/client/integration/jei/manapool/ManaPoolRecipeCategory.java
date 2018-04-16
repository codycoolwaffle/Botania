/**
 * This class was created by <williewillus>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p/>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.client.integration.jei.manapool;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.lib.LibMisc;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ManaPoolRecipeCategory implements IRecipeCategory {

	public static final String UID = "botania.manaPool";
	private final IDrawable background;
	private final String localizedName;
	private final IDrawable overlay;
	private final ItemStack renderStack = new ItemStack(ModBlocks.pool);

	public ManaPoolRecipeCategory(IGuiHelper guiHelper) {
		background = guiHelper.createBlankDrawable(168, 64);
		localizedName = I18n.format("botania.nei.manaPool");
		overlay = guiHelper.createDrawable(new ResourceLocation("botania", "textures/gui/pureDaisyOverlay.png"),
				0, 0, 64, 46);
		ItemNBTHelper.setBoolean(renderStack, "RenderFull", true);
	}

	@Nonnull
	@Override
	public String getUid() {
		return UID;
	}

	@Nonnull
	@Override
	public String getTitle() {
		return localizedName;
	}

	@Nonnull
	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Nullable
	@Override
	public IDrawable getIcon() {
		return null;
	}

	@Override
	public void drawExtras(@Nonnull Minecraft minecraft) {
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		overlay.draw(minecraft, 48, 0);
		GlStateManager.disableBlend();
		GlStateManager.disableAlpha();
	}

	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
		if(!(recipeWrapper instanceof ManaPoolRecipeWrapper))
			return;

		int index = 0;

		recipeLayout.getItemStacks().init(index, true, 40, 12);
		recipeLayout.getItemStacks().set(index, ingredients.getInputs(ItemStack.class).get(0));

		index++;

		if(ingredients.getInputs(ItemStack.class).size() > 1) {
			// Has catalyst
			recipeLayout.getItemStacks().init(index, true, 20, 12);
			recipeLayout.getItemStacks().set(index, ingredients.getInputs(ItemStack.class).get(1));
			index++;
		}

		recipeLayout.getItemStacks().init(index, true, 70, 12);
		recipeLayout.getItemStacks().set(index, renderStack);
		index++;

		recipeLayout.getItemStacks().init(index, false, 99, 12);
		recipeLayout.getItemStacks().set(index, ingredients.getOutputs(ItemStack.class).get(0));
	}

	@Override
	public List getTooltipStrings(int mouseX, int mouseY) {
		return new ArrayList();
	}

	@Nonnull
	@Override
	public String getModName() {
		return LibMisc.MOD_NAME;
	}

}
