package il.client;

import io.github.palexdev.materialfx.beans.NumberRange;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import io.github.palexdev.materialfx.effects.Interpolators;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.AnimationUtils;
import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class HomeController {

    private MainPageController main_page_holder;

    @FXML
    private MFXProgressSpinner determinateSpinner;

    private static HomeController instance = null;

    @FXML
    private Label guestUserText;

    @FXML
    private Label lilachEntrance1;

    public static HomeController getInstance(){
        if(instance == null){
            System.out.println("Error Occured");
        }
        return instance;
    }

    public void RefreshHome() throws IOException {
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader();
        MainPageController.getInstance().getMain_first_load_pane().getChildren().clear();
        var = getClass().getResource("Home.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
//        root_map.put("Home", root);
//        controller_map.put("Home", controller);
        MainPageController.getInstance().getMain_first_load_pane().getChildren().addAll(root);
    }

    public void setMain_page_holder(MainPageController main_page_holder) {
        this.main_page_holder = main_page_holder;
    }

    @FXML
    void initialize(){
        instance = this;
        determinateSpinner.getRanges1().add(NumberRange.of(0.0, 0.30));
        determinateSpinner.getRanges2().add(NumberRange.of(0.31, 0.60));
        determinateSpinner.getRanges3().add(NumberRange.of(0.61, 1.0));
        createAndPlayAnimation(determinateSpinner);
        if(this.determinateSpinner.getProgress()==100){
            this.determinateSpinner.setVisible(false);
        }
        if(UserClient.getInstance().getPriority() == 1){
            this.guestUserText.setText("Dear Guest, You can watch our products on the catalog section. Enjoy!");
        }else if(UserClient.getInstance().getPriority() == 2){
            this.guestUserText.setText("Hello, " + UserClient.getInstance().getName() +". Good to see you again.");
        }else if(UserClient.getInstance().getPriority() > 2){
            this.guestUserText.setText("Hello " + UserClient.getInstance().getUserName() +", Have a good day.");
        }
    }

    private void createAndPlayAnimation(ProgressIndicator indicator) {
        Animation a1 = AnimationUtils.TimelineBuilder.build()
                .add(
                        AnimationUtils.KeyFrames.of(2000, indicator.progressProperty(), 0.3, Interpolators.INTERPOLATOR_V1),
                        AnimationUtils.KeyFrames.of(4000, indicator.progressProperty(), 0.6, Interpolators.INTERPOLATOR_V1),
                        AnimationUtils.KeyFrames.of(6000, indicator.progressProperty(), 1.0, Interpolators.INTERPOLATOR_V1)
                )
                .getAnimation();

        Animation a2 = AnimationUtils.TimelineBuilder.build()
                .add(
                        AnimationUtils.KeyFrames.of(1000, indicator.progressProperty(), 100, Interpolators.INTERPOLATOR_V2)
                )
                .getAnimation();

        a1.setOnFinished(end -> AnimationUtils.PauseBuilder.build()
                .setDuration(Duration.seconds(1))
                .setOnFinished(event -> a2.playFromStart())
                .getAnimation()
                .play()

        );
//        a2.setOnFinished(end -> AnimationUtils.PauseBuilder.build()
//                .setDuration(Duration.seconds(1))
//                .setOnFinished(event -> a1.playFromStart())
//                .getAnimation()
//                .play()
//        );

        a1.play();
    }

    /* gets and sets*/

    public void setDeterminateSpinner100() {
         determinateSpinner.setProgress(100.0);
    }

    public void setDeterminateSpinnerDisable() {
        this.determinateSpinner.setDisable(true);
    }

    /* end gets and sets*/
}