package nerdlab.main;
public class NavDrawerItem
{
    public int icon;
    public String name;

    public NavDrawerItem(int icon, String name)
    {
        this.icon = icon;
        this.name = name;
    }
    
    public String getName(){
		return name;
	}
	public int getImageId() {
		return icon;
	}

}