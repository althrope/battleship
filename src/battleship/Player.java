/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

/**
 *
 * @author alfath
 */
public class Player {
    private final String Name;
    private BattleField bt;
    private boolean surrender;

    public Player(String Name){
        this.Name = Name;
        surrender = false;
        bt = new BattleField();
        bt.generateField();
    }

    public void setBt(BattleField bt) {
        this.bt = bt;
    }

    public void setSurrender(boolean surrender) {
        this.surrender = surrender;
    }
    
    public boolean isSurrender() {
        return surrender;
    }
    public String getName() {
        return Name;
    }

    public BattleField getBt() {
        return bt;
    }
    
}
