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
package com.imageloader.example.fragment;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import org.advait.imageloader.core.ImageLoader;
import org.advait.imageloader.core.listener.PauseOnScrollListener;

import com.imageloader.example.R;

/**
 * @author Utsav (utsav.gokalani@gmail.com)
 */
public abstract class AbsListViewBaseFragment extends BaseFragment {

	protected static final String STATE_PAUSE_ON_SCROLL = "STATE_PAUSE_ON_SCROLL";
	protected static final String STATE_PAUSE_ON_FLING = "STATE_PAUSE_ON_FLING";

	protected AbsListView listView;

	protected boolean pauseOnScroll = false;
	protected boolean pauseOnFling = true;

	@Override
	public void onResume() {
		super.onResume();
		applyScrollListener();
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		MenuItem pauseOnScrollItem = menu.findItem(R.id.item_pause_on_scroll);
		pauseOnScrollItem.setVisible(true);
		pauseOnScrollItem.setChecked(pauseOnScroll);

		MenuItem pauseOnFlingItem = menu.findItem(R.id.item_pause_on_fling);
		pauseOnFlingItem.setVisible(true);
		pauseOnFlingItem.setChecked(pauseOnFling);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.item_pause_on_scroll) {
			pauseOnScroll = !pauseOnScroll;
			item.setChecked(pauseOnScroll);
			applyScrollListener();
			return true;
		} else if (item.getItemId() == R.id.item_pause_on_fling) {
			pauseOnFling = !pauseOnFling;
			item.setChecked(pauseOnFling);
			applyScrollListener();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void applyScrollListener() {
		listView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), pauseOnScroll, pauseOnFling));
	}
}
