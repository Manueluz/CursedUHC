package ccplugins.curseduhc.MiscFeatures.CustomItems;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GoldenHeadEatListener implements Listener {
    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent event){
        if(event.getItem().getItemMeta().hasLore() && event.getItem().getItemMeta().getLore().get(0).equals("You killed someone for this")){
            event.setCancelled(true);

            Player player = event.getPlayer();
            player.setFoodLevel(player.getFoodLevel()+4);
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,1200,2));
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,300,1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,3000,0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,6000,0));

            ItemStack item = event.getItem();
            item.setAmount(item.getAmount()-1);
            if(item.getAmount()<=0){item=null;}
            if(player.getInventory().getItemInMainHand().getItemMeta().hasLore() && player.getInventory().getItemInMainHand().getItemMeta().getLore().get(0).equals("You killed someone for this")) {
                player.getInventory().setItemInMainHand(item);
            }else {
                player.getInventory().setItemInOffHand(item);
            }
        }
    }
}
