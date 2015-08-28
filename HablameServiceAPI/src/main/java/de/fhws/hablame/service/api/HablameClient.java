package de.fhws.hablame.service.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import de.fhws.hablame.service.api.util.function.BiConsumer;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Kristoffer Schneider kschneider@codingfalx.de
 * @created 13.08.2015
 */
public class HablameClient
        implements IHablameClient
{
  // <editor-fold desc="Static">

  static
  {
    HablameClient.logger = Logger.getLogger( "HablameClientLogger" );
    HablameClient.logger.setLevel( Level.CONFIG );
  }

  public static final String DEFAULT_BASE_ADDRESS = "http://194.95.221.229:8080/Hablame-BotBackend";
  private static Logger logger;

  //</editor-fold>

  //<editor-fold desc="Fields">
  private String baseAddress;

  //</editor-fold>

  //<editor-fold desc="Constructor/finalize">

  /**
   * @param baseAddress
   *         the base service-address used by this serviceClient
   */
  public HablameClient ( String baseAddress )
  {
    this.baseAddress = baseAddress;
  }

  /**
   * uses the default base address
   */
  public HablameClient()
  {
    this( HablameClient.DEFAULT_BASE_ADDRESS );
  }

  @Override
  public void finalize () throws Throwable
  {
    super.finalize();
    try
    {
      Unirest.shutdown();
    }
    catch ( IOException exc )
    {
      HablameClient.logger.log( Level.SEVERE, "thrown while being within finalize", exc );
      throw exc;
    }

  }

  //</editor-fold>

  //<editor-fold desc="Methods">

  @Override
  public final void getReplyForMessageAsync ( String message, final BiConsumer<Integer, String> callback )
  {
    HttpRequestWithBody request = this.createReplyForMessageRequest( message );
    request.asStringAsync( new Callback<String>()
    {
      public void cancelled ()
      {
        callback.accept( -1, null );
      }

      public void completed ( HttpResponse<String> httpResponse )
      {
        callback.accept( httpResponse.getStatus(), httpResponse.getBody() );
      }

      public void failed ( UnirestException e )
      {
        callback.accept( -1, null );
      }
    } );
  }

  @Override
  public final Future<HttpResponse<String>> getReplyForMessageAsync ( String message )
  {
    return this.createReplyForMessageRequest( message ).asStringAsync();
  }

  @Override
  public final Future<HttpResponse<String>> stopConversationAndBot ()
  {
    return this.createStopMessageAndConversationRequest().asStringAsync();
  }

  @Override
  public final void stopConversationAndBot ( final BiConsumer<Integer, String> callback )
  {
    GetRequest request = this.createStopMessageAndConversationRequest();
    request.asStringAsync( new Callback<String>() {
      @Override
      public void completed ( HttpResponse<String> httpResponse )
      {
        callback.accept( httpResponse.getStatus(), httpResponse.getBody() );
      }

      @Override
      public void failed ( UnirestException e )
      {
        callback.accept( -1, null );
      }

      @Override
      public void cancelled ()
      {
        callback.accept( -1, null );
      }
    } );
  }

  private HttpRequestWithBody createReplyForMessageRequest ( String message )
  {
    HttpRequestWithBody request = Unirest.post( this.baseAddress + IHablameClient.CONVERSATION_RESOURCE );
    request.body( message );

    return request;
  }

  private GetRequest createStopMessageAndConversationRequest ()
  {
    GetRequest request = Unirest.get( this.baseAddress + IHablameClient.BOT_SHUTDOWN_RESOURCE );
    return request;
  }

  //</editor-fold>
}
