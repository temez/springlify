package dev.temez.springlify.platform.task;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public abstract class AbstractTask implements Runnable {

  @NotNull JavaPlugin plugin;

  int period;

  @Getter
  @NonFinal
  int taskId;

  public void start() {
    beforeStart();
    taskId = Bukkit.getScheduler()
        .runTaskTimer(plugin, this::doTaskCycle, 0, period)
        .getTaskId();
  }

  public void cancel() {
    onCancel();
    Bukkit.getScheduler().cancelTask(taskId);
  }

  protected void doTaskCycle() {
    run();
  }

  protected void onCancel() {

  }

  protected void beforeStart() {

  }
}


