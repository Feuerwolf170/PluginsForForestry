package denoflionsx.plugins;

import buildcraft.api.liquids.LiquidStack;
import denoflionsx.API.PfFManagers;
import net.minecraft.src.*;
import denoflionsx.denLib.denLib;
import denoflionsx.core.core;
import denoflionsx.denLib.Config.Config;
import denoflionsx.plugins.Forestry.Modules.peatModule;
import denoflionsx.plugins.Forestry.RefineryHack;
import denoflionsx.plugins.Forestry.addFermenterRecipes;
import forestry.api.core.ItemInterface;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.recipes.RecipeManagers;
import denoflionsx.plugins.Forestry.Modules.*;
import denoflionsx.plugins.Forestry.Modules.solidfuel.solidfuelModule;

public class pluginForestry extends pluginBase {

    public pluginForestry() {
        this.mod = "Forestry";
        this.name = "pluginForestry";
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    @Override
    protected boolean init() {
        if (!detect()) {
            return hooked;
        }
        if (this.config.getOptionBool("WoodenBucketIntegration")) {
            PfFManagers.ItemManager.registerItem("seedoil", ItemInterface.getItem("liquidSeedOil"));
            PfFManagers.ItemManager.registerItem("applejuice", ItemInterface.getItem("liquidJuice"));
            PfFManagers.ItemManager.registerItem("honey", ItemInterface.getItem("liquidHoney"));
            PfFManagers.ItemManager.registerItem("biomass", ItemInterface.getItem("liquidBiomass"));
            PfFManagers.ItemManager.registerItem("biofuel", ItemInterface.getItem("liquidBiofuel"));
        }
        this.registerModules();
        hooked = true;
        if (hooked) {
            core.print(getName() + " Loaded!");
        }
        return hooked;
    }

    @Override
    protected void recipes() {
        if (true) {
            addFermenterRecipes.addItem(Item.sugar, 200, this);
            addFermenterRecipes.add(ItemInterface.getItem("liquidSeedOil"), 1.5F);
        }
        if (denLib.convertToBoolean(this.config.getOption("StillFix"))) {
            RecipeManagers.stillManager.addRecipe(Integer.valueOf(this.config.getOption("Still_WorkCycles")), new LiquidStack(ItemInterface.getItem("liquidBiomass").getItem(), Integer.valueOf(this.config.getOption("Still_BiomassPerCast"))), new LiquidStack(ItemInterface.getItem("liquidBiofuel").getItem(), Integer.valueOf(this.config.getOption("Still_BiofuelPerCast"))));
        }
        if (denLib.convertToBoolean(this.config.getOption("BiofuelInBiogas"))) {
            FuelManager.bronzeEngineFuel.put(Integer.valueOf(ItemInterface.getItem("liquidBiofuel").itemID), new EngineBronzeFuel(ItemInterface.getItem("liquidBiofuel"), Integer.valueOf(this.config.getOption("BiofuelMJt")), Integer.valueOf(this.config.getOption("BiofuelBurnTime")), 1));
        }
    }

    @Override
    public void register() {
        if (!loaded) {
            this.defaults();
            stillModule.load(this);
            peatModule.load(this);
            extraFuelsModule.load(this);
            solidfuelModule.load(this);
            blueswaxModule.load(this);
            this.runConfig();
            if (loaded = init()) {
                recipes();
            }
        }
    }

    @Override
    protected void defaults() {
        config.addDefault("[Forestry Options]");
        config.addDefault("BiofuelInBiogas=true");
        config.addDefault("BiofuelMJt=5");
        config.addDefault("BiofuelBurnTime=40000");
        config.addDefault("WoodenBucketIntegration=" + "true");
    }
}
