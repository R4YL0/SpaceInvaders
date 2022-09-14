package firstVSCodeProject;

/**
 *
 * @author R4YL0
 */

public class UI {
    SpaceInvaders game;
    public UI(SpaceInvaders game)
        {this.game = game;} 
    public void score()
        { game.points++; } 
}
