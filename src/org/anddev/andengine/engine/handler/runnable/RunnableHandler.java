package org.anddev.andengine.engine.handler.runnable;

import java.util.ArrayList;

import org.anddev.andengine.engine.handler.IUpdateHandler;

/**
 * @author Nicolas Gramlich
 * @since 10:24:39 - 18.06.2010
 */
public class RunnableHandler implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private ArrayList<Runnable> mRunnables = new ArrayList<Runnable>();

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(float pSecondsElapsed) {
		final ArrayList<Runnable> runnables = this.mRunnables;
		final int runnableCount = runnables.size();
		for(int i = runnableCount - 1; i >= 0; i--) {
			runnables.get(i).run();
		}
		runnables.clear();
	}

	@Override
	public void reset() {
		this.mRunnables.clear();
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	public void postRunnable(final Runnable pRunnable) {
		this.mRunnables.add(pRunnable);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
