package Sisters.stepSis;

import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public final class stepSis extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    public PlayerInventory Liran(Player player) {
        return player.getInventory();
    }

    private boolean isShulker(Material mat) {
        return mat.name().endsWith("SHULKER_BOX");
    }

    private boolean isShulkerInventory(Inventory inv) {
        return inv.getType().name().equals("SHULKER_BOX");
    }

    private ItemStack createVirtualShulker(ItemStack original) {
        if (!(original.getItemMeta() instanceof BlockStateMeta meta)) return original;
        if (!(meta.getBlockState() instanceof ShulkerBox shulker)) return original;

        ItemStack fake = new ItemStack(Material.SHULKER_BOX);
        BlockStateMeta fakeMeta = (BlockStateMeta) fake.getItemMeta();
        if (fakeMeta == null) return fake;

        ShulkerBox fakeShulker = (ShulkerBox) fakeMeta.getBlockState();
        fakeShulker.getInventory().setContents(shulker.getInventory().getContents());

        String displayName = original.hasItemMeta() && original.getItemMeta().hasDisplayName()
                ? original.getItemMeta().getDisplayName()
                : "Liran";

        fakeMeta.setDisplayName(displayName);
        fakeMeta.setBlockState(fakeShulker);
        fake.setItemMeta(fakeMeta);

        return fake;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Inventory Itay = e.getView().getTopInventory();
        ItemStack cursor = e.getCursor();
        ItemStack current = e.getCurrentItem();

        if (cursor != null
                && cursor.getType() != Material.AIR
                && isShulker(cursor.getType())
                && isShulkerInventory(Itay)
                && e.getRawSlot() < Itay.getSize()
                && !e.isShiftClick()
                && e.getSlotType() == InventoryType.SlotType.CONTAINER) {

            e.setCancelled(true);

            ItemStack fake = createVirtualShulker(cursor);
            e.setCurrentItem(fake);
            e.getWhoClicked().setItemOnCursor(null);
        }

        if (current != null && isShulker(current.getType())) {
        }
    }
}
