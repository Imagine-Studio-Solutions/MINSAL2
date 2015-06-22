package com.fichafamiliar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sincro.Sincronizar;
 


import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Menu_Activity extends Activity {
	TextView texto,usuario_menu;
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB;
	public Sincronizar objSincronizar;
	
	//variables para sp
	int id_sp;
	String user_sp;
	String pass_sp;
	int id_estasib_user_sp;
	String nombreusuario;
	int id_sibasi;
	int id_perfil;
	ImageButton mantenimiento_usuarios;
	
	private ProgressDialog progressDialog;
	ImageButton btnImage_new_ficha;
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	
	int id_estasib;// id establecimiento

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_activity);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		// texto=(TextView)findViewById(R.id.textdmenu);
		
		btnImage_new_ficha = (ImageButton) findViewById(R.id.new_ficha);
		usuario_menu = (TextView)findViewById(R.id.text_usuario_menu);
		mantenimiento_usuarios = (ImageButton) findViewById(R.id.mantenimiento_usuarios);
		 SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		 id_sp = preferencias.getInt("id_sp", 0);//id del usuario
		 user_sp = preferencias.getString("user_sp", "");
		 pass_sp = preferencias.getString("pass_sp", "");
		 id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0);
		 nombreusuario=preferencias.getString("nombreusuario_sp", "");
		 id_sibasi = preferencias.getInt("id_sibasi_sp", 0);
		 id_perfil = preferencias.getInt("codigo_perfil", 0); 
		 usuario_menu.setText(" "+nombreusuario);
		 
		 if(id_perfil==7||id_perfil==8)
		 {
			 mantenimiento_usuarios.setVisibility(View.VISIBLE);
		 }
		 else
		 {
			 mantenimiento_usuarios.setVisibility(View.INVISIBLE);
		 }
		 
		 id_estasib = preferencias.getInt("id_estasib_user_sp", 0); // id establecimiento
		 
		 /*BaseDeDatos db2 = new BaseDeDatos(this);
			try {
				db2.createDataBase();*/
				// db2.openDataBase();
				//this.conexOpen = db2.myDataBase;
				this.objGestionDB = new GestionDB();// creo el objeto de la
			/*											// clase que gestiona la DB
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		 
		// texto.setText("Estas variables estan en sesion sharedPreferences:  id_sp: "+id_sp+" user_sp: "+user_sp+" pass_sp: "+pass_sp);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_option_menu, menu);
		return true;
	}
@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
	switch (item.getItemId()) {
    case R.id.action_manual:
    	mostar_archivo_pdf("manualAppSiff.pdf"); 
        break;    
    case R.id.action_instructivo_ff:
    	mostar_archivo_pdf("instructivoFF2015.pdf");    
        break;
   
    case R.id.action_acerca_de:
         
        break;    
}
return true;
}

@Override
public void onBackPressed() {
    // Do Here what ever you want do on back press;
	/*Toast.makeText(getApplicationContext(), " ",      
	Toast.LENGTH_LONG).show(); */
}
	public void cerrarsesion(View view) {
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		// lo que hacemos es setear a 0 y "" respectivamente
		Editor editor = preferencias.edit();
		editor.putInt("id_sp", 0);
		editor.putString("user_sp", "");
		editor.putString("pass_sp", "");
		editor.putInt("id_estasib_user_sp", 0);
		editor.putString("nombreusuario_sp", "");
		editor.putInt("id_sibasi_sp", 0);
		editor.putInt("codigo_perfil", 0);
		editor.commit();
		
		
		startActivity(new Intent(getBaseContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)); 
		/*int id_sp = preferencias.getInt("id_sp", 0);
		String user_sp = preferencias.getString("user_sp", "");
		String pass_sp = preferencias.getString("pass_sp", "");
		texto.setText("sesiones cerradas:  id_sp: " + id_sp + " user_sp: "+ user_sp + " pass_sp: " + pass_sp);*/
	}

	public void new_ficha(View view) {
		Intent i = new Intent(this, Ficha_familia_activity.class);
		i.putExtra("id_estasib_user_sp",id_estasib_user_sp);
		i.putExtra("action", "New");
		startActivity(i);
	}
	
	public void buscar_ficha(View view) {
		Intent i = new Intent(this, Buscar_por.class);
		i.putExtra("id_estasib_user_sp",id_estasib_user_sp);
		startActivity(i);
	}
	public void mantenimiento_usuarios(View view){
		Intent i = new Intent (this, Mantenimiento_Usuario.class);
		
		//i.putExtra("aun_nose", );
		startActivity(i);
	}
	public void cambio_contrasena(View v)
	{
		Intent i = new Intent (this, CambioPassword.class);
		
		//i.putExtra("aun_nose", );
		startActivity(i);
	}//
	public void sincronizar_datos(View v)
	{
		/*
		Intent intent = new Intent();
		intent.setAction(android.content.Intent.ACTION_VIEW);
		File file = new File("/storage/sdcard0/Download/Tabla_13.pdf");
		intent.setDataAndType(Uri.fromFile(file),"text/*");
		startActivity(intent); 		
		*/
		
		 
		
		/*int datosSinSincro=objGestionDB.cantidadDatosSincronizar(this.contexto);
		if(datosSinSincro>0){
			
			sincronizarDatos DatosAsync = new sincronizarDatos(datosSinSincro,this);
			DatosAsync.execute();
			
		}else{
			ToastExito("NO hay datos pendientes de sincronizar");
			}*/
		//Hay datos sin sincronizar???
	/*int datosSinSincro=objGestionDB.existenDatosSincronizar(this.contexto);
		
	
	
	if(datosSinSincro==1){
			//ToastExito("SI hay datos pendientes de sincronizar");
			sincronizarDatos DatosAsync = new sincronizarDatos(this);
			DatosAsync.execute();
		}else{
			ToastExito("NO hay datos pendientes de sincronizar");
			
			/*Intent intent = new Intent();
			intent.setAction(android.content.Intent.ACTION_VIEW);
			File file = new File("/sdcard/test.mp4");
			intent.setDataAndType(Uri.fromFile(file), "v|ideo/*");
			startActivity(intent); */
				//	}
		
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

public void ToastExito(String texto) 
	{
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
public class sincronizarDatos extends AsyncTask<String, Void, String> {
		Context contexto;
		int datosSinSincro;
		public sincronizarDatos(int datosSinSincro,Context contexto){	
			this.contexto = contexto;
			this.datosSinSincro=datosSinSincro;
			}
		@Override
		protected String doInBackground(String... params) {
			//objGestionDB.get_datosSincronizar(contexto);
			
			int x=0;
			objSincronizar = new Sincronizar();
			
			
			//double repeticiones=(this.datosSinSincro/5000);
			//int repeticionesInt = ((int)repeticiones)+1;
			//ToastExito("Vale: "+repeticionesInt);
			
			//while(x<repeticionesInt){				
			while(x<this.datosSinSincro){		
			objSincronizar.enviarBitacora(contexto);
				x++;
				//ToastExito("X vale: "+x+"cuantas veces debe entrar: "+repeticionesInt );
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
	    return null;
	    }
		@Override
    	protected void onPostExecute(String result) {
    		super.onPostExecute(result);
    		progressDialog.dismiss();
		}
		@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = ProgressDialog.show(contexto,"","Enviando datos al servidor...",true);
        }	
	}

	public void lanzar(View view){
		Intent i = new Intent(this, MainActivityMapa.class);
		i.putExtra("id_estasib_user_sp",id_estasib_user_sp);
		startActivity(i);
	}
}