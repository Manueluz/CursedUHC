package ccplugins.curseduhc.MiscFeatures.CustomCrafts;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;


import java.util.ArrayList;
import java.util.Collections;

public class CraftRegisterer {

    private static ArrayList<Recipe> recipeList = new ArrayList<>();

    public static void registerCustomCrafts(Plugin plugin){
        ShapelessRecipe melonRecipe = new ShapelessRecipe(new NamespacedKey(plugin,"melonRecipe"),new ItemStack(Material.GLISTERING_MELON_SLICE,9));
        melonRecipe.addIngredient(Material.MELON);
        melonRecipe.addIngredient(Material.GOLD_BLOCK);
        recipeList.add(melonRecipe);

        ShapedRecipe goldApple = new ShapedRecipe(new NamespacedKey(plugin,"goldAppleRecipe"),new ItemStack(Material.GOLDEN_APPLE,1));
        goldApple.shape("GGG","GAG","GGG");
        goldApple.setIngredient('G',Material.GOLD_INGOT);
        goldApple.setIngredient('A',Material.APPLE);
        recipeList.add(goldApple);

        ItemStack goldenSkull = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE,1);
        ItemMeta skullMeta = goldenSkull.getItemMeta();
        skullMeta.setDisplayName("GoldenSkull");
        skullMeta.setLore(Collections.singletonList("You killed someone for this"));
        goldenSkull.setItemMeta(skullMeta);
        ShapedRecipe goldSkull = new ShapedRecipe(new NamespacedKey(plugin,"goldenSkullRecipe"),goldenSkull);
        goldSkull.shape("GGG","GAG","GGG");
        goldSkull.setIngredient('G',Material.GOLD_INGOT);
        goldSkull.setIngredient('A',Material.PLAYER_HEAD);
        recipeList.add(goldSkull);

        ShapedRecipe blockApple = new ShapedRecipe(new NamespacedKey(plugin,"blockAppleRecipe"),new ItemStack(Material.ENCHANTED_GOLDEN_APPLE,1));
        blockApple.shape("GGG","GAG","GGG");
        blockApple.setIngredient('G',Material.GOLD_BLOCK);
        blockApple.setIngredient('A',Material.APPLE);
        recipeList.add(blockApple);

        for(Recipe recipe : recipeList){
            plugin.getServer().addRecipe(recipe);
        }
    }
}
