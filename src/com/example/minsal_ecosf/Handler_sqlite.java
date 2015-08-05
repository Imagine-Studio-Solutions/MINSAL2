package com.example.minsal_ecosf;

import org.apache.http.impl.cookie.BasicDomainHandler;
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

public Cursor menuNivel1(){
	BD = new DBHelper(ctx);
	BD.open();
	
	//Cursor c = BD.getReadableDatabase().rawQuery("SELECT  variable.denominacion, iddescriptor " +
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT variable.denominacion, iddescriptor " +
			"FROM descriptor, variable " +
			"WHERE variable.tipo_referente ='c'  AND variable.fechafin IS NULL " +
			"AND descriptor.iddescriptor = variable.id_descriptor " +
			"AND descriptor.descripciondescriptor IS NOT \"Valores Si y No\"	" +
			"ORDER BY  variable.denominacion ASC", null);
			//"ORDER BY  descripciondescriptor ASC", null);
	
	return c;
}

public Cursor menuNivel2(String id){
	BD = new DBHelper(ctx);
	BD.open();
	
	int idN1 = Integer.parseInt(id);
	Cursor c;
	
	switch(idN1){
	case 1:
		//Pueblo indígena
		c= BD.getReadableDatabase().rawQuery("SELECT puebloindigena.nombreetnia, puebloindigena.idpuebloindigena " +
				"FROM puebloindigena " +
				"ORDER BY puebloindigena.nombreetnia",null);
		return c;
		//break;
	case 2:
		//Religión
		/*c= BD.getReadableDatabase().rawQuery("SELECT religion.nombrereligion, religion.idreligion " +
				"FROM religion " +
				"WHERE religion.idreligion = 1 OR religion.idreligion = 2 OR religion.idreligion = 3 " +
				"OR religion.idreligion = 6 OR religion.idreligion = 7 " +
				"ORDER BY religion.nombrereligion",null);*/
		c= BD.getReadableDatabase().rawQuery("SELECT religion.nombrereligion, religion.idreligion " +
				"FROM religion " +
				"ORDER BY religion.nombrereligion",null);
		
		
		return c;
		//break;
	case 3:
		//Tipo de familia
		 c= BD.getReadableDatabase().rawQuery("SELECT tipofamilia.nombretipofamilia, tipofamilia.codigotipofamilia " +
		 		"FROM tipofamilia " +
		 		"ORDER BY tipofamilia.nombretipofamilia",null);
		return c;
		//break;
	default:
		c = BD.getReadableDatabase().rawQuery("SELECT descripcion, idvalor, valordescriptor.abreviatura " +
				"FROM descriptor, valordescriptor, variable " +
				"WHERE descriptor.iddescriptor = valordescriptor.id_descriptor " +
				"AND variable.tipo_referente ='c' " +
				"AND descriptor.iddescriptor = variable.id_descriptor " +
				"AND descriptor.iddescriptor = " + id +
				" ORDER BY valordescriptor.descripcion ASC", null);
		return c;
	}
	
	
	
}


public Cursor manejoDeAguasGrises (){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda, familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia.idfamilia_tablet = familia_variable.idfamiliavariable_tablet " +
			"AND familia_variable.id_variable = variable.idvariable " +
			"AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor =22 AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL", null);
	
	return c;	
}

/************************************************************************************************************
 * 
 *************************************** Query Dinámico  *******************************************
 * 
 **********************************************************************************************************/
public Cursor filtrar (int idN1, int idN2, String valor){
	BD = new DBHelper(ctx);
	BD.open();
	String idNivel1 = Integer.toString(idN1);
	String idNivel2 = Integer.toString(idN2);
	
	if (valor.equals("NA")){
		valor = "\""+valor+"\"";
	}
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " + 
												"FROM familia, familia_variable, descriptor, variable, valordescriptor "+ 
												"WHERE familia.idfamilia_tablet = familia_variable.id_familia_tablet "+
												"AND familia_variable.id_variable = variable.idvariable "+ 
												"AND variable.id_descriptor = descriptor.iddescriptor "+ 
												"AND descriptor.iddescriptor = "+idNivel1+ 
												" AND familia.longitud_vivienda NOTNULL "+ 
												"AND familia.latitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
												"AND familia_variable.valor = "+valor+
												" AND valordescriptor.abreviatura = familia_variable.valor "+
												"AND valordescriptor.idvalor = "+idNivel2, null);
			
	return c;		
		
}

public Cursor totalPorDescriptor  (int idN1, int idN2, String valor){
	BD = new DBHelper(ctx);
	BD.open();
	String idNivel1 = Integer.toString(idN1);
	String idNivel2 = Integer.toString(idN2);
	
	
	if (valor.equals("NA")){
		valor = "\""+valor+"\"";
	}
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT count() " +
			"FROM familia, familia_variable, descriptor, variable, valordescriptor " +
			"WHERE familia.idfamilia_tablet = familia_variable.id_familia_tablet " +
			"AND familia_variable.id_variable = variable.idvariable " +
			"AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = " + idNivel1 +
			" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.valor = " + valor +
			" AND valordescriptor.abreviatura = familia_variable.valor " +
			"AND valordescriptor.idvalor = "+idNivel2, null);
		
				
	return c;
				
}

public String numeroDeExpedienteRural(String lon, String lat){
	BD = new DBHelper(ctx);
	BD.open();
	String numE="";
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT (departamento.dpto_digestyc || municipios.munic_digestyc || " +
			"familia.codigo_area || cantones.canton_digestyc || familia.codigo_zona || familia.numerovivienda " +
			"|| familia.numerofamilia) as numero_expediente " +
			"FROM familia, departamento, municipios, area, cantones " +
			"WHERE familia.codigo_departamento = departamento.departamentoid " +
			"AND familia.codigo_municipio = municipios.idmunicipio " +
			"AND familia.codigo_area = \"R\" " +
			"AND familia.codigo_canton = cantones.idcanton " +
			"AND familia.latitud_vivienda = \""+lat+"\" "+
			"AND FAMIlia.longitud_vivienda = \""+lon+"\" ", null);
	
	try{	
		if(c.moveToFirst()){
			do{
				numE = c.getString(0);
			}while(c.moveToNext());
		}	
		//return numE;
	}catch(Exception e){
		
	}
	return numE;			
}


public String numeroDeExpedienteUrbano(String lon, String lat){
	BD = new DBHelper(ctx);
	BD.open();
	String numE="";
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT  (departamento.dpto_digestyc || municipios.munic_digestyc " +
			"|| familia.codigo_area || colonias.col_digestyc || familia.codigo_zona || familia.numerovivienda || " +
			"familia.numerofamilia) as numero_expediente " +
			"FROM familia, departamento, municipios, area, colonias " +
			"WHERE familia.codigo_departamento = departamento.departamentoid " +
			"AND familia.codigo_municipio = municipios.idmunicipio " +
			"AND familia.codigo_area = \"U\" " +
			"AND familia.codigo_colonia = colonias.idcolonia " +
			"AND familia.latitud_vivienda = \""+lat+"\" "+
			"AND FAMIlia.longitud_vivienda = \""+lon+"\" ", null);
	
	try{	
	
		if(c.moveToFirst()){
			do{
				numE = c.getString(0);
			}while(c.moveToNext());
		}	
		//return numE;
	}catch(Exception e){
		
	}
	return numE;
			
}



/************************************************************************************************************
 * 
 ***************************************** Bienes de familia ****************************************¨*****
 * 
 **********************************************************************************************************/

public Cursor filtrarSeleccionMuliple(int idN1, String valor){
	BD = new DBHelper(ctx);
	BD.open();
	String idNivel1 = Integer.toString(idN1);
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable " +
			"AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = "+idNivel1+" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet " +
			"AND familia_variable.valor REGEXP '\b"+valor+"\b'", null);
	
	return c;	
}

public Cursor cultivoAgricolaPropio(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable  " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor =59 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND familia_variable.valor LIKE '1%'", null);
	
	return c;	
}

public Cursor avesDeCorral(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable " +
			"AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor =59 " +
			"AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet " +
			"AND (familia_variable.valor LIKE '2%' OR familia_variable.valor LIKE '__2%')", null);
	
	return c;	
}

public Cursor ganadoVacuno(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor	" +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor =59 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet " +
			"AND (familia_variable.valor LIKE '3%' OR familia_variable.valor LIKE '__3%' OR familia_variable.valor LIKE '____3%')", null);
	
	return c;	
}

public Cursor ganadoPorcino(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor =59 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND (familia_variable.valor LIKE '4%' " +
			"OR familia_variable.valor LIKE '__4%' OR familia_variable.valor LIKE '____4%' " +
			"OR familia_variable.valor LIKE '______4%')", null);
	
	return c;	
}

public Cursor negocioPropio(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor =59 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND (familia_variable.valor LIKE '5%' " +
			"OR familia_variable.valor LIKE '__5%' OR familia_variable.valor LIKE '____5%' OR familia_variable.valor LIKE '______5%' " +
			"OR familia_variable.valor LIKE '________5%')", null);
	
	return c;	
}

public Cursor ningunBien(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor	" +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor =59 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND (familia_variable.valor LIKE '6%' " +
			"OR familia_variable.valor LIKE '__6%' OR familia_variable.valor LIKE '____6%' " +
			"OR familia_variable.valor LIKE '______6%' OR familia_variable.valor LIKE '________6%' " +
			"OR familia_variable.valor LIKE '__________6%') AND familia.longitud_vivienda IS NOT \"GPS Habilitado\" " +
			"AND familia.latitud_vivienda IS NOT \"GPS Habilitado\"", null);
	
	return c;	
}

/************************************************************************************************************
 * 
 ***************************************** Bienes del hogar****************************************¨*****
 * 
 **********************************************************************************************************/

public Cursor radio(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable  " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor =60 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND familia_variable.valor LIKE '1%'", null);
	
	return c;	
}

public Cursor equipoDeSonido(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor =60 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet " +
			"AND (familia_variable.valor LIKE '2%' OR familia_variable.valor LIKE '__2%')" +
			"AND familia.latitud_vivienda IS NOT \"GPS Habilitado\"", null);
	
	return c;	
}

public Cursor televisor(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor	" +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor =60 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet " +
			"AND (familia_variable.valor LIKE '3%' OR familia_variable.valor LIKE '__3%' OR familia_variable.valor LIKE '____3%')", null);
	
	return c;	
}

public Cursor refrigeradora(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor =60 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND (familia_variable.valor LIKE '4%' " +
			"OR familia_variable.valor LIKE '__4%' OR familia_variable.valor LIKE '____4%' " +
			"OR familia_variable.valor LIKE '______4%')", null);
	
	return c;	
}

public Cursor lavadora(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor =60 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND (familia_variable.valor LIKE '5%' " +
			"OR familia_variable.valor LIKE '__5%' OR familia_variable.valor LIKE '____5%' OR familia_variable.valor LIKE '______5%' " +
			"OR familia_variable.valor LIKE '________5%')", null);
	
	return c;	
}

public Cursor computadora(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor	" +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor =60 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND (familia_variable.valor LIKE '6%' " +
			"OR familia_variable.valor LIKE '__6%' OR familia_variable.valor LIKE '____6%' " +
			"OR familia_variable.valor LIKE '______6%' OR familia_variable.valor LIKE '________6%' " +
			"OR familia_variable.valor LIKE '__________6%') AND familia.longitud_vivienda IS NOT \"GPS Habilitado\" " +
			"AND familia.latitud_vivienda IS NOT \"GPS Habilitado\"", null);
	
	return c;	
}

public Cursor ningunBienHogar(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor =60 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND (familia_variable.valor LIKE '7%' " +
			"OR familia_variable.valor LIKE '__7%' OR familia_variable.valor LIKE '____7%' OR familia_variable.valor LIKE '______7%' " +
			"OR familia_variable.valor LIKE '________7%' OR familia_variable.valor LIKE '__________7%' " +
			"OR familia_variable.valor LIKE '____________7%')", null);
	
	return c;	
}


/************************************************************************************************************
 * 
* ***************************************** Medio de Comunicación****************************************
 * 
 **********************************************************************************************************/

public Cursor telefonoFijo(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable  " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 64 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND familia_variable.valor LIKE '1%'", null);
	
	return c;	
}

public Cursor telefonoCelular(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 64 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet " +
			"AND (familia_variable.valor LIKE '2%' OR familia_variable.valor LIKE '__2%')" +
			"AND familia.latitud_vivienda IS NOT \"GPS Habilitado\"", null);
	
	return c;	
}

public Cursor internet(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor	" +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 64 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet " +
			"AND (familia_variable.valor LIKE '3%' OR familia_variable.valor LIKE '__3%' OR familia_variable.valor LIKE '____3%')", null);
	
	return c;	
}

public Cursor cable(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 64 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND (familia_variable.valor LIKE '4%' " +
			"OR familia_variable.valor LIKE '__4%' OR familia_variable.valor LIKE '____4%' " +
			"OR familia_variable.valor LIKE '______4%')", null);
	
	return c;	
}

public Cursor noTieneMedioDeComunicacion(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 64 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND (familia_variable.valor LIKE '5%' " +
			"OR familia_variable.valor LIKE '__5%' OR familia_variable.valor LIKE '____5%' OR familia_variable.valor LIKE '______5%' " +
			"OR familia_variable.valor LIKE '________5%')", null);
	
	return c;	
}

/************************************************************************************************************
 * 
 ***************************************** Presencia de Vectores ********************************************
 * 
 **********************************************************************************************************/

public Cursor zancudos(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable  " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 70 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND familia_variable.valor LIKE '1%'", null);
	
	return c;	
}

public Cursor moscas(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 70 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet " +
			"AND (familia_variable.valor LIKE '2%' OR familia_variable.valor LIKE '__2%')" +
			"AND familia.latitud_vivienda IS NOT \"GPS Habilitado\"", null);
	
	return c;	
}

public Cursor chinchePicuda(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor	" +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 70 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet " +
			"AND (familia_variable.valor LIKE '3%' OR familia_variable.valor LIKE '__3%' OR familia_variable.valor LIKE '____3%')", null);
	
	return c;	
}

public Cursor cucarachas(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 70 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND (familia_variable.valor LIKE '4%' " +
			"OR familia_variable.valor LIKE '__4%' OR familia_variable.valor LIKE '____4%' " +
			"OR familia_variable.valor LIKE '______4%')", null);
	
	return c;	
}

public Cursor roedores(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 70 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND (familia_variable.valor LIKE '5%' " +
			"OR familia_variable.valor LIKE '__5%' OR familia_variable.valor LIKE '____5%' OR familia_variable.valor LIKE '______5%' " +
			"OR familia_variable.valor LIKE '________5%')", null);
	
	return c;	
}

public Cursor otrosVectores(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor	" +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 70 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND (familia_variable.valor LIKE '6%' " +
			"OR familia_variable.valor LIKE '__6%' OR familia_variable.valor LIKE '____6%' " +
			"OR familia_variable.valor LIKE '______6%' OR familia_variable.valor LIKE '________6%' " +
			"OR familia_variable.valor LIKE '__________6%') AND familia.longitud_vivienda IS NOT \"GPS Habilitado\" " +
			"AND familia.latitud_vivienda IS NOT \"GPS Habilitado\"", null);
	
	return c;	
}

public Cursor noHayPresenciaDeVectores(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 70 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND (familia_variable.valor LIKE '7%' " +
			"OR familia_variable.valor LIKE '__7%' OR familia_variable.valor LIKE '____7%' OR familia_variable.valor LIKE '______7%' " +
			"OR familia_variable.valor LIKE '________7%' OR familia_variable.valor LIKE '__________7%' " +
			"OR familia_variable.valor LIKE '____________7%')", null);
	
	return c;	
}

/************************************************************************************************************
 * 
** ***************************************** Riesgo Ambiental **********************************************
 * 
 **********************************************************************************************************/

public Cursor sinRiesgoAmbiental(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable  " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 61 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND familia_variable.valor LIKE '0%'", null);
	
	return c;	
}

public Cursor deslaves(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 61 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet " +
			"AND (familia_variable.valor LIKE '1%' OR familia_variable.valor LIKE '__1%')" +
			"AND familia.latitud_vivienda IS NOT \"GPS Habilitado\"", null);
	
	return c;	
}

public Cursor inundaciones(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor	" +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 61 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet " +
			"AND (familia_variable.valor LIKE '2%' OR familia_variable.valor LIKE '__2%' OR familia_variable.valor LIKE '____2%')", null);
	
	return c;	
}

public Cursor contaminacionPorDisposicionNoAdecuadaDeDesechosSolidosQuimicos(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 61 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND (familia_variable.valor LIKE '3%' " +
			"OR familia_variable.valor LIKE '__3%' OR familia_variable.valor LIKE '____3%' " +
			"OR familia_variable.valor LIKE '______3%')", null);
	
	return c;	
}

public Cursor erupcion(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor " +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 61 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND (familia_variable.valor LIKE '4%' " +
			"OR familia_variable.valor LIKE '__4%' OR familia_variable.valor LIKE '____4%' OR familia_variable.valor LIKE '______4%' " +
			"OR familia_variable.valor LIKE '________4%')", null);
	
	return c;	
}

public Cursor otrosRiesgosAmbientales(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda,familia_variable.valor	" +
			"FROM familia, familia_variable, descriptor, variable " +
			"WHERE familia_variable.id_variable = variable.idvariable AND variable.id_descriptor = descriptor.iddescriptor " +
			"AND descriptor.iddescriptor = 61 AND familia.longitud_vivienda NOTNULL AND familia.latitud_vivienda NOTNULL " +
			"AND familia_variable.id_familia_tablet = familia.idfamilia_tablet AND (familia_variable.valor LIKE '5%' " +
			"OR familia_variable.valor LIKE '__5%' OR familia_variable.valor LIKE '____5%' " +
			"OR familia_variable.valor LIKE '______5%' OR familia_variable.valor LIKE '________5%' " +
			"OR familia_variable.valor LIKE '__________5%') AND familia.longitud_vivienda IS NOT \"GPS Habilitado\" " +
			"AND familia.latitud_vivienda IS NOT \"GPS Habilitado\"", null);
	
	return c;	
}

/************************************************************************************************************
 * 
 ************************************************ Pueblos indígenas *******************************************
 * 
 **********************************************************************************************************/

public Cursor noPerteneceAPueblosIndigenas(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, puebloindigena WHERE familia.codigo_puebloindigena = puebloindigena.codigoetnia " +
			"AND puebloindigena.codigoetnia = \"01\" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL ", null);
	
	return c;	
}

public Cursor nahuatPipil(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, puebloindigena WHERE familia.codigo_puebloindigena = puebloindigena.codigoetnia " +
			"AND puebloindigena.codigoetnia = \"02\" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL ", null);
	
	return c;	
}

public Cursor chorti(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, puebloindigena WHERE familia.codigo_puebloindigena = puebloindigena.codigoetnia " +
			"AND puebloindigena.codigoetnia = \"03\" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL ", null);
	
	return c;	
}

public Cursor cacoperaOkakawira(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, puebloindigena WHERE familia.codigo_puebloindigena = puebloindigena.codigoetnia " +
			"AND puebloindigena.codigoetnia = \"04\" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL ", null);
	
	return c;	
}

public Cursor lenca(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, puebloindigena WHERE familia.codigo_puebloindigena = puebloindigena.codigoetnia " +
			"AND puebloindigena.codigoetnia = \"05\" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL ", null);
	
	return c;	
}

public Cursor noDatoPuebloIndigena(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, puebloindigena WHERE familia.codigo_puebloindigena = puebloindigena.codigoetnia " +
			"AND puebloindigena.codigoetnia = \"06\" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL ", null);
	
	return c;	
}


/************************************************************************************************************
 * 
 *************************************************** Religión *********************************************
 * 
 **********************************************************************************************************/

public Cursor ningunaReligion(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, religion WHERE familia.codigo_religion = religion.codigoreligion  " +
			"AND religion.codigoreligion = \"01\" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL ", null);
	
	return c;	
}


public Cursor catolicos(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, religion WHERE familia.codigo_religion = religion.codigoreligion  " +
			"AND religion.codigoreligion = \"02\" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL ", null);
	
	return c;	
}

public Cursor evangelicos(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, religion WHERE familia.codigo_religion = religion.codigoreligion  " +
			"AND religion.codigoreligion = \"03\" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL ", null);
	
	return c;	
}

public Cursor mormones(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, religion WHERE familia.codigo_religion = religion.codigoreligion  " +
			"AND religion.codigoreligion = \"04\" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL ", null);
	
	return c;	
}

public Cursor masDeUnaReligion(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, religion WHERE familia.codigo_religion = religion.codigoreligion  " +
			"AND religion.codigoreligion = \"05\" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL ", null);
	
	return c;	
}

public Cursor otraReligion(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, religion WHERE familia.codigo_religion = religion.codigoreligion  " +
			"AND religion.codigoreligion = \"06\" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL ", null);
	
	return c;	
}

public Cursor noDatoRelgion(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, religion WHERE familia.codigo_religion = religion.codigoreligion  " +
			"AND religion.codigoreligion = \"07\" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL ", null);
	
	return c;	
}

/************************************************************************************************************
 * 
 ************************************************ Tipo de Familia *******************************************
 * 
 **********************************************************************************************************/

public Cursor familiaNuclear(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, tipofamilia " +
			"WHERE familia.codigo_tipofamilia = tipofamilia.codigotipofamilia  " +
			"AND tipofamilia.codigotipofamilia = \"01\" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL", null);
	
	return c;	
}

public Cursor familiaMixtaOAmpliada(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, tipofamilia " +
			"WHERE familia.codigo_tipofamilia = tipofamilia.codigotipofamilia  " +
			"AND tipofamilia.codigotipofamilia = \"02\" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL", null);
	
	return c;	
}

public Cursor familiaExtensaExtendida(){
	BD = new DBHelper(ctx);
	BD.open();
	
	Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.longitud_vivienda, familia.latitud_vivienda " +
			"FROM familia, tipofamilia " +
			"WHERE familia.codigo_tipofamilia = tipofamilia.codigotipofamilia  " +
			"AND tipofamilia.codigotipofamilia = \"03\" AND familia.longitud_vivienda NOTNULL " +
			"AND familia.latitud_vivienda NOTNULL", null);
	
	return c;	
}



//--------------------------------------------------------------------------------------------------------------------------------------------
	public Cursor informacionFamilia(double lon, double lat){
	
		BD = new DBHelper(ctx);
		BD.open();		
		
		String longitud = Double.toString(lon);
		String latitud = Double.toString(lat);
		
		//Extrayendo la información de la BD
		Cursor c = BD.getReadableDatabase().rawQuery("SELECT familia.codigo_departamento, familia.codigo_municipio, familia.codigo_area, " +
				"familia.codigo_canton, familia.codigo_zona,  familia.numerovivienda, familia.numerofamilia, " +
				"(integrante.primernombre || \" \" || integrante.segundonombre || \" \" " +
				"|| integrante.primerapellido || \" \" ||integrante.segundoapellido) AS JefeFamilia " +
				"FROM familia, situacionvivienda, integrante " +
				"WHERE familia.codigo_sit_vivienda = situacionvivienda.codigosituacion AND familia.longitud_vivienda NOTNULL " +
				"AND familia.latitud_vivienda NOTNULL AND familia.tipo_riesgofamiliar NOTNULL " +
				"AND familia.idfamilia_tablet= integrante.idfamilia_tablet " +
				"AND integrante.numerocorrelativo = \"01\" AND familia.codigo_canton NOTNULL " +
				"AND familia.longitud_vivienda = \""+longitud+"\" AND familia.latitud_vivienda = \""+latitud+"\"",null);
		
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
