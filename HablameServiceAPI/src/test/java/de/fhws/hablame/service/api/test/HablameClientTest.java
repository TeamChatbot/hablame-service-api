package de.fhws.hablame.service.api.test;

import com.mashape.unirest.http.HttpResponse;
import de.fhws.hablame.service.api.util.function.BiConsumer;
import de.fhws.hablame.service.api.HablameClient;
import junit.framework.TestCase;

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
    final Object obj = new Object();

    this.testClient.getReplyForMessageAsync( "Wie ist das Wetter in Wuerzburg", new BiConsumer<Integer, String>() {

      @Override
      public void accept ( Integer status, String answer )
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
    final Object obj = new Object();

    this.testClient.stopConversationAndBot( new BiConsumer<Integer, String>( ) {
      @Override
      public void accept ( Integer status, String answer )
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
      }
    });

    synchronized ( obj )
    {
      obj.wait( 10000 );
    }
  }
}