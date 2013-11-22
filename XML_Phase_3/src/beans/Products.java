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
 *         &lt;element name="product" type="{}product" maxOccurs="unbounded" minOccurs="0"/>
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
    "product"
})
public class Products {

    protected List<Product> product;

    /**
     * Gets the value of the product property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the product property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProduct().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Product }
     *
     *
     */
    public List<Product> getProduct() {
        if (product == null) {
            product = new ArrayList<Product>();
        }
        return this.product;
    }
       
    public void addProduct(Product cat) {
        if (product == null) {
            product = new ArrayList<Product>();
        }
        product.add(cat);
    }

    public int size() {
        if (product == null) {
            product = new ArrayList<Product>();
        }
        return product.size();
    }

    public void deleteProduct(Product cat) {
        if (product == null) {
            return;
        }
        if (product.contains(cat)) {
            product.remove(cat);
        }
    }
    
    public void deleteProduct(int index) {
        if (product == null) {
            return;
        }
        if (index >= 0 && index < product.size()) {
            product.remove(index);
        }
    }
    
    public void clear() {
        if (product != null) {
            product.clear();
        }
    }
    
    public boolean isEmpty(){
        return product.isEmpty();
    }
}
