package de.pilz.customnpcsadvanced.network.messages;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class BaseMessageTileEntityPosition implements IMessage {

    public int posX;
    public int posY;
    public int posZ;

    public BaseMessageTileEntityPosition() {}

    public BaseMessageTileEntityPosition(int posX, int posY, int posZ) {
        this();
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        posX = buf.readInt();
        posY = buf.readInt();
        posZ = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(posX);
        buf.writeInt(posY);
        buf.writeInt(posZ);
    }
}
