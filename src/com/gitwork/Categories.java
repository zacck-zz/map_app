package com.gitwork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Categories extends ListActivity {

	
	
	public class getCats extends AsyncTask<Void, Void, Void>
	{
		String[] mCategories, mIds, mDescriptions;
		ArrayList<HashMap<String, String>> listy = new ArrayList<HashMap<String, String>>();
		

		@Override
		protected Void doInBackground(Void... params) {
			
			HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost = new HttpPost("http://zacckos.heliohost.org/git_work/listview_populate.php");

		    try {
		        // Add your data
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("id", "12345"));
		        nameValuePairs.add(new BasicNameValuePair("stringdata", "AndDev is Cool!"));
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		        // Execute HTTP Post Request
		        HttpResponse response = httpclient.execute(httppost);
		        
		        JSONObject parent = new JSONObject(EntityUtils.toString(response.getEntity()));
		        
		        JSONArray childArray = parent.getJSONArray("cats");
		        
		        mCategories = new String[childArray.length()];
		        mIds = new String[childArray.length()];
		        mDescriptions = new String[childArray.length()];
		        
		        
		        
		        for (int i=0; i<mCategories.length; i++)
		        {
		        	
		        	JSONObject child = childArray.getJSONObject(i).getJSONObject("post");
		        	Log.v("list", child.toString());
		        	mCategories[i] = child.getString("category_title");
		        	mIds[i] = child.getString("category_id");
		        	mDescriptions[i] = child.getString("category_description");
		        }
		        HashMap<String, String> hash;
				for (int i = 0; i < mCategories.length; i++) {
					hash = new HashMap<String, String>();
					hash.put("catName", mCategories[i]);
					hash.put("catDesc", mDescriptions[i]);
					hash.put("catId", mIds[i]);
					listy.add(hash);

				}
		        
		    } catch (ClientProtocolException e) {
		        // TODO Auto-generated catch block
		    } catch (IOException e) {
		        // TODO Auto-generated catch block 0722793945
		    } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			
			SimpleAdapter adp = new SimpleAdapter(Categories.this, listy,
					R.layout.list_item_primary, 
					new String[] { "catName","catDesc" },
					new int[] {R.id.tvFocus, R.id.tvMeta});
			setListAdapter(adp);
			ListView lv = getListView();
			
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int pos, long arg3) {
					String n = mIds[pos];
					Intent taskIntent = new Intent(Categories.this, Task.class);
					taskIntent.putExtra("param", n);
					startActivity(taskIntent);
					
				}
			});
			
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		
		getCats g = new getCats();
		g.execute();
		
//		setListAdapter(new ArrayAdapter<String>(Categories.this,
//				android.R.layout.simple_list_item_1, categories));
		}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.cool_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.watchlist:
			Intent i = new Intent("");
			startActivity(i);
			break;
		case R.id.preferences:
			Intent prefs = new Intent("");
			startActivity(prefs);
			break;
		case R.id.exit:
			finish();
			break;
		}
		return false;
	}

}
