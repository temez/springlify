package dev.temez.springlify.platform.condition;

import dev.temez.springlify.platform.annotation.ConditionalOnPluginEnabled;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

public class OnPluginEnabledCondition implements Condition {

  @Override
  @SuppressWarnings("DataFlowIssue")
  public boolean matches(@NotNull ConditionContext context,@NotNull AnnotatedTypeMetadata metadata) {
    Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionalOnPluginEnabled.class.getName());
    String pluginName = attributes.get("value").toString();
    return Bukkit.getPluginManager().isPluginEnabled(pluginName);
  }
}
