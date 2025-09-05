package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.template.block.TemplateOreBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;
import org.jetbrains.annotations.NotNull;

public class LazyOreTemplate extends TemplateOreBlock implements CustomTooltipProvider {
    private ToolTierEnum toolTierEnum;
    int textureInternal;
    Identifier dropID;

    public LazyOreTemplate(Identifier identifier, float hardness) {
        super(identifier, 0);
        setTranslationKey(identifier.namespace, identifier.path);
        setResistance(500F);
        setHardness(hardness);
        setSoundGroup(Block.STONE_SOUND_GROUP);
        this.dropID = identifier;
    }

    public LazyOreTemplate(Identifier identifier, float hardness, ToolTierEnum toolTierEnum) {
        this(identifier, hardness);
        this.toolTierEnum = toolTierEnum;
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, int x, int y, int z, int meta) {
        if (world.isRemote) return;
        dropStack(world, x, y, z, new ItemStack(ItemRegistry.INSTANCE.get(dropID), 1, 0));
    }

    public void specifyCustomDrop(Identifier dropID) {
        this.dropID = dropID;
    }

    public void specifyTextures(int texture) {
        textureInternal = texture;
    }

    @Override
    public int getTexture(int side, int meta) {
        return textureInternal;
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        if (toolTierEnum == null) {
            return new String[] {originalTooltip};
        }
        return new String[]{toolTierEnum.getColourCode() + originalTooltip,
                "§7" + TranslationStorage.getInstance().get("tool_tier.tier") + ": " + toolTierEnum.getColourCode() + TranslationStorage.getInstance().get(toolTierEnum.getName())};
    }
}
