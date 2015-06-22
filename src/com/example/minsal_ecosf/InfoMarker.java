package com.example.minsal_ecosf;

import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.map.layer.overlay.Marker;

import com.fichafamiliar.Busqueda_ficha;
import com.fichafamiliar.Ficha_familia_activity;
import com.fichafamiliar.Ver_detalle_ficha;

import db_gestion.GestionDB;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class InfoMarker extends Marker{
	private int id_estasib_user_sp;// id establecimiento
	private Context ctx;
	private int actionType;
	private boolean tieneExp;
	private GestionDB objGestionDB;
	private int depto, municipio, ctn_bar_col; 
	private String area, zona, num_vivienda, num_familia;

	public InfoMarker(Context ctx, LatLong latLong, Bitmap bitmap, int horizontalOffset,
			int verticalOffset, int actionType, boolean tieneExp, int depto, int municipio, 
			String area, int ctn_bar_col, String zona, String num_vivienda, String  num_familia) {
		super(latLong, bitmap, horizontalOffset, verticalOffset);
		this.ctx = ctx;
		this.actionType = actionType;
		//this.numExp = numExp;
		this.tieneExp = tieneExp;
		this.objGestionDB = new GestionDB();
		this.depto = depto;
		this.municipio = municipio;
		this.area = area;
		this.ctn_bar_col = ctn_bar_col;
		this.zona = zona;
		this.num_vivienda = num_vivienda;
		this.num_familia = num_familia;
	}
	
	public void setIdEstasib (int id_estasib){
		id_estasib_user_sp = id_estasib;
	}

	@Override
	public boolean onTap(LatLong tapLatLong, Point layerXY, Point tapXY) {
		if (this.contains(layerXY, tapXY)) {
			Intent i;
			switch(actionType){
				case 0:
					i = new Intent(ctx, Ficha_familia_activity.class);
	        		i.putExtra("id_estasib_user_sp",id_estasib_user_sp);
	        		i.putExtra("action", "New");
	        		ctx.startActivity(i);
	        		break;
				case 1:
					if (tieneExp){
						int existe=objGestionDB.existeNumExpediente(depto, municipio, area,ctn_bar_col,zona,num_vivienda,num_familia,ctx);
						if(existe==0){
							Toast.makeText(ctx, "No existe este número de expediente", Toast.LENGTH_SHORT).show();
						}else{
							objGestionDB.getIdFamilia_direccion(depto, municipio, area,ctn_bar_col,zona,num_vivienda,num_familia,ctx);
							Toast.makeText(ctx, "IdFamilia: "+objGestionDB.idfamilia, Toast.LENGTH_SHORT).show();
							Toast.makeText(ctx, "DirFamilia: "+objGestionDB.direccionFamilia, Toast.LENGTH_SHORT).show();
							i = new Intent(ctx, Ver_detalle_ficha.class);
							i.putExtra("busquedaPor",2);//si la busqueda es por jefe de familia busquedaPor=1
														//si la busqueda es por numero de expediente familiar busquedaPor=2
							i.putExtra("idfamilia",objGestionDB.idfamilia);
							i.putExtra("direccion",objGestionDB.direccionFamilia);
							ctx.startActivity(i);
						}
					}
					break;
			}
			super.onTap(tapLatLong, layerXY, tapXY);
			return true;
		}else
			return false;
	}
}
