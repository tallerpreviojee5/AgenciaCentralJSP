
package webserviceimm;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WebserviceTicketsIMM", targetNamespace = "http://webserviceIMM/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WebserviceTicketsIMM {


    /**
     * 
     * @param arg0
     * @return
     *     returns webserviceimm.DatatypeVenta
     */
    @WebMethod(operationName = "Venta")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "Venta", targetNamespace = "http://webserviceIMM/", className = "webserviceimm.Venta")
    @ResponseWrapper(localName = "VentaResponse", targetNamespace = "http://webserviceIMM/", className = "webserviceimm.VentaResponse")
    public DatatypeVenta venta(
        @WebParam(name = "arg0", targetNamespace = "")
        DatatypeVenta arg0);

}
