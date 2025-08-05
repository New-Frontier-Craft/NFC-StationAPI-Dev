package net.newfrontiercraft.nfc.mixin.graphics;

import net.glasslauncher.mods.alwaysmoreitems.gui.RecipeLayout;
import net.glasslauncher.mods.alwaysmoreitems.gui.screen.RecipesGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(value = RecipesGui.class, remap = false)
public interface RecipesGuiAccessor {
    @Accessor(remap = false)
    RecipeLayout getHovered();

    @Accessor
    List<RecipeLayout> getRecipeLayouts();
}
