package denoflionsx.PluginsforForestry.Proxy;

import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRLiquidBlock;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRLiquidItem;
import java.io.File;
import net.minecraft.item.ItemStack;

public interface IPfFProxy {
    
    public void print(String msg);
    
    public void warning(String msg);
    
    public void severe(String msg);
    
    public void registerClientSide();
    
    public void registerLiquidBlock(String perma, String name, LRLiquidBlock b);
    
    public void registerLiquidItem(String perma, String name, LRLiquidItem i);
    
    public void registerRecipe(ItemStack i, Object[] o);
    
    public void registerShapelessRecipe(ItemStack i, ItemStack[] stacks);
    
    public void registerAllRecipes();
    
    public void sendMessageToPlayer(String msg);
    
    public String translate(String key);
    
    public String getLang();
    
    public void setTabs();
    
    public void findInternalAddons(File source);
    
}
