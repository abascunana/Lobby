package lobbyController;
import lobbyView.*;
import lobbyModel.Model;

public class Controller {
    private Model model;
    private View view;

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }


    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }


}
