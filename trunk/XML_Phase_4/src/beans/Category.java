package beans;

import java.io.Serializable;
import java.math.BigInteger;

public class Category implements Serializable {

	protected Integer categoryID;
    protected String categoryName;
    protected String description;
    protected String picture;
    protected String file;

    /**
     * Gets the value of the categoryID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public Integer getCategoryID() {
        return categoryID;
    }

    /**
     * Sets the value of the categoryID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCategoryID(Integer value) {
        this.categoryID = value;
    }

    /**
     * Gets the value of the categoryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the value of the categoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the picture property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Sets the value of the picture property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicture(String value) {
        this.picture = value;
    }

    /**
     * Gets the value of the file property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFile() {
        return file;
    }

    /**
     * Sets the value of the file property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFile(String value) {
        this.file = value;
    }

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != getClass()) {
			return false;
		}
		Category cat = (Category) obj;
		if (this.categoryID.equals(cat.categoryID)) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 47 * hash
				+ (this.categoryID != null ? this.categoryID.hashCode() : 0);
		return hash;
	}
	
	public boolean isValidate(){
		if (this.categoryName == null || this.categoryName.equals("")){
			return false;
		}
		return true;
	}
}
