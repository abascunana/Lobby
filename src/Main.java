public class Main {
    public static void main(String[] args) {

        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model,view);
        model.setController(controller);
        view.setController(controller);
        new Thread(model).start();

    }

}
