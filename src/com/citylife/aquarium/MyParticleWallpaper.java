package com.citylife.aquarium;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.particle.ParticleSystem;
import org.anddev.andengine.entity.particle.modifier.AccelerationInitializer;
import org.anddev.andengine.entity.particle.modifier.AlphaModifier;
import org.anddev.andengine.entity.particle.modifier.ColorInitializer;
import org.anddev.andengine.entity.particle.modifier.ColorModifier;
import org.anddev.andengine.entity.particle.modifier.ExpireModifier;
import org.anddev.andengine.entity.particle.modifier.RotationInitializer;
import org.anddev.andengine.entity.particle.modifier.RotationModifier;
import org.anddev.andengine.entity.particle.modifier.ScaleModifier;
import org.anddev.andengine.entity.particle.modifier.VelocityInitializer;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.ui.livewallpaper.BaseLiveWallpaperService;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import android.content.SharedPreferences;
import android.view.MotionEvent;

public class MyParticleWallpaper extends MyBaseLiveWallpaperService implements SharedPreferences.OnSharedPreferenceChangeListener {

	public static final String SHARED_PREFS_NAME = "MyPrefs";
	SharedPreferences mPrefs;
	private static final int CAMERA_WIDTH = 480;
	private static final int CAMERA_HEIGHT = 800;

	private Camera mCamera;
	private Texture mLeaveTextureAtlas1, mLeaveTextureAtlas2,
			mLeaveTextureAtlas3, mLeaveTextureAtlas4, mLeaveTextureAtlas5,
			mLeaveTextureAtlas6, mLeaveTextureAtlas7, mBgTextureAtlas;
	private TextureRegion mParticleTextureRegion1, mParticleTextureRegion2,
			mParticleTextureRegion3, mParticleTextureRegion4,
			mParticleTextureRegion5, mParticleTextureRegion6,
			mParticleTextureRegion7, mBgTextureRegion;
	private MySprite bg;
	boolean dirleft = true;
	int flakesNum = 11;
	int flakesSpeed = 1;
	double flakesSize = 0.5;
	boolean moveBG = true;
	String bgImage = "bg1";
	boolean mSettingsChanged = false;

	@Override
	public org.anddev.andengine.engine.Engine onLoadEngine() {
		return new org.anddev.andengine.engine.Engine(new EngineOptions(true,
				ScreenOrientation.LANDSCAPE, new FillResolutionPolicy(),
				new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT)));
	}

	@Override
	public void onLoadResources() {
		loadPreferences(mPrefs);
		buildResources();
	}
	
	public void buildResources() {
		this.mLeaveTextureAtlas1 = new Texture(128, 128,
				TextureOptions.BILINEAR);
		this.mLeaveTextureAtlas2 = new Texture(128, 128,
				TextureOptions.BILINEAR);
		this.mLeaveTextureAtlas3 = new Texture(128, 128,
				TextureOptions.BILINEAR);
		this.mLeaveTextureAtlas4 = new Texture(128, 128,
				TextureOptions.BILINEAR);
		this.mLeaveTextureAtlas5 = new Texture(128, 128,
				TextureOptions.BILINEAR);
		this.mLeaveTextureAtlas6 = new Texture(128, 128,
				TextureOptions.BILINEAR);
		this.mLeaveTextureAtlas7 = new Texture(128, 128,
				TextureOptions.BILINEAR);
		this.mBgTextureAtlas = new Texture(2048, 2048, TextureOptions.BILINEAR);
		this.mParticleTextureRegion1 = TextureRegionFactory.createFromAsset(
				this.mLeaveTextureAtlas1, this, "lv/l1.png", 0, 0);
		this.mParticleTextureRegion2 = TextureRegionFactory.createFromAsset(
				this.mLeaveTextureAtlas2, this, "lv/l2.png", 0, 0);
		this.mParticleTextureRegion3 = TextureRegionFactory.createFromAsset(
				this.mLeaveTextureAtlas3, this, "lv/l3.png", 0, 0);
		this.mParticleTextureRegion4 = TextureRegionFactory.createFromAsset(
				this.mLeaveTextureAtlas4, this, "lv/l4.png", 0, 0);
		this.mParticleTextureRegion5 = TextureRegionFactory.createFromAsset(
				this.mLeaveTextureAtlas5, this, "lv/l5.png", 0, 0);
		this.mParticleTextureRegion6 = TextureRegionFactory.createFromAsset(
				this.mLeaveTextureAtlas6, this, "lv/l6.png", 0, 0);
		this.mParticleTextureRegion7 = TextureRegionFactory.createFromAsset(
				this.mLeaveTextureAtlas7, this, "lv/l7.png", 0, 0);
		this.mBgTextureRegion = TextureRegionFactory.createFromAsset(
				this.mBgTextureAtlas, this, "bg/"+bgImage+".jpg", 0, 0);
		this.mEngine.getTextureManager().loadTexture(this.mBgTextureAtlas);
		this.mEngine.getTextureManager().loadTexture(this.mLeaveTextureAtlas1);
		this.mEngine.getTextureManager().loadTexture(this.mLeaveTextureAtlas2);
		this.mEngine.getTextureManager().loadTexture(this.mLeaveTextureAtlas3);
		this.mEngine.getTextureManager().loadTexture(this.mLeaveTextureAtlas4);
		this.mEngine.getTextureManager().loadTexture(this.mLeaveTextureAtlas5);
		this.mEngine.getTextureManager().loadTexture(this.mLeaveTextureAtlas6);
		this.mEngine.getTextureManager().loadTexture(this.mLeaveTextureAtlas7);
	}

	@Override
	public Scene onLoadScene() {
		this.mEngine.registerPostFrameHandler(new FPSLogger());
		final Scene scene = new Scene(2);
		buildScene(scene);
		return scene;

	}
	
	public void buildScene(Scene scene) {
		if (scene != null) {
			scene.clearPostFrameHandlers();
			scene.getLayer(0).clear();
			scene.getLayer(1).clear();
		}
		bg = new MySprite(-CAMERA_WIDTH, 0, this.mBgTextureRegion);
		scene.getLayer(0).addEntity(bg);
		/* Leaves 1 type. */
		{
			final ParticleSystem particleSystem = new ParticleSystem(0, -50,
				  CAMERA_WIDTH, -50, Math.round(flakesNum/10-1), Math.round(flakesNum/10), flakesNum, 
				  this.mParticleTextureRegion1);
			particleSystem.addParticleInitializer(new VelocityInitializer(15,
					15, flakesSpeed, flakesSpeed+10));
			particleSystem.addParticleInitializer(new AccelerationInitializer(
					5, 15));
			particleSystem.addParticleInitializer(new RotationInitializer(0.0f,
					360.0f));
			particleSystem.addParticleModifier(new RotationModifier(0, 360.0f,
					0, 15));
			particleSystem.addParticleModifier(new ExpireModifier(10f));
			particleSystem.addParticleModifier(new ScaleModifier(0.17f, (float)flakesSize, 0,
					5));

			scene.getLayer(1).addEntity(particleSystem);
		}

		/* Leaves 2 type. */
		{
			final ParticleSystem particleSystem = new ParticleSystem(
					CAMERA_WIDTH, -50, -CAMERA_WIDTH, -50, Math.round(flakesNum/10-1), Math.round(flakesNum/10), 
					flakesNum, this.mParticleTextureRegion2);
			particleSystem.addParticleInitializer(new VelocityInitializer(-15,
					-15, flakesSpeed, flakesSpeed+10));
			particleSystem.addParticleInitializer(new AccelerationInitializer(
					-5, 15));
			particleSystem.addParticleInitializer(new RotationInitializer(0.0f,
					-360.0f));
			particleSystem.addParticleModifier(new RotationModifier(0, -360.0f,
					0, 15));
			particleSystem.addParticleModifier(new ExpireModifier(10f));
			particleSystem.addParticleModifier(new ScaleModifier(0.17f, (float)flakesSize, 0,
					5));

			scene.getLayer(1).addEntity(particleSystem);
		}

		/* Leaves 3 type. */
		{
			final ParticleSystem particleSystem = new ParticleSystem(0, -50,
					CAMERA_WIDTH, -50, Math.round(flakesNum/10-1), Math.round(flakesNum/10), flakesNum, 
					this.mParticleTextureRegion3);
			particleSystem.addParticleInitializer(new VelocityInitializer(15,
					15, flakesSpeed, flakesSpeed+10));
			particleSystem.addParticleInitializer(new AccelerationInitializer(
					5, 15));
			particleSystem.addParticleInitializer(new RotationInitializer(0.0f,
					360.0f));
			particleSystem.addParticleModifier(new RotationModifier(0, 360.0f,
					0, 10));
			particleSystem.addParticleModifier(new ExpireModifier(10f));
			particleSystem.addParticleModifier(new ScaleModifier(0.17f, (float)flakesSize, 0,
					5));

			scene.getLayer(1).addEntity(particleSystem);
		}

		/* Leaves 4 type. */
		{
			final ParticleSystem particleSystem = new ParticleSystem(
					CAMERA_WIDTH, -50, -CAMERA_WIDTH, -50, Math.round(flakesNum/10-1), Math.round(flakesNum/10), 
					flakesNum, this.mParticleTextureRegion4);
			particleSystem.addParticleInitializer(new VelocityInitializer(-15,
					-15, flakesSpeed, flakesSpeed+10));
			particleSystem.addParticleInitializer(new AccelerationInitializer(
					-5, 15));
			particleSystem.addParticleInitializer(new RotationInitializer(0.0f,
					-360.0f));
			particleSystem.addParticleModifier(new RotationModifier(0, -360.0f,
					0, 15));
			particleSystem.addParticleModifier(new ExpireModifier(10f));
			particleSystem.addParticleModifier(new ScaleModifier(0.17f, (float)flakesSize, 0,
					5));

			scene.getLayer(1).addEntity(particleSystem);
		}

		/* Leaves 5 type. */
		{
			final ParticleSystem particleSystem = new ParticleSystem(-100, -50,
					CAMERA_WIDTH, -50, Math.round(flakesNum/10-1), Math.round(flakesNum/10), flakesNum, 
					this.mParticleTextureRegion5);
			particleSystem.addParticleInitializer(new VelocityInitializer(10,
					10, flakesSpeed, flakesSpeed+10));
			particleSystem.addParticleInitializer(new AccelerationInitializer(
					5, 15));
			particleSystem.addParticleInitializer(new RotationInitializer(0.0f,
					360.0f));
			particleSystem.addParticleModifier(new RotationModifier(0, 360.0f,
					0, 20));
			particleSystem.addParticleModifier(new ExpireModifier(10f));
			particleSystem.addParticleModifier(new ScaleModifier(0.17f, (float)flakesSize, 0,
					5));

			scene.getLayer(1).addEntity(particleSystem);
		}

		/* Leaves 6 type. */
		{
			final ParticleSystem particleSystem = new ParticleSystem(
					CAMERA_WIDTH, -50, -CAMERA_WIDTH, -50, Math.round(flakesNum/10-1), Math.round(flakesNum/10), 
					flakesNum, this.mParticleTextureRegion6);
			particleSystem.addParticleInitializer(new VelocityInitializer(-15,
					-15, flakesSpeed, flakesSpeed+10));
			particleSystem.addParticleInitializer(new AccelerationInitializer(
					-5, 15));
			particleSystem.addParticleInitializer(new RotationInitializer(0.0f,
					-360.0f));
			particleSystem.addParticleModifier(new RotationModifier(0, -360.0f,
					0, 10));
			particleSystem.addParticleModifier(new ExpireModifier(10f));
			particleSystem.addParticleModifier(new ScaleModifier(0.17f, (float)flakesSize, 0,
					5));

			scene.getLayer(1).addEntity(particleSystem);
		}

		/* Leaves 7 type. */
		{
			final ParticleSystem particleSystem = new ParticleSystem(
					CAMERA_WIDTH / 2, -50, -CAMERA_WIDTH / 2, -50, Math.round(flakesNum/10-1), Math.round(flakesNum/10), 
					flakesNum, this.mParticleTextureRegion7);
			particleSystem.addParticleInitializer(new VelocityInitializer(-15,
					15, flakesSpeed, flakesSpeed+10));
			particleSystem.addParticleInitializer(new AccelerationInitializer(
					5, 15));
			particleSystem.addParticleInitializer(new RotationInitializer(0.0f,
					360.0f));
			particleSystem.addParticleModifier(new RotationModifier(0, 360.0f,
					0, 13));
			particleSystem.addParticleModifier(new ExpireModifier(10f));
			particleSystem.addParticleModifier(new ScaleModifier(0.17f, (float)flakesSize, 0,
					5));

			scene.getLayer(1).addEntity(particleSystem);
		}
		if (moveBG) {
			scene.registerPostFrameHandler(moveBackground);
		}
	}

	TimerHandler moveBackground = new TimerHandler(0.03f, new ITimerCallback() {
		@Override
		public void onTimePassed(TimerHandler pTimerHandler) {
			if (bg.getX() == -2*CAMERA_WIDTH) {
				dirleft = false;
			} else if (bg.getX() == 0) {
				dirleft = true;
			}
			if (dirleft) {
				bg.setPosition(bg.getX()-1, bg.getY());
			} else {
				bg.setPosition(bg.getX()+1, bg.getY());
			}
			moveBackground.reset();
		}
	});

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub

	}

	public void loadPreferences(SharedPreferences mPrefs) {
		mPrefs = MyParticleWallpaper.this.getSharedPreferences(SHARED_PREFS_NAME, 0);
        mPrefs.registerOnSharedPreferenceChangeListener(this);
        onSharedPreferenceChanged(mPrefs, null);
	}
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences prefs, String arg1) {
		
		moveBG = prefs.getBoolean("movebg", true);
		bgImage = prefs.getString("bg", "bg1");
		flakesNum = Integer.parseInt(prefs.getString("amount", "0"));
		flakesSpeed = Integer.parseInt(prefs.getString("speed", "0"));
		flakesSize = Double.parseDouble(prefs.getString("size", "0.0"));
		mSettingsChanged = true;
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override 
	public void onResume() {
		super.onResume();
		if (mSettingsChanged == true)
        {
			buildResources();
			buildScene(this.getEngine().getScene());
			mSettingsChanged = false;
        }

	}

	@Override
	public void offsetsChanged(float xOffset, float yOffset, float xOffsetStep,
			float yOffsetStep, int xPixelOffset, int yPixelOffset) {
		// TODO Auto-generated method stub
		
	}

}
