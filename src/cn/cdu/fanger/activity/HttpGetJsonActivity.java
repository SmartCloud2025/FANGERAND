package cn.cdu.fanger.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import cn.cdu.fanger.ac.view.AbstractAsyncListActivity;
import cn.cdu.fanger.constant.ServerUrl;
import cn.cdu.fanger.rest.entity.AndrSpot;
import cn.cdu.fanger.view.adpter.AndrSpotsListAdapter;

public class HttpGetJsonActivity extends AbstractAsyncListActivity {
	protected static final String TAG = HttpGetJsonActivity.class.getSimpleName();
	// ***************************************
		// Activity methods
		// ***************************************
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		}

		@Override
		public void onStart() {
			super.onStart();

			// when this activity starts, initiate an asynchronous HTTP GET request
			new DownloadStatesTask().execute();
		}

		// ***************************************
		// Private methods
		// ***************************************
		private void refreshStates(List<AndrSpot> spots) {
			if (spots == null) {
				return;
			}

			AndrSpotsListAdapter adapter = new AndrSpotsListAdapter(this, spots);
			setListAdapter(adapter);
		}

		// ***************************************
		// Private classes
		// ***************************************
		private class DownloadStatesTask extends AsyncTask<Void, Void, List<AndrSpot>> {

			@Override
			protected void onPreExecute() {
				showLoadingProgressDialog();
			}

			@Override
			protected List<AndrSpot> doInBackground(Void... params) {
				try {
					// The URL for making the GET request
					final String url = ServerUrl.spotList;

					// Set the Accept header for "application/json"
					HttpHeaders requestHeaders = new HttpHeaders();
					List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
					acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
					requestHeaders.setAccept(acceptableMediaTypes);

					// Populate the headers in an HttpEntity object to use for the request
					HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

					// Create a new RestTemplate instance
					RestTemplate restTemplate = new RestTemplate();
					restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

					// Perform the HTTP GET request
					ResponseEntity<AndrSpot[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
							AndrSpot[].class);

					// convert the array to a list and return it
					return Arrays.asList(responseEntity.getBody());
				} catch (Exception e) {
					Log.e(TAG, e.getMessage(), e);
				}

				return null;
			}

			@Override
			protected void onPostExecute(List<AndrSpot> result) {
				dismissProgressDialog();
				refreshStates(result);
			}
		}

		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			
		}
		
		
}
