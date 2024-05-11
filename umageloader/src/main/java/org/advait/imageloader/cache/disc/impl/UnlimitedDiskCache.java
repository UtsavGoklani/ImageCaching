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
package org.advait.imageloader.cache.disc.impl;

import org.advait.imageloader.cache.disc.DiskCache;
import org.advait.imageloader.cache.disc.naming.FileNameGenerator;

import java.io.File;

/**
 * Default implementation of {@linkplain DiskCache disk cache}.
 * Cache size is unlimited.
 *
 * @author Utsav (utsav.gokalani@gmail.com)
 * @since 1.0.0
 */
public class UnlimitedDiskCache extends BaseDiskCache {
	/** @param cacheDir Directory for file caching */
	public UnlimitedDiskCache(File cacheDir) {
		super(cacheDir);
	}

	/**
	 * @param cacheDir        Directory for file caching
	 * @param reserveCacheDir null-ok; Reserve directory for file caching. It's used when the primary directory isn't available.
	 */
	public UnlimitedDiskCache(File cacheDir, File reserveCacheDir) {
		super(cacheDir, reserveCacheDir);
	}

	/**
	 * @param cacheDir          Directory for file caching
	 * @param reserveCacheDir   null-ok; Reserve directory for file caching. It's used when the primary directory isn't available.
	 * @param fileNameGenerator {@linkplain FileNameGenerator
	 *                          Name generator} for cached files
	 */
	public UnlimitedDiskCache(File cacheDir, File reserveCacheDir, FileNameGenerator fileNameGenerator) {
		super(cacheDir, reserveCacheDir, fileNameGenerator);
	}
}
