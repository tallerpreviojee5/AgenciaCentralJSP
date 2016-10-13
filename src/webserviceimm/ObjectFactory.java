
package webserviceimm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webserviceimm package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _VentaResponse_QNAME = new QName("http://webserviceIMM/", "VentaResponse");
    private final static QName _CancelarTicket_QNAME = new QName("http://webserviceIMM/", "CancelarTicket");
    private final static QName _CancelarTicketResponse_QNAME = new QName("http://webserviceIMM/", "CancelarTicketResponse");
    private final static QName _Venta_QNAME = new QName("http://webserviceIMM/", "Venta");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webserviceimm
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CancelarTicket }
     * 
     */
    public CancelarTicket createCancelarTicket() {
        return new CancelarTicket();
    }

    /**
     * Create an instance of {@link CancelarTicketResponse }
     * 
     */
    public CancelarTicketResponse createCancelarTicketResponse() {
        return new CancelarTicketResponse();
    }

    /**
     * Create an instance of {@link Venta }
     * 
     */
    public Venta createVenta() {
        return new Venta();
    }

    /**
     * Create an instance of {@link VentaResponse }
     * 
     */
    public VentaResponse createVentaResponse() {
        return new VentaResponse();
    }

    /**
     * Create an instance of {@link DatatypeVenta }
     * 
     */
    public DatatypeVenta createDatatypeVenta() {
        return new DatatypeVenta();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VentaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webserviceIMM/", name = "VentaResponse")
    public JAXBElement<VentaResponse> createVentaResponse(VentaResponse value) {
        return new JAXBElement<VentaResponse>(_VentaResponse_QNAME, VentaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelarTicket }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webserviceIMM/", name = "CancelarTicket")
    public JAXBElement<CancelarTicket> createCancelarTicket(CancelarTicket value) {
        return new JAXBElement<CancelarTicket>(_CancelarTicket_QNAME, CancelarTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelarTicketResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webserviceIMM/", name = "CancelarTicketResponse")
    public JAXBElement<CancelarTicketResponse> createCancelarTicketResponse(CancelarTicketResponse value) {
        return new JAXBElement<CancelarTicketResponse>(_CancelarTicketResponse_QNAME, CancelarTicketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Venta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webserviceIMM/", name = "Venta")
    public JAXBElement<Venta> createVenta(Venta value) {
        return new JAXBElement<Venta>(_Venta_QNAME, Venta.class, null, value);
    }

}
