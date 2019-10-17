package softagi.chef.Models;

public class ChefModel
{
    private String fullname,email,address,imageurl;

    public ChefModel() {
    }

    public ChefModel(String fullname, String email, String address, String imageurl) {
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.imageurl = imageurl;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
