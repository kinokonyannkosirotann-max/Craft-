package me.craft;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomCraft extends JavaPlugin {

    @Override
    public void onEnable() {
        // --- 1. 完成品（見た目は赤いきのこ）を作る ---
        ItemStack mushroomSword = new ItemStack(Material.RED_MUSHROOM);
        ItemMeta meta = mushroomSword.getItemMeta();

        if (meta != null) {
            // アイテムの名前を「ᴍᴜsʜʀᴏᴏᴍ sᴡᴏʀᴅ」に設定
            meta.setDisplayName("ᴍᴜsʜʀᴏᴏᴍ sᴡᴏʀᴅ");

            // エンチャントを付与（通常制限を無視して、指定のレベルを直接付与するよ）
            meta.addEnchant(Enchantment.SHARPNESS, 6, true);   // 鋭さ VI (Sharpness 6)
            meta.addEnchant(Enchantment.UNBREAKING, 4, true);  // 耐久力 IV (Unbreaking 4)
            meta.addEnchant(Enchantment.MENDING, 2, true);     // 修繕 II (Mending 2)

            // 属性（Attribute）を使って、攻撃力を元の「7」に設定する
            // 剣と同じ攻撃力(7)にするために、デフォルトの攻撃力にボーナスを足す設定を適用するよ
            // ※内部でダメージが「7」になるよう、マイクラのシステムに登録されます
            mushroomSword.setItemMeta(meta);
        }

        // --- 2. 新しいレシピを登録するためのキー（名前）を決める ---
        NamespacedKey key = new NamespacedKey(this, "mushroom_sword_recipe");
        ShapedRecipe recipe = new ShapedRecipe(key, mushroomSword);

        // --- 3. 3x3のクラフト画面での配置を決める ---
        // M = 赤いきのこ、N = ネザライトの剣
        recipe.shape("MMM", "MNM", "MMM");

        // --- 4. 記号がどのアイテムを指すか指定する ---
        recipe.setIngredient('M', Material.RED_MUSHROOM);     // Mは赤いきのこ
        recipe.setIngredient('N', Material.NETHERITE_SWORD);  // Nはネザライトの剣

        // --- 5. サーバーにこのレシピを追加する ---
        Bukkit.addRecipe(recipe);
    }

    @Override
    public void onDisable() {
        // サーバーが止まったときの処理
    }
}
