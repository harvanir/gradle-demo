package org.harvanir.gradle.gradledemo.repository.support;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

/** @author Harvan Irsyadi */
public class QueryDslJpaEnhancedRepositoryImpl<T, K extends Serializable>
    extends QuerydslJpaRepository<T, K> implements QueryDslPredicateAndProjectionExecutor<T> {

  private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER =
      SimpleEntityPathResolver.INSTANCE;

  private final EntityPath<T> path;

  private final Querydsl querydsl;

  public QueryDslJpaEnhancedRepositoryImpl(
      JpaEntityInformation<T, K> entityInformation, EntityManager entityManager) {
    this(entityInformation, entityManager, DEFAULT_ENTITY_PATH_RESOLVER);
  }

  public QueryDslJpaEnhancedRepositoryImpl(
      JpaEntityInformation<T, K> entityInformation,
      EntityManager entityManager,
      EntityPathResolver resolver) {
    super(entityInformation, entityManager, resolver);
    this.path = resolver.createPath(entityInformation.getJavaType());
    PathBuilder<T> builder = new PathBuilder<>(path.getType(), path.getMetadata());
    this.querydsl = new Querydsl(entityManager, builder);
  }

  @Override
  public <P> List<P> findAll(FactoryExpression<P> factoryExpression) {
    return querydsl.createQuery(path).select(factoryExpression).fetch();
  }

  @Override
  public <P> List<P> findAll(FactoryExpression<P> factoryExpression, Predicate predicate) {
    return querydsl.createQuery(path).select(factoryExpression).where(predicate).fetch();
  }

  @Override
  public <P> Page<P> findAll(FactoryExpression<P> factoryExpression, Pageable pageable) {
    JPQLQuery<P> query = querydsl.createQuery(path).select(factoryExpression);
    query = querydsl.applyPagination(pageable, query);

    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }

  @Override
  public <P> Page<P> findAll(
      FactoryExpression<P> factoryExpression, Predicate predicate, Pageable pageable) {
    JPQLQuery<P> query = querydsl.createQuery(path).select(factoryExpression).where(predicate);

    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }

  @Override
  public <P> P findOne(FactoryExpression<P> factoryExpression, Predicate predicate) {
    return querydsl.createQuery(path).select(factoryExpression).where(predicate).fetchOne();
  }
}
