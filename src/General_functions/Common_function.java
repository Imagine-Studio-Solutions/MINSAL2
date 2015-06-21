/*------------------------------------------------------
				 06/11/14
				 Ministerio de Salud
				 Samuel Menjivar Nieto
				 Esta clase contiene metodos comunes
 -------------------------------------------------------*/
package General_functions;
import java.util.Calendar;

import java.util.GregorianCalendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

 

import android.R.array;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
public class Common_function {
 
	// Calcula la edad solamente en años a partir de un string fechaNac de la forma aaaa-mm-dd
	public int  getEdad(String fechaNac)
	{
		int edad=0;
		
		String[] fechaArreglo=getFechaArray(fechaNac);
		
		int AnioNacimiento=Integer.parseInt(fechaArreglo[0]);
		
		int MesNacimiento=Integer.parseInt(fechaArreglo[1]);
		MesNacimiento=MesNacimiento-1;
		int DiaNacimiento=Integer.parseInt(fechaArreglo[2]);
		edad=calcularEdad(AnioNacimiento,MesNacimiento,DiaNacimiento);	
		return edad;
	}

	//regresa una fecha como arreglo a partir de un String de la forma tipo aaaa-mm-dd
		public String[]  getFechaArray(String fechaNac) {
		    String[] fechaArray = fechaNac.split("-");
			return fechaArray;
		}

		public int calcularEdad(int anioseleccionado, int messeleccionado, int diaseleccionado)
		{
			//fechaActual;
			Calendar fechaActual = new GregorianCalendar();
			int anioActual = fechaActual.get(Calendar.YEAR);
			int mesoActual = fechaActual.get(Calendar.MONTH);
			int diaActual  = fechaActual.get(Calendar.DAY_OF_MONTH);
			int diferenciaAnios;
			
			//GregorianCalendar fechaAnterior = new GregorianCalendar(anioseleccionado, (messeleccionado + 1), diaseleccionado); 
			diferenciaAnios = (anioActual - anioseleccionado - 1) + (mesoActual == messeleccionado ? (diaActual >= diaseleccionado ? 1 : 0) : mesoActual >= messeleccionado ? 1 : 0);
			return diferenciaAnios;
		}
		
	//prepara el los datos que se van a enviar y envia el Json al servidor 	
	public void set_datosSincronizar(Context contexto, int id,String fechaaccion, String horaaccion, String codigousuario, String instruccion, String descripcion, String tipoaccion){	
		try {
            JSONObject toSend = new JSONObject();                        
            toSend.put("fechaaccion",   "id: "+id+" "+fechaaccion);
            toSend.put("horaaccion",    horaaccion);
            toSend.put("codigousuario", codigousuario);
            toSend.put("instruccion",   instruccion);
            toSend.put("descripcion",   descripcion);
            toSend.put("tipoaccion",    tipoaccion);
                        
           // JSONTransmitter transmitter = new JSONTransmitter(contexto,id);
            //transmitter.sinc(toSend);
     // transmitter.execute(new JSONObject[] {toSend});         
		} catch (JSONException e) 
        {
	    	e.printStackTrace();
	    }
	}
	
//averiguamos si tenemos conexion wifi	
	public static boolean isNetworkOnline(Context context) {
		//Bandera que nos indica si hay conectividad o no 
	    boolean status=false;
	    try{
	        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo netInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	        if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
	            status= true;
	        }
	    }catch(Exception e){
	        e.printStackTrace();  
	        return false;
	    }
	    return status;

	    }
		 
}
