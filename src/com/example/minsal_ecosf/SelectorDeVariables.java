package com.example.minsal_ecosf;

import java.util.ArrayList;

import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.view.MapView;

import com.fichafamiliar.MainActivityMapa;
import com.fichafamiliar.R;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.widget.Toast;

public class SelectorDeVariables {
	
	//private ArrayList<MyMarker> markerList = new ArrayList<MyMarker>();
	private Context ctx;
	private Handler_sqlite manejador;
	private MapView mapView;
	private Bitmap p;
	private Cursor cd; //cursor dibujo
	
	public SelectorDeVariables(Context ctx, Handler_sqlite manejador, MapView mapView, int idNivel1,int idOpcion2Seleccionada, String valor) {
		this.ctx = ctx;
		this.manejador = manejador;
		this.mapView = mapView;
		
		//Mostrar el total de viviendas 
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
		// Setting Dialog Title
        alertDialog.setTitle("Información");
		
        Cursor c;
        
		switch(idNivel1){
		
		case 0://Riesgo
			switch (idOpcion2Seleccionada) {
			case 0:
				//riesgoAlto();
				Toast.makeText(ctx, "Agregar llamada a función R. Alto", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				//riesgoMedio();
				Toast.makeText(ctx, "Agregar llamada a función R. Medio", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				//riesgoBajo();
				Toast.makeText(ctx, "Agregar llamada a función R. Bajo", Toast.LENGTH_SHORT).show();
				break;
			case 3:
				//viviendasDeshabitadas();
				Toast.makeText(ctx, "Agregar llamada a función Viviendas Desabitadas", Toast.LENGTH_SHORT).show();
				break;
			case 4:
				//dibujarCasitas();
				Toast.makeText(ctx, "Agregar llamada a función Todas las casas", Toast.LENGTH_SHORT).show();
				break;
			
			}
			break;
		
		
    	//Opciones para Opciones de Menú que son tablas catálogos		
		case 1:
			switch(idOpcion2Seleccionada){
			case 1:
				c= manejador.noPerteneceAPueblosIndigenas();
				
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.no_pertenece_pueblos_indigenas));	    				 	    			   	 
	        		 
			        // Setting Dialog Message
			        alertDialog.setMessage("El número de familias que se identifican con el pueblo indígena \"No pertenece a pueblos indígenas\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			                    dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					
			        alertDialog.setMessage("No hay familias que se identifiquen con el pueblo indígena \"No pertenece a pueblos indígenas\" ");
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 2:
				c= manejador.nahuatPipil();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.nahuat_pipil));	    				 	    			   	 
	        		
			        alertDialog.setMessage("El número de familias que se identifican con el pueblo indígena \"Nahuat/Pipil\" son: "+ c.getCount());
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					
			        alertDialog.setMessage("No hay familias que se identifiquen con el pueblo indígena \"Nahuat/Pipil\" ");
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 3:
				c= manejador.chorti();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.chorti));	    				 	    			   	 
	        		 
			        alertDialog.setMessage("El número de familias que se identifican con el pueblo indígena \"Chorti\" son: "+ c.getCount());
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
				
			        alertDialog.setMessage("No hay familias que se identifiquen con el pueblo indígena \"Chorti\" ");
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 4:
				c= manejador.cacoperaOkakawira();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cacaopera_kakawira));	    				 	    			   	 
	        		 
			        alertDialog.setMessage("El número de familias que se identifican con el pueblo indígena \"Cacaopera o kakawira\" son: "+ c.getCount());
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
				
			        alertDialog.setMessage("No hay familias que se identifiquen con el pueblo indígena \"Cacopera o kakawira\" ");
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 5:
				c= manejador.lenca();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.lenca));	    				 	    			   	 
	        		 
			        alertDialog.setMessage("El número de familias que se identifican con el pueblo indígena \"Lenca\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					
			        alertDialog.setMessage("No hay familias que se identifiquen con el pueblo indígena \"Lenca\" ");
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 6:
				c= manejador.noDatoPuebloIndigena();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.no_dato));	    				 	    			   	 
	        		 
			        alertDialog.setMessage("El número de familias que se identifican con el pueblo indígena \"No dato\" son: "+ c.getCount());
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					
			        alertDialog.setMessage("No hay familias que se identifiquen con el pueblo indígena \"No dato\" ");
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
			
			}//switch opción 2
			break;
			
		case 2:
			switch(idOpcion2Seleccionada){
			case 1:
				c= manejador.ningunaReligion();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.ninguna_religion));	    				 	    			   	 
	        		 
			        alertDialog.setMessage("El número de familias pertencen a la religión \"Ninguna religión\" son: "+ c.getCount());
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            
			            	
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					
			        alertDialog.setMessage("No hay familias pertenezcan a la religión \"Ninguna religión\" ");
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 2:
				c= manejador.catolicos();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.catolica));	    				 	    			   	 
	        		 
			        alertDialog.setMessage("El número de familias pertencen a la religión \"Católicos\" son: "+ c.getCount());
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
				
			        alertDialog.setMessage("No hay familias pertenezcan a la religión \"Católicos\" ");
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 3:
				c= manejador.evangelicos();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.evangelica));	    				 	    			   	 
	        		 
			        alertDialog.setMessage("El número de familias pertencen a la religión \"Evangélicos\" son: "+ c.getCount());
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					
			        alertDialog.setMessage("No hay familias pertenezcan a la religión \"Evangélicos\" ");
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 4:
				c= manejador.mormones();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.mormon));	    				 	    			   	 
	        		 
			        alertDialog.setMessage("El número de familias pertencen a la religión \"Mormones\" son: "+ c.getCount());
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					
			        alertDialog.setMessage("No hay familias pertenezcan a la religión \"Mormones\" ");
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 5:
				c= manejador.masDeUnaReligion();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.mas_de_una_religion));	    				 	    			   	 
	        		
			        alertDialog.setMessage("El número de familias pertencen a la religión \"Más de una religión\" son: "+ c.getCount());
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
		
			        alertDialog.setMessage("No hay familias pertenezcan a la religión \"Más de una religión\" ");
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 6:
				c= manejador.otraReligion();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otra_religion));	    				 	    			   	 
	        		 
			        alertDialog.setMessage("El número de familias pertencen a la religión \"Otros\" son: "+ c.getCount());
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
				
			        alertDialog.setMessage("No hay familias pertenezcan a la religión \"Otros\" ");
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 7:
				c= manejador.noDatoRelgion();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.no_dato));	    				 	    			   	 
	        		 
			        alertDialog.setMessage("El número de familias pertencen a la religión \"No dato\" son: "+ c.getCount());
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					
			        alertDialog.setMessage("No hay familias pertenezcan a la religión \"No dato\" ");
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
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
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.familia_nuclear));	    				 	    			   	 
	        		 
			        alertDialog.setMessage("El número de familias pertencen al tipo de familia \"Familia nuclear\" son: "+ c.getCount());
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					
			        alertDialog.setMessage("No hay familias pertenezcan al tipo de familia \"Familia nuclear\" ");
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {

			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			case 2:
				c= manejador.familiaMixtaOAmpliada();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.familia_ampliada));	    				 	    			   	 
	        		 
			        alertDialog.setMessage("El número de familias pertencen al tipo de familia \"Familia mixta o ampliada\" son: "+ c.getCount());
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
				
			        alertDialog.setMessage("No hay familias pertenezcan al tipo de familia \"Familia mixta o ampliada\" ");
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
			case 3:
				c= manejador.familiaExtensaExtendida();
				if(c.getCount()!=0){
					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.familia_extendida));	    				 	    			   	 
	        		 
			        alertDialog.setMessage("El número de familias pertencen al tipo de familia \"Familia extensa o extendida\" son: "+ c.getCount());
			 
			        // On pressing Settings button
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
			        
					//Dibujando los puntos en el mapa
					dibujarPuntosFiltrados (c, p);
				}
				else{
					
			        alertDialog.setMessage("No hay familias pertenezcan al tipo de familia \"Familia extensa o extendida\" ");
			 
			        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			            	dialog.cancel();
			            }
			        });
			        alertDialog.show();
				}
				break;
				
			}//switch level 2
			break;
			
		
		//Opciones de Menú Dinámico
	    	case 22:
	    		//Manejo de Aguas Grises
	    				    			
	    			switch (idOpcion2Seleccionada){
	    			case 119:
	    				//A cielo abierto al solar
	    				//c= manejador.aCieloAbiertoAlSolar();
	    				c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    				if(c.getCount()!=0){
	    					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cielo_abierto_al_solar));	    				 	    			   	 
   		         		 
		    		        alertDialog.setMessage("El número de viviendas que manejan las aguas grises \"A cielo abierto al solar\" son: "+ c.getCount());
		    		 
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
		    		        
		    				//Dibujando los puntos en el mapa
		    				dibujarPuntosFiltrados (c, p);
	    				}
	    				else{
	    					
		    		        alertDialog.setMessage("No hay viviendas con manejo de aguas grises \"A cielo abierto al solar\" ");
		    		 
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
	    				}
	    				break;
	    				
	    			case 120:
	    				//A la calle
	    				//c= manejador.aLaCalle();
	    				c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    				if(c.getCount()!=0){
	    					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.a_la_calle));
   				 		 
		    		        alertDialog.setMessage("El número de viviendas que manejan las aguas grises \"A la calle\" son: "+ c.getCount());
		    		 
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
		    				
		    		        //Dibujando los puntos en el mapa
		    				dibujarPuntosFiltrados (c, p);
	    				}
	    				else{
	    					
		    		        alertDialog.setMessage("No hay viviendas con manejo de aguas grises \"A la calle\" ");
		    		 
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
	    				}
	    				break;
	    				
	    			case 117:
	    				//La eliminación es a alcantarillado
	    				//c= manejador.eliminacionAAlcantarillado();
	    				c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    				if(c.getCount()!=0){
	    					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.es_a_alcantarillado));
		    				
		    		        alertDialog.setMessage("El número de viviendas que manejan las aguas grises \"Por eliminación a alcantarillado\" son: "+ c.getCount());
		    		 
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            
		    		            	
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
		    				
		    		        //Dibujando los puntos en el mapa	    				
		    				dibujarPuntosFiltrados (c, p);
	    				}
	    				else{
	    					
		    		        alertDialog.setMessage("No hay viviendas con manejo de aguas grises \"Por eliminación a alcantarillado\" ");
		    		 
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
	    				}
	    				break;
	    				
	    			case 118:
	    				//Por sistema de pozo resumidero
	    				//c= manejador.porPozoResumidero();
	    				c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    				if(c.getCount()!=0){
	    					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.pozo_resumidero));
	    				
		    		        alertDialog.setMessage("El número de viviendas que manejan las aguas grises \"Por sistema de pozo resumidero\" son: "+ c.getCount());
		    		 
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
		    				
		    		        //Dibujando los puntos en el mapa
		    				dibujarPuntosFiltrados (c, p);
	    				}
	    				else{
	    				
		    		        alertDialog.setMessage("No hay viviendas con manejo de aguas grises \"Por sistema de pozo resumidero\" ");
		    		 
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
	    				}
	    				
	    				break;	
	    					
	    				
	    			case 121:
	    				//Quebradas ríos u otro lugar
	    				//c= manejador.quebradasORios();
	    				c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    				if(c.getCount()!=0){
	    					p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.quebrada_rio));
		    			
		    		        alertDialog.setMessage("El número de viviendas que manejan las aguas grises \"Por quebrada río u otro lugar\" son: "+ c.getCount());
		    		 
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {		    		            	
		    		            	
		    		            	dialog.cancel();
		    		            }
		    		        });
		    		        alertDialog.show();
		    				
		    		        //Dibujando los puntos en el mapa
		    				dibujarPuntosFiltrados (c, p);
	    				}
	    				else{
	    					// Setting Dialog Message
		    		        alertDialog.setMessage("No hay viviendas con manejo de aguas grises \"Por quebrada río u otro lugar\" ");
		    		 
		    		        // On pressing Settings button
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            
		    		            	
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
	    			//c= manejador.eliminacionDeAlcantarilladoPorPozoResumideroOCuerpoReceptor();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.pozo_o_cuerpo_receptor));
    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas que manejan las aguas negras \"Por eliminación a alcantarillado por pozo resumidero o cuerpo receptor\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con manejo de aguas negras \"Por eliminación a alcantarillado por pozo resumidero o cuerpo receptor\" ");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 123:
	    			//c= manejador.eliminacionDeAlcantarilladoSinTratamiento();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Usando el mismo ícono que para agua grises
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.es_a_alcantarillado));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas que manejan las aguas negras \"Por eliminación de alcantarillado sin tratamiento\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con manejo las aguas negras \"Por eliminación de alcantarillado sin tratamiento\" ");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			
	    			break;
	    			
	    		case 166:
	    			//c= manejador.noAplicaAguasNegras();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.no_aplica));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas que no aplica manejo de aguas negras son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    			//c= manejador.adobe();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.adobe));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de paredes \"Adobe\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    				
	    			break;
	    			
	    		case 347:
	    			//c= manejador.laminaMetalicaEnMalEstado();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar icono
		    			p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.lamina_metalica_mal_estado));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de paredes \"Lámina metálica en mal estado\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de paredes \"Lámina metálica en mal estado\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 348:
	    			//c= manejador.pajaOPalma();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.paja_o_palma));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de paredes \"Paja o palma\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 349:
	    			//c= manejador.materialesDeDesecho();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar icono 
		    			p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.paja_o_palma));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de paredes \"Materiales de desecho\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 350:
	    			//c= manejador.otrosMateriales();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de paredes \"Otros materiales\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			
	    			break;
	    			
	    		case 351:
	    			//c= manejador.noTieneParedes();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar icono
		    			p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.no_tiene_paredes));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de paredes \"No tiene paredes\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			
	    			break;
	    			
	    		case 540:
	    			//c= manejador.concretoOMixto();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.concreto_o_mixto));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de paredes \"Concreto o mixto\"son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			
	    			break;
	    			
	    		case 541:
	    			//c= manejador.bajareque();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.bajareque));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de paredes \"Bajareque\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			
	    			break;
	    			
	    		case 543:
	    			//c= manejador.madera();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.madera));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de paredes \"Madera\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			
	    			break;
	    			
	    		case 544:
	    			//c= manejador.madera();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.lamina_metalica_buen_estado));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de paredes \"Lámina metálica en buen estado\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de paredes \"Lámina metálica en buen estado\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    			//c= manejador.ladrilloCeramico();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.ladrillo_ceramico));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de piso \"Ladrillo cerámico\" son: "+ c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de piso \"Ladrillo cerámico\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    		case 353:
	    			//c= manejador.ladrilloCemento();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.ladrillo_cemento));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de piso \"Ladrillo de cemento\"son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}	    			
	    			break;
	    			
	    		case 354:
	    			//c= manejador.ladrilloBarro();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.ladrillo_barro));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de piso \"Ladrillo de barro\"son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 355:
	    			//c= manejador.cemento();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cemento));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de piso \"Cemento\"son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;	    			
	    			
	    		case 356:
	    			//c= manejador.tierra();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);	    			
	    			if(c.getCount()!=0){
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.tierra));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de piso \"Tierra\"son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    		
	    			case 357:
	    				//c= manejador.otrosMaterialesPiso();
	    				c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
		    			
		    			if(c.getCount()!=0){
		    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
		    				
		    				// Setting Dialog Message
		    		        alertDialog.setMessage("El número de viviendas con material de piso \"Otros materiales\" son: "+c.getCount());
		    		 
		    		        // On pressing Settings button
		    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog,int which) {
		    		            
		    		            	
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
	    			//c= manejador.lozaDeConcreto();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.loza_de_concreto));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de techo \"Loza de concreto\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}	    		
	    			break;
	    			
	    		case 359:
	    			//c= manejador.tejaDeBarroOCemento();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.teja_barro_cemento));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de techo \"Teja de barro o cemento\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 360:
	    			//c= manejador.laminaDeAsbetoODuralita();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de techo \"Lámina de asbeto o duralita\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de techo \"Lámina de asbeto o duralita\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 361:
	    			//c= manejador.laminaMetalicaEnBuenEstadoTecho();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.lamina_metalica_buen_estado));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de techo \"Lámina metálica en buen estado\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de techo \"Lámina metálica en buen estado\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 362:
	    			//c= manejador.laminaMetalicaEnMalEstadoTecho();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.lamina_metalica_mal_estado));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de techo \"Lámina metálica en mal estado\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con material de techo \"Lámina metálica en mal estado\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 363:
	    			//c= manejador.pajaOPalmaTecho();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.paja_o_palma));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de techo \"Paja o palma\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 364:
	    			//c= manejador.materialesDeDesechoTecho();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.materiales_desecho));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de techo \"Materiales de desecho\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 365:
	    			//c= manejador.otrosMaterialesTecho();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de techo \"Otros materiales\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 366:
	    			//c= manejador.noTieneTecho();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.no_tiene_techo));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con material de techo \"No tiene techo\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    			//c= manejador.noLeDanTratamiento();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.no_le_dan_ningun_tratamiento));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tratamiento de agua para consumo que \"No le dan ningún tratamiento\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tratamiento de agua para consumo que \"No le dan ningún tratamiento\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 311:
	    			//c= manejador.laHierven();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.la_hierven));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tratamiento de agua para consumo que \"La hierven\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			}
	    			break;
	    			
	    		case 312:
	    			//c= manejador.laTratanConLejiaOPuriagua();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.lejia_o_puriagua));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tratamiento de agua para consumo que \"La tratan con lejía o puriagua\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tratamiento de agua para consumo que \"La tratan con lejía o puriagua\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 313:
	    			//c= manejador.usaFiltroDeAgua();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.filtro_se_agua));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tratamiento de agua para consumo que \"Usa filtro de agua\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 314:
	    			//c= manejador.compraAguaEnvasada();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.agua_envasada));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tratamiento de agua para consumo que \"Compra agua envasada\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 315:
	    			//c= manejador.otrosAgua();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_materiales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tratamiento de agua para consumo \"Otros\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    			//c= manejador.canieriaDentroAnda();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.canieria_dentro_de_la_vivienda_anda));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con abastecimiento de agua \"Por cañería dentro de la vivienda con abastecimiento público (ANDA)\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con abastecimiento de agua \"Por cañería dentro de la vivienda con abastecimiento público (ANDA)\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 402:
	    			//c= manejador.canieriaDentroOtroAbastecimiento();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.canieria_dentro_de_la_vivienda_otro));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con abastecimiento de agua \"Por cañería dentro de la vivienda con otro tipo de abastecimiento\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con abastecimiento de agua \"Por cañería dentro de la vivienda con otro tipo de abastecimiento\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 403:
	    			//c= manejador.canieriaFueraDeLaPropiedad();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.canieria_fuera_de_la_propiedad));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con abastecimiento de agua \"Por cañería fuera de la propiedad\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con abastecimiento de agua \"Por cañería fuera de la propiedad\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 405:
	    			//c= manejador.pozoDentro();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.pozo_dentro_de_la_propiedad));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con abastecimiento de agua \"Por pozo dentro de la propiedad\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 406:
	    			//c= manejador.camionCarretaOPipa();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.camion_carreta_pipa));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con abastecimiento de agua \"Camión, carreta o pipa\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con abastecimiento de agua \"Camión, carreta o pipa\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 407:
	    			//c= manejador.aguaLluvia();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.agua_lluvia));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con abastecimiento de agua \"Agua lluvia\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 408:
	    			//c= manejador.rioQuebradaLagoOjoDeAguaManantial();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.rio_quebrada_ojo_de_agua_manantial));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con abastecimiento de agua \"Río, quebrada, lago, ojo de agua o manantial\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con abastecimiento de agua \"Río, quebrada, lago, ojo de agua o manantial\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 518:
	    			//c= manejador.pozoFuera();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.pozo_fuera_uso_comunitario));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con abastecimiento de agua \"Pozo fuera de la propiedad (De uso comunitario)\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 519:
	    			//c= manejador.canieriaFueraDeLaPropiedad();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.canieria_fuera_de_la_propiedad));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con abastecimiento de agua \"Por cañería fuera de la propiedad\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con abastecimiento de agua \"Por cañería fuera de la propiedad\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    			//c= manejador.electricidad();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){

	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.electricidad));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con alumbrado por \"Electricidad\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 389:
	    			//c= manejador.conexionElectricaDelVecino();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.conexion_electrica_del_vecino));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con alumbrado por \"Conexión eléctrica del vecino\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con alumbrado por \"Conexión eléctrica del vecino\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 391:
	    			//c= manejador.keroseneAlumbrado();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.kerosene));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con alumbrado por \"Kerosene\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 392:
	    			//c= manejador.candela();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.candela));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con alumbrado por \"Candela\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 393:
	    			//c= manejador.panelSolar();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.panel_solar));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con alumbrado por \"Panel solar\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 394:
	    			//c= manejador.generadorElectrico();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.generador_electrico));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con alumbrado por \"Generador eléctrico\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con alumbrado por \"Generador eléctrico\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 395:
	    			//c= manejador.otraClaseAlumbrado();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otra_clase_alumbradoi));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con alumbrado por \"Otra clase\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con bienes de familia\"Cultivo agrícola propio\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con bienes de familia \"Cultivo agrícola propio\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con bienes de familia\"Aves de corral\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con bienes de familia\"Ganado vacuno\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con bienes de familia\"Ganado porcino\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con bienes de familia\"Negocio propio\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con bienes de familia\"Ningún bien\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con bienes de familia \"Ningún bien\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con bienes del hogar \"Radio\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con bienes del hogar \"Equipo de sonido\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con bienes del hogar \"Telvisor\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con bienes del hogar \"Refrigeradora\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con bienes del hogar \"Lavadora\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            		    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con bienes de familia \"Computadora\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con bienes de familia \"Ningún bien\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con bienes del hogar \"Ningún bien\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    			//c= manejador.electricidadParaCocina();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.electricidad));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas que utilizan \"Electricidad\" como combustible para cocinar son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 528:
	    			//c= manejador.kerosenParaCocina();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.kerosen_gas));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas que utilizan \"Kerosen (gas)\" como combustible para cocinar son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 529:
	    			//c= manejador.gasPropanoParaCocina();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.gas_propano));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas que utilizan \"Gas propano\" como combustible para cocinar son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 530:
	    			//c= manejador.leniaParaCocina();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.lenia));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas que utilizan \"Leña\" como combustible para cocinar son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que utilicen \"Leña\" como combustible para cocinar");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 531: 
	    			//c= manejador.carbonParaCocina();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			//Toast.makeText(ctx, "Está en carbón con "+c.getCount()+" registros", Toast.LENGTH_SHORT).show();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.carbon));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas que utilizan \"Carbón\" como combustible para cocinar son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que utilicen \"Carbón\" como combustible para cocinar");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 532:
	    			//c= manejador.estopaDeCocoParaCocina();
	    			
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			//Toast.makeText(ctx, "Está en estopa de coco con "+c.getCount()+" registros", Toast.LENGTH_SHORT).show();
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.estopa_de_coco));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas que utilizan \"Estopa de coco\" como combustible para cocinar son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 533:
	    			//c= manejador.otrasParaCocina();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_cocina));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas que utilizan \"Otras\" como combustible para cocinar son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 534:
	    			//c= manejador.ningunaParaCocina();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.ninguno));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas que utilizan \"Ninguna\" como combustible para cocinar son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    			//c= manejador.recoleccionDomiciliariaPublica();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.recoleccion_domiciliaria_publica));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con manejo de basura \"Recolección domiciliaria pública\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con manejo de basura \"Recolección domiciliaria pública\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 420:
	    			//c= manejador.recoleccionDomiciliariaPrivada();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.recoleccion_domiciliaria_privada));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con manejo de basura \"Recolección domiciliaria privada\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con manejo de basura \"Recolección domiciliaria privada\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 421:
	    			//c= manejador.laDeopositanEnContenedores();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.la_depositan_en_contenedores));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con manejo de basura \"La depositan en contenedores\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 422:
	    			//c= manejador.laEntierran();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.la_entierran));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con manejo de basura \"La entierran\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 423:
	    			//c= manejador.laQueman();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.la_quman));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con manejo de basura \"La queman\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 424:
	    			//c= manejador.laDepositanEnCualquierLugar();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.la_depositan_en_cualquier_lugar));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con manejo de basura \"La depositan en cualquier lugar\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 425:
	    			//c= manejador.otrasFormasBasura();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otras_formas_basura));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con manejo de basura \"Otras formas\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas que tienen medio de comunicación \"Teléfono fijo\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que tengan medio de comunicación \"Teléfono fijo\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas que tienen medio de comunicación \"Teléfono celular\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que tengan medio de comunicación \"Teléfono celular\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas que tienen medio de comunicación \"Internet\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que tengan medio de comunicación \"Internet\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.cable));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas que tienen medio de comunicación \"Cable\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que tengan medio de comunicación \"Cable\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas que tienen medio de comunicación \"No tiene\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas que tengan medio de comunicación \"No tiene\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con presencia de vectores \"Zancudos\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con presencia de vectores \"Chinche picuda\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con presencia de vectores \"Moscas\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con presencia de vectores \"Cucarachas\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con presencia de vectores \"Roedores\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con presencia de vectores \"Otros\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		        alertDialog.setMessage("El número de viviendas con presencia de vectores \"No hay presencia de vectores\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.sin_riesgo_ambiental));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con riesgo ambiental \"Sin riesgo\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.deslave));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con riesgo ambiental \"Deslaves\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.inundacion));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con riesgo ambiental \"Inundaciones\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.contaminacion_por_disposicion_inadecuada_de_desechos_solidos_quimicos));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con riesgo ambiental \"Contaminación por disposición no adecuada de desechos sólidos, químicos\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con riesgo ambiental \"Contaminación por disposición no adecuada de desechos sólidos, químicos\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.erupcion));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con riesgo ambiental \"Erupción\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con riesgo ambiental \"Erupción\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_riesgos_ambientales));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con riesgo ambiental \"Otros riesgos\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    			//c= manejador.siDeUsoPrivado();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.si_uso_privado));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con servicio sanitario \"Si y es de uso privado\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 410:
	    			//c= manejador.siDeUsoColectivo();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.si_uso_colectivo));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con servicio sanitario \"Si y es de uso colectivo\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 411:
	    			//c= manejador.noTieneServicioSanitario();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.no_aplica));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con servicio sanitario \"No tiene\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    			//c= manejador.inquilina();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.inquilina));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tenencia de vivienda \"Inquilina(o)\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 333:
	    			//c= manejador.propietariaPeroPagandoAPlazos();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.propietario_pagando_a_plazos));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tenencia de vivienda \"Propietaria(o) de la vivienda pero la está pagando a plazos\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tenencia de vivienda \"Propietaria(o) de la vivienda pero la está pagando a plazos \"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 334:
	    			//c= manejador.propietaria();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.propietario));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tenencia de vivienda \"Propietaria(o)\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 335:
	    			//c= manejador.propietarioEnTerrenoPublico();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.propiedad_publica));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tenencia de vivienda \"Propietaria(o) de la vivienda en terreno públicoS\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tenencia de vivienda \"Propietaria(o) de la vivienda en terreno público\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 336:
	    			//c= manejador.propietariaEnTerrenoPrivado();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.vivienda_privada));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tenencia de vivienda \"Propietaria(o) de la vivienda en terreno privado\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 535:
	    			//c= manejador.colono();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.colona));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tenencia de vivienda \"Colona(o)\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 536:
	    			//c= manejador.guardianDeLaVivienda();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.guardian_de_la_vivienda));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tenencia de vivienda \"Guardián de la vivienda\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tenencia de vivienda \"Guardián de la vivienda\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 537:
	    			//c= manejador.ocupanteGratuito();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.ocupante_gratuito));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tenencia de vivienda \"Ocupante gratuito\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 538:
	    			//c= manejador.otroTenenciaDeVivienda();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otro_tenencia_vivienda));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tenencia de vivienda \"Otro\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 539:
	    			//c= manejador.noDatoTenenciaVivienda();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.no_dato));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tenencia de vivienda \"No dato\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    			//c= manejador.inodoroConectadoAAlcantarillado();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.inodoro_a_alcantarillado));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tipo de sanitario \"Inodoro conectado a alcantarillado\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 413:
	    			//c= manejador.inodoroAFosaSeptica();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.inodoro_a_fosa_septica));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tipo de sanitario \"Inodoro a fosa séptica\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tipo de sanitario \"Inodoro a fosa séptica\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 414:
	    			//c= manejador.letrinaAbonera();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.letrina_abonera));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tipo de sanitario \"Letrina abonera\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 415:
	    			//c= manejador.letrinaHoyoSeco();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.letrina_hoyo_seco));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tipo de sanitario \"Letrina de hoyo seco\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 416:
	    			//c= manejador.letrinaSolar();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.letrina_solar));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tipo de sanitario \"Letrina solar\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 417: 
	    		//c= manejador.otroTipo_TipoSanitario();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
    			
    			if(c.getCount()!=0){
    				//Queda pendiente cambiar el icono
    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otro_tipo));
    				
    				// Setting Dialog Message
    		        alertDialog.setMessage("El número de viviendas con tipo de sanitario \"Otro tipo\" son: "+c.getCount());
    		 
    		        // On pressing Settings button
    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    		            public void onClick(DialogInterface dialog,int which) {
    		            
    		            	
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
    		            
    		            	
    		            	dialog.cancel();
    		            }
    		        });
    		        alertDialog.show();
    			};
    			break;
    			
	    		case 418:
	    			//c= manejador.noAplicaTipoSanitario();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.no_aplica_sanitario));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tipo de sanitario \"No aplica\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    			//c= manejador.casaPrivadaOIndependiente();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.casa_privada_independiente));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tipo de vivienda \"Casa privada o independiente\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 317:
	    			//c= manejador.casaCompartida();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.casa_compartida));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tipo de vivienda \"Casa compartida\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 318:
	    			//c= manejador.apartamento();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.apartamento));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tipo de vivienda \"Apartamento\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 319:
	    			//c= manejador.condominio();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.condominio));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tipo de vivienda \"Condominio\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 329:
	    			//c= manejador.champa();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.champa));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tipo de vivienda \"Champa\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 330:
	    			//c= manejador.piezaEnCasaOMeson();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.pieza_en_una_casa_o_meson));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tipo de vivienda \"Pieza en una casa o mesón\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    				
	    		        //Dibujando los puntos en el mapa	    				
	    				dibujarPuntosFiltrados (c, p);
	    			}
	    			else{
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("No hay viviendas con tipo de vivienda \"Pieza en una casa o mesón\"");
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
	    		            	dialog.cancel();
	    		            }
	    		        });
	    		        alertDialog.show();
	    			};
	    			break;
	    			
	    		case 331:
	    			//c= manejador.otroTipoVivienda();
	    			c = manejador.filtrar(idNivel1, idOpcion2Seleccionada, valor);
	    			
	    			if(c.getCount()!=0){
	    				//Queda pendiente cambiar el icono
	    				p = AndroidGraphicFactory.convertToBitmap(ctx.getResources().getDrawable(R.drawable.otros_tipo_vivienda));
	    				
	    				// Setting Dialog Message
	    		        alertDialog.setMessage("El número de viviendas con tipo de vivienda \"Otro tipo\" son: "+c.getCount());
	    		 
	    		        // On pressing Settings button
	    		        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		            public void onClick(DialogInterface dialog,int which) {
	    		            
	    		            	
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
	
	public void dibujarPuntosFiltrados (Cursor cursor, Bitmap point){
		//}
		cd= cursor;
		p = point;
		final ProgressDialog progressDialog = new ProgressDialog(ctx);
		progressDialog.setTitle("Por favor espere...");
		progressDialog.setMessage("Dibujando viviendas en el mapa.");
		progressDialog.show();	

		new Thread() { 
			public void run() { 
				//operaciones que tengas que realizar
				if(cd.moveToFirst()){
					try{
						do{
							double lon = cd.getDouble(0);
							double lat = cd.getDouble(1);
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

									//String num_exp = info.getString(0)+ info.getString(1)+ info.getString(2)+info.getString(3)+info.getString(4)+info.getString(5)+info.getString(6);
									String num_exp = "";
									
									if (area.equals("R")){
										num_exp = manejador.numeroDeExpedienteRural(cd.getString(0), cd.getString(1));
									}
									else{
										num_exp = manejador.numeroDeExpedienteUrbano(cd.getString(0), cd.getString(1));
									}
									
									//Toast.makeText(ctx, "Antes de dibujar punto", Toast.LENGTH_SHORT).show();
									MyMarker fichaFiltro = new MyMarker(ctx, new LatLong(lat,lon), p, 0, 0, mapView, "Número de Expediente: "+num_exp+"\nJefe de Familia: "+info.getString(7), false, true, depto,municipio,area,ctn_bar_col,zona,num_vivienda,num_familia);
									mapView.getLayerManager().getLayers().add(fichaFiltro);
									//markerList.add(fichaFiltro);

								}while(info.moveToNext());
							}										    								    											    										    											    						
						}while (cd.moveToNext());

					}catch(Exception e){
						Toast.makeText(ctx, "Error:"+ e, Toast.LENGTH_SHORT).show();
					}
				}
				progressDialog.dismiss(); 
			} 
		}.start();
		
	}
}
