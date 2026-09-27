package com.badbones69.crazycrates.support;

// エラーの原因になる Adventure 関連のインポートを削除、または無視
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;

public class AdventureUtil {

    // MiniMessage は使わないので初期化もしない
    public static void init() {}

    /**
     * 文字列の色変換 (& -> §)
     */
    public static String replaceLegacy(String message) {
        if (message == null) return "";
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Componentを要求された場合、空のものを返す（現状はLegacy文字列を使用するため）
     */
    @NotNull
    public static Component miniMessage(String s) {
        return Component.empty();
    }

    /**
     * 文字列のリストを色変換して返す (ItemMeta.setLore 用)
     */
    @NotNull
    public static List<String> parseLore(List<String> list) {
        if (list == null) return new ArrayList<>();
        List<String> converted = new ArrayList<>();
        for (String s : list) {
            converted.add(replaceLegacy(s));
        }
        return converted;
    }

    // --- 以下、既存のメソッドがコンパイルエラーにならないための「空」の実装 ---

    @NotNull
    public static String miniMessage(Component component) {
        return "";
    }

    @NotNull
    public static List<Component> miniMessage(List<String> list) {
        return new ArrayList<>();
    }

    @NotNull
    public static List<String> miniMessage_(List<Component> components) {
        return new ArrayList<>();
    }
}
