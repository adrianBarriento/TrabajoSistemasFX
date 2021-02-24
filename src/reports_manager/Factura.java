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
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Factura {
    private int idCliente;
    private Date fecha;
    private int idFactura;
    private JasperReport report;
    public static final String PATH = "src\\reports\\factura.jasper";
    private Map parametros;
    private Connection connection;

    public Factura(java.sql.Date fecha, int idCliente, int factura){
        connection = new Common().getConexion();
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idFactura = factura;
    }

    public void newFactura(){
        try {
            System.out.println(idFactura);
            parametros = new HashMap();
            parametros.put("idCliente", idCliente);
            parametros.put("fecha", fecha);
            parametros.put("factura", idFactura);
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
