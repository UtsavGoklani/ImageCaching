/*******************************************************************************
 * Copyright 2024 Utsav
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package org.advait.imageloader.core.listener;

import android.graphics.Bitmap;
import android.view.View;
import org.advait.imageloader.core.assist.FailReason;

/**
 * A convenient class to extend when you only want to listen for a subset of all the image loading events. This
 * implements all methods in the {@link ImageLoadingListener} but does
 * nothing.
 *
 * @author Utsav (utsav.gokalani@gmail.com)
 * @since 1.0.0
 */
public class SimpleImageLoadingListener implements ImageLoadingListener {
	@Override
	public void onLoadingStarted(String imageUri, View view) {
		// Empty implementation
	}

	@Override
	public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
		// Empty implementation
	}

	@Override
	public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
		// Empty implementation
	}

	@Override
	public void onLoadingCancelled(String imageUri, View view) {
		// Empty implementation
	}
}
