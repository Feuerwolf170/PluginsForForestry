package denoflionsx.plugins.Railcraft.Modules.OreDicRecipeManager;

import java.util.ArrayList;
import net.minecraft.src.ItemStack;

public interface IOreDictRecipeManager {
    
    public void addRecipes(ItemStack output, ArrayList<ItemStack> ores);
    
}
