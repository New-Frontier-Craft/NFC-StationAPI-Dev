package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.IntProperty;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.math.Direction;
import net.modificationstation.stationapi.api.world.BlockStateView;
import net.newfrontiercraft.nfc.block.entity.HeatCoilBlockEntity;
import net.newfrontiercraft.nfc.events.init.ItemListener;
import net.newfrontiercraft.nfc.utils.BoxUtil;
import net.newfrontiercraft.nfc.utils.CoilDamageCooldown;

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
    public Box getCollisionShape(World world, int x, int y, int z) {
        return Box.createCached((float)x + 0.01, y + 0.01, (float)z + 0.01, (float)(x + 0.99F), y + 0.99F, (float)(z + 0.99F));
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public void onEntityCollision(World world, int x, int y, int z, Entity entity) {
        if(entity instanceof LivingEntity livingEntity){
            CoilDamageCooldown coilDamageCooldown = (CoilDamageCooldown)livingEntity;
            if(coilDamageCooldown.getCoilDamageCooldown() <= 0){
                BlockState blockState = world.getBlockState(x, y, z);
                if(blockState.contains(HEAT) && blockState.get(HEAT) > 0 && shouldBurnEntity(world, livingEntity, blockState, x, y, z)){
                    coilDamageCooldown.setCoilDamageCooldown(20);
                    livingEntity.damage(null, blockState.get(HEAT));
                    world.playSound(livingEntity, "random.fizz", 0.5F, 2.6F + (livingEntity.world.random.nextFloat() - livingEntity.world.random.nextFloat()) * 0.8F);
                }
            }
        }
    }

    private boolean shouldBurnEntity(World world, LivingEntity livingEntity, BlockState blockState, int x, int y, int z){
        if(livingEntity instanceof PlayerEntity playerEntity){
            float var7 = playerEntity.width / 2.0F;
            float var8 = playerEntity.height;
            Box playerBox = Box.create(playerEntity.x - (double)var7, playerEntity.y - (double)playerEntity.standingEyeHeight + (double)playerEntity.cameraOffset, playerEntity.z - (double)var7, playerEntity.x + (double)var7, playerEntity.y - (double)playerEntity.standingEyeHeight + (double)playerEntity.cameraOffset + (double)var8, playerEntity.z + (double)var7);
            Box coilBox = blockState.getBlock().getBoundingBox(world, x, y, z);

            Direction collisionSide = BoxUtil.getCollisionSideFromBoxes(coilBox, playerBox);

            switch(collisionSide){
                case NORTH:
                case EAST:
                case SOUTH:
                case WEST:
                    if(playerEntity.inventory.armor[1] != null && isArmorPieceEffective(playerEntity.inventory.armor[1]) && playerEntity.inventory.armor[2] != null && isArmorPieceEffective(playerEntity.inventory.armor[2])){
                        return false;
                    }
                    break;
                case UP:
                    if(playerEntity.inventory.armor[0] != null && isArmorPieceEffective(playerEntity.inventory.armor[0])){
                        return false;
                    }
                    break;
                case DOWN:
                    if(playerEntity.inventory.armor[3] != null && isArmorPieceEffective(playerEntity.inventory.armor[3])){
                        return false;
                    }
            }
        }
        return true;
    }

    private boolean isArmorPieceEffective(ItemStack armorPiece){
        TagKey HEAT_PROTECTION = TagKey.of(ItemRegistry.KEY, ItemListener.MOD_ID.id("heat_protection"));
        return armorPiece.isIn(HEAT_PROTECTION);
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
