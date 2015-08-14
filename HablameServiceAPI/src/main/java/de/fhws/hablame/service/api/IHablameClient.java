package de.fhws.hablame.service.api;

import com.mashape.unirest.http.HttpResponse;

import java.util.concurrent.Future;
import java.util.function.BiConsumer;

/**
 * @author Kristoffer Schneider
 * @created 14.08.2015
 */
public interface IHablameClient
{

  /**
   * Path to the resource to start a converation
   */
  String START_CONVERSATION_RESOURCE = "/conversation";

  /**
   * Requests the answer for the given message.
   * @param message message to send to the service
   * @return Future to the service' response
   */
  Future<HttpResponse<String>> getReplyForMessageAsync ( String message );

  /**
   * Requests the answer for the given message.
   * @param message message to send to the service
   * @param callback callback to call when a service response is available. First param is the Http-status code, second
   *                 the response text
   */
  void getReplyForMessageAsync ( String message, BiConsumer<Integer, String> callback );
}
