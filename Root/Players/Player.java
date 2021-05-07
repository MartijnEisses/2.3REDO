package Players;

public class Player {

    private String ign;
    private playertype PlayerType;


    public Player(String name, playertype type){
        this.ign = name;
        this.PlayerType = type;
    }

    public void setPlayerType(playertype playerType) {
        PlayerType = playerType;
    }

    public playertype getPlayerType() {
        return PlayerType;
    }

    public String getIgn() {
        return ign;
    }

    public void setIgn(String ign) {
        this.ign = ign;
    }
}
