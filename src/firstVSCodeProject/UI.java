package firstVSCodeProject;

/**
 *
 * @author Khaled
 */

public class UI {
    SpaceInvaders game;
    public UI(SpaceInvaders game)
        {this.game = game;} 
    public void score()
        { game.points++; } 
}
