package medvedev.lettercrush.screens;

import com.badlogic.gdx.files.FileHandle;
import com.idp.engine.base.AppUtils;
import com.idp.engine.ui.graphics.actors.Text;
import com.idp.engine.ui.screens.AppScreen;
import medvedev.lettercrush.LetterCrush;

public class LoadScreen extends AppScreen{

    @Override
    public void init() {
        super.init();

        Text t = new Text("LOADING");
        addActor(t);
        readFile();
    }

    public void readFile() {

        String[] s = AppUtils.files.readFileString(new FileHandle("dic")).split("\n");

        for (String ss : s) {
            System.out.println("new line: " + ss);
        }
        System.out.println("done");

        LetterCrush.backScreen();
    }
}
