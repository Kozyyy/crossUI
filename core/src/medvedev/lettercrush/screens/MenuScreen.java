package medvedev.lettercrush.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.idp.engine.App;
import com.idp.engine.ui.graphics.actors.Button;
import com.idp.engine.ui.graphics.actors.Image;
import com.idp.engine.ui.graphics.actors.Text;
import com.idp.engine.ui.graphics.actors.layouts.VerticalLayout;
import com.idp.engine.ui.screens.AppScreen;
import com.idp.engine.ui.screens.layers.MainLayer;
import medvedev.lettercrush.LetterCrush;
import org.ozv.crossUI.screens.LoginScreen;

public class MenuScreen extends AppScreen {

    private Button newGame;
    private Button continueGame;
    private Button records;
    public MenuScreen() {
        super();
    }

    @Override
    public void init() {
        super.init();
        getMainLayer().setContentLayout(MainLayer.LayoutType.Vertical);
        getMainLayer().getContent().setPadding(App.dp2px(50),0,0,0);
        ((VerticalLayout)(getMainLayer().getContent())).setAlign(VerticalLayout.Align.Center);
        hideNavBar();
        getMainLayer().setBackgroundColor(Color.WHITE);


        float newGameWidth = App.dp2px(160);
        float newGameHeight = App.dp2px(36);


        newGame = new Button("НОВАЯ ИГРА");
        newGame.setSize(newGameWidth, newGameHeight);
        addActor(newGame);

        newGame.addListener(new ActorGestureListener() {
            public void tap(InputEvent event, float x, float y, int count, int button) {
                LetterCrush.setCurrentScreen(new LoadScreen());
            }
        });

        continueGame = new Button("ПРОДОЛЖИТЬ");

        continueGame.setSize(newGameWidth, newGameHeight);
        addActor(continueGame);

        continueGame.addListener(new ActorGestureListener() {
            public void tap(InputEvent event, float x, float y, int count, int button) {
                LetterCrush.setCurrentScreen(new GameScreen());
            }
        });

        records = new Button("РЕКОРДЫ");

        records.setSize(newGameWidth, newGameHeight);
        addActor(records);

        records.addListener(new ActorGestureListener() {
            public void tap(InputEvent event, float x, float y, int count, int button) {
                LetterCrush.setCurrentScreen(new GameScreen());
            }
        });

    }
}
