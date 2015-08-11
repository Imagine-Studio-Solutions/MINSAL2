package com.fichafamiliar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.util.AndroidUtil;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.layer.Layer;
import org.mapsforge.map.layer.Layers;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.overlay.Marker;
import org.mapsforge.map.layer.renderer.TileRendererLayer;
import org.mapsforge.map.rendertheme.InternalRenderTheme;

import com.example.minsal_ecosf.*;
import com.fichafamiliar.R;
import com.fichafamiliar.R.string;
 
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.location.LocationManager;

import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivityMapa extends Activity {
	
	private MapView mapView;
	private TileCache tileCache;
	private TileRendererLayer tileRendererLayer;
	private MyMarker gpsPointer;
	
	//---------------------Menu lateral-------------------------
	private DrawerLayout drawerLayout;
	private ListView drawer;
	private ActionBarDrawerToggle toggle;
	//Menú Nivel 1
	private String opciones[], idMenu[];
	//=================Para mostrar fichas segun filtro=================
	private Bitmap p;
	private MyMarker fichaFiltro;
	///=================================================================
	//Menú nivel 2
	private List<String> listItems = new ArrayList<String>();
	int band=0;
	//Para el menú usando BD
	//Para nivel 1
	private Cursor cMenu;
	//Para subítems
	private Cursor cSubmenu;
	private String idSubMenu[];
	private String valores[];
	private String valor;
	//Para el submenú 
	private String encabezado; 
	private int idNivel1;
    
	private int id_estasib_user_sp; 
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
 
	//para listener...
	private MyLocationListener mlocListener;
	private boolean modoSeguimiento = true;
		
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidGraphicFactory.createInstance(getApplication());
		getActionBar().setIcon(R.drawable.logo_uca_ecosf);
		setContentView(R.layout.main_activity_mapa);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		//===============================================================================================
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0);
		//===============================================================================================
		
		mapView = (MapView)findViewById(R.id.mapView);
		mapView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
 
		mapView.setClickable(true);
 
		// create a tile cache of suitable size
		tileCache = AndroidUtil.createTileCache(this, "mapcache",
				mapView.getModel().displayModel.getTileSize(), 1f, 
				this.mapView.getModel().frameBufferModel.getOverdrawFactor());
 
		mapView.getModel().mapViewPosition.setZoomLevel((byte) 15);
		mapView.getMapZoomControls().setZoomLevelMin((byte) 10);
		mapView.getMapZoomControls().setZoomLevelMax((byte) 30);
 
		// tile renderer layer using internal render theme
		tileRendererLayer = new TileRendererLayer(tileCache, this.mapView.getModel().mapViewPosition, false, true, AndroidGraphicFactory.INSTANCE);
		
		File f = new File(getCacheDir()+"/elsalvador.map");
		  if (!f.exists()) try {

		    InputStream is = getAssets().open("elsalvador.map");
		    int size = is.available();
		    byte[] buffer = new byte[size];
		    is.read(buffer);
		    is.close();


		    FileOutputStream fos = new FileOutputStream(f);
		    fos.write(buffer);
		    fos.close();
		  } catch (Exception e) { throw new RuntimeException(e); }
		
		tileRendererLayer.setMapFile(new File(f.getPath()));
		tileRendererLayer.setXmlRenderTheme(InternalRenderTheme.OSMARENDER);
 
		// only once a layer is associated with a mapView the rendering starts
		mapView.getLayerManager().getLayers().add(tileRendererLayer);

		mapView.setBuiltInZoomControls(false);
		mapView.getMapScaleBar().setVisible(true);
 
		mapView.getModel().mapViewPosition.setCenter(new LatLong(13.6801783,-89.231388));//punto inical del mapa
 
		gpsPointer = new MyMarker(this, new LatLong(13.6801783,-89.231388), AndroidGraphicFactory.convertToBitmap(getResources().getDrawable(R.drawable.pointer)),
				0, 0, mapView, "Posicion actual del equipo", true, false,0, 0, "", 0,"", "", "");
		gpsPointer.setIdEstasib(id_estasib_user_sp);
		mapView.getLayerManager().getLayers().add(gpsPointer);
		
		//------------------------------------------------------------------------------------
		
		//Instancio la clase del manejador de la BASE DE DATOS.
		final Handler_sqlite manejador = new Handler_sqlite(this, mapView);
        
		manejador.abrir();
		manejador.insertarFicha(1,13.6801783,-89.136031);
		
		
		String[] x = manejador.leer();
		
		dibujarCasitas();	
		
		
		
		//-------------------------------------Menu lateral-----------------------------------
		// Rescatamos el Action Bar y activamos el boton Home
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);	
				
		drawer = (ListView) findViewById(R.id.drawer);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		
		cMenu = manejador.menuNivel1();		
		int cant = cMenu.getCount(); 
		opciones = new String[cant+4];
		idMenu = new String [cant+4];
		
		//Para filtros de catálogos
		opciones[0]= "Riesgo o Vulnerabilidad";
		opciones[1]="Pueblo indígena";
		opciones[2]="Religión";
		opciones[3]="Tipo de Familia";
		
		idMenu[0]="4";
		idMenu[1]="1";
		idMenu[2]="2";
		idMenu[3]="3";
		
		try{
			if(cMenu.moveToFirst()){
				int i=4;
				do{
					opciones[i]=cMenu.getString(0);
					idMenu[i] = cMenu.getString(1);
					i++;
				}while(cMenu.moveToNext());
			}
		}catch (Exception e){
			
		}
		// Declarar adapter y eventos al hacer click
		
		drawer.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, opciones));

		drawer.setOnItemClickListener(new OnItemClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				
				removeOldMarkers();
				if(idMenu[arg2].equals("4")){
					dibujarCasitas();
				}
				else{
					//Para filtros
					cSubmenu = manejador.menuNivel2(idMenu[arg2]);
					idNivel1 = Integer.parseInt(idMenu[arg2]);
					encabezado = opciones[arg2];
					int lon = cSubmenu.getCount();
					idSubMenu = new String [lon+1];
					valores = new String [lon+1];
					
					try{
						if(cSubmenu.moveToFirst()){
							int i=1;
							listItems.clear();
							
							
							if(idNivel1>3){
								if(idNivel1 == 59 || idNivel1 == 60 || idNivel1 ==64 || idNivel1 == 70 || idNivel1 == 61){
									do{
										listItems.add(cSubmenu.getString(0));
										idSubMenu[i]=cSubmenu.getString(1);
										valores[i]= cSubmenu.getString(2);
										i++;
									}while(cSubmenu.moveToNext());
									showDialog(band);
									band++;
								}else{
									listItems.add("Ver totales por descriptor");
									do{
										listItems.add(cSubmenu.getString(0));
										idSubMenu[i]=cSubmenu.getString(1);
										valores[i]= cSubmenu.getString(2);
										i++;
									}while(cSubmenu.moveToNext());
									showDialog(band);
									band++;
								}
							}else{
								do{
									listItems.add(cSubmenu.getString(0));
									idSubMenu[i]=cSubmenu.getString(1);
									valor = "0";
									i++;
								}while(cSubmenu.moveToNext());
								showDialog(band);
								band++;
							}
						}
					}catch (Exception e){
						Toast.makeText(MainActivityMapa.this, "Error: "+e, Toast.LENGTH_SHORT).show();
					}
				}
				
				
				drawerLayout.closeDrawers();
			}
		});

		// Sombra del panel Navigation Drawer
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		// Integracion boton oficial
		toggle = new ActionBarDrawerToggle(
				this, // Activity
				drawerLayout, // Panel del Navigation Drawer
				R.drawable.ic_drawer, // Icono que va a utilizar
				R.string.app_name, // Descripcion al abrir el drawer
				R.string.hello_world // Descripcion al cerrar el drawer
				){
			
					public void onDrawerClosed(View view) {
						// Drawer cerrado
						getActionBar().setTitle("Bienvenido");					
						invalidateOptionsMenu();
					}
		
					public void onDrawerOpened(View drawerView) {
						// Drawer abierto
						getActionBar().setTitle("Menu");
						invalidateOptionsMenu(); 
					}
				};

		drawerLayout.setDrawerListener(toggle);
		
			//---------------------------------GPS----------------------------------
			/* usando la clase LocationManager para obtener informacion GPS*/
		LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		mlocListener = new MyLocationListener(this, gpsPointer, mapView, mlocManager);
		//----------------------------------------------------------------------
		//para click del boton FAB
		ImageButton fabImageButton = (ImageButton) findViewById(R.id.fab_image_button);

	    fabImageButton.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	if(modoSeguimiento){
		        	if(!mlocListener.canGetLocation()){
		        		mlocListener.showSettingsAlert();
		        	}
		        	else{
		        		mlocListener.actualizarPosicion();
		        	}
	        	}
	        	else{
	        		mlocListener.onFocusMapPosition ();
	        	}
	        }
	    });
	}
	
	@Override
	protected void onDestroy() {
		removeOldMarkers();
		mapView.destroy();
		super.onDestroy();
		this.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_mapa, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
    	switch (item.getItemId()) {
        case R.id.action_posicion:
        	if(modoSeguimiento){
	        	if(!mlocListener.canGetLocation()){
	        		mlocListener.showSettingsAlert();
	        	}
	        	else{
	        		Toast.makeText(this, "activando modo de seguimiento constante",
	        				Toast.LENGTH_SHORT).show();
	        		modoSeguimiento = false;
	        		mlocListener.setModoSeguimiento(false);
	        	}
        	}
        	else{
        		if(!mlocListener.canGetLocation()){
        			mlocListener.showSettingsAlert();
        		}
        		else{
            		Toast.makeText(this, "activando modo de seguimiento discreto",
            				Toast.LENGTH_SHORT).show();
            		modoSeguimiento = true;
            		mlocListener.setModoSeguimiento(true);
        		}
        	}
        	
            break;
        case R.id.action_creditos:
        	showCredits();
        	break;
        	
        case R.id.action_manual:
        	mostar_archivo_pdf("manual_usuario_mapa.pdf"); 
        	break;
    	}
		return toggle != null && toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
	}
	

 
	// Activamos el toggle con el icono
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		toggle.syncState();
	}
	
	//para menu emergente
	@Override
	protected Dialog onCreateDialog(int id) {
		final CharSequence[] items;

		items = listItems.toArray(new CharSequence[listItems.size()]);

		final Handler_sqlite manejador = new Handler_sqlite(this, mapView);

		removeOldMarkers();
		manejador.abrir();

		switch (id) {			
		default:				
			return new AlertDialog.Builder(this)
			.setIcon(R.drawable.buscar)
			.setTitle(encabezado)//Cambiar después
			.setItems(items, new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {

					if(idNivel1>3){
						if (which == 0 && idNivel1 != 59 && idNivel1 != 60 && idNivel1 !=64 && idNivel1 != 70 && idNivel1 != 61){
							int cant = cSubmenu.getCount();
							int i=0;
							Cursor t;
							String mensaje = "El total por descriptor es:\n";
							for (i=1; i<=cant ; i++){
								String valor = valores[i];
								t = manejador.filtrar(idNivel1,Integer.parseInt(idSubMenu[i]),valor);
								String descriptor = items[i] + ": ";	
								String total = Integer.toString(t.getCount());
								mensaje = mensaje + descriptor + total + "\n";
							}
							AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivityMapa.this);
							// Setting Dialog Title
							alertDialog.setTitle("Información");  
							alertDialog.setMessage(mensaje);
							// On pressing Settings button
							alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int which) {							            	
									dialog.cancel();
								}
							});
							alertDialog.show();
						}
						else{
							if (idNivel1 != 59 && idNivel1 != 60 && idNivel1 !=64 && idNivel1 != 70 && idNivel1 != 61){
								int idOpcion2Seleccionada = Integer.parseInt(idSubMenu[which]);
								valor = valores[which];
								SelectorDeVariables sv = new SelectorDeVariables(MainActivityMapa.this, manejador, mapView, idNivel1 ,idOpcion2Seleccionada, valor);
							}else{
								int idOpcion2Seleccionada = Integer.parseInt(idSubMenu[which+1]);
								valor = valores[which];
								SelectorDeVariables sv = new SelectorDeVariables(MainActivityMapa.this, manejador, mapView, idNivel1 ,idOpcion2Seleccionada, valor);
							}


						}																											

					}
					else{
						int idOpcion2Seleccionada = Integer.parseInt(idSubMenu[which+1]);
						SelectorDeVariables sv = new SelectorDeVariables(MainActivityMapa.this, manejador, mapView, idNivel1 ,idOpcion2Seleccionada, valor);
					}
				}

			})
			.create();
		}
	}

	void removeOldMarkers (){
		mapView.getLayerManager().getLayers().clear();
		mapView.getLayerManager().getLayers().add(tileRendererLayer);
		mapView.getLayerManager().getLayers().add(gpsPointer);
	}
	
	
	void dibujarCasitas(){
		final Handler_sqlite manejador = new Handler_sqlite(this, mapView);
		manejador.abrir();
		/*******************************************************************************************************************
		 *********************************** PARA MOSTRAR CASITAS POR RIESGO ***********************************************
		 *****************************************************************************************************************/
		
		//Sacando longitud, latitud, tipo de riesgo, código situación, número de expediente y nombre de jefe de familia
		//infoFicha() está en la clase Handler_sqlite y devuelve el resultado del query 
		Cursor c = manejador.numExpediente();
		
		//Para almacenar los campos extraídos
		double lon, lat;
		String cod_sit_viv, num_exp, jefe, area, zona, num_vivienda, num_familia;
		int tipo_riesgo, depto, municipio,ctn_bar_col;
		
		//Pointer y MyMarket
		Bitmap pointer;
		MyMarker fichasDefault;
		
		//Para las casitas de colores habitadas
		try{
			if(c.moveToFirst()){
				do{
					
					lon = c.getDouble(0);
					lat = c.getDouble(1);
					tipo_riesgo = c.getInt(2);
					cod_sit_viv = c.getString(3);
					depto = c.getInt (4);
					municipio = c.getInt(5);
					area = c.getString(6);
				    ctn_bar_col = c.getInt (7);				    
				    zona = c.getString(8);
				    num_vivienda = c.getString(9);
				    num_familia = c.getString(10);
					jefe = c.getString(11);
					
					num_exp = "";
					
					if (area.equals("R")){
						num_exp = manejador.numeroDeExpedienteRural(c.getString(0), c.getString(1));
					}
					else{
						num_exp = manejador.numeroDeExpedienteUrbano(c.getString(0), c.getString(1));
					}
					
					switch(tipo_riesgo){
					
						//Riesgo Alto--> Rojo
						case 1:	pointer = AndroidGraphicFactory.convertToBitmap(getResources().getDrawable(R.drawable.red_house));
								fichasDefault = new MyMarker(this, new LatLong(lat,lon), pointer, 0, 0, mapView, "Número de Expediente: "+num_exp+"\nJefe de Familia: "+jefe, false, true, depto,municipio,area,ctn_bar_col,zona,num_vivienda,num_familia);
								mapView.getLayerManager().getLayers().add(fichasDefault);
								break;
								
						//Riesgo Medio--> Amarillo
						case 2:	pointer = AndroidGraphicFactory.convertToBitmap(getResources().getDrawable(R.drawable.yellow_house));
								fichasDefault = new MyMarker(this, new LatLong(lat,lon), pointer, 0, 0, mapView, "Número de Expediente: "+num_exp+"\nJefe de Familia: "+jefe, false, true, depto,municipio,area,ctn_bar_col,zona,num_vivienda,num_familia);
								mapView.getLayerManager().getLayers().add(fichasDefault);
								break;		
						
						//Riesgo Bajo--> Verde
						case 3:	pointer = AndroidGraphicFactory.convertToBitmap(getResources().getDrawable(R.drawable.green_house));
								fichasDefault = new MyMarker(this, new LatLong(lat,lon), pointer, 0, 0, mapView, "Número de Expediente: "+num_exp+"\nJefe de Familia: "+jefe, false, true, depto,municipio,area,ctn_bar_col,zona,num_vivienda,num_familia);
								mapView.getLayerManager().getLayers().add(fichasDefault);
								break;
										
					}					
				}while (c.moveToNext());
			}
		}
		catch(Exception e){		
			
		}

		c = manejador.viviendasDeshabitadas();
		//Viviendas Deshabitadas
		try{
			if(c.moveToFirst()){
				do{
					//Deshabitada--> Celeste
					lon = c.getDouble(0);
					lat = c.getDouble(1);
					pointer = AndroidGraphicFactory.convertToBitmap(getResources().getDrawable(R.drawable.lightblue_house));
					fichasDefault = new MyMarker(this, new LatLong(lat,lon), pointer, 0, 0, mapView, "Vivienda Deshabitada", false, false, 0, 0, "", 0,"", "", "");
					mapView.getLayerManager().getLayers().add(fichasDefault);
				}while(c.moveToNext());
				manejador.cerrar();
			}
		}
		catch (Exception e){
			
		}
	}
	
	public void showCredits(){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

		StringBuilder sb = new StringBuilder();
		sb.append("Departamento de Electrónica e Informática UCA.\n\n");
		sb.append("Módulo de geo visualización y georreferencia.\n\n");
		sb.append("Desarrolladores:\n");
		sb.append("\tLic. Evelyn del Carmen Alvarenga\n");
		sb.append("\tLic. Irvin Balmore Cabezas\n");
		sb.append("\tLic. Telma Milagro Pineda\n");
		sb.append("\tLic. Erick Giovanni Varela\n");
		sb.append("\tEn conjunto con la DTIC del Ministerio de Salud. \n\n");
		sb.append("Versión del módulo: 1.0 – agosto de 2015.");
		alertDialog.setMessage(sb.toString());
		alertDialog.setIcon(R.drawable.logo_uca_minsal);
		alertDialog.setTitle("Créditos.");
        // On pressing Settings button
        alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
            	dialog.cancel();
            }
        });
        // Showing Alert Message
        alertDialog.show();
	}
	
	//muestra un archivo pdf	ejemplo //"manualAppSiff.pdf"
	   private void mostar_archivo_pdf(String nombre_archivo){
		AssetManager assetManager = getAssets();
	    
	    InputStream in = null;
	    OutputStream out = null;
	    File file = new File(getFilesDir(), nombre_archivo);
	    try
	    {
	        in = assetManager.open(nombre_archivo);
	        out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

	        copyFile(in, out);
	        in.close();
	        in = null;
	        out.flush();
	        out.close();
	        out = null;
	    } catch (Exception e)
	    {
	        Log.e("tag", e.getMessage());
	    }

	    Intent intent = new Intent(Intent.ACTION_VIEW);
	    intent.setDataAndType(
	            Uri.parse("file://" + getFilesDir() + "/"+nombre_archivo),"application/pdf");

	    startActivity(intent);
	    }	
		private void copyFile(InputStream in, OutputStream out) throws IOException
	    {
	        byte[] buffer = new byte[1024];
	        int read;
	        while ((read = in.read(buffer)) != -1)
	        {
	            out.write(buffer, 0, read);
	        }
	    }
}

