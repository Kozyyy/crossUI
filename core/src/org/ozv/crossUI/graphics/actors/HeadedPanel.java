package org.ozv.crossUI.graphics.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.idp.engine.ui.graphics.actors.Text;
import com.idp.engine.ui.graphics.base.Rect;

import org.ozv.crossUI.TestApp;

/**
 * Panel with a header.
 * 
 * Created by ozvairon on 31.08.16.
 */
public class HeadedPanel extends Rect {

    protected Group header;
    protected Text title;
    protected Group content;

    private final int sp = TestApp.dp2px(8);  // small gap
    private final int mp = TestApp.dp2px(12); // medium gap
    private final int lp = TestApp.dp2px(16); // large gap

    private final int wPhoto = TestApp.dp2px(42); // photo width
    private final int hPhoto = TestApp.dp2px(42); // photo height


    public HeadedPanel() {
        this("");
    }

    public HeadedPanel(String t){

        header = new Group();
        content = new Group();
        title = new Text(t, TestApp.getResources().getLabelStyle("header"));

        setBackgroundColor(Color.WHITE);
        setBorder(TestApp.dp2px(1));
        setBorderColor(Color.LIGHT_GRAY);


        header.addActor(title);
        title.setPosition(lp, mp);
        header.setSize(Gdx.graphics.getWidth(), mp + title.getHeight() + mp);
        addActor(header);
        addActor(content);
        setSize(header.getWidth(), header.getHeight());
    }


    private void layout() {
        header.setWidth(getWidth());
        content.setWidth(getWidth());
        content.setY(header.getHeight());
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        layout();
    }

    public void setContent(Group g){
        content.clearChildren();
        content.addActor(g);
        setHeight(header.getHeight() + g.getHeight());
    }

	public Group getHeader() {
		return header;
	}

	public Text getTitle() {
		return title;
	}
}
