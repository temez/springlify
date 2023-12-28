package dev.temez.springlify.commander.commons.initializer;

import dev.temez.springlify.commander.commons.execution.preprocessor.ExecutionPreprocessor;
import dev.temez.springlify.commander.commons.execution.preprocessor.chain.ExecutionPreprocessorChain;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExecutionPreprocessorInitializer implements BeanPostProcessor {

  @NotNull ExecutionPreprocessorChain executionPreprocessorChain;

  @Lazy
  public ExecutionPreprocessorInitializer(
      @NotNull ExecutionPreprocessorChain executionPreprocessorChain) {
    this.executionPreprocessorChain = executionPreprocessorChain;
  }

  @Override
  public Object postProcessBeforeInitialization(@NotNull Object bean, @NotNull String beanName) {
    if (bean instanceof ExecutionPreprocessor preprocessor) {
      executionPreprocessorChain.register(preprocessor);
      log.info("Registered {} as execution preprocessor.", bean.getClass().getSimpleName());
    }
    return bean;
  }
}