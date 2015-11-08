public class Cinema {

    private String name;
    private String address;
    private String site;

public Cinema(String text) {

    String[] s = text.split("#");
    name =  s[1];
    address =  s[2];
    site =  s[3];
}

public void setName(String name) {
    this.name = name;
}

public String getName() {
    return name;
}

public void setAddress(String address) {
        this.address = address;
}

public String getAddress() {
        return address;
}

public void setSite(String site) {
        this.site = site;
}

public String getSite() {
        return site;
}
    
public void showIt() {
    System.out.println(this.name+" "+this.address+" "+this.site+" ");
}


}
