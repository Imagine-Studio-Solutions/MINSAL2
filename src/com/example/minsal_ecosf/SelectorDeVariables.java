package com.example.minsal_ecosf;

import java.util.ArrayList;

import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.view.MapView;

import com.fichafamiliar.MainActivityMapa;
import com.fichafamiliar.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.widget.Toast;

public class SelectorDeVariables {
	
	private ArrayList<MyMarker> markerList = new ArrayList<MyMarker>();
	private Context ctx;
	private Handler_sqlite manejador;
	private MapView mapView;
	private Bitmap p;
	
	public SelectorDeVariables(Context ctx, Handler_sqlite manejador, MapView mapView, int idNivel1,int idOpcion2Seleccionada) {
		this.ctx = ctx;
		this.manejador = manejador;
		this.mapView = mapView;
		
		//Mostrar el total de viviendas 
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
		// Setting Dialog Title
        alertDialog.setTitle("Informaci�n");
		
        Cursor c;
        
		switch(idNivel1){
		
		case 0://Riesgo
			switch (idOpcion2Seleccionada) {
			case 0:
				//riesgoAlto();
				Toast.makeText(ctx, "Agregar llamada a funci�n R. Alto", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				//riesgoMedio();
				Toast.makeText(ctx, "Agregar llamada a funci�n R. Medio", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				//riesgoBajo();
				Toast.makeText(ctx, "Agregar llamada a funci�n R. Bajo", Toast.LENGTH_SHORT).show();
				break;
			case 3:
				//viviendasDeshabitadas();
				Toast.makeText(ctx, "Agregar llamada a funci�n Viviendas Desabitadas", Toast.LENGTH_SHORT).show();
				break;
			case 4:
				//dibujarCasitas();
				Toast.makeText(ctx, "Agregar llamada a funci�n Todas las casas", Toast.LENGTH_SHORT).show();
				break;
			
			}
			break;
		
		
    	//Opciones para Opciones de Men� que son tablas cat�logos		
		case 1:
			switch(idOpcion2Seleccionada){
			case 1:
				c= manejador.noPerteneceAPueblosIndigenas();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));	    				 	    			   	 
	        		 
			        // Setting Dialog Message
			        alertDialog.setMessage("El n�mero de familias que se identifican con el pueblo ind�gena \"No pertenece a pueblos ind�genas\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					// Setting Dialog Message
			        alertDialog.setMessage("No hay familias que se identifiquen con el pueblo ind�gena \"No pertenece a pueblos ind�genas\" ");
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 2:
				c= manejador.nahuatPipil();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));	    				 	    			   	 
	        		 
			        // Setting Dialog Message
			        alertDialog.setMessage("El n�mero de familias que se identifican con el pueblo ind�gena \"Nahuat/Pipil\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					// Setting Dialog Message
			        alertDialog.setMessage("No hay familias que se identifiquen con el pueblo ind�gena \"Nahuat/Pipil\" ");
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 3:
				c= manejador.chorti();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));	    				 	    			   	 
	        		 
			        // Setting Dialog Message
			        alertDialog.setMessage("El n�mero de familias que se identifican con el pueblo ind�gena \"Chorti\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					// Setting Dialog Message
			        alertDialog.setMessage("No hay familias que se identifiquen con el pueblo ind�gena \"Chorti\" ");
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 4:
				c= manejador.cacoperaOkakawira();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));	    				 	    			   	 
	        		 
			        // Setting Dialog Message
			        alertDialog.setMessage("El n�mero de familias que se identifican con el pueblo ind�gena \"Cacaopera o kakawira\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					// Setting Dialog Message
			        alertDialog.setMessage("No hay familias que se identifiquen con el pueblo ind�gena \"Cacopera o kakawira\" ");
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 5:
				c= manejador.lenca();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));	    				 	    			   	 
	        		 
			        // Setting Dialog Message
			        alertDialog.setMessage("El n�mero de familias que se identifican con el pueblo ind�gena \"Lenca\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					// Setting Dialog Message
			        alertDialog.setMessage("No hay familias que se identifiquen con el pueblo ind�gena \"Lenca\" ");
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 6:
				c= manejador.noDatoPuebloIndigena();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));	    				 	    			   	 
	        		 
			        // Setting Dialog Message
			        alertDialog.setMessage("El n�mero de familias que se identifican con el pueblo ind�gena \"No dato\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					// Setting Dialog Message
			        alertDialog.setMessage("No hay familias que se identifiquen con el pueblo ind�gena \"No dato\" ");
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
			
			}//switch opci�n 2
			break;
			
		case 2:
			switch(idOpcion2Seleccionada){
			case 1:
				c= manejador.ningunaReligion();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));	    				 	    			   	 
	        		 
			        // Setting Dialog Message
			        alertDialog.setMessage("El n�mero de familias pertencen a la religi�n \"Ninguna religi�n\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					// Setting Dialog Message
			        alertDialog.setMessage("No hay familias pertenezcan a la religi�n \"Ninguna religi�n\" ");
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 2:
				c= manejador.catolicos();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));	    				 	    			   	 
	        		 
			        // Setting Dialog Message
			        alertDialog.setMessage("El n�mero de familias pertencen a la religi�n \"Cat�licos\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					// Setting Dialog Message
			        alertDialog.setMessage("No hay familias pertenezcan a la religi�n \"Cat�licos\" ");
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 3:
				c= manejador.evangelicos();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));	    				 	    			   	 
	        		 
			        // Setting Dialog Message
			        alertDialog.setMessage("El n�mero de familias pertencen a la religi�n \"Evang�licos\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					// Setting Dialog Message
			        alertDialog.setMessage("No hay familias pertenezcan a la religi�n \"Evang�licos\" ");
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 6:
				c= manejador.otraReligion();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));	    				 	    			   	 
	        		 
			        // Setting Dialog Message
			        alertDialog.setMessage("El n�mero de familias pertencen a la religi�n \"Otros\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					// Setting Dialog Message
			        alertDialog.setMessage("No hay familias pertenezcan a la religi�n \"Otros\" ");
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 7:
				c= manejador.noDatoRelgion();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));	    				 	    			   	 
	        		 
			        // Setting Dialog Message
			        alertDialog.setMessage("El n�mero de familias pertencen a la religi�n \"No dato\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					// Setting Dialog Message
			        alertDialog.setMessage("No hay familias pertenezcan a la religi�n \"No dato\" ");
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
			
			}//switch nivel 2
			break;
			
		case 3:
			//Tipo de familia
			switch(idOpcion2Seleccionada){
			case 1:
				c= manejador.familiaNuclear();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));	    				 	    			   	 
	        		 
			        // Setting Dialog Message
			        alertDialog.setMessage("El n�mero de familias pertencen al tipo de familia \"Familia nuclear\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					// Setting Dialog Message
			        alertDialog.setMessage("No hay familias pertenezcan al tipo de familia \"Familia nuclear\" ");
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 2:
				c= manejador.familiaMixtaOAmpliada();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));	    				 	    			   	 
	        		 
			        // Setting Dialog Message
			        alertDialog.setMessage("El n�mero de familias pertencen al tipo de familia \"Familia mixta o ampliada\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					// Setting Dialog Message
			        alertDialog.setMessage("No hay familias pertenezcan al tipo de familia \"Familia mixta o ampliada\" ");
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
			case 3:
				c= manejador.familiaExtensaExtendida();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));	    				 	    			   	 
	        		 
			        // Setting Dialog Message
			        alertDialog.setMessage("El n�mero de familias pertencen al tipo de familia \"Familia extensa o extendida\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					// Setting Dialog Message
			        alertDialog.setMessage("No hay familias pertenezcan al tipo de familia \"Familia extensa o extendida\" ");
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			            	//ctx.startActivity(intent);
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			}//switch level 2
			break;
			
		
		//Opciones de Men� Din�mico
	    	case 22:
	    		//Manejo de Aguas Grises
	    				    			
	    			switch (idOpcion2Seleccionada){
	    			case 119:
	    				//A cielo abierto al solar
	    				c= manejador.aCieloAbiertoAlSolar();
	    				if(c.getCount()!=0){
	    					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));	    				 	    			   	 
   		         		 
		    		        // Setting Dialog Message
		    		        alertDialog.setMessage("El n�mero de viviendas que manejan las aguas grises \"A cielo abierto al solar\" son: "+ c.getCount());
		    		 
		    		        // On pressing Settings button
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		    		            	//ctx.startActivity(intent);
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
		    		        
		    				//Dibujando los puntos en el mapa
		    				dibujarPuntosFiltrados (c, p);
	    				}
	    				else{
	    					// Setting Dialog Message
		    		        alertDialog.setMessage("No hay viviendas con manejo de aguas grises \"A cielo abierto al solar\" ");
		    		 
		    		        // On pressing Settings button
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		    		            	//ctx.startActivity(intent);
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
	    				}
	    				break;
	    				
	    			case 120:
	    				//A la calle
	    				c= manejador.aLaCalle();
	    				if(c.getCount()!=0){
	    					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.a_la_calle));
   				 		 
		    		        // Setting Dialog Message
		    		        alertDialog.setMessage("El n�mero de viviendas que manejan las aguas grises \"A la calle\" son: "+ c.getCount());
		    		 
		    		        // On pressing Settings button
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		    		            	//ctx.startActivity(intent);
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
		    				
		    		        //Dibujando los puntos en el mapa
		    				dibujarPuntosFiltrados (c, p);
	    				}
	    				else{
	    					// Setting Dialog Message
		    		        alertDialog.setMessage("No hay viviendas con manejo de aguas grises \"A la calle\" ");
		    		 
		    		        // On pressing Settings button
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		    		            	//ctx.startActivity(intent);
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
	    				}
	    				break;
	    				
	    			case 117:
	    				//La eliminaci�n es a alcantarillado
	    				c= manejador.eliminacionAAlcantarillado();
	    				if(c.getCount()!=0){
	    					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.es_a_alcantarillado));
		    				
		    				// Setting Dialog Message
		    		        alertDialog.setMessage("El n�mero de viviendas que manejan las aguas grises \"Por eliminaci�n a alcantarillado\" son: "+ c.getCount());
		    		 
		    		        // On pressing Settings button
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		    		            	//ctx.startActivity(intent);
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
		    				
		    		        //Dibujando los puntos en el mapa	    				
		    				dibujarPuntosFiltrados (c, p);
	    				}
	    				else{
	    					// Setting Dialog Message
		    		        alertDialog.setMessage("No hay viviendas con manejo de aguas grises \"Por eliminaci�n a alcantarillado\" ");
		    		 
		    		        // On pressing Settings button
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		    		            	//ctx.startActivity(intent);
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
	    				}
	    				break;
	    				
	    			case 118:
	    				//Por sistema de pozo resumidero
	    				c= manejador.porPozoResumidero();
	    				if(c.getCount()!=0){
	    					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.pozo_resumidero));
	    				
		    				// Setting Dialog Message
		    		        alertDialog.setMessage("El n�mero de viviendas que manejan las aguas grises \"Por sistema de pozo resumidero\" son: "+ c.getCount());
		    		 
		    		        // On pressing Settings button
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		    		            	//ctx.startActivity(intent);
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
		    				
		    		        //Dibujando los puntos en el mapa
		    				dibujarPuntosFiltrados (c, p);
	    				}
	    				else{
	    					// Setting Dialog Message
		    		        alertDialog.setMessage("No hay viviendas con manejo de aguas grises \"Por sistema de pozo resumidero\" ");
		    		 
		    		        // On pressing Settings button
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		    		            	//ctx.startActivity(intent);
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
	    				}
	    				
	    				break;	
	    					
	    				
	    			case 121:
	    				//Quebradas r�os u otro lugar
	    				c= manejador.quebradasORios();
	    				if(c.getCount()!=0){
	    					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.quebrada_rio));
		    				// Setting Dialog Message
		    		        alertDialog.setMessage("El n�mero de viviendas que manejan las aguas grises \"Por quebrada r�o u otro lugar\" son: "+ c.getCount());
		    		 
		    		        // On pressing Settings button
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		    		            	//ctx.startActivity(intent);
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
		    				
		    		        //Dibujando los puntos en el mapa
		    				dibujarPuntosFiltrados (c, p);
	    				}
	    				else{
	    					// Setting Dialog Message
		    		        alertDialog.setMessage("No hay viviendas con manejo de aguas grises \"Por quebrada r�o u otro lugar\" ");
		    		 
		    		        // On pressing Settings button
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		    		            	//ctx.startActivity(intent);
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
	    				}
	    				break;
    			}
	    			break;
	    		
	    	case 23:
	    		switch (idOpcion2Seleccionada){
	    		case 122:
	    			c= manejador.eliminacionDeAlcantarilladoPorPozoResumideroOCuerpoReceptor();
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.pozo_o_cuerpo_receptor));
    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas que manejan las aguas negras \"Por eliminaci�n a alcantarillado por pozo resumidero o cuerpo receptor\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con manejo de aguas negras \"Por eliminaci�n a alcantarillado por pozo resumidero o cuerpo receptor\" ");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 123:
	    			c= manejador.eliminacionDeAlcantarilladoSinTratamiento();
	    			
	    			if(c.getCount()!=0){
	    				//Usando el mismo �cono que para agua grises
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.es_a_alcantarillado));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas que manejan las aguas negras \"Por eliminaci�n de alcantarillado sin tratamiento\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con manejo las aguas negras \"Por eliminaci�n de alcantarillado sin tratamiento\" ");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			
	    			break;
	    			
	    		case 166:
	    			c= manejador.noAplicaAguasNegras();
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.no_aplica));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas que no aplica manejo de aguas negras son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
		    			break;	    	
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas donde no aplique el manejo de aguas negras ");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();	    					
	    			}
	    			
	    			
	    		}	    		
	    		break;
	    		
	    	case 55:
	    		switch (idOpcion2Seleccionada){
	    		case 344: //Adobe
	    			c= manejador.adobe();
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.adobe));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de paredes \"Adobe\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de paredes \"Adobe\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    				
	    			break;
	    			
	    		case 347:
	    			c= manejador.laminaMetalicaEnMalEstado();
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar icono
		    			p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.lamina_metalica_mal_estado));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de paredes \"L�mina met�lica en mal estado\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de paredes \"L�mina met�lica en mal estado\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 348:
	    			c= manejador.pajaOPalma();
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.paja_o_palma));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de paredes \"Paja o palma\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else {
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de paredes \"Paja o palma\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 349:
	    			c= manejador.materialesDeDesecho();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar icono 
		    			p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.paja_o_palma));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de paredes \"Materiales de desecho\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de paredes \"Materiales de desecho\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 350:
	    			c= manejador.otrosMateriales();
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de paredes \"Otros materiales\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de paredes \"Otros materiales\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			
	    			break;
	    			
	    		case 351:
	    			c= manejador.noTieneParedes();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar icono
		    			p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de paredes \"No tiene paredes\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay de viviendas con material de paredes \"No tiene paredes\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			
	    			break;
	    			
	    		case 540:
	    			c= manejador.concretoOMixto();
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.concreto_o_mixto));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de paredes \"Concreto o mixto\"son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de paredes \"Concreto o mixto\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			
	    			break;
	    			
	    		case 541:
	    			c= manejador.bajareque();
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.bajareque));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de paredes \"Bajareque\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de paredes \"Bajareque\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			
	    			break;
	    			
	    		case 543:
	    			c= manejador.madera();
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.madera));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de paredes \"Madera\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de paredes \"Madera\" ");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			
	    			break;
	    			
	    		case 544:
	    			c= manejador.madera();
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.lamina_metalica_buen_estado));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de paredes \"L�mina met�lica en buen estado\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de paredes \"L�mina met�lica en buen estado\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			
	    			break;	    		
	    		}//Switch idNivel2
	    	case 56:
	    		//Material predominante de piso
	    		switch(idOpcion2Seleccionada){
	    		case 352:
	    			c= manejador.ladrilloCeramico();
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.ladrillo_ceramico));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de piso \"Ladrillo cer�mico\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de piso \"Ladrillo cer�mico\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    		case 353:
	    			c= manejador.ladrilloCemento();
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.ladrillo_cemento));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de piso \"Ladrillo de cemento\"son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de piso \"Ladrillo de cemento\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}	    			
	    			break;
	    			
	    		case 354:
	    			c= manejador.ladrilloBarro();
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.ladrillo_barro));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de piso \"Ladrillo de barro\"son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de piso \"Ladrillo de barro\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 355:
	    			c= manejador.cemento();
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cemento));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de piso \"Cemento\"son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de piso \"Cemento\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;	    			
	    			
	    		case 356:
	    			c= manejador.tierra();
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.tierra));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de piso \"Tierra\"son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de piso \"Tierra\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    		
	    			case 357:
	    				c= manejador.otrosMaterialesPiso();
		    			
		    			if(c.getCount()!=0){
		    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
		    				
		    				// Setting Dialog Message
		    		        alertDialog.setMessage("El n�mero de viviendas con material de piso \"Otros materiales\" son: "+c.getCount());
		    		 
		    		        // On pressing Settings button
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		    		            	//ctx.startActivity(intent);
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
		    				
		    		        //Dibujando los puntos en el mapa	    				
		    				dibujarPuntosFiltrados (c, p);
		    			}
		    			else{
		    				// Setting Dialog Message
		    		        alertDialog.setMessage("No hay viviendas con material de piso \"Otros materiales\"");
		    		 
		    		        // On pressing Settings button
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		    		            	//ctx.startActivity(intent);
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
		    			}	    				
	    				break;	    		
	    		}//Switch id Nivel2
	    		break;
	    		
	    	case 57:
	    		switch (idOpcion2Seleccionada){
	    		case 358:
	    			c= manejador.lozaDeConcreto();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de techo \"Loza de concreto\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de techo \"Loza de concreto\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}	    		
	    			break;
	    			
	    		case 359:
	    			c= manejador.tejaDeBarroOCemento();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de techo \"Teja de barro o cemento\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de techo \"Teja de barro o cemento\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 360:
	    			c= manejador.laminaDeAsbetoODuralita();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de techo \"L�mina de asbeto o duralita\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de techo \"L�mina de asbeto o duralita\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 361:
	    			c= manejador.laminaMetalicaEnBuenEstadoTecho();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.lamina_metalica_buen_estado));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de techo \"L�mina met�lica en buen estado\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de techo \"L�mina met�lica en buen estado\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 362:
	    			c= manejador.laminaMetalicaEnMalEstadoTecho();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.lamina_metalica_mal_estado));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de techo \"L�mina met�lica en mal estado\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de techo \"L�mina met�lica en mal estado\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 363:
	    			c= manejador.pajaOPalmaTecho();
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.paja_o_palma));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de techo \"Paja o palma\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de techo \"Paja o palma\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 364:
	    			c= manejador.materialesDeDesechoTecho();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de techo \"Materiales de desecho\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de techo \"Materiales de desecho\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 365:
	    			c= manejador.otrosMaterialesTecho();
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de techo \"Otros materiales\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de techo \"Otros materiales\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 366:
	    			c= manejador.noTieneTecho();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con material de techo \"No tiene techo\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de techo \"No tiene techo\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    		
	    		}//Switch id nivel 2
	    		break;
	    		
	    	case 46:
	    		
	    		switch(idOpcion2Seleccionada){
	    		case 310:
	    			c= manejador.noLeDanTratamiento();
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.no_le_dan_ningun_tratamiento));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tratamiento de agua para consumo que \"No le dan ning�n tratamiento\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tratamiento de agua para consumo que \"No le dan ning�n tratamiento\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 311:
	    			c= manejador.laHierven();
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.la_hierven));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tratamiento de agua para consumo que \"La hierven\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tratamiento de agua para consumo que \"La hierven\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 312:
	    			c= manejador.laTratanConLejiaOPuriagua();
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.lejia_o_puriagua));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tratamiento de agua para consumo que \"La tratan con lej�a o puriagua\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tratamiento de agua para consumo que \"La tratan con lej�a o puriagua\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 313:
	    			c= manejador.usaFiltroDeAgua();
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.filtro_se_agua));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tratamiento de agua para consumo que \"Usa filtro de agua\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tratamiento de agua para consumo que \"Usa filtro de agua\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 314:
	    			c= manejador.compraAguaEnvasada();
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.agua_envasada));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tratamiento de agua para consumo que \"Compra agua envasada\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tratamiento de agua para consumo que \"Compra agua envasada\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 315:
	    			c= manejador.otrosAgua();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tratamiento de agua para consumo \"Otros\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tratamiento de agua para consumo \"Otros\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    		
	    		}//Switch nivel 2	    		
	    		break;
	    		
	    	case 65:
	    		switch(idOpcion2Seleccionada){
	    		case 401:
	    			c= manejador.canieriaDentroAnda();
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.canieria_dentro_de_la_vivienda_anda));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con abastecimiento de agua \"Por ca�er�a dentro de la vivienda con abastecimiento p�blico (ANDA)\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con abastecimiento de agua \"Por ca�er�a dentro de la vivienda con abastecimiento p�blico (ANDA)\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 402:
	    			c= manejador.canieriaDentroOtroAbastecimiento();
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.canieria_dentro_de_la_vivienda_otro));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con abastecimiento de agua \"Por ca�er�a dentro de la vivienda con otro tipo de abastecimiento\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con abastecimiento de agua \"Por ca�er�a dentro de la vivienda con otro tipo de abastecimiento\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 403:
	    			c= manejador.canieriaFueraDeLaPropiedad();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.canieria_fuera_de_la_propiedad));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con abastecimiento de agua \"Por ca�er�a fuera de la propiedad\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con abastecimiento de agua \"Por ca�er�a fuera de la propiedad\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 405:
	    			c= manejador.pozoDentro();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.pozo_dentro_de_la_propiedad));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con abastecimiento de agua \"Por pozo dentro de la propiedad\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con abastecimiento de agua \"Por pozo dentro de la propiedad\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 406:
	    			c= manejador.camionCarretaOPipa();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.camion_carreta_pipa));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con abastecimiento de agua \"Cami�n, carreta o pipa\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con abastecimiento de agua \"Cami�n, carreta o pipa\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 407:
	    			c= manejador.aguaLluvia();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.agua_lluvia));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con abastecimiento de agua \"Agua lluvia\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con abastecimiento de agua \"Agua lluvia\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 408:
	    			c= manejador.rioQuebradaLagoOjoDeAguaManantial();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.rio_quebrada_ojo_de_agua_manantial));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con abastecimiento de agua \"R�o, quebrada, lago, ojo de agua o manantial\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con abastecimiento de agua \"R�o, quebrada, lago, ojo de agua o manantial\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 518:
	    			c= manejador.pozoFuera();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.pozo_fuera_uso_comunitario));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con abastecimiento de agua \"Pozo fuera de la propiedad (De uso comunitario)\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con abastecimiento de agua \"Pozo fuera de la propiedad (De uso comuntario)\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 519:
	    			c= manejador.canieriaFueraDeLaPropiedad();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.canieria_fuera_de_la_propiedad));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con abastecimiento de agua \"Por ca�er�a fuera de la propiedad\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con abastecimiento de agua \"Por ca�er�a fuera de la propiedad\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    		
	    		}//switch nivel 2
	    		break;
	    	
	    	case 63:
	    		switch(idOpcion2Seleccionada){
	    		case 388:
	    			c= manejador.electricidad();
	    			
	    			if(c.getCount()!=0){

	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.electricidad));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con alumbrado por \"Electricidad\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con alumbrado por \"Electricidad\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 389:
	    			c= manejador.conexionElectricaDelVecino();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.conexion_electrica_del_vecino));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con alumbrado por \"Conexi�n el�ctrica del vecino\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con alumbrado por \"Conexi�n el�ctrica del vecino\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 391:
	    			c= manejador.keroseneAlumbrado();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.kerosene));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con alumbrado por \"Kerosene\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con alumbrado por \"Kerosene\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 392:
	    			c= manejador.candela();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.candela));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con alumbrado por \"Candela\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con alumbrado por \"Candela\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 393:
	    			c= manejador.panelSolar();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.panel_solar));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con alumbrado por \"Panel solar\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con alumbrado por \"Panel solar\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 394:
	    			c= manejador.generadorElectrico();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.generador_electrico));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con alumbrado por \"Generador el�ctrico\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con alumbrado por \"Generador el�ctrico\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 395:
	    			c= manejador.otraClaseAlumbrado();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otra_clase_alumbradoi));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con alumbrado por \"Otra clase\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con alumbrado por \"Otra clase\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		}//switch dee nivel 2
	    		break;
	    		
	    	case 59:
	    		switch (idOpcion2Seleccionada){
				case 367:
					c= manejador.cultivoAgricolaPropio();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cultivo_agricola_propio));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con bienes de familia\"Cultivo agr�cola propio\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con bienes de familia \"Cultivo agr�cola propio\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
					break;
					
				case 368:
					c= manejador.avesDeCorral();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.aves_de_corral));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con bienes de familia\"Aves de corral\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con bienes de familia \"Aves de corral\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
					break;
					
				case 369:
					c= manejador.ganadoVacuno();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.ganado_vacuno));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con bienes de familia\"Ganado vacuno\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con bienes de familia \"Ganado vacuno\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
					break;
					
				case 371:
					c= manejador.ganadoPorcino();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.ganado_porcino));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con bienes de familia\"Ganado porcino\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con bienes de familia \"Ganado porcino\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
					
					break;
					
				case 372:
					c= manejador.negocioPropio();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.negocio_propio));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con bienes de familia\"Negocio propio\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con bienes de familia \"Negocio propio\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
					break;
					
				case 373:
					c= manejador.ningunBien();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.ninguno));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con bienes de familia\"Ning�n bien\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con bienes de familia \"Ning�n bien\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
					break;
					

				
				}//Switch nivel 2
	    		break;
	    		
	    	case 60:
	    		switch (idOpcion2Seleccionada){
				case 374:
					c= manejador.radio();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.radio));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con bienes del hogar \"Radio\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con bienes del hogar \"Radio\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
					break;
					
				case 375:
					c= manejador.equipoDeSonido();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.equipo_de_sonido));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con bienes del hogar \"Equipo de sonido\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con con bienes del hogar \"Equipo de sonido\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
					break;
					
				case 376:
					c= manejador.televisor();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.televisor));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con bienes del hogar \"Telvisor\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con bienes del hogar \"Televisor\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
					break;
					
				case 377:
					c= manejador.refrigeradora();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.refrigeradora));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con bienes del hogar \"Refrigeradora\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con bienes del hogar \"Refrigeradora\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
					
					break;
					
				case 378:
					c= manejador.lavadora();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.lavadora));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con bienes del hogar \"Lavadora\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con bienes del hogar \"Lavadora\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
					break;
					
				case 379:
					c= manejador.computadora();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.computadora));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con bienes de familia \"Computadora\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con bienes del hogar \"Computadora\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
					break;		
					
				case 380:
					c= manejador.ningunBienHogar();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.ninguno));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con bienes de familia \"Ning�n bien\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con bienes del hogar \"Ning�n bien\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
					break;

				
				}//Switch nivel 2
	    		break;
	    	case 62:
	    		switch(idOpcion2Seleccionada){
	    		case 527:
	    			c= manejador.electricidadParaCocina();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.electricidad));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas que utilizan \"Electricidad\" como combustible para cocinar son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que utilicen \"Electricidad\" como combustible para cocinar");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 528:
	    			c= manejador.kerosenParaCocina();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.kerosen_gas));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas que utilizan \"Kerosen (gas)\" como combustible para cocinar son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que utilicen \"Kerosen (gas)\" como combustible para cocinar");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 529:
	    			c= manejador.gasPropanoParaCocina();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.gas_propano));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas que utilizan \"Gas propano\" como combustible para cocinar son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que utilicen \"Gas propano\" como combustible para cocinar");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 530:
	    			c= manejador.leniaParaCocina();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.lenia));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas que utilizan \"Le�a\" como combustible para cocinar son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que utilicen \"Le�a\" como combustible para cocinar");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 531: 
	    			c= manejador.carbonParaCocina();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.carbon));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas que utilizan \"Carb�n\" como combustible para cocinar son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que utilicen \"Carb�n\" como combustible para cocinar");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			
	    		case 532:
	    			c= manejador.estopaDeCocoParaCocina();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.estopa_de_coco));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas que utilizan \"Estopa de coco\" como combustible para cocinar son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que utilicen \"Estopa de coco\" como combustible para cocinar");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 533:
	    			c= manejador.otrasParaCocina();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_cocina));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas que utilizan \"Otras\" como combustible para cocinar son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que utilicen \"Otras\" como combustible para cocinar");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 534:
	    			c= manejador.ningunaParaCocina();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.ninguno));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas que utilizan \"Ninguna\" como combustible para cocinar son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que utilicen \"Ninguna\" como combustible para cocinar");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		}//switch nivel 2
	    		break;
	    		
	    	case 69:
	    		switch (idOpcion2Seleccionada){
	    		case 419:
	    			c= manejador.recoleccionDomiciliariaPublica();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.recoleccion_domiciliaria_publica));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con manejo de basura \"Recolecci�n domiciliaria p�blica\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con manejo de basura \"Recolecci�n domiciliaria p�blica\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 420:
	    			c= manejador.recoleccionDomiciliariaPrivada();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.recoleccion_domiciliaria_privada));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con manejo de basura \"Recolecci�n domiciliaria privada\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con manejo de basura \"Recolecci�n domiciliaria privada\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 421:
	    			c= manejador.laDeopositanEnContenedores();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.la_depositan_en_contenedores));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con manejo de basura \"La depositan en contenedores\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con manejo de basura \"La depositan en contenedores\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 422:
	    			c= manejador.laEntierran();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.la_entierran));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con manejo de basura \"La entierran\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con manejo de basura \"La entierran\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 423:
	    			c= manejador.laQueman();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.la_quman));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con manejo de basura \"La queman\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con manejo de basura \"La queman\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 424:
	    			c= manejador.laDepositanEnCualquierLugar();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.la_depositan_en_cualquier_lugar));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con manejo de basura \"La depositan en cualquier lugar\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con manejo de basura \"La depositan en cualquier lugar\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 425:
	    			c= manejador.otrasFormasBasura();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otras_formas_basura));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con manejo de basura \"Otras formas\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con manejo de basura \"Otras formas\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;	    		
	    			
	    		}//switch nivel 2
	    		break;
	    		
	    	case 64:
	    		switch(idOpcion2Seleccionada){
	    		case 396:
	    			c= manejador.telefonoFijo();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.telefono_fijo));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas que tienen medio de comunicaci�n \"Tel�fono fijo\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que tengan medio de comunicaci�n \"Tel�fono fijo\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 397:
	    			c= manejador.telefonoCelular();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.telefono_celular));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas que tienen medio de comunicaci�n \"Tel�fono celular\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que tengan medio de comunicaci�n \"Tel�fono celular\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 398:
	    			c= manejador.internet();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.internet));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas que tienen medio de comunicaci�n \"Internet\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que tengan medio de comunicaci�n \"Internet\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 399:
	    			c= manejador.cable();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas que tienen medio de comunicaci�n \"Cable\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que tengan medio de comunicaci�n \"Cable\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 400:
	    			c= manejador.noTieneMedioDeComunicacion();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.no_tiene_medio_comunicacion));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas que tienen medio de comunicaci�n \"No tiene\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que tengan medio de comunicaci�n \"No tiene\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			    			
	    		}//switch nivel 2;
	    		break;
	    		
	    	case 70: 
	    		switch(idOpcion2Seleccionada){
	    		case 426:
	    			c= manejador.zancudos();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.zancudos));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con presencia de vectores \"Zancudos\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con presencia de vectores \"Zancudos\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 427:
	    			c= manejador.chinchePicuda();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.chinche_picuda));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con presencia de vectores \"Chinche picuda\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con presencia de vectores \"Chinche picuda\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 428:
	    			c= manejador.moscas();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.moscas));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con presencia de vectores \"Moscas\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con presencia de vectores \"Moscas\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 429:
	    			c= manejador.cucarachas();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cucarachas));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con presencia de vectores \"Cucarachas\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con presencia de vectores \"Cucarachas\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 430:
	    			c= manejador.roedores();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.roedores));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con presencia de vectores \"Roedores\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con presencia de vectores \"Roedores\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 431:
	    			c= manejador.otrosVectores();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_vectores));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con presencia de vectores \"Otros\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con presencia de vectores \"Otros\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 432:
	    			c= manejador.noHayPresenciaDeVectores();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.ninguno));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con presencia de vectores \"No hay presencia de vectores\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con presencia de vectores \"No hay presencia de vectores\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    		
	    		}//switch nivel 2
	    		break;
	    		
	    	case 61:
	    		switch(idOpcion2Seleccionada){
	    		case 381:
	    			c= manejador.sinRiesgoAmbiental();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con riesgo ambiental \"Sin riesgo\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con riesgo ambiental \"Sin riesgo\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 382:
	    			c= manejador.deslaves();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con riesgo ambiental \"Deslaves\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con riesgo ambiental \"Deslaves\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 384:
	    			c= manejador.inundaciones();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con riesgo ambiental \"Inundaciones\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con riesgo ambiental \"Inundaciones\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 385:
	    			c= manejador.contaminacionPorDisposicionNoAdecuadaDeDesechosSolidosQuimicos();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con riesgo ambiental \"Contaminaci�n por disposici�n no adecuada de desechos s�lidos, qu�micos\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con riesgo ambiental \"Contaminaci�n por disposici�n no adecuada de desechos s�lidos, qu�micos\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 386:
	    			c= manejador.erupcion();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con riesgo ambiental \"Erupci�n\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con riesgo ambiental \"Erupci�n\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 387:
	    			c= manejador.otrosRiesgosAmbientales();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con riesgo ambiental \"Otros riesgos\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con riesgo ambiental \"Otros riesgos\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		}//switch nivel 2
	    		break;
	    		
	    	case 66:
	    		switch (idOpcion2Seleccionada){
	    		case 409:
	    			c= manejador.siDeUsoPrivado();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con servicio sanitario \"Si y es de uso privado\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con servicio sanitario \"Si y es de uso privado\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 410:
	    			c= manejador.siDeUsoColectivo();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con servicio sanitario \"Si y es de uso colectivo\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con servicio sanitario \"Si y es de uso colectivo\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 411:
	    			c= manejador.noTieneServicioSanitario();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con servicio sanitario \"No tiene\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con servicio sanitario \"No tiene\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		}//switch nivel 2
	    		break;
	    		
	    	case 54:
	    		switch(idOpcion2Seleccionada){
	    		case 332:
	    			c= manejador.inquilina();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tenencia de vivienda \"Inquilina(o)\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tenencia de vivienda \"Inquilina(o)\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 333:
	    			c= manejador.propietariaPeroPagandoAPlazos();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tenencia de vivienda \"Propietaria(o) de la vivienda pero la est� pagando a plazos\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tenencia de vivienda \"Propietaria(o) de la vivienda pero la est� pagando a plazos \"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 334:
	    			c= manejador.propietaria();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tenencia de vivienda \"Propietaria(o)\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tenencia de vivienda \"Propietaria(o)\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 335:
	    			c= manejador.propietarioEnTerrenoPublico();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tenencia de vivienda \"Propietaria(o) de la vivienda en terreno p�blicoS\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tenencia de vivienda \"Propietaria(o) de la vivienda en terreno p�blico\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 336:
	    			c= manejador.propietariaEnTerrenoPrivado();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tenencia de vivienda \"Propietaria(o) de la vivienda en terreno privado\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tenencia de vivienda \"Propietaria(o) de la vivienda en terreno privado\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 535:
	    			c= manejador.colono();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tenencia de vivienda \"Colona(o)\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tenencia de vivienda \"Colona(o)\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 536:
	    			c= manejador.guardianDeLaVivienda();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tenencia de vivienda \"Guardi�n de la vivienda\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tenencia de vivienda \"Guardi�n de la vivienda\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 537:
	    			c= manejador.ocupanteGratuito();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tenencia de vivienda \"Ocupante gratuito\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tenencia de vivienda \"Ocupante gratuito\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 538:
	    			c= manejador.otroTenenciaDeVivienda();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tenencia de vivienda \"Otro\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tenencia de vivienda \"Otro\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 539:
	    			c= manejador.noDatoTenenciaVivienda();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tenencia de vivienda \"No dato\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tenencia de vivienda \"No dato\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		}//switch de nivel 2
	    		break;
	    		
	    	case 67:
	    		switch(idOpcion2Seleccionada){
	    		case 412:
	    			c= manejador.inodoroConectadoAAlcantarillado();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tipo de sanitario \"Inodoro conectado a alcantarillado\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tipo de sanitario \"Inodoro conectado a alcantarillado\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 413:
	    			c= manejador.inodoroAFosaSeptica();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tipo de sanitario \"Inodoro a fosa s�ptica\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tipo de sanitario \"Inodoro a fosa s�ptica\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 414:
	    			c= manejador.letrinaAbonera();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tipo de sanitario \"Letrina abonera\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tipo de sanitario \"Letrina abonera\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 415:
	    			c= manejador.letrinaHoyoSeco();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tipo de sanitario \"Letrina de hoyo seco\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tipo de sanitario \"Letrina de hoyo seco\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 416:
	    			c= manejador.letrinaSolar();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tipo de sanitario \"Letrina solar\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tipo de sanitario \"Letrina solar\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 417: 
	    		c= manejador.otroTipo_TipoSanitario();	
    			
    			if(c.getCount()!=0){
    				//Queda pendiente cambiar el icono
    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
    				
    				// Setting Dialog Message
    		        alertDialog.setMessage("El n�mero de viviendas con tipo de sanitario \"Otro tipo\" son: "+c.getCount());
    		 
    		        // On pressing Settings button
    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    		            public void onClick(DialogInterface dialog,int which) {
    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    		            	//ctx.startActivity(intent);
    		            	dialog.cancel();
    		            }
    		        });
    		        alertDialog.show();
    				
    		        //Dibujando los puntos en el mapa	    				
    				dibujarPuntosFiltrados (c, p);
    			}
    			else{
    				// Setting Dialog Message
    		        alertDialog.setMessage("No hay viviendas con tipo de sanitario \"Otro tipo\"");
    		 
    		        // On pressing Settings button
    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    		            public void onClick(DialogInterface dialog,int which) {
    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    		            	//ctx.startActivity(intent);
    		            	dialog.cancel();
    		            }
    		        });
    		        alertDialog.show();
    			};
    			break;
    			
	    		case 418:
	    			c= manejador.noAplicaTipoSanitario();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tipo de sanitario \"No aplica\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tipo de sanitario \"No aplica\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		}//switch nivel 2
	    		break;
	    		
	    	case 47:
	    		switch(idOpcion2Seleccionada){
	    		case 316:
	    			c= manejador.casaPrivadaOIndependiente();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tipo de vivienda \"Casa privada o independiente\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tipo de vivienda \"Casa privada o independiente\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 317:
	    			c= manejador.casaCompartida();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tipo de vivienda \"Casa compartida\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tipo de vivienda \"Casa compartida\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 318:
	    			c= manejador.apartamento();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tipo de vivienda \"Apartamento\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tipo de vivienda \"Apartamento\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 319:
	    			c= manejador.condominio();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tipo de vivienda \"Condominio\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tipo de vivienda \"Condominio\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 329:
	    			c= manejador.champa();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tipo de vivienda \"Champa\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tipo de vivienda \"Champa\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 330:
	    			c= manejador.piezaEnCasaOMeson();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tipo de vivienda \"Pieza en una casa o mes�n\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tipo de vivienda \"Pieza en una casa o mes�n\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 331:
	    			c= manejador.otroTipoVivienda();	
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El n�mero de viviendas con tipo de vivienda \"Otro tipo\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tipo de vivienda \"Otro tipo\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    		            	//ctx.startActivity(intent);
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		}//switch de nivel 2
	    		break;
	    		
    	}
	}
	
	public void dibujarPuntosFiltrados (Cursor c, Bitmap p){
		if(c.moveToFirst()){
			try{
				do{
					double lon = c.getDouble(0);
					double lat = c.getDouble(1);
					//Toast.makeText(ctx, "Lat: "+lat+"| Lon: "+lon, Toast.LENGTH_SHORT).show();
					Cursor info = manejador.informacionFamilia(lon, lat);
					//Toast.makeText(ctx, "Total info: "+ info.getCount(), Toast.LENGTH_SHORT).show();
					if(info.moveToFirst()){
						do{
							int depto = info.getInt (0);
    						int municipio = info.getInt(1);
    						String area = info.getString(2);
    					    int ctn_bar_col = info.getInt (3);				    
    					    String zona = info.getString(4);
    					    String num_vivienda = info.getString(5);
    					    String num_familia = info.getString(6);
    					    			
    					    String num_exp = info.getString(0)+ info.getString(1)+ info.getString(2)+info.getString(3)+info.getString(4)+info.getString(5)+info.getString(6);
    					    //Toast.makeText(ctx, "Antes de dibujar punto", Toast.LENGTH_SHORT).show();
    						MyMarker fichaFiltro = new MyMarker(ctx, new LatLong(lat,lon), p, 0, 0, mapView, "N�mero de Expediente: "+num_exp+"\nJefe de Familia: "+info.getString(7), false, true, depto,municipio,area,ctn_bar_col,zona,num_vivienda,num_familia);
    						mapView.getLayerManager().getLayers().add(fichaFiltro);
    						markerList.add(fichaFiltro);
    						
						}while(info.moveToNext());
					}										    								    											    										    											    						
				}while (c.moveToNext());
			}catch(Exception e){
				Toast.makeText(ctx, "Error:"+ e, Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	public ArrayList<MyMarker> getVariablesDibujadas (){
		return markerList;
	}
	
}
