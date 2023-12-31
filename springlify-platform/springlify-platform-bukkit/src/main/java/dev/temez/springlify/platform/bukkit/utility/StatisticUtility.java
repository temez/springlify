package dev.temez.springlify.platform.bukkit.utility;

import java.io.File;
import java.io.FileReader;
import java.util.concurrent.CompletableFuture;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * The {@code StatisticUtility} class provides utility methods for retrieving player statistics.
 * It includes methods for both synchronous and asynchronous retrieval of statistics.
 *
 * @since 0.5.9.8dev
 */
@UtilityClass
@SuppressWarnings("all")
public class StatisticUtility {

  /**
   * Retrieves the specified {@link Statistic} for the given {@link OfflinePlayer}.
   *
   * @param player    The player whose statistic is to be retrieved.
   * @param statistic The statistic to retrieve.
   * @return The value of the specified statistic for the player, or -1 if the statistic or player
   * data is not available.
   */
  @SneakyThrows
  public long getStatistic(@NotNull OfflinePlayer player, @NotNull Statistic statistic) {
    File worldFolder = new File(Bukkit.getServer().getWorlds().get(0).getWorldFolder(), "stats");
    File playerStatistics = new File(worldFolder, player.getUniqueId().toString() + ".json");
    if (playerStatistics.exists()) {
      JSONParser parser = new JSONParser();
      JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(playerStatistics));
      StringBuilder statisticNmsName = new StringBuilder("stat.");
      for (char character : statistic.name().toCharArray()) {
        if (statisticNmsName.charAt(statisticNmsName.length() - 1) == '_') {
          statisticNmsName.setCharAt(statisticNmsName.length() - 1,
              Character.toUpperCase(character));
        } else {
          statisticNmsName.append(Character.toLowerCase(character));
        }
      }
      if (jsonObject.containsKey(statisticNmsName.toString())) {
        return (long) jsonObject.get(statisticNmsName.toString());
      }
      return 0;
    }
    return -1;
  }

  /**
   * Retrieves the specified {@link Statistic} for the given {@link OfflinePlayer} asynchronously.
   *
   * @param player    The player whose statistic is to be retrieved.
   * @param statistic The statistic to retrieve.
   * @return A {@link CompletableFuture} containing the value of the specified statistic for the
   * player, or -1 if the statistic or player data is not available.
   */
  public @NotNull CompletableFuture<Long> getStatisticAsync(
      @NotNull OfflinePlayer player,
      @NotNull Statistic statistic
  ) {
    return CompletableFuture.supplyAsync(() -> getStatistic(player, statistic));
  }
}
