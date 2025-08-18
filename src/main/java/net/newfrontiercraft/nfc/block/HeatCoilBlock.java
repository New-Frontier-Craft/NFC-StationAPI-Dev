package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.BlockView;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.IntProperty;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.world.BlockStateView;
import net.modificationstation.stationapi.api.world.StationFlatteningWorld;
import net.modificationstation.stationapi.api.world.StationFlatteningWorldPopulationRegion;
import net.newfrontiercraft.nfc.block.entity.HeatCoilBlockEntity;

public class HeatCoilBlock extends TemplateBlockWithEntity {

    public static final IntProperty HEAT = IntProperty.of("heat", 0, 3);

    int heatLevel0Texture;
    int heatLevel1Texture;
    int heatLevel2Texture;
    int heatLevel3Texture;

    public HeatCoilBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material);
        setTranslationKey(identifier.namespace, identifier.path);
        setHardness(hardness);
        setSoundGroup(blockSounds);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(HEAT);
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new HeatCoilBlockEntity();
    }

    public void specifyTextures(int heatLevel0Texture, int heatLevel1Texture, int heatLevel2Texture, int heatLevel3Texture){
        this.heatLevel0Texture = heatLevel0Texture;
        this.heatLevel1Texture = heatLevel1Texture;
        this.heatLevel2Texture = heatLevel2Texture;
        this.heatLevel3Texture = heatLevel3Texture;
    }

    @Override
    public float getLuminance(BlockView blockView, int x, int y, int z) {
        return super.getLuminance(blockView, x, y, z);
    }

    @Override
    public int getTexture(int side, int meta) {
        return heatLevel0Texture;
    }

    @Override
    public int getTextureId(BlockView blockView, int x, int y, int z, int side) {
        if(blockView instanceof BlockStateView blockStateView){
            BlockState blockState = blockStateView.getBlockState(x, y, z);
            if(blockState.contains(HEAT)){
                System.out.println(blockState.get(HEAT));
                switch (blockState.get(HEAT)){
                    case 0:
                        return heatLevel0Texture;
                    case 1:
                        return heatLevel1Texture;
                    case 2:
                        return heatLevel2Texture;
                    case 3:
                        return heatLevel3Texture;
                }
            }
        }
        return heatLevel0Texture;
    }
}
