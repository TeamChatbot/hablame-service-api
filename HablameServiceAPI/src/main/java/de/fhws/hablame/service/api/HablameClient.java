package de.fhws.hablame.service.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.apache.http.HttpStatus;

import java.util.List;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 * @author Kristoffer Schneider kschneider@codingfalx.de
 * @created 13.08.2015
 */
public class HablameClient
        implements IHablameClient
{
  // <editor-fold desc="Static">

  private static Logger logger;

  static
  {
    HablameClient.logger = Logger.getLogger( "HablameClientLogger" );
    HablameClient.logger.setLevel( Level.CONFIG );
  }

  //</editor-fold>

  //<editor-fold desc="Fields">

  private String baseAddress;

  //</editor-fold>

  //<editor-fold desc="Constructor">

  /**
   *
   * @param baseAddress the base service-address used by this serviceClient
   */
  public HablameClient ( String baseAddress )
  {
    this.baseAddress = baseAddress;
  }

  //</editor-fold>

  //<editor-fold desc="Methods">

  private GetRequest createReplyForMessageRequest ( String message )
  {
    final String PARAM_NAME = "message";

    GetRequest request = Unirest.get( this.baseAddress + IHablameClient.START_CONVERSATION_RESOURCE );
    request.queryString( PARAM_NAME, message );

    return request;
  }

  @Override
  public final Future<HttpResponse<String>> getReplyForMessageAsync ( String message )
  {
    return this.createReplyForMessageRequest( message ).asStringAsync();
  }

  @Override
  public final void getReplyForMessageAsync ( String message, final BiConsumer<Integer, String> callback )
  {
    final String BODY_PARAM_NAME = "message";

    GetRequest request = this.createReplyForMessageRequest( message );
    request.asStringAsync( new Callback<String>() {
      public void completed ( HttpResponse<String> httpResponse )
      {
        callback.accept( httpResponse.getStatus(), httpResponse.getBody() );
      }

      public void failed ( UnirestException e )
      {
        callback.accept( -1, null );
      }

      public void cancelled ()
      {
        callback.accept( -1, null );
      }
    });
  }

  //</editor-fold>
}
