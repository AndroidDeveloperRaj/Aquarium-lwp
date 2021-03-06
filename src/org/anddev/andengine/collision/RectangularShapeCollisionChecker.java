package org.anddev.andengine.collision;

import static org.anddev.andengine.util.constants.Constants.VERTEX_INDEX_Y;

import org.anddev.andengine.entity.shape.RectangularShape;
import org.anddev.andengine.util.MathUtils;

/**
 * @author Nicolas Gramlich
 * @since 11:50:19 - 11.03.2010
 */
public class RectangularShapeCollisionChecker extends ShapeCollisionChecker {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final int RECTANGULARSHAPE_VERTEX_COUNT = 4;

	private static final float[] VERTICES_CONTAINS_TMP = new float[2 * RECTANGULARSHAPE_VERTEX_COUNT];
	private static final float[] VERTICES_COLLISION_TMP_A = new float[2 * RECTANGULARSHAPE_VERTEX_COUNT];
	private static final float[] VERTICES_COLLISION_TMP_B = new float[2 * RECTANGULARSHAPE_VERTEX_COUNT];

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public static boolean checkContains(final RectangularShape pRectangularShape, final float pX, final float pY) {
		fillVertices(pRectangularShape, VERTICES_CONTAINS_TMP);
		return checkContains(VERTICES_CONTAINS_TMP, 2 * RECTANGULARSHAPE_VERTEX_COUNT, pX, pY);
	}

	public static boolean checkCollision(final RectangularShape pRectangularShapeA, final RectangularShape pRectangularShapeB) {
		fillVertices(pRectangularShapeA, VERTICES_COLLISION_TMP_A);
		fillVertices(pRectangularShapeB, VERTICES_COLLISION_TMP_B);

		return checkCollision(2 * RECTANGULARSHAPE_VERTEX_COUNT, 2 * RECTANGULARSHAPE_VERTEX_COUNT, VERTICES_COLLISION_TMP_A, VERTICES_COLLISION_TMP_B);
	}

	private static void fillVertices(final RectangularShape pRectangularShape, final float[] pVertices) {
		final float left = pRectangularShape.getX();
		final float top = pRectangularShape.getY();
		final float right = pRectangularShape.getWidth() + left;
		final float bottom = pRectangularShape.getHeight() + top;

		pVertices[0] = left;
		pVertices[0 + VERTEX_INDEX_Y] = top;

		pVertices[2] = right;
		pVertices[2 + VERTEX_INDEX_Y] = top;

		pVertices[4] = right;
		pVertices[4 + VERTEX_INDEX_Y] = bottom;

		pVertices[6] = left;
		pVertices[6 + VERTEX_INDEX_Y] = bottom;

		MathUtils.rotateAndScaleAroundCenter(pVertices,
				pRectangularShape.getRotation(), left + pRectangularShape.getRotationCenterX(), top + pRectangularShape.getRotationCenterY(),
				pRectangularShape.getScaleX(), pRectangularShape.getScaleY(), left + pRectangularShape.getScaleCenterX(), top + pRectangularShape.getScaleCenterY());
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
