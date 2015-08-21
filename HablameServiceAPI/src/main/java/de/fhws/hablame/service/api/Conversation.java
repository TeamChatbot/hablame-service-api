package de.fhws.hablame.service.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Kristoffer Schneider kschneider@codingfalx.de
 * @created 14.08.2015
 */
public class Conversation
{
  //<editor-fold desc="Fields">

  public static final String DEFAULT_LOCAL_ALIAS = "Local:";
  public static final String DEFAULT_REMOTE_ALIAS = "Remote:";

  private final String localAlias;
  private final String remoteAlias;

  private ArrayList<ConversationEntry> conversationHistory;

  //</editor-fold>

  //<editor-fold desc="Constructor">

  /**
   * Constructs a new Converation using the default local aliases.
   *
   * @see Conversation.DEFAULT_LOCAL_ALIAS
   * @see Conversation.DEFAULT_REMOTE_ALIAS
   */
  public Conversation ()
  {
    this( Conversation.DEFAULT_LOCAL_ALIAS, Conversation.DEFAULT_REMOTE_ALIAS );
  }

  /**
   * Constrcuts a new Converation using the fiven aliases.
   *
   * @param localAlias
   *         alias for local messages
   * @param remoteAlias
   *         alias for remote messages
   */
  public Conversation ( String localAlias, String remoteAlias )
  {
    this.localAlias = localAlias;
    this.remoteAlias = remoteAlias;

    this.conversationHistory = new ArrayList<ConversationEntry>();
  }

  /**
   * Adds a new message to this Conversation using the local alias.
   *
   * @param message
   *         message to add
   */
  public void addMessageFromLocal ( String message )
  {
    this.conversationHistory.add( new ConversationEntry( this.localAlias, message, new Date() ) );
  }

  /**
   * Adds a new message to this Conversation using the remote alias.
   *
   * @param message
   *         message to add
   */
  public void addMessageFromRemote ( String message )
  {
    this.conversationHistory.add( new ConversationEntry( this.remoteAlias, message, new Date() ) );
  }

  //</editor-fold>

  //<editor-fold desc="Methods">

  /**
   * @return the used local alias
   */
  public String getLocalAlias ()
  {
    return localAlias;
  }

  /**
   * @return an unmodifiable List containing all ConversationEntries
   *
   * @see ConversationEntry
   * @see java.util.Collections.UnmodifiableList
   */
  public List<ConversationEntry> getReadOnlyConverationHistory ()
  {
    return Collections.unmodifiableList( this.conversationHistory );
  }

  /**
   * @return the used remote alias
   */
  public String getRemoteAlias ()
  {
    return remoteAlias;
  }

  //</editor-fold>

  public static class ConversationEntry
  {
    public final String Alias;
    public final Date Date;
    public final String Message;

    public ConversationEntry ( String alias, String message, Date date )
    {
      this.Alias = alias;
      this.Message = message;
      this.Date = date;
    }
  }
}
