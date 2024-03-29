package org.harvanir.gradle.gradledemo.repository.support;

import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Predicate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/** @author Harvan Irsyadi */
@NoRepositoryBean
public interface QueryDslPredicateAndProjectionExecutor<T> extends QuerydslPredicateExecutor<T> {

  <P> List<P> findAll(FactoryExpression<P> factoryExpression);

  <P> List<P> findAll(FactoryExpression<P> factoryExpression, Predicate predicate);

  <P> Page<P> findAll(FactoryExpression<P> factoryExpression, Pageable pageable);

  <P> Page<P> findAll(
      FactoryExpression<P> factoryExpression, Predicate predicate, Pageable pageable);

  <P> P findOne(FactoryExpression<P> factoryExpression, Predicate predicate);
}
