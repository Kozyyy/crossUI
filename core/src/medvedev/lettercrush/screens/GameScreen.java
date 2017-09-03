package medvedev.lettercrush.screens;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.idp.engine.base.AppUtils;
import com.idp.engine.base.IdpFiles;
import com.idp.engine.ui.graphics.actors.Button;
import com.idp.engine.ui.screens.AppScreen;
import com.idp.engine.ui.screens.layers.MainLayer;

public class GameScreen extends AppScreen{

    GameScreen() {
        super("GAME1");
    }

    @Override
    public void init() {
        super.init();

        getMainLayer().setContentLayout(MainLayer.LayoutType.Absolute);
        this.hideNavBar();


        Button n = new Button("");
        n.setSize(100,100);
        addActor(n);
        //readFile();


    }
    public void readFile() {

        String[] s = AppUtils.files.readFileString(new FileHandle("dic")).split("\n");

        for (String ss : s) {
            System.out.println("new line: " + ss);
        }
    }
}
