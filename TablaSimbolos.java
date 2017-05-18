import java.util.*;
public class TablaSimbolos{
	HashMap t;
	
	public TablaSimbolos(){
		t=new HashMap();
	}
	
	public Simbolo insertar (String id, int dir, String tipo){
		Simbolo s=new Simbolo(id,dir,tipo);
		t.put(id,s);
		return s;
	}
	public Simbolo buscar (String etiqueta){
		return (Simbolo)(t.get(etiqueta));
	}
	public boolean existe (String etiqueta){
		if(buscar(etiqueta)==null){
			return false;
		}
		return true;
	}

	public void setTam (String etiqueta, int tam){
		if(existe(etiqueta)){
			Simbolo n=buscar(etiqueta);	
            n.defTam(tam);
		}
	}
	
	
	public void imprimir(){
		Iterator it=t.values().iterator();
		System.out.println("Tabla de Simbolos ");
		System.out.println("Id    Dirección    Tipo    Tamaño");
		int uT=0, uD=0;
		while (it.hasNext()){
			Simbolo s=(Simbolo)it.next();
			System.out.println(s.etiqueta + "  ||  "+Integer.toHexString(s.direccion).toUpperCase()+"  ||  "+s.tipo+"   ||  "+Integer.toHexString(s.tam).toUpperCase());
		if(s.direccion>=uD){
			uT=s.tam; 
			uD=s.direccion;
		}
		}
		System.out.println("Tamaño total :"+Integer.toHexString(uT+uD).toUpperCase());
	}
	
}
