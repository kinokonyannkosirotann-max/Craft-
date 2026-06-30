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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomCraft extends JavaPlugin {

    @Override
    public void onEnable() {
        // 1. 【解決】ベースを「赤いきのこ」にすることで、リソースパックなしでも100%きのこの見た目にする！
        ItemStack mushroomSword = new ItemStack(Material.RED_MUSHROOM);
        ItemMeta meta = mushroomSword.getItemMeta();

        if (meta != null) {
            // 2. 斜めにならないように真っ直ぐな名前にする
            meta.setDisplayName("§rᴍᴜsʜʀᴏᴏᴍ sᴡᴏʀᴅ");

            // 3. レアリティを EPIC (紫色) にする
            meta.setRarity(ItemRarity.EPIC);

            // 4. 【能力維持】きのこなのに「ダイヤの剣と同じ攻撃力（+7）」をプログラムで付与！
            NamespacedKey attackKey = new NamespacedKey(this, "mushroom_attack");
            AttributeModifier damageModifier = new AttributeModifier(
                attackKey, 
                7.0, 
                AttributeModifier.Operation.ADD_NUMBER, 
                EquipmentSlotGroup.MAINHAND
            );
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damageModifier);

            // 5. 【能力維持】クールダウン（攻撃速度）もダイヤの剣と全く同じ「1.6」にする
            NamespacedKey speedKey = new NamespacedKey(this, "mushroom_speed");
            AttributeModifier speedModifier = new AttributeModifier(
                speedKey, 
                -2.4, 
                AttributeModifier.Operation.ADD_NUMBER, 
                EquipmentSlotGroup.MAINHAND
            );
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, speedModifier);

            // 6. 【能力維持】薙ぎ払い（スイープ攻撃）のダメージ能力も解放
            NamespacedKey sweepKey = new NamespacedKey(this, "mushroom_sweep");
            AttributeModifier sweepModifier = new AttributeModifier(
                sweepKey,
                1.0, 
                AttributeModifier.Operation.ADD_NUMBER, 
                EquipmentSlotGroup.MAINHAND
            );
            meta.addAttributeModifier(Attribute.GENERIC_SWEEP_ATTACK_DAMAGE, sweepModifier);

            // 7. 最強エンチャントを安全に付与
            meta.addEnchant(Enchantment.SHARPNESS, 6, true);
            meta.addEnchant(Enchantment.UNBREAKING, 4, true);
            meta.addEnchant(Enchantment.MENDING, 2, true);

            mushroomSword.setItemMeta(meta);
        }

        // 8. 【修正】クラフトレシピの真ん中を「ネザライトの剣」にする
        NamespacedKey key = new NamespacedKey(this, "mushroom_sword");
        ShapedRecipe recipe = new ShapedRecipe(key, mushroomSword);

        recipe.shape(
            "MMM",
            "MSM",
            "MMM"
        );

        recipe.setIngredient('M', Material.RED_MUSHROOM);
        recipe.setIngredient('S', Material.NETHERITE_SWORD); // 真ん中はネザライトの剣！

        Bukkit.addRecipe(recipe);
        getLogger().info("Mushroom Sword プラグインが有効化されました！(見た目きのこ・ネザライトレシピ版)");
    }

    @Override
    public void onDisable() {
        getLogger().info("Mushroom Sword プラグインが停止しました。");
    }
}
