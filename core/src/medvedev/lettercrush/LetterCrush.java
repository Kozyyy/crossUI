package medvedev.lettercrush;

import com.idp.engine.App;

public class LetterCrush extends App {
    @Override
    public void create(){
        super.create();


        //setScreen(new MenuScreen());
    }

    public static LetterCrush getInstance() {
        return (LetterCrush) instance;
    }


}
