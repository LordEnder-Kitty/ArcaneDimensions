package net.enderkitty.block.block_entities;

import net.enderkitty.block.ArcaneDimsBlockEntities;
import net.enderkitty.item.items.WardCageItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class WardCageBlockEntity extends BlockEntity {
    public WardCageBlockEntity(BlockPos pos, BlockState state) {
        super(ArcaneDimsBlockEntities.WARD_CAGE_BLOCK_ENTITY, pos, state);
    }
    
    public int Souls = 0;
    
    public int getSouls() { return Souls; }
    public void setSouls(int amount) { this.Souls = amount; }
    public final boolean hasSouls() { return getSouls() > 0; }
    
    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putInt(WardCageItem.SOULS_KEY, getSouls());
        super.writeNbt(nbt);
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Souls = nbt.getInt(WardCageItem.SOULS_KEY);
    }
    
    @Override
    public @Nullable Object getRenderData() {
        return getSouls();
    }
    
    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
    
    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
}
