package dev.temez.springlify.platform.task;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public abstract class SyncTimerTask extends AbstractTask {

  long time;

  @NonFinal
  long remainingTime;


  public SyncTimerTask(@NotNull JavaPlugin plugin, int period, long time) {
    super(plugin, period);
    this.time = time;
    this.remainingTime = time;
  }

  public long getElapsedTime() {
    return time - remainingTime;
  }

  @Override
  public void doTaskCycle() {
    remainingTime -= period;
    run();
    if (remainingTime <= 0) {
      cancel();
    }
  }
}
