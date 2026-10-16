package net.minheur.itemtanks.operations;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minheur.itemtanks.TankHandler;
import net.minheur.itemtanks.file.ConfigHandler;
import org.jspecify.annotations.NonNull;

public final class IllegalTankItems {

    public static boolean isItemForbidden(@NonNull ItemStack stack) {
        if (stack.isEmpty()) return true;
        if (TankHandler.isTank(stack)) return true;
        String id = Registries.ITEM.getId(stack.getItem()).toString();
        if (ConfigHandler.getConfig().illegalTanksItems.contains(id)) return true;

        NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
        if (nbtComponent == null) return false;
        NbtCompound nbt = nbtComponent.copyNbt();

        Boolean flag = nbt.getBoolean("no_tank_flag").orElse(null);
        return flag != null && flag;
    }

}
