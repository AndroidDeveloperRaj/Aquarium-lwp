package org.anddev.andengine.entity.shape.modifier.ease;

import org.anddev.andengine.util.constants.MathConstants;

import android.util.FloatMath;

/**
 * @author Gil, Nicolas Gramlich
 * @since 16:52:11 - 26.07.2010
 */
public class EaseSineInOut implements IEaseFunction {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private static EaseSineInOut INSTANCE;

	// ===========================================================
	// Constructors
	// ===========================================================

	private EaseSineInOut() {
	}

	public static EaseSineInOut getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new EaseSineInOut();
		}
		return INSTANCE;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public float calc(final float t, final float b, final float c, final float d) {
		return (float) (-c * 0.5f * (FloatMath.cos(MathConstants.PI * t / d) - 1) + b);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
