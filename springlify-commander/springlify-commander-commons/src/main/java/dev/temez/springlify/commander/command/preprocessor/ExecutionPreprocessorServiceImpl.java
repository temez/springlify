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

/**
 * Implementation of {@link ExecutionPreprocessorService} for pre-processing command executions.
 * <p>
 * This service orchestrates the pre-processing of command invocations by delegating to registered {@link InvocationPreprocessor} instances.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ExecutionPreprocessorServiceImpl implements ExecutionPreprocessorService {

  @NotNull
  List<InvocationPreprocessor> invocationPreprocessors;

  /**
   * Processes the given command invocation by executing pre-processing steps.
   *
   * @param commandInvocation The command invocation to preprocess.
   * @throws CommandException If an error occurs during the pre-processing.
   */
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
