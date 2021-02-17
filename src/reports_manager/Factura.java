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

public class Factura {
    private int idCliente;
    private Date fecha;
    private JasperReport report;
    public static final String PATH = "src\\reports\\factura.jasper";
    private Map parametros;
    private Connection connection;

    public Factura(Date fecha, int idCliente){
        connection = new Common().getConexion();
        this.fecha = fecha;
        this.idCliente = idCliente;
    }

    public void newFactura(){
        try {
            parametros = new HashMap();
            parametros.put("idCliente", idCliente);
            parametros.put("fecha", fecha);
            report = (JasperReport) JRLoader.loadObjectFromFile(PATH);
            JasperPrint jprint = JasperFillManager.fillReport(report, parametros, connection);

            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            viewer.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
