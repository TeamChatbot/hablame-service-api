package de.fhws.hablame.service.api;

import com.mashape.unirest.http.HttpResponse;

import java.util.concurrent.Future;
import java.util.function.BiConsumer;

/**
 * This interface defines the contract, each client needs to full fill, if it
 * wants to speak with the Hablame Botbackend.
 *
 * @author Kristoffer Schneider kschneider@codingfalx.de
 * @created 14.08.2015
 */
public interface IHablameClient
{
  /**
   * Path to the resource to start a converation
   */
  String CONVERSATION_RESOURCE = "/conversation";

  /**
   * Path to the resource to stop a bot.
   * IMPORTANT: Currently it's just a test feature!
   */
  String BOT_SHUTDOWN_RESOURCE = "/stopconversation";

  /**
   * Requests the answer for the given message.
   *
   * @param message
   *         message to send to the service
   *
   * @return Future to the service' response
   */
  Future<HttpResponse<String>> getReplyForMessageAsync ( String message );

  /**
   * Requests the answer for the given message.
   *
   * @param message
   *         message to send to the service
   * @param callback
   *         callback to call when a service response is available. First param is the Http-status code, second
   *         the response text
   */
  void getReplyForMessageAsync ( String message, BiConsumer<Integer, String> callback );

  /**
   * Requests to stop the conversation and the running bot.
   *
   * @return informations about stopping the conversation and bot
   */
  Future<HttpResponse<String>> stopConversationAndBot ();

  /**
   * Requests to stop the conversation and the running bot.
   *
   * @param callback
   *         is called when the service response is available. First param is the Http-Status from
   *         the service backend. Seconds parameter is the response text.
   */
  void stopConversationAndBot( BiConsumer<Integer, String> callback );
}

