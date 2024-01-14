package items;

/*
 * Brant Eckert, 2019
 * Implementation of items with protected variables; meant to be inherited, not stand alone.
 */
public class Item {
    protected String name = "";
    protected String desc = "";
    protected String attributes = "";
    protected int cost = 0;

    /**
     * Instantiates an empty Item.
     */
    public Item(){ }

    /**
     * An item with a name and a description.
     * @param inName Name of the item.
     * @param inDesc Description of the item.
     */
    public Item(String inName, String inDesc){
        name = inName;
        desc = inDesc;
    }

    /**
     * Instantiates an item with a name, description, and attributes
     * @param inName Name of the item
     * @param inDesc Description of the item
     * @param inAttributes Attributes of the item
     */
    public Item(String inName, String inDesc, String inAttributes){
        name = inName;
        desc = inDesc;
        attributes = inAttributes; // the idea for attributes is to have it so it has a parsable string field with stuff like "STR +2" so the character gets +2 strength, randomized effects.
    }

    /**
     * Getter for name.
     * @return Name of the item
     */
    public String getName(){
        return name;
    }

    /**
     * Setter for the name
     * @param newName Sets the object's name to something new
     */
    public void setName(String newName){
        name = newName;
    }
    public String getDesc(){
        return desc;

    }

    /**
     * Setter for the description.
     * @param newDesc The new description.
     */
    public void setDesc(String newDesc){
        desc = newDesc;
    }

    /**
     *  Adds a new line to the description.
     * @param addDesc Description line to add.
     */
    public void addDesc(String addDesc){
        desc += "\n" + addDesc;
    }

    /**
     * Getter for the attribute string.
     * @return Returns the attribute string.
     */
    public String getAttributes(){
        return attributes;
    }

    /**
     * Setter for the attribute string.
     * @param newAtts Sets the attributes.
     */
    public void setAttributes(String newAtts){
        attributes = newAtts;
    }

    /**
     * Adds new attributes.
     * @param addAtts Attribute to be added
     */
    public void addAttributes(String addAtts){
        attributes += "\n" + addAtts;
    }

    /**
     * Getter for the cost
     * @return Returns the cost for the item.
     */
    public int getCost(){
        return cost;
    }

    /**
     * Setter to the cost (To be replaced with a cost calculation method)
     * @param newCost The new cost
     */
    public void setCost(int newCost){
        cost = newCost;
    }

    /**
     * Translates the item into a string.
     * @return A formatted string with the item's fields organized into parts of the string.
     */
    public String toString(){
        String end = "";
        end += "Name: " + name + "\n" +
                "Description:\n" + desc + "\n";
        if(!attributes.equals(""))
            end += "Attributes: \n" + attributes + "\n";
        if(cost != 0)
            end += "Cost: "  + cost + "\n";
        return end;
    }


}
