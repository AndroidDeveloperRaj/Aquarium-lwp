package org.anddev.andengine.entity.shape.modifier.ease;

/**
 * @author Gil, Nicolas Gramlich
 * @since 16:52:11 - 26.07.2010
 */
public class EaseQuintInOut implements IEaseFunction {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private static EaseQuintInOut INSTANCE;

	// ===========================================================
	// Constructors
	// ===========================================================

	private EaseQuintInOut() {
	}

	public static EaseQuintInOut getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new EaseQuintInOut();
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
	public float calc(float t, final float b, final float c, final float d) {
		if((t /= d * 0.5f) < 1) {
			return c * 0.5f * t * t * t * t * t + b;
		}
		return c * 0.5f * ((t -= 2) * t * t * t * t + 2) + b;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
