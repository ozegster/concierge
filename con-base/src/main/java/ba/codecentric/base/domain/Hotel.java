package ba.codecentric.base.domain;

import javax.persistence.*;

@Entity
public class Hotel {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private int rating;

    private String address;

    private String zip;

    private String city;

    private String phone;

    private String fax;

    private String email;

    private String website;

    private String description;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Id: " + this.getId() + ", Name: " + this.getName() + " , Rating: " + this.getRating() + " ,Address: " + this.getAddress() +
                " ,Zip: " + this.getZip() + " ,City: " + this.getCity() +
                " ,Phone: " + this.getPhone() + " ,Fax: " + this.getFax() + " ,Email: " + this.getEmail() + " ,Website: " + this.getWebsite() +
                " ,Description: " + this.getDescription();
    }
}
