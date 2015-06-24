package com.example.minsal_ecosf;

import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.layer.overlay.Marker;

import com.fichafamiliar.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MyMarker extends Marker{
	private MapView mapView;
	private Bitmap bubble;
	private boolean tapped = false;
	private Marker markerBubble;
	private String content;
	private Button crearFicha;
	private Button verFicha;
	private LatLong pointerPosition;
	private int infoType;
	private boolean tieneExp;
	private int depto; 
	private int municipio;
	private String area;
	private int ctn_bar_col;
	private String zona, num_vivienda, num_familia;
	private Context ctx;
	
	private int id_estasib_user_sp;// id establecimiento

	public MyMarker(final Context ctx, LatLong latLong, Bitmap bitmap, int horizontalOffset,
			int verticalOffset, MapView mapView, String bubbleContent, boolean gpsPointer, boolean tieneExp, 
			int depto, int municipio, String area, int ctn_bar_col, String zona, String num_vivienda, String  num_familia) {
		super(latLong, bitmap, horizontalOffset, verticalOffset);
		this.mapView = mapView; 
		this.tieneExp = tieneExp;
		this.depto = depto; 
		this.municipio = municipio;
		this.area = area;
		this.ctn_bar_col = ctn_bar_col;
		this.zona = zona; 
		this.num_vivienda = num_vivienda; 
		this.num_familia = num_familia;
		this.ctx = ctx;
		content = bubbleContent; 
		pointerPosition = latLong;
		
		LayoutInflater inflater = (LayoutInflater) ctx.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View bubbleView = inflater.inflate(R.drawable.info_window, null);
		crearFicha = (Button) bubbleView.findViewById(R.id.bCrearFicha); 
		verFicha = (Button) bubbleView.findViewById(R.id.bVerFicha);
		
		if (gpsPointer){
			crearFicha.setVisibility(View.VISIBLE);
	     	verFicha.setVisibility(View.GONE);
	     	infoType = 0;
		} else {
			crearFicha.setVisibility(View.GONE);
	     	verFicha.setVisibility(View.VISIBLE);
	     	infoType = 1;
		}
		TextView textArea = (TextView) bubbleView.findViewById( R.id.contenido );
		setBackground(bubbleView, ctx.getResources().getDrawable(R.drawable.balloon_overlay_unfocused));
		textArea.setGravity(Gravity.CENTER);
		textArea.setMaxEms(20);
		textArea.setTextSize(18);
		textArea.setTextColor(Color.BLACK);
		textArea.setText(content);
		bubble = viewToBitmap(ctx, bubbleView);
		bubble.incrementRefCount();
	}
	@Override
	public boolean onTap(LatLong tapLatLong, Point layerXY, Point tapXY) {
		if (this.contains(layerXY, tapXY) && !tapped) {
			markerBubble = new InfoMarker(ctx, tapLatLong, bubble, 0, -bubble.getHeight()/2, infoType, tieneExp, depto, 
					municipio, area, ctn_bar_col, zona, num_vivienda, num_familia);
			((InfoMarker) markerBubble).setIdEstasib(id_estasib_user_sp);
			mapView.getLayerManager().getLayers().add(markerBubble);
			tapped = !tapped;
			super.onTap(tapLatLong, layerXY, tapXY);
			return true;
		}else if (!this.contains(layerXY, tapXY) && tapped) {
			mapView.getLayerManager().getLayers().remove(markerBubble);
			tapped = !tapped;
			return true;
		}else
			return false;
	}
	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static void setBackground(View view, Drawable background) {
		if (background == null) {
			
		}
		if (android.os.Build.VERSION.SDK_INT >= 16) {
			view.setBackground(background);
		} else {
			view.setBackgroundDrawable(background);
		}
	}
	
	static Bitmap viewToBitmap(Context c, View view) {
		view.measure(MeasureSpec.getSize(view.getMeasuredWidth()),
				MeasureSpec.getSize(view.getMeasuredHeight()));
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		view.setDrawingCacheEnabled(true);
		Drawable drawable = new BitmapDrawable(c.getResources(),
				android.graphics.Bitmap.createBitmap(view.getDrawingCache()));
		view.setDrawingCacheEnabled(false);
		return AndroidGraphicFactory.convertToBitmap(drawable);
	}
	
	public void setIdEstasib (int id_estasib){
		id_estasib_user_sp = id_estasib;
	}
}
