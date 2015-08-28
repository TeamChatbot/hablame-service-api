package de.fhws.hablame.service.api.util.function;

/**
 * This interface is used, since Android does not support JDK 8 and lambdas at the moment!
 *
 * @author Kristoffer Schneider kschneider@codingfalx.de
 * @created 28.08.2015
 */
public interface BiConsumer <T, K>
{
  void accept ( T t, K k );
}
