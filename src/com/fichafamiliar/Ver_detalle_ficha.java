package com.fichafamiliar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//import org.apache.commons.logging.Log;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
import android.text.Html.ImageGetter;
import android.util.Log;

public class Ver_detalle_ficha extends Activity {
			//public static Activity ActivityVerDetalleFicha;
			
			private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
			public SQLiteDatabase conexOpen;
			public Context contexto = this;
			public GestionDB objGestionDB;
			String direccionFam ="";
			int idJefeFamilia=0;
			int idfamilia=0;
			int id_sp;
			String user_sp;
			String pass_sp;
			int id_estasib_user_sp;
			String nombreusuario;
			int id_sibasi;
			int correlativo_tablet;
			int todas=1;
			ListView listaMiem;
			TextView direccion;
			
			private ImageButton 
						ImageFicha,ImageVivienda,ImagePatrimonio, ImageAmenazas,ImageServiciosBasicos, 
						ImageDesechos, ImageVectores,ImageMascotas, ImageMiembroFamilia,ImageRiesgoFamilia,ImageGPS;
			private String 
						actionFicha="Edit", actionVivienda, actionPatrimonio, actionAmenazas,actionServiciosBasicos,
						actionDesechos, actionVectores, actionMascotas, actionRiesgoFamilia="Edit",actionGps;
@Override
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ver_detalle_ficha_activity);	
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		//ActivityVerDetalleFicha=this;
		this.objGestionDB = new GestionDB();// creo el objeto de la DB
		/*BaseDeDatos db2 = new BaseDeDatos(this);
		try {
			db2.createDataBase();
			// db2.openDataBase();
			this.conexOpen = db2.myDataBase;
			this.objGestionDB = new GestionDB(this);// creo el objeto de la
													// clase que gestiona la DB
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		
		 id_sp = preferencias.getInt("id_sp", 0);// id usuario tablet
		 id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0); // id establecimiento
		 nombreusuario=preferencias.getString("nombreusuario_sp", ""); //nombre usuario
		 id_sibasi = preferencias.getInt("id_sibasi_sp", 0);//id sibasi
		 correlativo_tablet=preferencias.getInt("correlativo_tablet",0);//correlativo tablet
		 
		
		 listaMiem = (ListView) findViewById(R.id.listaMiembros);
		 
		 
		 
		 direccion = (TextView) findViewById(R.id.txtDireccion);
	
         ImageFicha  = (ImageButton) findViewById(R.id.imgButonFicha);  
		 ImageVivienda  = (ImageButton) findViewById(R.id.imgButonvienda);
		 ImagePatrimonio = (ImageButton) findViewById(R.id.imgButonPatrimonio); 
		 ImageAmenazas = (ImageButton) findViewById(R.id.imgButonAmenazas); 
		 ImageServiciosBasicos = (ImageButton) findViewById(R.id.imgButonServiciosBasicos); 
		 ImageDesechos = (ImageButton) findViewById(R.id.imgButonDesechos); 
		 ImageVectores = (ImageButton) findViewById(R.id.imgButonVectores); 
		 ImageMascotas = (ImageButton) findViewById(R.id.imgButonMascotas);
		 ImageMiembroFamilia = (ImageButton) findViewById(R.id.imgButonMiembroFamilia);
		 ImageRiesgoFamilia= (ImageButton) findViewById(R.id.imgButonRiesgoFam);
		 ImageGPS=(ImageButton) findViewById(R.id.imgButonGps);
		 
		Bundle bundle = getIntent().getExtras();
		int busquedaPor    = bundle.getInt("busquedaPor");
		
		
		if(busquedaPor==1){
			idJefeFamilia    = bundle.getInt("idJefeFamilia");
			objGestionDB.getIdFamilia(idJefeFamilia,this.contexto);
			direccionFam=objGestionDB.direccionFamilia;
			idfamilia=objGestionDB.idfamilia;
		}else if(busquedaPor==2){
			idfamilia    = bundle.getInt("idfamilia");
			direccionFam    = bundle.getString("direccion");
		}else if(busquedaPor==3){
			idfamilia    = bundle.getInt("idfamilia");
			objGestionDB.getFamiliaInfoEdit(idfamilia,this.contexto);
			direccionFam=objGestionDB.direccionFamilia;
		} 
		 
		direccion.setText(direccionFam);
		loadJefes(idfamilia);
		
		listaMiem.setTextFilterEnabled(true);
		listaMiem.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> elemento, View v, int position,long id) {
					int elegido = ((SpinnerObject) elemento.getItemAtPosition(position)).getId(); 					
					Intent i = new Intent(Ver_detalle_ficha.this, Edit_delete_miembro_fam.class);
					i.putExtra("idmiembrofam",elegido);
					i.putExtra("idfamilia",idfamilia);
					i.putExtra("busquedaPor",3);
					startActivity(i); 
				}
			});
		
 iconos_ficha_on_off(idfamilia);
		
}
/*@Override
public void onBackPressed() {
    // Do Here what ever you want do on back press;
	Toast.makeText(getApplicationContext(), "Para regresar haga clik en el boton Menu",      
	Toast.LENGTH_LONG).show();
}*/
private void iconos_ficha_on_off(int idfamilia) {
	
	if(objGestionDB.variables_completas("83,91,92,93,94", idfamilia,5,this.contexto)==1){
		ImageVivienda.setImageResource(R.drawable.vivienda_on);
		actionVivienda="Edit";		
	}else{
		ImageVivienda.setImageResource(R.drawable.vivienda_off);
		actionVivienda="New";
		this.todas=0;
	}
	if(objGestionDB.variables_completas("57,95,96,97,67", idfamilia,5,this.contexto)==1){
		ImagePatrimonio.setImageResource(R.drawable.patrimonio_on);
		actionPatrimonio="Edit";
	}else{
		ImagePatrimonio.setImageResource(R.drawable.patrimonio_off);
		actionPatrimonio="New";
		this.todas=0;
	}
	if(objGestionDB.variables_completas("98,99", idfamilia,2,this.contexto)==1){
		ImageAmenazas.setImageResource(R.drawable.amenazas_on);
		actionAmenazas="Edit";
	}else{
		ImageAmenazas.setImageResource(R.drawable.amenazas_off);
		actionAmenazas="New";
		this.todas=0;
	}
	if(objGestionDB.variables_completas("100,101,102,55,103,104,105", idfamilia,7,this.contexto)==1){
		ImageServiciosBasicos.setImageResource(R.drawable.servicios_basicos_on);
		actionServiciosBasicos="Edit";
	}else{
		ImageServiciosBasicos.setImageResource(R.drawable.servicios_basicos_off);
		actionServiciosBasicos="New";
		this.todas=0;
	}
	if(objGestionDB.variables_completas("38,39,106", idfamilia,3,this.contexto)==1){
		ImageDesechos.setImageResource(R.drawable.desechos_on);
		actionDesechos="Edit";
	}else{
		ImageDesechos.setImageResource(R.drawable.desechos_off);
		actionDesechos="New";
		this.todas=0;
	}
	if(objGestionDB.variables_completas("107", idfamilia,1,this.contexto)==1){
		ImageVectores.setImageResource(R.drawable.vectores_on);
		actionVectores="Edit";
	}else{
		ImageVectores.setImageResource(R.drawable.vectores_off);
		actionVectores="New";
		this.todas=0;
	}
	if(objGestionDB.variables_completas("43,45,62", idfamilia,3,this.contexto)==1){
		ImageMascotas.setImageResource(R.drawable.mascotas_on);
		actionMascotas="Edit";
	}else{
		ImageMascotas.setImageResource(R.drawable.mascotas_off);
		actionMascotas="New";
		this.todas=0;
	}
objGestionDB.getFamiliaInfoEdit(idfamilia,this.contexto);
//String algo=objGestionDB.s_latitud;
if(objGestionDB.s_latitud==null || objGestionDB.s_longitud==null){
	ImageGPS.setImageResource(R.drawable.gps_off);
	actionGps="Edit";
	//this.todas=0;
}else{
	ImageGPS.setImageResource(R.drawable.gps_on);
	actionGps="Edit";
}
	if(objGestionDB.tipo_riesgofamiliar==0 || objGestionDB.tipo_riesgofamiliar==null){
		ImageRiesgoFamilia.setImageResource(R.drawable.riesgo_familiar_off);
		//actionRiesgoFamilia="Edit";
	}else{
		ImageRiesgoFamilia.setImageResource(R.drawable.riesgo_familiar_on);
		//actionRiesgoFamilia="Edit";
	}
}
 
private void loadJefes(int idfamilia) {
	List<SpinnerObject> lista =  objGestionDB.getFamiliaJefe(idfamilia,this.contexto);
	ArrayAdapter<SpinnerObject> adap = new ArrayAdapter<SpinnerObject>(this, R.layout.simplerow, lista);
	//listaMiem.setBackgroundColor(Color.RED); 
	adap.setDropDownViewResource(R.layout.simplerow);
	listaMiem.setAdapter(adap);
	

	/*for(int i=0; i<lista.size(); i++){
		//int  idinte=lista.get(i).getId();
		//llamar a la funcion que averigua si esta vivo o muerto
		//si esta vivo
		
		//listaMiem.getChildAt(i).setBackgroundColor(Color.RED);
		 
		listaMiem.getChildAt(i).setBackgroundColor(Color.CYAN);
		//si no esta vivo, no pasa nada
       
}*/ 

	setListViewHeightBasedOnChildren(listaMiem);
	
	}

public void showMyToast(String texto) {
	LayoutInflater inflater = getLayoutInflater();
	View layout = inflater.inflate(R.layout.mensajetoast,(ViewGroup) findViewById(R.id.toast_layout_root)); //"inflamos" nuestro layout
	TextView text = (TextView) layout.findViewById(R.id.text_toast);
	text.setText(texto); //texto a mostrar y asignado al textView de nuestro layout
	Toast toast = new Toast(getApplicationContext()); //Instanciamos un objeto Toast
	toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0); //lo situamos centrado verticalmente en la pantalla
	toast.setDuration(Toast.LENGTH_LONG); //duracion del toast
	toast.setView(layout); //asignamos nuestro layout personalizado al objeto Toast
	toast.show(); //mostramos el Toast en pantalla
	}
public void retur_home_click(View view) {
	Intent i = new Intent(this, Menu_Activity.class);
	/*i.putExtra("id_estasib_user_sp",id_estasib_user_sp);
	i.putExtra("var_user",nombreusuario);*/
	startActivity(i);
}
public void mensaje_si_no(){
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setMessage("La vivienda debe estar habitada,¿Desea cambiar la situacón de la vivienda?");
			alertDialog.setTitle("Situación de la vivienda");
			alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
			alertDialog.setCancelable(false);
			alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int which)
				{					
						ir_ficha_infGeneral();													
				}
		   });
		   alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
		   {
		      public void onClick(DialogInterface dialog, int which)
		      {
		    	 //no pasa nada
		      }
		    });
		    alertDialog.show();
}
public void go_fallecidos(View view) {
	Intent i = new Intent(this, Integrantes_fallecidos.class);
	i.putExtra("idfamilia",idfamilia);
	i.putExtra("action", actionFicha);
	//finish();
	startActivity(i);	
}
public void ir_ficha_infGeneral(){
	Intent i = new Intent(this, Ficha_familia_activity.class);
	i.putExtra("idfamilia",idfamilia);
	i.putExtra("action", actionFicha);
	//finish();
	startActivity(i);
}
public void ficha_infGeneral(View view) {
		Intent i = new Intent(this, Ficha_familia_activity.class);
		i.putExtra("idfamilia",idfamilia);
		i.putExtra("action", actionFicha);
		//finish();
		startActivity(i);	
}
public void caracteristicas_vivienda(View view) {
	if(objGestionDB.codigo_sit_vivienda.equals("01")){
		Intent i = new Intent(this, Caracteristicas_vivienda.class);
		i.putExtra("idfamilia",idfamilia);
		i.putExtra("action", actionVivienda);
		//finish();
		startActivity(i);
	}else{
		mensaje_si_no();
	}
	
}
public void ubicacion_vivienda(View view) {
	/*if(objGestionDB.codigo_sit_vivienda.equals("01")){
		Intent i = new Intent(this, Ubicacion_vivienda.class);
		i.putExtra("idfamilia",idfamilia);
		i.putExtra("action", actionVivienda);
		//finish();
		startActivity(i);
	}else{
		mensaje_si_no();
	}*/
	Intent i = new Intent(this, Ubicacion_vivienda.class);
	i.putExtra("idfamilia",idfamilia);
	i.putExtra("action", actionVivienda);
	//finish();
	startActivity(i);
	
}
public void patrimonio(View view) {
	if(objGestionDB.codigo_sit_vivienda.equals("01")){
		Intent i = new Intent(this, Patrimonio.class);
		i.putExtra("idfamilia",idfamilia);
		i.putExtra("action", actionPatrimonio);
		//finish();
		startActivity(i); 
	}else{
		mensaje_si_no();
	}
}
public void amenazas(View view) {
if(objGestionDB.codigo_sit_vivienda.equals("01")){
	Intent i = new Intent(this, Amenazas.class);
	i.putExtra("idfamilia",idfamilia);
	i.putExtra("action", actionAmenazas);
	//finish();
	startActivity(i); 
	}else{
		mensaje_si_no();
	}
}
public void servicios_basicos(View view) {
if(objGestionDB.codigo_sit_vivienda.equals("01")){
	Intent i = new Intent(this, ServiciosBasicos.class);
	i.putExtra("idfamilia",idfamilia);
	i.putExtra("action", actionServiciosBasicos);
	//finish();
	startActivity(i);
	}else{
		mensaje_si_no();
	}
}
public void desechos(View view) {
if(objGestionDB.codigo_sit_vivienda.equals("01")){
	Intent i = new Intent(this, Manejo_desechos.class);
	i.putExtra("idfamilia",idfamilia);
	i.putExtra("action", actionDesechos);
	//finish();
	startActivity(i);
	}else{
		mensaje_si_no();
	}
}
public void vectores(View view) {
if(objGestionDB.codigo_sit_vivienda.equals("01")){
	Intent i = new Intent(this, Vectores.class);
	i.putExtra("idfamilia",idfamilia);
	i.putExtra("action", actionVectores);
	//finish();
	startActivity(i);
	}else{
		mensaje_si_no();
	}
}
public void mascotas(View view) {
if(objGestionDB.codigo_sit_vivienda.equals("01")){
	Intent i = new Intent(this, Mascotas.class);
	i.putExtra("idfamilia",idfamilia);
	i.putExtra("action", actionMascotas);
	//finish();
	startActivity(i);
	}else{
		mensaje_si_no();
	}
}
public void click_riesgoFamiliar(View view) {
	int cant_miembros_familia=(objGestionDB.getCorrelativoIntegrante(idfamilia,this.contexto))-1;
	if(this.todas==0){
		showMyToast("Debe llenar todas las variables de la familia");
	}else if(cant_miembros_familia<1){
		showMyToast("Debe haber ingresado al menos un integrante en esta familia");
	}	else{
		Intent i = new Intent(this, RiesgoFamiliar.class);
		i.putExtra("idfamilia",idfamilia);
		i.putExtra("action", actionRiesgoFamilia);
		//finish();
		startActivity(i);
	}
	
}
public void click_miembros(View view) {
	
	/*Toast t_primer_nombre=Toast.makeText(this, "idfamilia: "+idfamilia, Toast.LENGTH_LONG);
	t_primer_nombre.setGravity(Gravity.CENTER, 0, 0);
	t_primer_nombre.show();*/
	/*Intent i = new Intent(this, Add_new_miembro_familia.class);
	i.putExtra("idfamilia",idfamilia);
	i.putExtra("action","New");
	startActivity(i);
	*/
	Intent i = new Intent(this, Add_new_miembro_familia.class);
	i.putExtra("idfamilia",idfamilia);
	i.putExtra("action", "New");
	/*Toast t_primer_nombre=Toast.makeText(this, "idfamilia: "+idfamilia, Toast.LENGTH_LONG);
	t_primer_nombre.setGravity(Gravity.CENTER, 0, 0);
	t_primer_nombre.show();*/
	startActivity(i);
	
}//miembros
public void retur_menu_principal(){
	Intent i = new Intent(this, Menu_Activity.class);
	startActivity(i);
    showMyToast("Se ha eliminado la ficha");
} 
public void click_delete_ficha(View v){
	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
    alertDialogBuilder.setMessage("Eliminar por completo a esta familia?")
    .setCancelable(false)
    .setPositiveButton("Aceptar",
            new DialogInterface.OnClickListener(){
        public void onClick(DialogInterface dialog, int id){
    
        objGestionDB.deleteVariablesFam(idfamilia,contexto); //eliminar variables de la familia
        objGestionDB.deleteIntegrante_VariableFam(idfamilia,contexto);	//eliminar las variables de los miembros	
        objGestionDB.deleteMiembrosFam(idfamilia,contexto);  //eliminar miembros de la familia
        objGestionDB.deleteFamilia(idfamilia,contexto);//eliminar la ficha 
        retur_menu_principal();
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
	/*public static void setListViewHeightBasedOnChildren(ListView listView) 
	{
	    ListAdapter listAdapter = listView.getAdapter();
	    if (listAdapter == null) {
	        return;
	    }
	    int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.AT_MOST);
	    int totalHeight = 0;
	    View view = null;
	    for (int i = 0; i < listAdapter.getCount(); i++) {
	        view = listAdapter.getView(i, view, listView);
	        if (i == 0) {
	            view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));
	        }
	        view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
	        totalHeight += view.getMeasuredHeight();
	    }
	    ViewGroup.LayoutParams params = listView.getLayoutParams();
	    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	    listView.setLayoutParams(params);
	    listView.requestLayout();
	}*/
	public static void setListViewHeightBasedOnChildren(ListView listView) {
	    ListAdapter listAdapter = listView.getAdapter(); 
	    if (listAdapter == null) {
	        // pre-condition
	        return;
	    }
	
	    int totalHeight = 0;
	    for (int i = 0; i < listAdapter.getCount(); i++) {
	        View listItem = listAdapter.getView(i, null, listView);
	        listItem.measure(0, 0);
	        totalHeight += listItem.getMeasuredHeight();
	    }
	
	    ViewGroup.LayoutParams params = listView.getLayoutParams();
	    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	    listView.setLayoutParams(params);
	    listView.requestLayout();
	}

}
