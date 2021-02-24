package reports_manager;

import models.Common;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InformeCompras {
    private Date fechaInicio, fechaFinal;
    private JasperReport report;
    public static final String PATH_COMPRAS = "src\\reports\\InformeCompra.jasper";
    private Map parametros;
    private Connection connection;

    public InformeCompras(Date fechaInicio, Date fechaFinal){
        connection = new Common().getConexion();
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }

    public void newInformeCompra(){
        try {
            parametros = new HashMap();
            parametros.put("fechaInicio", fechaInicio);
            parametros.put("fechaFin", fechaFinal);
            report = (JasperReport) JRLoader.loadObjectFromFile(PATH_COMPRAS);
            JasperPrint jprint = JasperFillManager.fillReport(report, parametros, connection);

            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            viewer.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
