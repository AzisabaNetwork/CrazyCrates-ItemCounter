package com.badbones69.crazycrates.api;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountsManager {

    private static File folder;

    /**
     * 初期化処理。plugins/CrazyCrates/counts/ フォルダを作成します。
     */
    public static void init(File dataFolder) {
        folder = new File(dataFolder, "counts");
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    /**
     * 指定したクレートのデータファイルをロードします。
     */
    private static File getFile(String crateName) {
        return new File(folder, crateName + ".yml");
    }

    private static FileConfiguration getConfig(String crateName) {
        return YamlConfiguration.loadConfiguration(getFile(crateName));
    }

    /**
     * アイテムの累計当選回数を取得します。
     */
    public static int getCount(String crateName, String prizeName) {
        if (crateName == null || prizeName == null) return 0;
        FileConfiguration config = getConfig(crateName);
        return config.getInt("Items." + prizeName, 0);
    }

    /**
     * カウントを1増やして即時保存します。
     */
    public static void addCount(String crateName, String prizeName) {
        if (crateName == null || prizeName == null) return;

        File file = getFile(crateName);
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        int current = config.getInt("Items." + prizeName, 0);
        config.set("Items." + prizeName, current + 1);

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lore（説明文）内のプレースホルダー %count% を実際の数字に置き換えます。
     */
    public static List<String> replacePlaceholders(List<String> lore, String crateName, String prizeName) {
        if (lore == null) return new ArrayList<>();

        String countValue = String.valueOf(getCount(crateName, prizeName));
        List<String> newLore = new ArrayList<>();

        for (String line : lore) {
            // %count% または %Count% を置換
            newLore.add(line.replace("%count%", countValue).replace("%Count%", countValue));
        }
        return newLore;
    }
}