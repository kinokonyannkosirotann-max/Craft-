package me.craft;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomCraft extends JavaPlugin {

    @Override
    public void onEnable() {
        /* =======================================================
         * 1. 元々の「赤いきのこのMushroom Sword」（元の仕様のまま維持）
         * ======================================================= */
        ItemStack mushroomSword = new ItemStack(Material.RED_MUSHROOM);
        ItemMeta meta = mushroomSword.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§rᴍᴜsʜʀᴏᴏᴍ sᴡᴏʀᴅ");
            meta.setRarity(ItemRarity.EPIC);

            // 元々のダメージ「+7」
            NamespacedKey attackKey = new NamespacedKey(this, "mushroom_attack");
            AttributeModifier damageModifier = new AttributeModifier(
                attackKey, 
                7.0, 
                AttributeModifier.Operation.ADD_NUMBER, 
                EquipmentSlotGroup.MAINHAND
            );
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damageModifier);

            // クールダウン速度「1.6」
            NamespacedKey speedKey = new NamespacedKey(this, "mushroom_speed");
            AttributeModifier speedModifier = new AttributeModifier(
                speedKey, 
                -2.4, 
                AttributeModifier.Operation.ADD_NUMBER, 
                EquipmentSlotGroup.MAINHAND
            );
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, speedModifier);

            // 元々のエンチャント
            meta.addEnchant(Enchantment.SHARPNESS, 6, true);
            meta.addEnchant(Enchantment.UNBREAKING, 4, true);
            meta.addEnchant(Enchantment.MENDING, 2, true);

            mushroomSword.setItemMeta(meta);
        }

        // 赤いきのこのレシピ登録
        NamespacedKey key = new NamespacedKey(this, "mushroom_sword");
        ShapedRecipe recipe = new ShapedRecipe(key, mushroomSword);
        recipe.shape(
            "MMM",
            "MSM",
            "MMM"
        );
        recipe.setIngredient('M', Material.RED_MUSHROOM);
        recipe.setIngredient('S', Material.NETHERITE_SWORD);
        Bukkit.addRecipe(recipe);


        /* =======================================================
         * 2. 新機能：進化した「茶色のきのこのMushroom Sword」
         * ======================================================= */
        ItemStack upgradedMushroom = new ItemStack(Material.BROWN_MUSHROOM); // 茶色のきのこ
        ItemMeta upgradedMeta = upgradedMushroom.getItemMeta();

        if (upgradedMeta != null) {
            // 名前やレアリティはそのまま維持
            upgradedMeta.setDisplayName("§rᴍᴜsʜʀᴏᴏᴍ sᴡᴏʀᴅ");
            upgradedMeta.setRarity(ItemRarity.EPIC);

            // 【強化】元のダメージを「7」から「9.0」にアップ！
            NamespacedKey upgradeAttackKey = new NamespacedKey(this, "mushroom_upgrade_attack");
            AttributeModifier upgradeDamageModifier = new AttributeModifier(
                upgradeAttackKey, 
                9.0, 
                AttributeModifier.Operation.ADD_NUMBER, 
                EquipmentSlotGroup.MAINHAND
            );
            upgradedMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, upgradeDamageModifier);

            // クールダウン速度「1.6」はそのままキープ
            NamespacedKey upgradeSpeedKey = new NamespacedKey(this, "mushroom_upgrade_speed");
            AttributeModifier upgradeSpeedModifier = new AttributeModifier(
                upgradeSpeedKey, 
                -2.4, 
                AttributeModifier.Operation.ADD_NUMBER, 
                EquipmentSlotGroup.MAINHAND
            );
            upgradedMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, upgradeSpeedModifier);

            // 【強化】シャープネスを「7」に強化！他は維持
            upgradedMeta.addEnchant(Enchantment.SHARPNESS, 7, true);
            upgradedMeta.addEnchant(Enchantment.UNBREAKING, 4, true);
            upgradedMeta.addEnchant(Enchantment.MENDING, 2, true);

            upgradedMushroom.setItemMeta(upgradedMeta);
        }

        /* =======================================================
         * 3. 新機能：どこに置いてもいい「進化レシピ（不定形）」の登録
         * ======================================================= */
        NamespacedKey upgradeKey = new NamespacedKey(this, "mushroom_sword_upgrade");
        // ShapelessRecipeにすることで、作業台のどこに置いてもクラフト可能になります
        ShapelessRecipe upgradeRecipe = new ShapelessRecipe(upgradeKey, upgradedMushroom);

        // 必要な3つの素材を指定
        upgradeRecipe.addIngredient(Material.RED_MUSHROOM);      // クラフトした赤いきのこ（※仕様上、赤いきのこであれば受け入れます）
        upgradeRecipe.addIngredient(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE); // ネザライトのかじがた
        upgradeRecipe.addIngredient(Material.NETHERITE_INGOT);  // ネザライトインゴット

        Bukkit.addRecipe(upgradeRecipe);

        getLogger().info("Mushroom Sword プラグインが有効化されました！(赤きのこ作成 ＆ 茶きのこ進化機能搭載)");
    }

    @Override
    public void onDisable() {
        getLogger().info("Mushroom Sword プラグインが停止しました。");
    }
}
