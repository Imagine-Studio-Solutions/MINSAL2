package com.fichafamiliar;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//import com.fichafamiliar.Crear_Usuario.GuardarEditarUsuario;

import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CambioPassword extends Activity {
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	public GestionDB objGestionDB; // New GestionDB(this);//creo el objeto de lLa clase que gestiona la DB
	int id_sp;
	String cadenaclaveantigua, claveantiguabasededatos, clave, confirmacionclave;
	String user_sp;
	String pass_sp;
	int id_estasib_user_sp;
	String nombreusuario;
	int id_sibasi;
	public EditText claveantigua, claveusuarionuevo, confirmacion_clave;
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cambio_password);
		
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		id_sp = preferencias.getInt("id_sp", 0);//id del usuario
		user_sp = preferencias.getString("user_sp", "");
		pass_sp = preferencias.getString("pass_sp", "");
		id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0);
		nombreusuario=preferencias.getString("nombreusuario_sp", "");
		id_sibasi = preferencias.getInt("id_sibasi_sp", 0);
		
		
		
		//final ProgressDialog progressDialog;
		
		/*BaseDeDatos db2 = new BaseDeDatos(this);
		try 
		{
			db2.createDataBase();*/
			// db2.openDataBase();
			//this.conexOpen = db2.myDataBase;
			this.objGestionDB = new GestionDB();// creo el objeto de la clase que gestiona la DB
		/*} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		claveantigua = (EditText) findViewById(R.id.claveusuarioantigua);
		claveusuarionuevo= (EditText) findViewById(R.id.claveusuarioeditar);
		confirmacion_clave= (EditText) findViewById(R.id.confirmacion_claveeditar);
		
		/*Bundle bundle = getIntent().getExtras();
		
		objGestionDB.getUsuarioInfoEditPassword(id_sp);
		
		claveusuarionuevo.setText(objGestionDB.claveusuario);
		confirmacion_clave.setText(objGestionDB.claveusuario);*/
	}
	
	public void funcion_cambiar_password(View v)
	{
		cadenaclaveantigua = claveantigua.getText().toString();
		clave = claveusuarionuevo.getText().toString();
		confirmacionclave = confirmacion_clave.getText().toString();
		
		if(cadenaclaveantigua.equals(""))
		{
			Toast toastclaveantigua = Toast.makeText(getApplicationContext(), "Debe digitar la Contraseña Actual del Usuario", Toast.LENGTH_SHORT);
			
			toastclaveantigua.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
			toastclaveantigua.setGravity(Gravity.BOTTOM, 0, 0);
	 
			toastclaveantigua.show();
	        claveantigua.requestFocusFromTouch();
		}
		else
		{
			if(clave.equals(""))
			{
				Toast toastclave = Toast.makeText(getApplicationContext(), "Debe digitar la Contraseña del Usuario", Toast.LENGTH_SHORT);
				
				toastclave.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
				toastclave.setGravity(Gravity.BOTTOM, 0, 0);
		 
		        toastclave.show();
		        claveusuarionuevo.requestFocusFromTouch();
			}
			else
			{
				if(confirmacionclave.equals(""))
				{
					Toast toastconfirmacionclave = Toast.makeText(getApplicationContext(), "Debe Confirmar su contraseña", Toast.LENGTH_SHORT);
					
					toastconfirmacionclave.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
					toastconfirmacionclave.setGravity(Gravity.BOTTOM, 0, 0);
			 
			        toastconfirmacionclave.show();
			        confirmacion_clave.requestFocusFromTouch();
				}
				else
				{
		
					if(clave.equals(confirmacionclave))
					{
						if(clave.length()<6)
						{
							Toast toastlength= Toast.makeText(getApplicationContext(), "La Contraseña es demasiado corta", Toast.LENGTH_SHORT);
							
							toastlength.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
							toastlength.setGravity(Gravity.BOTTOM, 0, 0);
					 
							toastlength.show();
							claveusuarionuevo.requestFocusFromTouch();
						}
						else
						{
							Bundle bundle = getIntent().getExtras();
							
							objGestionDB.getUsuarioInfoEditPassword(id_sp,this.contexto);
							
							claveantiguabasededatos=objGestionDB.claveusuario;
							
							if(claveantiguabasededatos.equals(md5(cadenaclaveantigua)))
							{
							
								EditarPasswordUsuario EditarPass = new EditarPasswordUsuario(this);
								EditarPass.execute();
								 
								 
								ToastExito("Se ha cambiado la contraseña Usuario");
											 
								Intent i = new Intent(contexto, Mantenimiento_Usuario.class);
								//i.putExtra("idfamilia",id_familia);//pasar los datos que se necesitaran en la siguiente activity
		
								startActivity(i);
							}
							else
							{
								Toast toastcadenasantiguacomparacion = Toast.makeText(getApplicationContext(), "La Contraseña Actual no es la correcta", Toast.LENGTH_SHORT);
								
								toastcadenasantiguacomparacion.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
								toastcadenasantiguacomparacion.setGravity(Gravity.BOTTOM, 0, 0);
						 
								toastcadenasantiguacomparacion.show();
						        claveantigua.requestFocusFromTouch();
							}
						}
					}
					else
					{
						Toast toastcompararcadenas = Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT);
						
						toastcompararcadenas.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
						toastcompararcadenas.setGravity(Gravity.BOTTOM, 0, 0);
				 
				        toastcompararcadenas.show();
				        confirmacion_clave.requestFocusFromTouch();
					}
				}
			}
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
	
	public class EditarPasswordUsuario extends AsyncTask<String, Void, String> {  	
    	Context contexto;
    	public ProgressDialog progressDialog;
    	
		    protected String doInBackground(String... params) {
				    		
				    		//llamo el metodo que edita	
				    			actualizarPasswordUsuario();
				    		
		    		return null;
		    	}
		    public EditarPasswordUsuario(Context contexto){	
					this.contexto = contexto;
				}
	    	@Override
	    	protected void onPostExecute(String result) {
	    			progressDialog.dismiss();
	    		}
	    	@Override
	        protected void onPreExecute() 
	    	{
	            // TODO Auto-generated method stub
	            super.onPreExecute();
	            
	            	
        			progressDialog = ProgressDialog.show(contexto,"","Actualizando los datos...",true);
        		    
    		}
	    	
		    
		    public void actualizarPasswordUsuario()
		    {
		    	String clave_con_md5;
		    	clave_con_md5=md5(clave);
		    	objGestionDB.updatePassUsuario(clave_con_md5, id_sp,this.contexto);		  
		    }
	}
	
	public String md5(String s) 
	{ 
		MessageDigest digest; 
		try 
		{ 
			digest = MessageDigest.getInstance("MD5"); 
			digest.update(s.getBytes(),0,s.length()); 
			String hash = new BigInteger(1, digest.digest()).toString(16);  
			return hash; 
		} catch (NoSuchAlgorithmException e) 
		{ 
			e.printStackTrace(); 
		} 
		return "error"; 
	}

}
