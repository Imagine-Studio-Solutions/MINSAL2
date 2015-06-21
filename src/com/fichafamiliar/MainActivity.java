package com.fichafamiliar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
	}

	
	public void go_Login(View view){
		 
						  	//int id_familia=150120;
							//Intent i = new Intent(this, Integrantes.class);
		  Intent i = new Intent(this, Login_Activity.class);
		   // Intent i = new Intent(this, Sincronizar.class);
						  	//Intent i = new Intent(this, Miembros_familia.class);
						  	//i.putExtra("idfamilia",id_familia);
						  	//Intent i = new Intent(this, Add_new_miembro_familia.class);
	      startActivity(i);
		
	    /*Intent i = new Intent(this, Ver_detalle_ficha.class);
		i.putExtra("idfamilia",9);//pasar los datos que se necesitaran en la siguiente activity
		i.putExtra("busquedaPor",3);
		i.putExtra("action", "New");
		startActivity(i);*/
	}
	
}
