package net.newfrontiercraft.nfc.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.decoration.painting.PaintingVariants;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlas;
import net.newfrontiercraft.nfc.mixin.PaintingVariantsAccessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaintingRegistry {

    public static PaintingRegistry INSTANCE = new PaintingRegistry();
    public List<PaintingVariants> paintingVariants = new ArrayList<>();
    public List<String> paintingIds = new ArrayList<>();

    public Map<String, Atlas.Sprite> sprites = new HashMap<>();

    public Atlas.Sprite paintingBackSprite;

    public PaintingRegistry(){
        addPainting("Leo", 64, 64);
        addPainting("Fancy", 64, 64);
        addPainting("TheSpawn", 32, 32);
        addPainting("Nein", 16, 16);
        addPainting("Sonk", 32, 32);
        addPainting("Dex", 64, 64);
    }

    public PaintingVariants getPaintingVariant(String id){
        for (PaintingVariants variant : paintingVariants) {
            if (variant.id.equals(id)) {
                return variant;
            }
        }
        return null;
    }

    public void addPainting(String id, int width, int height){
        paintingVariants.add(PaintingVariantsAccessor.newPaintingVariants(id, PaintingVariants.values().length, id, width, height, 0, 0));
        paintingIds.add(id);
    }

    public boolean isNfcPaining(String id){
        return paintingIds.contains(id);
    }

    @Environment(EnvType.CLIENT)
    public void addTexture(String paintingId, Atlas.Sprite sprite){
        sprites.put(paintingId, sprite);
    }
}
