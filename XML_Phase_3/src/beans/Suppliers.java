package beans;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained
 * within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="supplier" type="{}supplier" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "supplier"
})
public class Suppliers {

    protected List<Supplier> supplier;

    /**
     * Gets the value of the supplier property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the supplier property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupplier().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
	 * {@link Supplier }
     *
     *
     */
    public List<Supplier> getSupplier() {
        if (supplier == null) {
            supplier = new ArrayList<Supplier>();
        }
        return this.supplier;
    }
    
    public void addSupplier(Supplier cat) {
        if (supplier == null) {
            supplier = new ArrayList<Supplier>();
        }
        supplier.add(cat);
    }

    public int size() {
        if (supplier == null) {
            supplier = new ArrayList<Supplier>();
        }
        return supplier.size();
    }

    public void deleteSupplier(Supplier cat) {
        if (supplier == null) {
            return;
        }
        if (supplier.contains(cat)) {
            supplier.remove(cat);
        }
    }
    
    public void deleteSupplier(int index) {
        if (supplier == null) {
            return;
        }
        if (index >= 0 && index < supplier.size()) {
            supplier.remove(index);
        }
    }
    
    public void clear() {
        if (supplier != null) {
            supplier.clear();
        }
    }
    
    public boolean isEmpty(){
        return supplier.isEmpty();
    }
}
