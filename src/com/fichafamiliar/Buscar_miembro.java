package com.fichafamiliar;

import java.io.IOException;

import db_gestion.BaseDeDatos;
import db_gestion.GestionDB;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Buscar_miembro extends Activity {
	private static final String PREFRENCES_NAME = "sesionesSharedPreferences";
	public GestionDB objGestionDB;
	public SQLiteDatabase conexOpen;
	public Context contexto = this;
	
	EditText primer_nombre,
			 segundo_nombre,
			 primer_apellido,
			 segundo_apellido;
	int id_estasib_user_sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscar_miembro);
		//getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		
		/*BaseDeDatos db2 = new BaseDeDatos(this);
		try {
			db2.createDataBase();*/
			// db2.openDataBase();
		//	this.conexOpen = db2.myDataBase;
			this.objGestionDB = new GestionDB();// creo el objeto de la
													// clase que gestiona la DB
		/*} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		SharedPreferences preferencias = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		id_estasib_user_sp = preferencias.getInt("id_estasib_user_sp", 0);//recupero el id del establecimiento
		
		primer_nombre=(EditText)findViewById(R.id.primer_nombre);
		segundo_nombre=(EditText)findViewById(R.id.segundo_nombre);
		primer_apellido=(EditText)findViewById(R.id.primer_apellido);
		segundo_apellido=(EditText)findViewById(R.id.segundo_apellido);	
	}
	
	public void result_busqueda_miembro(View view) {
		String   p_nombre = primer_nombre.getText().toString().trim();
		String   s_nombre = segundo_nombre.getText().toString().trim();
		String   p_apellido = primer_apellido.getText().toString().trim();
		String   s_apellido = segundo_apellido.getText().toString().trim();
		
		if(p_nombre.equals("") && s_nombre.equals("") && p_apellido.equals("") && s_apellido.equals("")){ 
			Toast t_msje=Toast.makeText(this, "Digite algun parametro para la busqueda", Toast.LENGTH_LONG);
			t_msje.setGravity(Gravity.CENTER, 0, 0);
			t_msje.show();
		}else{
			int cantidadJefes=objGestionDB.getCountJefesFamilias(p_nombre, s_nombre, p_apellido, s_apellido,id_estasib_user_sp,this.contexto);
			if(cantidadJefes>0){
				Intent i = new Intent(Buscar_miembro.this, Jefes_familia.class);
				i.putExtra("p_nombre",p_nombre);
				i.putExtra("s_nombre",s_nombre);
				i.putExtra("p_apellido",p_apellido);
				i.putExtra("s_apellido",s_apellido);
				startActivity(i);	
			}else{
				//mostrar mensaje
				showMyToast("No hay resultados para la busqueda");	
			}
			
		}	
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

}
