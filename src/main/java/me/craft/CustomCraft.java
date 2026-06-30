package me.craft;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomCraft extends JavaPlugin {

    @Override
    public void onEnable() {
        // 1. ベースアイテムをダイヤの剣にする
        ItemStack mushroomSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = mushroomSword.getItemMeta();

        if (meta != null) {
            // 2. 見た目を「赤いきのこ」にする (1.21+ item_model機能)
            meta.setItemModel(NamespacedKey.minecraft("red_mushroom"));

            // 3. 斜めにならないように名前を設定
            // 1.21からはカラーコード「§r」を入れるだけで、斜体にならず真っ直ぐ表示できます！
            meta.setDisplayName("§rᴍᴜsʜʀᴏᴏᴍ sᴡᴏʀᴅ");

            // 4. レアリティを EPIC (紫色) にする
            meta.setRarity(ItemRarity.EPIC);

            // 5. 前と同じ最強エンチャントを付与
            meta.addEnchant(Enchantment.SHARPNESS, 6, true);
            meta.addEnchant(Enchantment.UNBREAKING, 4, true);
            meta.addEnchant(Enchantment.MENDING, 2, true);

            mushroomSword.setItemMeta(meta);
        }

        // 6. レシピの登録（真ん中ダイヤの剣、周り赤いきのこ）
        NamespacedKey key = new NamespacedKey(this, "mushroom_sword");
        ShapedRecipe recipe = new ShapedRecipe(key, mushroomSword);

        recipe.shape(
            "MMM",
            "MSM",
            "MMM"
        );

        recipe.setIngredient('M', Material.RED_MUSHROOM);
        recipe.setIngredient('S', Material.DIAMOND_SWORD);

        Bukkit.addRecipe(recipe);
        getLogger().info("Mushroom Sword プラグインが有効化されました！(ベース: ダイヤの剣)");
    }

    @Override
    public void onDisable() {
        getLogger().info("Mushroom Sword プラグインが停止しました。");
    }
}
