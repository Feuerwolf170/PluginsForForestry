package denoflionsx.plugins;

import denoflionsx.Old.pluginBase;
import buildcraft.api.liquids.LiquidStack;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.EnumForestryLiquids;
import denoflionsx.Enums.EnumModIDs;
import denoflionsx.denLib.Config.Config;
import denoflionsx.plugins.FarmCraftory.Crops.Crop;
import denoflionsx.plugins.FarmCraftory.Crops.CropMulti;
import denoflionsx.plugins.FarmCraftory.Crops.EnumCrops;
import denoflionsx.plugins.FarmCraftory.Crops.Tree;
import denoflionsx.plugins.FarmCraftory.Modules.Liquidmodule;
import denoflionsx.plugins.FarmCraftory.cropHarvest;
import denoflionsx.plugins.Forestry.Helpers.SqueezerHelper;
import forestry.api.cultivation.CropProviders;

public class pluginFarmCraftory extends pluginBase {

    public pluginFarmCraftory() {
        this.name = "pluginFarmCraftory";
        this.mod = EnumModIDs.MODS.FARMCRAFTORY.getID();
        this.modid = this.mod;
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    @Override
    public void register() {
        if (!loaded) {
            Liquidmodule.load(this);
        }
        super.register();
    }

    @Override
    protected void defaults() {
        this.config.addDefault("[FarmCraftory Options]");
        this.config.addDefault("ForestryIntegration=" + "true");
        this.config.addDefault("#THIS OPTION BREAKS THE COMBINE IN TERRIBLE WAYS.");
        this.config.addDefault("TreeFruitHarvestByCombine=" + "false");
        this.config.addDefault("# This makes the plants grow super fast in the farm machine.");
        this.config.addDefault("CheatMode=" + "false");
        this.config.addDefault("Seeds_AmountPerSqueeze=" + 100);
    }

    @Override
    protected boolean init() {
        if (!this.detect()) {
            return false;
        }
        cropHarvest.cheatMode = this.getOptionBool("CheatMode");
        return true;
    }

    @Override
    protected void recipes() {
        int amount = this.config.getOptionInt("Seeds_AmountPerSqueeze");
        if (this.config.getOptionBool("ForestryIntegration")) {
            for (EnumCrops.SINGLE s : EnumCrops.SINGLE.values()) {
                CropProviders.cerealCrops.add(new Crop(s));
                SqueezerHelper.add(s.getPlant().getSeed(), new LiquidStack(EnumForestryLiquids.SEEDOIL.getLiquid().itemID, amount));
            }
            for (EnumCrops.MULTI s : EnumCrops.MULTI.values()) {
                CropProviders.cerealCrops.add(new CropMulti(s));
                SqueezerHelper.add(s.getPlant().getSeed(), new LiquidStack(EnumForestryLiquids.SEEDOIL.getLiquid().itemID, amount));
            }
            for (EnumCrops.TREE s : EnumCrops.TREE.values()) {
                CropProviders.poaleCrops.add(new Tree(s));
            }
        }
    }
}