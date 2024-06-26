package de.pilz.customnpcsadvanced;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import de.pilz.customnpcsadvanced.configuration.ConfigManager;
import de.pilz.customnpcsadvanced.feature.TileEntityNpcManager;
import de.pilz.customnpcsadvanced.network.NetworkManager;

public class CommonProxy implements IGuiHandler {

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        ConfigManager.init();
        CustomNpcPlusExtras.LOG.info("I am Custom NPC+ Extras at version " + Tags.VERSION);
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(TileEntityNpcManager.Instance);
        FMLCommonHandler.instance()
            .bus()
            .register(TileEntityNpcManager.Instance);
        NetworkManager.init();
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {}

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        TileEntityNpcManager.Instance.initNpcData();
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    public EntityPlayer getPlayer(MessageContext ctx) {
        return ctx.getServerHandler().playerEntity;
    }

    public World getWorld(MessageContext ctx) {
        return ctx.getServerHandler().playerEntity.worldObj;
    }
}
