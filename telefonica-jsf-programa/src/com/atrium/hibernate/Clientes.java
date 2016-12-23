package com.atrium.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Clientes entity. @author MyEclipse Persistence Tools
 */

public class Clientes  implements java.io.Serializable {


    // Fields    

     private Integer codigoCliente;
     private String nombreCliente;
     private String tipoEmpresa;
     private String cifCliente;
     private String nifCliente;
     private String nombreComercialCliente;
     private Date fechaAltaCliente;
     private Date fechaBajaCliente;
     private String calleCliente;
     private String ciudadCliente;
     private String cpCliente;
     private String provinciaCliente;
     private String telefonoCliente;
     private String faxCliente;
     private String emailCliente;
     private String contadoCliente;
     private String creditoCliente;
     private Double cifraVentasCliente;
     private Double beneficiosCliente;
     private String prepagoCliente;
     private Double capitalCliente;
     private Double inmovilizadoCliente;
     private Double pasivoCliente;
     private Double activoCliente;
     private Date añoBalanceCliente;
     private Double limiteCliente;
     private Double pendienteCliente;
     private Double restoCliente;
     private String calleEnvioCliente;
     private String ciudadEnvioCliente;
     private String cpEnvioCliente;
     private String provinciaEnvioCliente;
     private String telefonoEnvioCliente;
     private String faxEnvioCliente;
     private String emailEnvioCliente;
     private String observacionesCliente;
     private String estadoCliente;
     private Set pedidoses = new HashSet(0);
     private Set formasPagoClientes = new HashSet(0);
     private Set vencimientoses = new HashSet(0);


    // Constructors

    /** default constructor */
    public Clientes() {
    }

	/** minimal constructor */
    public Clientes(Integer codigoCliente, String nombreCliente, String estadoCliente) {
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.estadoCliente = estadoCliente;
    }
    
    /** full constructor */
    public Clientes(Integer codigoCliente, String nombreCliente, String tipoEmpresa, String cifCliente, String nifCliente, String nombreComercialCliente, Date fechaAltaCliente, Date fechaBajaCliente, String calleCliente, String ciudadCliente, String cpCliente, String provinciaCliente, String telefonoCliente, String faxCliente, String emailCliente, String contadoCliente, String creditoCliente, Double cifraVentasCliente, Double beneficiosCliente, String prepagoCliente, Double capitalCliente, Double inmovilizadoCliente, Double pasivoCliente, Double activoCliente, Date añoBalanceCliente, Double limiteCliente, Double pendienteCliente, Double restoCliente, String calleEnvioCliente, String ciudadEnvioCliente, String cpEnvioCliente, String provinciaEnvioCliente, String telefonoEnvioCliente, String faxEnvioCliente, String emailEnvioCliente, String observacionesCliente, String estadoCliente, Set pedidoses, Set formasPagoClientes, Set vencimientoses) {
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.tipoEmpresa = tipoEmpresa;
        this.cifCliente = cifCliente;
        this.nifCliente = nifCliente;
        this.nombreComercialCliente = nombreComercialCliente;
        this.fechaAltaCliente = fechaAltaCliente;
        this.fechaBajaCliente = fechaBajaCliente;
        this.calleCliente = calleCliente;
        this.ciudadCliente = ciudadCliente;
        this.cpCliente = cpCliente;
        this.provinciaCliente = provinciaCliente;
        this.telefonoCliente = telefonoCliente;
        this.faxCliente = faxCliente;
        this.emailCliente = emailCliente;
        this.contadoCliente = contadoCliente;
        this.creditoCliente = creditoCliente;
        this.cifraVentasCliente = cifraVentasCliente;
        this.beneficiosCliente = beneficiosCliente;
        this.prepagoCliente = prepagoCliente;
        this.capitalCliente = capitalCliente;
        this.inmovilizadoCliente = inmovilizadoCliente;
        this.pasivoCliente = pasivoCliente;
        this.activoCliente = activoCliente;
        this.añoBalanceCliente = añoBalanceCliente;
        this.limiteCliente = limiteCliente;
        this.pendienteCliente = pendienteCliente;
        this.restoCliente = restoCliente;
        this.calleEnvioCliente = calleEnvioCliente;
        this.ciudadEnvioCliente = ciudadEnvioCliente;
        this.cpEnvioCliente = cpEnvioCliente;
        this.provinciaEnvioCliente = provinciaEnvioCliente;
        this.telefonoEnvioCliente = telefonoEnvioCliente;
        this.faxEnvioCliente = faxEnvioCliente;
        this.emailEnvioCliente = emailEnvioCliente;
        this.observacionesCliente = observacionesCliente;
        this.estadoCliente = estadoCliente;
        this.pedidoses = pedidoses;
        this.formasPagoClientes = formasPagoClientes;
        this.vencimientoses = vencimientoses;
    }

   
    // Property accessors

    public Integer getCodigoCliente() {
        return this.codigoCliente;
    }
    
    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNombreCliente() {
        return this.nombreCliente;
    }
    
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTipoEmpresa() {
        return this.tipoEmpresa;
    }
    
    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getCifCliente() {
        return this.cifCliente;
    }
    
    public void setCifCliente(String cifCliente) {
        this.cifCliente = cifCliente;
    }

    public String getNifCliente() {
        return this.nifCliente;
    }
    
    public void setNifCliente(String nifCliente) {
        this.nifCliente = nifCliente;
    }

    public String getNombreComercialCliente() {
        return this.nombreComercialCliente;
    }
    
    public void setNombreComercialCliente(String nombreComercialCliente) {
        this.nombreComercialCliente = nombreComercialCliente;
    }

    public Date getFechaAltaCliente() {
        return this.fechaAltaCliente;
    }
    
    public void setFechaAltaCliente(Date fechaAltaCliente) {
        this.fechaAltaCliente = fechaAltaCliente;
    }

    public Date getFechaBajaCliente() {
        return this.fechaBajaCliente;
    }
    
    public void setFechaBajaCliente(Date fechaBajaCliente) {
        this.fechaBajaCliente = fechaBajaCliente;
    }

    public String getCalleCliente() {
        return this.calleCliente;
    }
    
    public void setCalleCliente(String calleCliente) {
        this.calleCliente = calleCliente;
    }

    public String getCiudadCliente() {
        return this.ciudadCliente;
    }
    
    public void setCiudadCliente(String ciudadCliente) {
        this.ciudadCliente = ciudadCliente;
    }

    public String getCpCliente() {
        return this.cpCliente;
    }
    
    public void setCpCliente(String cpCliente) {
        this.cpCliente = cpCliente;
    }

    public String getProvinciaCliente() {
        return this.provinciaCliente;
    }
    
    public void setProvinciaCliente(String provinciaCliente) {
        this.provinciaCliente = provinciaCliente;
    }

    public String getTelefonoCliente() {
        return this.telefonoCliente;
    }
    
    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getFaxCliente() {
        return this.faxCliente;
    }
    
    public void setFaxCliente(String faxCliente) {
        this.faxCliente = faxCliente;
    }

    public String getEmailCliente() {
        return this.emailCliente;
    }
    
    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getContadoCliente() {
        return this.contadoCliente;
    }
    
    public void setContadoCliente(String contadoCliente) {
        this.contadoCliente = contadoCliente;
    }

    public String getCreditoCliente() {
        return this.creditoCliente;
    }
    
    public void setCreditoCliente(String creditoCliente) {
        this.creditoCliente = creditoCliente;
    }

    public Double getCifraVentasCliente() {
        return this.cifraVentasCliente;
    }
    
    public void setCifraVentasCliente(Double cifraVentasCliente) {
        this.cifraVentasCliente = cifraVentasCliente;
    }

    public Double getBeneficiosCliente() {
        return this.beneficiosCliente;
    }
    
    public void setBeneficiosCliente(Double beneficiosCliente) {
        this.beneficiosCliente = beneficiosCliente;
    }

    public String getPrepagoCliente() {
        return this.prepagoCliente;
    }
    
    public void setPrepagoCliente(String prepagoCliente) {
        this.prepagoCliente = prepagoCliente;
    }

    public Double getCapitalCliente() {
        return this.capitalCliente;
    }
    
    public void setCapitalCliente(Double capitalCliente) {
        this.capitalCliente = capitalCliente;
    }

    public Double getInmovilizadoCliente() {
        return this.inmovilizadoCliente;
    }
    
    public void setInmovilizadoCliente(Double inmovilizadoCliente) {
        this.inmovilizadoCliente = inmovilizadoCliente;
    }

    public Double getPasivoCliente() {
        return this.pasivoCliente;
    }
    
    public void setPasivoCliente(Double pasivoCliente) {
        this.pasivoCliente = pasivoCliente;
    }

    public Double getActivoCliente() {
        return this.activoCliente;
    }
    
    public void setActivoCliente(Double activoCliente) {
        this.activoCliente = activoCliente;
    }

    public Date getAñoBalanceCliente() {
        return this.añoBalanceCliente;
    }
    
    public void setAñoBalanceCliente(Date añoBalanceCliente) {
        this.añoBalanceCliente = añoBalanceCliente;
    }

    public Double getLimiteCliente() {
        return this.limiteCliente;
    }
    
    public void setLimiteCliente(Double limiteCliente) {
        this.limiteCliente = limiteCliente;
    }

    public Double getPendienteCliente() {
        return this.pendienteCliente;
    }
    
    public void setPendienteCliente(Double pendienteCliente) {
        this.pendienteCliente = pendienteCliente;
    }

    public Double getRestoCliente() {
        return this.restoCliente;
    }
    
    public void setRestoCliente(Double restoCliente) {
        this.restoCliente = restoCliente;
    }

    public String getCalleEnvioCliente() {
        return this.calleEnvioCliente;
    }
    
    public void setCalleEnvioCliente(String calleEnvioCliente) {
        this.calleEnvioCliente = calleEnvioCliente;
    }

    public String getCiudadEnvioCliente() {
        return this.ciudadEnvioCliente;
    }
    
    public void setCiudadEnvioCliente(String ciudadEnvioCliente) {
        this.ciudadEnvioCliente = ciudadEnvioCliente;
    }

    public String getCpEnvioCliente() {
        return this.cpEnvioCliente;
    }
    
    public void setCpEnvioCliente(String cpEnvioCliente) {
        this.cpEnvioCliente = cpEnvioCliente;
    }

    public String getProvinciaEnvioCliente() {
        return this.provinciaEnvioCliente;
    }
    
    public void setProvinciaEnvioCliente(String provinciaEnvioCliente) {
        this.provinciaEnvioCliente = provinciaEnvioCliente;
    }

    public String getTelefonoEnvioCliente() {
        return this.telefonoEnvioCliente;
    }
    
    public void setTelefonoEnvioCliente(String telefonoEnvioCliente) {
        this.telefonoEnvioCliente = telefonoEnvioCliente;
    }

    public String getFaxEnvioCliente() {
        return this.faxEnvioCliente;
    }
    
    public void setFaxEnvioCliente(String faxEnvioCliente) {
        this.faxEnvioCliente = faxEnvioCliente;
    }

    public String getEmailEnvioCliente() {
        return this.emailEnvioCliente;
    }
    
    public void setEmailEnvioCliente(String emailEnvioCliente) {
        this.emailEnvioCliente = emailEnvioCliente;
    }

    public String getObservacionesCliente() {
        return this.observacionesCliente;
    }
    
    public void setObservacionesCliente(String observacionesCliente) {
        this.observacionesCliente = observacionesCliente;
    }

    public String getEstadoCliente() {
        return this.estadoCliente;
    }
    
    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public Set getPedidoses() {
        return this.pedidoses;
    }
    
    public void setPedidoses(Set pedidoses) {
        this.pedidoses = pedidoses;
    }

    public Set getFormasPagoClientes() {
        return this.formasPagoClientes;
    }
    
    public void setFormasPagoClientes(Set formasPagoClientes) {
        this.formasPagoClientes = formasPagoClientes;
    }

    public Set getVencimientoses() {
        return this.vencimientoses;
    }
    
    public void setVencimientoses(Set vencimientoses) {
        this.vencimientoses = vencimientoses;
    }
   








}