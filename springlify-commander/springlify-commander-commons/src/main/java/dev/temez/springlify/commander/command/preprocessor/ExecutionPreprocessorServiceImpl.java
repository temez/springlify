package dev.temez.springlify.commander.command.preprocessor;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.exception.CommandException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ExecutionPreprocessorServiceImpl implements ExecutionPreprocessorService {

  @NotNull
  List<InvocationPreprocessor> invocationPreprocessors;

  @Override
  public void process(@NotNull CommandInvocation commandInvocation) throws CommandException {
    List<InvocationPreprocessor> invocationPreprocessors = this.invocationPreprocessors.stream()
        .sorted(Comparator.comparingInt(
            preprocessor -> Optional
                .ofNullable(preprocessor.getClass().getAnnotation(Order.class))
                .map(Order::value)
                .orElse(0)
        ))
        .toList();

    log.debug(
        "Discovered {} preprocessors: {}",
        invocationPreprocessors.size(),
        invocationPreprocessors
            .stream()
            .map(preprocessor -> preprocessor.getClass().getSimpleName())
            .collect(Collectors.joining(" "))
    );

    invocationPreprocessors.forEach(preprocessor -> preprocessor.process(commandInvocation));
  }
}
