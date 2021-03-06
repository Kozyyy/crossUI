package org.ozv.crossUI.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.idp.engine.App;
import com.idp.engine.ui.graphics.actors.FieldText;
import com.idp.engine.ui.graphics.actors.Image;
import com.idp.engine.ui.graphics.actors.Text;
import com.idp.engine.ui.graphics.actors.layouts.VerticalLayout;
import com.idp.engine.ui.graphics.base.Loader;
import com.idp.engine.ui.screens.AppScreen;

import org.ozv.crossUI.TestApp;
import org.ozv.crossUI.api.StartTrackApi;

/**
 * Screen with sign in form.
 *
 * @author dhabensky <dhabensky@idp-crew.com>
 */
public class LoginScreen extends AppScreen {

	private VerticalLayout layout;
	private FieldText email;
    private Text signIn;
    private Loader loader;
    private Text message;

	public LoginScreen() {
		super();

	}


	@Override
	public void init() {
		super.init();
		hideNavBar();
		getMainLayer().setBackgroundColor(App.Colors.WIDGET_WHITE);

		layout = new VerticalLayout();
		layout.setAlign(VerticalLayout.Align.Center);
		layout.setWidth(getMainLayer().getWidth());
		layout.setPadding(0);
		layout.paddingTop = App.dp2px(48);

		layout.setGap(App.dp2px(18));
		addActor(layout);

		float textFieldXpadding = TestApp.dp2px(24);
		float textFieldWidth = Gdx.graphics.getWidth() - textFieldXpadding * 2;

		float signInWidth = TestApp.dp2px(160);
		float signInHeight = TestApp.dp2px(36);
        float messageHeight = TestApp.dp2px(20);

		TextFieldStyle textFieldStyle = TestApp.getResources().getTextFieldStyle("text_field");
		LabelStyle titleStyle = TestApp.getResources().getLabelStyle("logo");
		LabelStyle signInStyle = TestApp.getResources().getLabelStyle("navbar");
		signInStyle.fontColor = App.Colors.MAIN;

		Image logo = new Image(App.getResources().getIcon("logo-mobile"));
		logo.setHeight(App.dp2px(72));
		logo.setWidth(
				logo.getSprite().getRegionWidth() * logo.getHeight() / logo.getSprite().getRegionHeight()
		);

		logo.setColor(App.Colors.MAIN);
		layout.addActor(logo);


		Text title = new Text("StartTrack experts", titleStyle);
		title.setAlignment(Align.center);
		title.setSize(Gdx.graphics.getWidth(), App.dp2px(30));
		layout.addActor(title);


		message = new Text("", TestApp.getResources().getLabelStyle("label"));
		message.setAlignment(Align.center);
		message.setSize(getMainLayer().getWidth(), messageHeight);
		layout.addActor(message);

		email = new FieldText(textFieldStyle);
		email.setSize(textFieldWidth, textFieldStyle.font.getCapHeight() * 2);
		email.setColor(App.Colors.MAIN);
		layout.addActor(email);


		signIn = new Text("ВОЙТИ", signInStyle);
		signIn.setAlignment(Align.center);
		signIn.setSize(signInWidth, signInHeight);
		layout.addActor(signIn);

        loader = new Loader(TestApp.getResources().getIcon("loader"));
        loader.setWidth(signInWidth);
        loader.setHeight(signInHeight);
        loader.setPosition(signIn.getX(), signIn.getY());
        loader.setVisible(false);
        addActor(loader);

//		getMainLayer().addCaptureListener(new ClickListener() {
//			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//				stage.setKeyboardFocus(null);
//				return false;
//			}
//		});

		signIn.addListener(new ActorGestureListener() {
			public void tap(InputEvent event, float x, float y, int count, int button) {

                loader.start();
				showLoader();
                message.setText("");
                Gdx.input.setOnscreenKeyboardVisible(false);

				StartTrackApi.auth(email.getText().toUpperCase(), new StartTrackApi.AuthListener() {
                    public void loaded(String privateToken) {
						try {
							TestApp.getInstance().logIn(privateToken);
						} catch (Exception ex) {
							failed(ex);
						}
					}

                    @Override
                    public void failed(Throwable t) {
                        if (t.getMessage().equals("{\"code\":[\"This field may not be blank.\"]}")) {
                            message.setText("Введите код");
                        } else
						if (t.getMessage().equals("{\"detail\":\"Invalid token.\"}")) {
							message.setText("Ошибка авторизации");
						} else
                        if (t.getMessage().equals("{\"non_field_errors\":[\"Unable to log in with provided credentials.\"]}")) {
                            message.setText("Неверный код доступа");
                        } else
                        if (t.getMessage().equals("{\"non_field_errors\":[\"User account is disabled.\"]}")) {
                            message.setText("Доступ экспертов запрещен");
                        } else
						if (t.getMessage().equals("{\"detail\":\"User inactive or deleted.\"}")) {
							message.setText("Доступ экспертов запрещен");
						} else {
                            message.setText("Нет подключения к интернету");
                        }

                        loader.stop();
						showSignInButton();
                        stage.setKeyboardFocus(email);
                    }
                });
			}
		});
	}

	private void showSignInButton() {
		layout.removeActor(loader);
		layout.addActor(signIn);
	}

	private void showLoader() {
		layout.removeActor(signIn);
		layout.addActor(loader);
	}

	@Override
	public void show() {
		super.show();
		TestApp.getInstance().logIn();
		Gdx.input.setCatchBackKey(false);
	}


    class LoginError {
        String[] non_field_errors;
    }

}
