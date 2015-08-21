package de.fhws.hablame.service.api.test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.request.HttpRequestWithBody;
import de.fhws.hablame.service.api.HablameClient;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.Future;

/**
 * @author Kristoffer Schneider kschneider@codingfalx.de
 * @created 21.08.2015
 */
public class HablameClientTest
        extends TestCase
{

  private HablameClient testClient = null;

  public void setUp () throws Exception
  {
    super.setUp();

    this.testClient = new HablameClient();
  }

  public void testGetReplyForMessageAsync () throws Exception
  {
    Future<HttpResponse<String>> future = this.testClient.getReplyForMessageAsync( "Wie ist das wetter in wuerzburg" );
    assertNotNull( future );
    HttpResponse<String> response = future.get();
    assertNotNull( response );
    String answer = response.getBody();
    assertNotNull( answer );

    System.out.println( answer );
    System.out.flush();
  }

  public void testGetReplyForMessageAsync1ByCallback () throws Exception
  {
    Object obj = new Object();

    this.testClient.getReplyForMessageAsync( "Wie ist das Wetter in Wuerzburg", ( Integer status, String answer ) ->
    {
      assertNotNull( status );
      assertEquals( (int) status, 200 );
      assertNotNull( answer );
      System.out.println( answer );
      System.out.flush();

      synchronized ( obj )
      {
        obj.notifyAll();
      }
    } );

    synchronized ( obj )
    {
      obj.wait( 10000 );
    }
  }

  public void testStopConversationAndBot() throws Exception
  {
    Future<HttpResponse<String>> future = this.testClient.stopConversationAndBot ( );
    assertNotNull( future );
    HttpResponse<String> response = future.get();
    assertNotNull( response );
    String answer = response.getBody();
    assertNotNull( answer );

    System.out.println( answer );
    System.out.flush();
  }

  public void testStopConversationAndBotCallback () throws Exception
  {
    Object obj = new Object();

    this.testClient.stopConversationAndBot( ( Integer status, String answer ) ->
    {
      assertNotNull( status );
      assertEquals( (int) status, 200 );
      assertNotNull( answer );
      System.out.println( answer );
      System.out.flush();

      synchronized ( obj )
      {
        obj.notifyAll();
      }
    } );

    synchronized ( obj )
    {
      obj.wait( 10000 );
    }
  }
}