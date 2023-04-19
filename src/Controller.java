public class Controller {
    private Model model;
    private View view;

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }


    Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }


}
