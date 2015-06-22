package com.example.minsal_ecosf;

import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.map.layer.overlay.Marker;

import com.fichafamiliar.Ficha_familia_activity;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class InfoMarker extends Marker{
	private int id_estasib_user_sp;// id establecimiento
	private Context ctx;
	private int actionType;

	public InfoMarker(Context ctx, LatLong latLong, Bitmap bitmap, int horizontalOffset,
			int verticalOffset, int actionType) {
		super(latLong, bitmap, horizontalOffset, verticalOffset);
		this.ctx = ctx;
		this.actionType = actionType;
	}
	
	public void setIdEstasib (int id_estasib){
		id_estasib_user_sp = id_estasib;
	}

	@Override
	public boolean onTap(LatLong tapLatLong, Point layerXY, Point tapXY) {
		if (this.contains(layerXY, tapXY)) {
			switch(actionType){
				case 0:
					Intent i = new Intent(ctx, Ficha_familia_activity.class);
	        		i.putExtra("id_estasib_user_sp",id_estasib_user_sp);
	        		i.putExtra("action", "New");
	        		ctx.startActivity(i);
	        		break;
				case 1:
					Toast.makeText(ctx, "Ver detalle ficha", Toast.LENGTH_SHORT).show();
					break;
			}
			super.onTap(tapLatLong, layerXY, tapXY);
			return true;
		}else
			return false;
	}
}
