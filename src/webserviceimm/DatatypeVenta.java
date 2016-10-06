
package webserviceimm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para datatypeVenta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="datatypeVenta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fechaInicioServicio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaVenta" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="idAgencia" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="importe" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="matriculaAuto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="minutos" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nroTicket" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="secretoAgencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datatypeVenta", propOrder = {
    "fechaInicioServicio",
    "fechaVenta",
    "idAgencia",
    "importe",
    "matriculaAuto",
    "mensaje",
    "minutos",
    "nroTicket",
    "secretoAgencia"
})
public class DatatypeVenta {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaInicioServicio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaVenta;
    protected Long idAgencia;
    protected Long importe;
    protected String matriculaAuto;
    protected String mensaje;
    protected Integer minutos;
    protected Long nroTicket;
    protected String secretoAgencia;

    /**
     * Obtiene el valor de la propiedad fechaInicioServicio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicioServicio() {
        return fechaInicioServicio;
    }

    /**
     * Define el valor de la propiedad fechaInicioServicio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicioServicio(XMLGregorianCalendar value) {
        this.fechaInicioServicio = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaVenta.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaVenta() {
        return fechaVenta;
    }

    /**
     * Define el valor de la propiedad fechaVenta.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaVenta(XMLGregorianCalendar value) {
        this.fechaVenta = value;
    }

    /**
     * Obtiene el valor de la propiedad idAgencia.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAgencia() {
        return idAgencia;
    }

    /**
     * Define el valor de la propiedad idAgencia.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAgencia(Long value) {
        this.idAgencia = value;
    }

    /**
     * Obtiene el valor de la propiedad importe.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getImporte() {
        return importe;
    }

    /**
     * Define el valor de la propiedad importe.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setImporte(Long value) {
        this.importe = value;
    }

    /**
     * Obtiene el valor de la propiedad matriculaAuto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatriculaAuto() {
        return matriculaAuto;
    }

    /**
     * Define el valor de la propiedad matriculaAuto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatriculaAuto(String value) {
        this.matriculaAuto = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad minutos.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinutos() {
        return minutos;
    }

    /**
     * Define el valor de la propiedad minutos.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinutos(Integer value) {
        this.minutos = value;
    }

    /**
     * Obtiene el valor de la propiedad nroTicket.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNroTicket() {
        return nroTicket;
    }

    /**
     * Define el valor de la propiedad nroTicket.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNroTicket(Long value) {
        this.nroTicket = value;
    }

    /**
     * Obtiene el valor de la propiedad secretoAgencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecretoAgencia() {
        return secretoAgencia;
    }

    /**
     * Define el valor de la propiedad secretoAgencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecretoAgencia(String value) {
        this.secretoAgencia = value;
    }

}
