/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ozv.crossUI.graphics.starttrack_widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;
import com.idp.engine.App;
import com.idp.engine.resources.assets.IdpColorPixmap;
import com.idp.engine.ui.graphics.actors.Text;

import org.ozv.crossUI.TestApp;
import org.ozv.crossUI.api.model.Report;
import org.ozv.crossUI.graphics.starttrack_widgets.base.RadioGroup;

/**
 * StartTrackWidget representing a module.
 *
 * @author idp
 */
public class TeamGradeWidget extends StartTrackWidget<Report> {
	
	public TeamGradeWidget(Report report) {
		super(report);
	}
	
	
	public void init() {
		
		setBackgroundColor(App.Colors.BACK);
		setWidth(Gdx.graphics.getWidth());
		
		layout.paddingLeft = mp;
		layout.paddingTop = mp;
		layout.paddingBottom = sp;
		
		layout.addActor(new Text("КОМАНДНАЯ ОЦЕНКА",
				TestApp.getResources().getLabelStyle("header")));
		
		if (TestApp.getState().game.team_grade_required) {

			RadioGroup rg = new RadioGroup(0, 3, data);
			rg.paddingLeft = sp / 2;
			rg.paddingRight = sp / 2;
			rg.paddingBottom = mp;
			rg.paddingTop = mp;
			rg.setWidth(getWidth() - layout.paddingLeft - layout.paddingRight);
			rg.layout();
			layout.addActor(rg);

			Actor a = new IdpColorPixmap(App.Colors.WIDGET_BORDER).buildActor();
			a.setSize(getWidth() - 2 * sp, App.dp2px(2));
			Group g = new Group();
			g.setWidth(getWidth() - layout.paddingLeft - layout.paddingRight);
			g.setHeight(a.getHeight());
			g.addActor(a);
			a.setX(g.getWidth() / 2 - a.getWidth() / 2);
			layout.addActor(g);

		} else {
			Text note = new Text("Не требуется".toUpperCase(), TestApp.getResources().getLabelStyle("number"));
			note.getStyle().fontColor = Color.valueOf("999999");
			layout.setGap(App.dp2px(18));
			note.setWidth(Gdx.graphics.getWidth() - layout.paddingRight - layout.paddingLeft);
			note.setAlignment(Align.center);
			layout.addActor(note);
		}
		
        Text participantsListTitle = new Text("СОСТАВ КОМАНДЫ",
				TestApp.getResources().getLabelStyle("header"));
        participantsListTitle.setPosition(lp, layout.getY() + layout.getHeight() + mp);
        layout.addActor(participantsListTitle);
	}
	
}
