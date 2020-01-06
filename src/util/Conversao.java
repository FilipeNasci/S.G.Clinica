package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Conversao {
	
	public Date StringToDate(String strData) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date dtData = new Date(sdf.parse(strData).getTime());
			return dtData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String DataInvertida(Date dtData) {
		if(dtData != null) {
			int intDia, intMes, intAno;
			String strData;
			Calendar calendario = Calendar.getInstance();
			
			calendario.setTime(dtData);
			
			intDia = calendario.get(Calendar.DAY_OF_MONTH);
			intMes = calendario.get(Calendar.MONTH)+1;
			intAno = calendario.get(Calendar.YEAR);
			
			strData = intAno+"/"+intMes+"/"+intDia;
			return strData;
		}else {
			return null;
		}
	}
	
	public String DateToString(Date dtData) {
		String strData;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			strData = sdf.format(dtData);
			return strData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int DiaData(Date dtData) {
		if(dtData != null) {
			int intDia;
			Calendar calendario = Calendar.getInstance();
			
			calendario.setTime(dtData);
			
			intDia = calendario.get(Calendar.DAY_OF_MONTH);
			return intDia;
		}else {
			return 0;
		}
	}
	
	public int MesData(Date dtData) {
		if(dtData != null) {
			int intMes;
			Calendar calendario = Calendar.getInstance();
			
			calendario.setTime(dtData);
			
			intMes = calendario.get(Calendar.MONTH)+1;
			return intMes;
		}else {
			return 0;
		}
	}
	
	public int AnoData(Date dtData) {
		if(dtData != null) {
			int intAno;
			Calendar calendario = Calendar.getInstance();
			
			calendario.setTime(dtData);
			
			intAno = calendario.get(Calendar.YEAR);
			return intAno;
		}else {
			return 0;
		}
	}
}
