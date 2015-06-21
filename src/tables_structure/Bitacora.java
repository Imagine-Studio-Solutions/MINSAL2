package tables_structure;

import java.io.Serializable;

public class Bitacora implements Serializable{
	private int 	Id;
	private String 	FechaAccion;
	private String 	HoraAccion;
	private String 	CodigoUsuario;
	private String  Instruccion;
	private String	Descripcion;
	private String	TipoAccion;
	private int    	Sincronizado;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	
	public String getFechaAccion() {
		return FechaAccion;
	}

	public void setFechaAccion(String fechaaccion) {
		FechaAccion = fechaaccion;
	}
	
	public String getHoraAccion() {
		return HoraAccion;
	}

	public void setHoraAccion(String horaaccion) {
		HoraAccion = horaaccion;
	}
	
	public String getCodigoUsuario() {
		return CodigoUsuario;
	}

	public void setCodigoUsuario(String codigousuario) {
		CodigoUsuario=codigousuario;
	}
	
	public String getInstruccion() {
		return Instruccion;
	}

	public void setInstruccion(String instruccion) {
		Instruccion=instruccion;
	}
	
	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion=descripcion;
	}
	
	public String getTipoAccion() {
		return TipoAccion;
	}

	public void setTipoAccion(String tipoaccion) {
		TipoAccion=tipoaccion;
	}
	
	public int getSincronizado() {
		return Sincronizado;
	}

	public void setSincronizado(int sincronizado) {
		Sincronizado=sincronizado;
	}
}
