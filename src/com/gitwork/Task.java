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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleAdapter;

public class Task extends ListActivity{
	
	String mActivity;
	
	public class ActivitiesFetcher extends AsyncTask<Void, Void, Void>
	{
		String[] mTitles, mRequirements, mDatePosted, mDeadLine, mOwnerName, mTaskId;
		ArrayList<HashMap<String, String>> listy = new ArrayList<HashMap<String,String>>();
		
		@Override
		protected Void doInBackground(Void... arg0) {
			

			HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost = new HttpPost("http://zacckos.heliohost.org/git_work/activities_populate.php");

		    try {
		        // Add your data
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		        nameValuePairs.add(new BasicNameValuePair("cat", mActivity));
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		        // Execute HTTP Post Request
		        HttpResponse response = httpclient.execute(httppost);
		        //String resp = EntityUtils.toString(response.getEntity());
		        //Log.v("resp", resp);
		        
		        JSONObject parent = new JSONObject(EntityUtils.toString(response.getEntity()));
		        
		        JSONArray childArray = parent.getJSONArray("jobs");
		        
		        mTitles = new String[childArray.length()];
		        mRequirements = new String[childArray.length()];
		        mDatePosted = new String[childArray.length()];
		        mOwnerName = new String[childArray.length()];
		        mDeadLine = new String[childArray.length()];
		        mTaskId = new String[childArray.length()];
		       
		        
		        
		        
		        for (int i=0; i<mTitles.length; i++)
		        {
		        	
		        	JSONObject child = childArray.getJSONObject(i).getJSONObject("post");
		        	Log.v("resp", child.toString());
		        	
		        	mTitles[i] = child.getString("task_name");
			        mRequirements[i] = child.getString("task_requirements");
			        mDatePosted[i] = child.getString("post_date");
			        mOwnerName[i] = child.getString("username");
			        mDeadLine[i] = child.getString("deadline");
			        mTaskId[i] = child.getString("task_id");
		        }
		        HashMap<String, String> hash;
				for (int i = 0; i < mTitles.length; i++) {
					hash = new HashMap<String, String>();
					hash.put("tName", mTitles[i]);
					hash.put("tReq", mRequirements[i]);
					hash.put("pDate", "Date Posted: "+mDatePosted[i]);
					hash.put("dDate", "Deadline: "+mDeadLine[i]);
					hash.put("owner", mOwnerName[i]);
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
			
			SimpleAdapter adp = new SimpleAdapter(Task.this, listy,
					R.layout.task_detail, 
					new String[] { "tName","tReq", "pDate","dDate","owner" },
					new int[] {R.id.tvTname, R.id.tvTreq, R.id.tvTpost, R.id.tvDead, R.id.tvOwner});
			setListAdapter(adp);
			
		}
		
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		
		mActivity = getIntent().getExtras().getString("param");
		Log.v("param", mActivity);
		
		ActivitiesFetcher af = new ActivitiesFetcher();
		af.execute();
	}

}
