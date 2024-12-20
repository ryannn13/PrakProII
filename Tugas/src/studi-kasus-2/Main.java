import controller.KosController;
import views.KosView;

public class Main {
    public static void main(String[] args) {
        KosView kosView = new KosView();
        KosController kosController = new KosController(kosView);
        kosController.showView();
    }
}