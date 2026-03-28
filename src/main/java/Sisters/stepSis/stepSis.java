package Sisters.stepSis;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import java.util.UUID;
import java.util.HashMap;

public final class stepSis extends JavaPlugin implements Listener {

    private final HashMap<String, ItemStack> shulkers = new HashMap<>();
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
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

    private ItemStack createVirtualShulker(String id) {
        ItemStack item = new ItemStack(Material.SHULKER_BOX);
        ItemMeta menta = item.getItemMeta();
        if (menta != null) {
            menta.setDisplayName("Liran");
            item.setItemMeta(menta);
        }
        return item;
    }
    private void MeidaShulker(ItemStack cursor,String id){
        shulkers.put(id,cursor);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Inventory Itay = e.getView().getTopInventory();
        ItemStack cursor = e.getCursor();
        ItemStack current = e.getCurrentItem();

        if (cursor.getType() == Material.AIR) return;
        if (current == null) return;

        if (isShulker(cursor.getType()) && isShulkerInventory(Itay)) {
            e.setCancelled(true);

            String id = UUID.randomUUID().toString();
            MeidaShulker(cursor,id);

            ItemStack fake = createVirtualShulker(id);
            e.setCurrentItem(fake);
        }
    }
}
