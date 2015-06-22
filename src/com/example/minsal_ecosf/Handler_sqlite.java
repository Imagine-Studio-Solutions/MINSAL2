package com.example.minsal_ecosf;

import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.core.graphics.Bitmap;

import com.fichafamiliar.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import static android.provider.BaseColumns._ID;

public class Handler_sqlite extends SQLiteOpenHelper{
	private Context ctx;
	private MapView mapView;
	private DBHelper BD;
	public Handler_sqlite(Context ctx,MapView mapView)
	{
		super(ctx, "MINSAL", null,1);
		this.ctx = ctx;
		this.mapView = mapView;
		
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		
		String query = "CREATE TABLE FICHA ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+"latitud REAL, longitud REAL);";	
		db.execSQL(query);
		
	}	
	@Override
	public void onUpgrade(SQLiteDatabase db,int v_anterior, int v_nueva)
	{
		
		db.execSQL("DROP TABLE FICHA IF EXISTS");
		onCreate(db);
		
	}
	
	public void insertarFicha(int id, double latitud, double longitud)
	{
		
		ContentValues valores = new ContentValues();
		valores.put("latitud", latitud);
		valores.put("longitud", longitud);
		this.getWritableDatabase().insert("FICHA", null, valores);
		
	}
	
	public String[] leer()
	{
		
		String result[] = new String[3];
		String columnas[]= {_ID,"latitud","longitud"};
		Cursor c = this.getReadableDatabase().query("FICHA", columnas, null, null, null,null, null);
		
		int id,ilat,ilong;
		id = c.getColumnIndex(_ID);
		ilat = c.getColumnIndex("latitud");
		ilong = c.getColumnIndex("longitud");
		
		int contador=0;
//		for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
//		{
//			
//			result[contador] = c.getString(id)+ "  " + c.getString(ilat)+ "  " + c.getString(ilong) + "\n";
//			Bitmap pointer = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.green_house));
//			String bubbleContent = "Id de ficha: 097896-456-12\nJefe de Familia: Pedro Perez";
//			//MyMarker marker = new MyMarker(ctx, new LatLong(Double.parseDouble(c.getString(ilat)),Double.parseDouble(c.getString(ilong))), pointer, 0, 0, mapView, bubbleContent, false, false, depto,municipio,area + ctn_bar_col + zona + num_vivienda + num_familia);
//			//mapView.getLayerManager().getLayers().add(marker);
//		}
//		
//		result = c.getString(id)+ "  " + c.getString(ilat)+ "  " + c.getString(ilong) + "\n";
		return result;
				
	}
	
	
	public Cursor infoFicha(){
		
		BD = new DBHelper(ctx);
		BD.open();		
		
		//Extrayendo la información de la BD
		Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda, " +
				"familia.tipo_riesgofamiliar, situacionvivienda.codigosituacion,  " +
				"(familia.codigo_departamento || familia.codigo_municipio || familia.codigo_area || familia.codigo_canton || familia.codigo_zona || familia.numerovivienda || familia.numerofamilia) AS CodigoFamilia,  " +
				"(integrante.primernombre || \" \" || integrante.segundonombre || \" \" || integrante.primerapellido || \" \" || integrante.segundoapellido) AS JefeFamilia " +
				"FROM	familia, situacionvivienda, integrante " +
				"WHERE familia.codigo_sit_vivienda = situacionvivienda.codigosituacion " +
				"AND familia.longitud_vivienda NOTNULL " +
				"AND familia.latitud_vivienda NOTNULL AND familia.tipo_riesgofamiliar NOTNULL " +
				"AND familia.idfamilia = integrante.id_familia " +
				"AND integrante.numerocorrelativo = \"01\"",null);
		
		return c;
		
	}
	
public Cursor numExpediente(){
		
		BD = new DBHelper(ctx);
		BD.open();		
		
		//Extrayendo la información de la BD
		Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda, " +
				"familia.tipo_riesgofamiliar, situacionvivienda.codigosituacion, " +
				"familia.codigo_departamento, familia.codigo_municipio, familia.codigo_area, familia.codigo_canton," +
				"familia.codigo_zona, familia.numerovivienda, familia.numerofamilia, " +
				"(integrante.primernombre || \" \" || integrante.segundonombre || \" \" || integrante.primerapellido || \" \" ||integrante.segundoapellido) AS JefeFamilia " +
				"FROM familia, situacionvivienda, integrante " +
				"WHERE familia.codigo_sit_vivienda = situacionvivienda.codigosituacion " +
				"AND familia.longitud_vivienda NOTNULL " +
				"AND familia.latitud_vivienda NOTNULL " +
				"AND familia.tipo_riesgofamiliar NOTNULL " +
				"AND familia.idfamilia = integrante.id_familia " +
				"AND integrante.numerocorrelativo = \"01\"" +
				"AND familia.codigo_canton NOTNULL",null);
		
		return c;
		
	}




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Cursor viviendasDeshabitadas(){
		BD = new DBHelper(ctx);
		BD.open();		
		
		//Extrayendo la información de la BD
		Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
				"FROM familia " +
				"WHERE familia.codigo_sit_vivienda = \"02\" " +
				"AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL",null);
		return c;
	}
	
	
	public void abrir()
	{
		
		this.getWritableDatabase();
		
	}
	public void cerrar()
	{
		
		this.close();
		
	}
	
	
	
	
	
}
