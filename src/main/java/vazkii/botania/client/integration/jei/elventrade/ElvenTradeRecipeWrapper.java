/**
 * This class was created by <williewillus>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p/>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.client.integration.jei.elventrade;

import com.google.common.collect.ImmutableList;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.recipe.RecipeElvenTrade;

import javax.annotation.Nonnull;
import java.util.List;

public class ElvenTradeRecipeWrapper implements IRecipeWrapper {

	private final List<List<ItemStack>> input;
	private final List<ItemStack> outputs;

	@SuppressWarnings("unchecked")
	public ElvenTradeRecipeWrapper(RecipeElvenTrade recipe) {
		ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();
		for(Object o : recipe.getInputs()) {
			if(o instanceof ItemStack) {
				builder.add(ImmutableList.of((ItemStack) o));
			}
			if(o instanceof String) {
				builder.add(OreDictionary.getOres((String) o));
			}
		}
		input = builder.build();
		outputs = ImmutableList.copyOf(recipe.getOutputs());
	}

	@Override
	public void getIngredients(@Nonnull IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, input);
		ingredients.setOutputs(ItemStack.class, outputs);
	}
}
