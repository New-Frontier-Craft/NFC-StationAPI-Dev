package net.newfrontiercraft.nfc.registry;

import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.block.States;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MultiBlockRecipe {
    private final List<String[]> layers;
    private final List<BlockPatternEntry> blockPatterns;

    public MultiBlockRecipe(List<String[]> layers, List<BlockPatternEntry> blockPatterns){
        this.layers = layers;
        this.blockPatterns = blockPatterns;
    }

    public List<BlockPatternEntry> getBlockPatterns() {
        return blockPatterns;
    }

    public List<String[]> getLayers(){
        return layers;
    }

    private int getPatternCount(char pattern){
        int count = 0;
        for(String[] layer : layers){
            for(String section : layer){
                for(char currentPattern : section.toCharArray()){
                    if(currentPattern == pattern){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public List<ItemStack> getCost(){
        List<ItemStack> cost = new ArrayList<>();
        for(BlockPatternEntry entry : blockPatterns){
            if(entry.item() == null) continue;
            ItemStack stack = entry.item().copy();
            stack.count = getPatternCount(entry.pattern());
            cost.add(stack);
        }
        cost.sort((a, b) -> Integer.compare(b.count, a.count));
        return cost;
    }

    @Nullable
    public BlockPatternEntry getEntryForPattern(char pattern){
        if(pattern == ' '){
            return new BlockPatternEntry(' ', States.AIR.get(), 0, null);
        }
        for(BlockPatternEntry patternEntry : blockPatterns){
            if(patternEntry.pattern() == pattern){
                return patternEntry;
            }
        }
        return null;
    }

    public List<ItemStack> getItems(){
        List<ItemStack> items = new ArrayList<>();
        for(BlockPatternEntry entry : blockPatterns){
            items.add(entry.item());
        }
        return items;
    }

    public int getStructureWidth(){
        int width = 0;
        for(String[] layer : layers){
            for(String section : layer){
                if(section.length() > width){
                    width = section.length();
                }
            }
        }
        return width;
    }

    public int getStructureHeight(){
        return layers.size();
    }

    public int getStructureDepth(){
        int depth = 0;
        for(String[] layer : layers){
            if(layer.length > depth){
                depth = layer.length;
            }
        }
        return depth;
    }
}
