package com.trxsh.caninesmp.crafting;

import com.trxsh.caninesmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CraftingRecipes {

    public static NamespacedKey goldenApple;
    public static NamespacedKey cobweb;

    public static void add() {

        NamespacedKey key = new NamespacedKey(Main.Instance, "golden_apple_cheap");
        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.GOLDEN_APPLE));

        recipe.addIngredient(4, Material.GOLD_INGOT);
        recipe.addIngredient(1, Material.APPLE);

        NamespacedKey key1 = new NamespacedKey(Main.Instance, "cobweb_cheap");
        ShapelessRecipe recipe1 = new ShapelessRecipe(key1, new ItemStack(Material.GOLDEN_APPLE));

        recipe1.addIngredient(5, Material.STRING);

        goldenApple = key;
        cobweb = key1;

        Bukkit.addRecipe(recipe);
        Bukkit.addRecipe(recipe1);

    }

    public static void remove() {

        if(goldenApple != null)
            Bukkit.removeRecipe(goldenApple);

        if(cobweb != null)
            Bukkit.removeRecipe(cobweb);

    }

}
