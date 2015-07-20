package org.anddev.progressmonitor;


public interface IProgressListener {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * @param pProgress between 0 and 100.
	 */
	public void onProgressChanged(final int pProgress);
}