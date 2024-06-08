package dev.temez.springlify.commander.command;

/**
 * Enumerates the types of commands supported by the Commander framework.
 * <p>
 * Each command can be categorized into one of the supported types: in-game commands,
 * shared commands (usable by both in-game and console), or console commands.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
public enum CommandType {

  /**
   * Represents an in-game command, usable only by players in the game.
   */
  INGAME,

  /**
   * Represents a shared command, usable by both players in-game and from the console.
   */
  SHARED,

  /**
   * Represents a console command, usable only from the server console.
   */
  CONSOLE
}
