package FurryFiesta.vista;

import java.awt.BorderLayout;
import java.net.URL;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Alejandro Huertas Herrero
 *
 */
public class PanelGeneral extends JPanel 
{
	/**
	 * Constante para quitar el warning
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Etiqueta que se utiliza para añadir toda la información acerca
	 * de la conexión establecida con el servidor
	 */
	private JLabel _labelInformacion;
	
	/**
	 * Función que se encarga de añadir todos los componentes al panel
	 */
	public PanelGeneral ()
	{
		int hora, minutos, segundos, anyo, mes, dia;
		
		this.setLayout(new BorderLayout());
		
		Calendar calendario = Calendar.getInstance();
		
		hora = calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		
		anyo = calendario.get(Calendar.YEAR);
		mes = calendario.get(Calendar.MONTH);
		dia = calendario.get(Calendar.DAY_OF_MONTH);
		
		String sSistemaOperativo = System.getProperty("os.name");
		
		String texto = "<html><center>--------------------------<P>" +
					   "<html><center>FURRY FIESTA - APLICACIÓN DE ESCRITORIO<P>" +
					   "<html><center>FURRY VERSIÓN 1.0<P>" +
					   "<html><center>Hora de incio: " + hora + ":" + minutos + ":" + segundos +"<P>" +
					   "<html><center>Fecha de incio: " + anyo + "/" + mes + "/" + dia +"<P>" +
					   "<html><center>SO: " + sSistemaOperativo + "<P>" +
					   "<html><center>Conexión establecida con el servidor<P>" +
				 	   "<html><center>--------------------<P>";
		
		//Añadir el texto a la etiqueta
		_labelInformacion = new JLabel();
		_labelInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		_labelInformacion.setText(texto);
		
		URL logo = VistaGUI.class.getResource("imagenes/logo.png");
		ImageIcon icon = (logo != null) ? new ImageIcon (logo) : null;
		_labelInformacion.setIcon(icon);
		
		//Añadir la etiqueta al panel
		add(_labelInformacion);
	}
}