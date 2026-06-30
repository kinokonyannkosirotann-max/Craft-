package me.craft;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomCraft extends JavaPlugin {

    @Override
    public void onEnable() {
        // 1. 完成品のベースアイテムは「赤いきのこ」
        ItemStack mushroomSword = new ItemStack(Material.RED_MUSHROOM);
        ItemMeta meta = mushroomSword.getItemMeta();

        if (meta != null) {
            // 2. 名前を斜めなし＆最高レアリティの紫（EPIC）にする
            meta.setDisplayName("§5§rᴍᴜsʜʀᴏᴏᴍ sᴡᴏʀᴅ");

            // 3. ダイヤの剣と同じ攻撃力「+7」を付与
            NamespacedKey attackKey = new NamespacedKey(this, "mushroom_attack");
            AttributeModifier damageModifier = new AttributeModifier(
                attackKey, 
                7.0, 
                AttributeModifier.Operation.ADD_NUMBER, 
                EquipmentSlotGroup.MAINHAND
            );
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damageModifier);

            // 4. クールダウン（攻撃速度）もダイヤの剣と全く同じ「1.6」にする
            NamespacedKey speedKey = new NamespacedKey(this, "mushroom_speed");
            AttributeModifier speedModifier = new AttributeModifier(
                speedKey, 
                -2.4, 
                AttributeModifier.Operation.ADD_NUMBER, 
                EquipmentSlotGroup.MAINHAND
            );
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, speedModifier);

            // 5. 薙ぎ払い（スイープ攻撃）のダメージをダイヤの剣と同じにする
            NamespacedKey sweepKey = new NamespacedKey(this, "mushroom_sweep");
            AttributeModifier sweepModifier = new AttributeModifier(
                sweepKey,
                1.0, 
                AttributeModifier.Operation.ADD_NUMBER, 
                EquipmentSlotGroup.MAINHAND
            );
            meta.addAttributeModifier(Attribute.GENERIC_SWEEP_ATTACK_DAMAGE, sweepModifier);

            // 6. 1.21用の正しい方法で最強エンチャントを付与（重複を完全に消しました！）
            Enchantment bSharp = Registry.ENCHANTMENT.get(NamespacedKey.minecraft("sharpness"));
            Enchantment bUnbr = Registry.ENCHANTMENT.get(NamespacedKey.minecraft("unbreaking"));
            Enchantment bMend = Registry.ENCHANTMENT.get(NamespacedKey.minecraft("mending"));

            if (bSharp != null) meta.addEnchant(bSharp, 6, true);
            if (bUnbr != null) meta.addEnchant(bUnbr, 4, true);
            if (bMend != null) meta.addEnchant(bMend, 2, true);

            mushroomSword.setItemMeta(meta);
        }

        // 7. レシピの登録（真ん中は「ネザライトの剣」）
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
        getLogger().info("Mushroom Sword プラグインが有効化されました！");
    }

    @Override
    public void onDisable() {
        getLogger().info("Mushroom Sword プラグインが停止しました。");
    }
}
