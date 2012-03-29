package com.gitwork;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class customPinPoint extends ItemizedOverlay<OverlayItem> {
	private ArrayList<OverlayItem> pinpoints = new ArrayList<OverlayItem>();
	private Context ctx;

	public customPinPoint(Drawable defaultMarker) {
		super(boundCenter(defaultMarker));
		// TODO Auto-generated constructor stub
	}

	public customPinPoint(Drawable m, Context c) {
		this(m);
		ctx = c;
	}

	@Override
	protected boolean onTap(int index) {
		OverlayItem ovi = pinpoints.get(index);
		Context c = this.ctx;
		Toast.makeText(c, ovi.getSnippet(), Toast.LENGTH_SHORT).show();
		return true;
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return pinpoints.get(i);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return pinpoints.size();
	}

	public void insertPinPoint(OverlayItem item) {
		pinpoints.add(item);
		this.populate();
	}

}
