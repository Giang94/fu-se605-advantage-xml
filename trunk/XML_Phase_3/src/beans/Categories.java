
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
 *         &lt;element name="category" type="{}category" maxOccurs="unbounded" minOccurs="0"/>
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
    "category"
})
public class Categories {

    protected List<Category> category;

    /**
     * Gets the value of the category property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the category property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategory().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
	 * {@link Category }
     *
     *
     */
    public List<Category> getCategory() {
        if (category == null) {
            category = new ArrayList<Category>();
        }
        return this.category;
    }

    public void addCategory(Category cat) {
        if (category == null) {
            category = new ArrayList<Category>();
        }
        category.add(cat);
    }

    public int size() {
        if (category == null) {
            category = new ArrayList<Category>();
        }
        return category.size();
    }

    public void deleteCategory(Category cat) {
        if (category == null) {
            return;
        }
        if (category.contains(cat)) {
            category.remove(cat);
        }
    }
    
    public void deleteCategory(int index) {
        if (category == null) {
            return;
        }
        if (index >= 0 && index < category.size()) {
            category.remove(index);
        }
    }
    
    public void clear() {
        if (category != null) {
            category.clear();
        }
    }
    
    public boolean isEmpty(){
        return category.isEmpty();
    }
}
