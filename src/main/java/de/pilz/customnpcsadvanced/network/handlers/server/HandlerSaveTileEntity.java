package de.pilz.customnpcsadvanced.network.handlers.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import de.pilz.customnpcsadvanced.CustomNpcPlusExtras;
import de.pilz.customnpcsadvanced.api.TileEntityNpc;
import de.pilz.customnpcsadvanced.feature.TileEntityNpcManager;
import de.pilz.customnpcsadvanced.network.messages.client.MessageSaveTileEntity;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.entity.EntityNPCInterface;

public class HandlerSaveTileEntity implements IMessageHandler<MessageSaveTileEntity, IMessage> {

    public HandlerSaveTileEntity() {}

    @Override
    public IMessage onMessage(MessageSaveTileEntity message, MessageContext ctx) {
        if (message.npcData != null) {
            EntityNPCInterface npcInterface = NoppesUtilServer.getEditingNpc(CustomNpcPlusExtras.proxy.getPlayer(ctx));
            if (npcInterface instanceof TileEntityNpc) {
                // Copy dialogs here on server side because CNPC+ saves them there always.
                message.npcData.setDialogOptions(((TileEntityNpc) npcInterface).dialogs);
                TileEntityNpcManager.Instance.saveNpcData(message.npcData);
            }
        }

        return null;
    }
}
