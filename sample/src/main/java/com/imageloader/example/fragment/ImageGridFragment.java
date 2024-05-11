package com.imageloader.example.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.advait.imageloader.core.DisplayImageOptions;
import org.advait.imageloader.core.ImageLoader;
import org.advait.imageloader.core.assist.FailReason;
import org.advait.imageloader.core.listener.ImageLoadingProgressListener;
import org.advait.imageloader.core.listener.SimpleImageLoadingListener;

import com.imageloader.example.R;
import com.imageloader.example.models.MainResponseModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Utsav (utsav.gokalani@gmail.com)
 */
public class ImageGridFragment extends AbsListViewBaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_image_grid, container, false);
		listView = (GridView) rootView.findViewById(R.id.grid);
		listView.setOnItemClickListener((parent, view, position, id) -> {
			Toast.makeText(requireContext(), "Clicked item = " +position, Toast.LENGTH_SHORT).show();
        });
		loadData();
		return rootView;
	}

	public String loadJSONFromAsset() {
		String json = null;
		try {
			InputStream is = getActivity().getAssets().open("data.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;
	}

	private void loadData() {
		Gson gson = new Gson();
		String data = loadJSONFromAsset();
		List<MainResponseModel> list = gson.fromJson(data, new TypeToken<List<MainResponseModel>>(){}.getType());
		((GridView) listView).setAdapter(new ImageAdapter(getActivity(),list));
	}
	
	private static class ImageAdapter extends BaseAdapter {

		private final LayoutInflater inflater;

		private final DisplayImageOptions options;
		private final List<MainResponseModel> list;

		ImageAdapter(Context context,List<MainResponseModel> list) {
			inflater = LayoutInflater.from(context);
			options = new DisplayImageOptions.Builder()
					.showImageOnLoading(R.drawable.ic_placeholder)
					.showImageForEmptyUri(R.drawable.ic_placeholder)
					.showImageOnFail(R.drawable.ic_placeholder)
					.cacheInMemory(true)
					.cacheOnDisk(true)
					.considerExifParams(true)
					.bitmapConfig(Bitmap.Config.RGB_565)
					.build();
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			View view = convertView;
			if (view == null) {
				view = inflater.inflate(R.layout.item_grid_image, parent, false);
				holder = new ViewHolder();
				assert view != null;
				holder.imageView = (ImageView) view.findViewById(R.id.image);
				holder.progressBar = (ProgressBar) view.findViewById(R.id.progress);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			MainResponseModel mainResponseModel = (MainResponseModel) getItem(position);
			String imageURL = mainResponseModel.getThumbnail().getDomain() + "/" + mainResponseModel.getThumbnail().getBasePath() + "/0/" + mainResponseModel.getThumbnail().getKey();
			ImageLoader.getInstance()
					.displayImage(imageURL, holder.imageView, options, new SimpleImageLoadingListener() {
						@Override
						public void onLoadingStarted(String imageUri, View view) {
							holder.progressBar.setProgress(0);
							holder.progressBar.setVisibility(View.VISIBLE);
						}

						@Override
						public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
							holder.progressBar.setVisibility(View.GONE);
						}

						@Override
						public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
							holder.progressBar.setVisibility(View.GONE);
						}
					}, (imageUri, view1, current, total) -> holder.progressBar.setProgress(Math.round(100.0f * current / total)));

			return view;
		}
	}

	static class ViewHolder {
		ImageView imageView;
		ProgressBar progressBar;
	}
}