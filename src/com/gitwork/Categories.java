package com.gitwork;

import java.io.IOException;
import java.util.ArrayList;
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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Categories extends ListActivity {

	String[] categories, ids;
	
	public class getCats extends AsyncTask<Void, Void, Void>
	{

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
		        
		        categories = new String[childArray.length()];
		        ids = new String[childArray.length()];
		        
		        JSONObject child;
		        
		        for (int i=0; i<categories.length; i++)
		        {
		        	child = childArray.getJSONObject(i).getJSONObject("post");
		        	categories[i] = child.getString("category_title") +"\n"+child.getString("category_description");
		        	ids[i] = child.getString("category_id");
		        }
		        
		    } catch (ClientProtocolException e) {
		        // TODO Auto-generated catch block
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
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
			// TODO Auto-generated method stub
			//super.onPostExecute(result);
			setListAdapter(new ArrayAdapter<String>(Categories.this,
				android.R.layout.simple_list_item_1, categories));
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
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
