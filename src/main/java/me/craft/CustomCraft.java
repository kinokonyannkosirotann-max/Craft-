package me.craft;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
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
            // 2. 見た目を「赤いきのこ」にする (1.21+ 新機能)
            meta.setItemModel(NamespacedKey.minecraft("red_mushroom"));

            // 3. 名前を斜体なし＆レアリティEPICの「紫色」にする
            // 「§5」がエピックの紫色、「§r」が斜め文字を真っ直ぐにリセットする記号だよ！
            meta.setDisplayName("§5§rᴍᴜsʜʀᴏᴏᴍ sᴡᴏʀᴅ");

            // 4. 1.21用の新しい方法で最強エンチャントを取得して付与
            Enchantment sharpness = Registry.ENCHANTMENT.get(NamespacedKey.minecraft("sharpness"));
            Enchantment unbreaking = Registry.ENCHANTMENT.get(NamespacedKey.minecraft("unbreaking"));
            Enchantment mending = Registry.ENCHANTMENT.get(NamespacedKey.minecraft("mending"));

            if (sharpness != null) meta.addEnchant(sharpness, 6, true);
            if (unbreaking != null) meta.addEnchant(unbreaking, 4, true);
            if (mending != null) meta.addEnchant(mending, 2, true);

            mushroomSword.setItemMeta(meta);
        }

        // 5. レシピの登録（真ん中ダイヤの剣、周り赤いきのこ）
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
        getLogger().info("Mushroom Sword プラグインが有効化されました！(1.21対応版)");
    }

    @Override
    public void onDisable() {
        getLogger().info("Mushroom Sword プラグインが停止しました。");
    }
}
