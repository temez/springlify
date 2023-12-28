package dev.temez.springlify.commander.commons.execution.preprocessor.chain;

import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.execution.CommandExecution;
import dev.temez.springlify.commander.commons.execution.preprocessor.ExecutionPreprocessor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ExecutionPreprocessorChainImpl implements ExecutionPreprocessorChain {

  @NotNull List<ExecutionPreprocessor> preprocessorList = new ArrayList<>();

  @Override
  public void register(
      @NotNull ExecutionPreprocessor executionPreprocessor) {
    preprocessorList.add(executionPreprocessor);
    preprocessorList.sort(Comparator.comparingInt(
        preprocessor -> Optional
            .ofNullable(preprocessor.getClass().getAnnotation(Order.class))
            .map(Order::value)
            .orElse(0)
    ));
  }

  @Override
  public void process(@NotNull CommandExecution execution) throws ConformableException {
    preprocessorList.forEach(preprocessor -> preprocessor.process(execution));
  }
}
