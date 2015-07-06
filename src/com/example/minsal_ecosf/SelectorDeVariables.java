package com.example.minsal_ecosf;

import java.util.ArrayList;

import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.view.MapView;

import com.fichafamiliar.R;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

public class SelectorDeVariables {
	
	private ArrayList<MyMarker> markerList = new ArrayList<MyMarker>();
	private Context ctx;
	private Handler_sqlite manejador;
	private MapView mapView;
	private Bitmap p;
	
	public SelectorDeVariables(Context ctx, Handler_sqlite manejador, MapView mapView, int idNivel1,int idOpcion2Seleccionada) {
		this.ctx = ctx;
		this.manejador = manejador;
		this.mapView = mapView;
		
		switch(idNivel1){
    	
	    	case 22:
	    		//Manejo de Aguas Grises
	    			Cursor c; 
	    			
	    			switch (idOpcion2Seleccionada){
	    			case 119:
	    				//A cielo abierto al solar
	    				c= manejador.aCieloAbiertoAlSolar();
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));
	    				dibujarPuntosFiltrados (c, p);
	    				break;
	    				
	    			case 120:
	    				//A la calle
	    				c= manejador.aLaCalle();
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.a_la_calle));
	    				dibujarPuntosFiltrados (c, p);
	    				break;
	    				
	    			case 117:
	    				//La eliminación es a alcantarillado
	    				c= manejador.eliminacionAAlcantarillado();
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.es_a_alcantarillado));
	    				dibujarPuntosFiltrados (c, p);
	    				break;
	    				
	    			case 118:
	    				//Por sistema de pozo resumidero
	    				c= manejador.porPozoResumidero();
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.pozo_resumidero));
	    				dibujarPuntosFiltrados (c, p);
	    				break;	
	    					
	    				
	    			case 121:
	    				//Quebradas ríos u otro lugar
	    				c= manejador.quebradasORios();
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.quebrada_rio));
	    				dibujarPuntosFiltrados (c, p);
	    				break;
    			}
    	}
	}
	
	public void dibujarPuntosFiltrados (Cursor c, Bitmap p){
		if(c.moveToFirst()){
			do{
				double lon = c.getDouble(0);
				double lat = c.getDouble(1);
				
				Cursor info = manejador.informacionFamilia(lon, lat);

				try{
					if(info.moveToFirst()){
						do{
							int depto = info.getInt (0);
    						int municipio = info.getInt(1);
    						String area = info.getString(2);
    					    int ctn_bar_col = info.getInt (3);				    
    					    String zona = info.getString(4);
    					    String num_vivienda = info.getString(5);
    					    String num_familia = info.getString(6);
    					    			
    					    String num_exp = info.getString(0)+ info.getString(1)+ info.getString(2)+info.getString(3)+info.getString(4)+info.getString(5)+info.getString(6);
    					    
    						MyMarker fichaFiltro = new MyMarker(ctx, new LatLong(lat,lon), p, 0, 0, mapView, "Número de Expediente: "+num_exp+"\nJefe de Familia: "+info.getString(7), false, true, depto,municipio,area,ctn_bar_col,zona,num_vivienda,num_familia);
    						mapView.getLayerManager().getLayers().add(fichaFiltro);
    						markerList.add(fichaFiltro);
    						
						}while(info.moveToNext());
					}
				}
				catch (Exception e){
					Toast.makeText(ctx, "Error:"+ e, Toast.LENGTH_SHORT).show();
				}					    								    						
									    										    											    						
			}while (c.moveToNext());
		}
	}
	
	public ArrayList<MyMarker> getVariablesDibujadas (){
		return markerList;
	}
	
}
