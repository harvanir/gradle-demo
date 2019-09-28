package org.harvanir.gradle.gradledemo.validation.support;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.validation.Valid;
import javax.validation.Validator;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.validation.annotation.Validated;

/** @author Harvan Irsyadi */
@Aspect
public class ValidationAspect {

  private Validator validator;

  private Set<Key> keySet = ConcurrentHashMap.newKeySet();

  public ValidationAspect(Validator validator) {
    this.validator = validator;
  }

  private boolean shouldValidate(Method method, int indexArg, Annotation annotation) {
    Key key = new Key(method, indexArg, annotation.getClass());

    if (keySet.contains(key)) {
      return true;
    } else {
      Annotation[] annotations = method.getParameterAnnotations()[indexArg];

      for (Annotation annotate : annotations) {
        if (annotate.equals(annotation)) {
          keySet.add(key);
          return true;
        }
      }
    }

    return false;
  }

  private Object validateAndProceed(ProceedingJoinPoint proceedingJoinPoint, Annotation annotation)
      throws Throwable { // NOSONAR
    MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
    Object[] args = proceedingJoinPoint.getArgs();
    Method method = signature.getMethod();

    for (int i = 0; i < args.length; i++) {
      if (shouldValidate(method, i, annotation)) {
        ValidationUtils.validate(validator, args[i]);
      }
    }

    return proceedingJoinPoint.proceed();
  }

  @Around("@annotation(validated)")
  public Object aroundValidated(ProceedingJoinPoint proceedingJoinPoint, Validated validated)
      throws Throwable { // NOSONAR
    return validateAndProceed(proceedingJoinPoint, validated);
  }

  @Around("@annotation(valid)")
  public Object aroundValidation(ProceedingJoinPoint proceedingJoinPoint, Valid valid)
      throws Throwable { // NOSONAR
    return validateAndProceed(proceedingJoinPoint, valid);
  }

  @Getter
  @Setter
  static class Key {

    private Method method;

    private Class<? extends Annotation> annotation;

    private Integer index;

    Key(Method method, Integer index, Class<? extends Annotation> annotation) {
      this.method = method;
      this.index = index;
      this.annotation = annotation;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Key key = (Key) o;
      return Objects.equals(method, key.method)
          && Objects.equals(annotation, key.annotation)
          && Objects.equals(index, key.index);
    }

    @Override
    public int hashCode() {
      return Objects.hash(method, annotation, index);
    }
  }
}
