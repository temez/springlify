package dev.temez.springlify.commander.command.executor.details;

import dev.temez.springlify.commander.annotation.EnableDetailsSupport;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.command.sender.SenderDetailsFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AnnotationSenderDetailsResolver implements SenderDetailsResolver {

  @NotNull
  ApplicationContext applicationContext;

  @Override
  public @NotNull Object resolve(@NotNull Method method, @NotNull Sender<?> sender) {
    EnableDetailsSupport annotation = method.getAnnotation(EnableDetailsSupport.class);
    if(annotation == null) {
      return sender.getPlatformSender();
    }
    SenderDetailsFactory<?> senderDetailsFactory = applicationContext.getBean(annotation.value());
    return senderDetailsFactory.getDetails(sender);
  }
}
