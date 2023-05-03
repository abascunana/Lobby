import lobbyController.Controller;
import gameParam.GameMode;
import gameParam.GameRules;
import lobbyModel.Model;
import lobbyView.View;

public class Main {
    public static void main(String[] args) {
        //Default starting values
        GameRules gameRules = new GameRules(8,10,5,10,2,true,5,5,false, GameMode.TeamDeathmatch,4,4,4,4);
        Model model = new Model(gameRules);
        View view = new View();
        Controller controller = new Controller(model, view);
        model.setController(controller);
        view.setController(controller);
        new Thread(model).start();

    }

}
