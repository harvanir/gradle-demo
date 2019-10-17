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
    Method method = signature.getMethod();
    Object[] args = proceedingJoinPoint.getArgs();

    validateArgs(method, annotation, args);

    return proceedingJoinPoint.proceed();
  }

  private void validateArgs(Method method, Annotation annotation, Object[] args) { // NOSONAR
    // optimization checking
    if (args.length > 0) {
      validateArg(method, annotation, args, 0);

      if (args.length > 1) {
        validateArg(method, annotation, args, 1);

        if (args.length > 2) {
          validateArg(method, annotation, args, 2);

          if (args.length > 3) {
            validateArg(method, annotation, args, 3);

            if (args.length > 4) {
              validateArg(method, annotation, args, 4);

              if (args.length > 5) {
                for (int i = 5; i < args.length; i++) {
                  validateArg(method, annotation, args, i);
                }
              }
            }
          }
        }
      }
    }
  }

  private void validateArg(Method method, Annotation annotation, Object[] args, int indexArgs) {
    if (shouldValidate(method, indexArgs, annotation)) {
      ValidationUtils.validate(validator, args[indexArgs]);
    }
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
