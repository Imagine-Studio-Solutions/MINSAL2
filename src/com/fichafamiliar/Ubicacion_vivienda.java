package com.fichafamiliar;

import java.io.IOException;

import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Ubicacion_vivienda extends Activity {
	//private Ver_detalle_ficha classVerDetalleFicha= new Ver_detalle_ficha();
	TextView t_latitud,t_longitud,t_lat,t_lon;
	int gps_bandera_clik=0;
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;
	String action="";
	int idfamilia;
	int id_sp;					// id usuario tablet
	int id_estasib_user_sp; 	// id establecimiento
	String  nombreusuario; 		// nombre usuario
	int  id_sibasi;				// id sibasi
	int correlativo_tablet;		// correlativo tablet
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ubicacion_vivienda);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		
		t_latitud 	= 	(TextView) findViewById(R.id.txtview_latitud);
		t_longitud 	=	(TextView) findViewById(R.id.txtview_longitud);
		
		t_lat = (TextView) findViewById(R.id.txtview_lat);
		t_lon = (TextView) findViewById(R.id.txtview_lon);
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		id_sp = preferencias.getInt("id_sp", 0);// id usuario tablet
		id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0); // id establecimiento
		nombreusuario=preferencias.getString("nombreusuario_sp", ""); //nombre usuario
		id_sibasi = preferencias.getInt("id_sibasi_sp", 0);//id sibasi
		correlativo_tablet=preferencias.getInt("correlativo_tablet",0);//correlativo tablet
		 	
		
		Bundle bundle = getIntent().getExtras();
		action= bundle.getString("action");//para saber si viene de "Edit" o "New"
		idfamilia= bundle.getInt("idfamilia");//para saber si viene de "Edit" o "New"
		//t_lat.setText(action+" + "+idfamilia);
		
		this.objGestionDB = new GestionDB();// creo el objeto de la DB
		/*BaseDeDatos db2 = new BaseDeDatos(this);
		try 
		{
			db2.createDataBase();
			// db2.openDataBase();
			this.conexOpen = db2.myDataBase;
			this.objGestionDB = new GestionDB(this);// creo el objeto de la clase que gestiona la DB
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
 
	public void ToastUpdate(String texto) {
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.toastupdate,(ViewGroup) findViewById(R.id.Linear_Update_Ficha)); //"inflamos" nuestro layout
		TextView text = (TextView) layout.findViewById(R.id.text_Update_Ficha);
		 
		text.setText(texto); //texto a mostrar y asignado al textView de nuestro layout
		Toast toast = new Toast(getApplicationContext()); //Instanciamos un objeto Toast
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0); //lo situamos centrado verticalmente en la pantalla
		toast.setDuration(Toast.LENGTH_LONG); //duracion del toast
		toast.setView(layout); //asignamos nuestro layout personalizado al objeto Toast
		toast.show(); //mostramos el Toast en pantalla
		}
	public void guardar_gps(View v){
		String s_latitud=t_latitud.getText().toString();
		String s_longitud=t_longitud.getText().toString();
		
		//if(gps_bandera_clik==0){
		if((s_latitud.equals(""))||(s_longitud.equals("")))
		{
			Toast click_gps=Toast.makeText(this, "Haga click en el boton de capturar posición GPS de la vivienda", Toast.LENGTH_LONG);
			click_gps.setGravity(Gravity.CENTER, 0, 0);
			click_gps.show();
		}
		else if((s_latitud.equals("GPS Deshabilitado"))||(s_longitud.equals("GPS Deshabilitado")))
		{
			Toast click_gps=Toast.makeText(this, "Haga click en el boton de capturar posición GPS de la vivienda", Toast.LENGTH_LONG);
			click_gps.setGravity(Gravity.CENTER, 0, 0);
			click_gps.show();
		}
		else if((s_latitud.equals("GPS Habilitado"))||(s_longitud.equals("GPS Habilitado")))
		{
			Toast click_gps=Toast.makeText(this, "Haga click en el boton de capturar posición GPS de la vivienda", Toast.LENGTH_LONG);
			click_gps.setGravity(Gravity.CENTER, 0, 0);
			click_gps.show();
		}
		else{
			ContentValues newValues = new ContentValues(); 
			newValues.put("latitud_vivienda", s_latitud); 
			newValues.put("longitud_vivienda",s_longitud);
			
			objGestionDB.updateReg("familia",newValues, "idfamilia_tablet="+idfamilia,this.contexto);
			
			
			
			ToastUpdate("Se ha actualizado el registro");
			Intent i = new Intent(this, Ver_detalle_ficha.class);
			i.putExtra("busquedaPor",3);		//porque ha hecho el edit       busquedaPor=3
			i.putExtra("idfamilia",idfamilia);	//pasar los datos que se necesitaran en la siguiente activity
			finish();
			//classVerDetalleFicha.ActivityVerDetalleFicha.finish();
			startActivity(i);
			
		}
	}
	public void get_geoPosition(View v){
		//obtenemos nuestro LocationManager
		LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

		// check if enabled and if not send user to the GSP settings
		// Better solution would be to display a dialog and suggesting to 
		// go to the settings
		if (!enabled) {
		  /*Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		  startActivity(intent);*/
			showGPSDisabledAlertToUser();
		} 
		else
		{
			//creamos nuestro listener de GPS
			DemoLocationListener gpsListener = new DemoLocationListener(t_latitud, t_longitud);
			//asignamos el listener de GPS
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, gpsListener);
			
		}
		/*
		LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
		{
			gps_bandera_clik=1;
			DemoLocationListener gpsListener = new DemoLocationListener(t_latitud, t_longitud);
			//asignamos el listener de GPS
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, gpsListener);
			try {
			} catch (Exception e) {
				//TODO: handle exception
			}
		}
		else
		{
        	if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        	{
        		gps_bandera_clik=1;
        		DemoLocationListener gpsListener = new DemoLocationListener(t_latitud,t_longitud);
    			//asignamos el listener de GPS
    			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, gpsListener);
        	}
        	else
        	{
        		showGPSDisabledAlertToUser();
        	}
        }*/
		
		
	}
	/*
	private void showGPSDisabledAlertToUser(){   
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setMessage("GPS esta desactivado en tu dispositivo. Desea Activarlo?").setCancelable(false).setPositiveButton("Ir a la Configuración para Activar el GPS",
            new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id){
					Intent callGPSSettingIntent = new Intent(
                    android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(callGPSSettingIntent);
        }
    });
    alertDialogBuilder.setNegativeButton("Cancelar",new DialogInterface.OnClickListener(){
        public void onClick(DialogInterface dialog, int id){
            dialog.cancel();	
        }
    });
    AlertDialog alert = alertDialogBuilder.create();
    alert.show();
	
}*/
	public void showGPSDisabledAlertToUser(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS esta desactivado en tu dispositivo. Desea Activarlo?")
        .setCancelable(false)
        .setPositiveButton("Ir a la Configuración para Activar el GPS",
                new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                Intent callGPSSettingIntent = new Intent(
                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(callGPSSettingIntent);
            }
        });
        alertDialogBuilder.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                dialog.cancel();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
		
	}
}
