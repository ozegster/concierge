package ba.codecentric.base.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Hotel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull(message = "Please enter name of the hotel")
    @Size.List({
            @Size(max = 128, message = "Name is too long, 128 characters allowed"),
            @Size(min = 1, message = "Please enter name of the hotel")})
    private String name;

    @Column(name = "rating")
    @Max(value = 5, message = "Rating must be less than 5")
    @Min(value = 1, message = "Rating must be greater than 0")
    private int rating;

    @Column(name = "address")
    @NotNull(message = "Please enter street and number")
    @Size.List({
            @Size(max = 128, message = "Address is too long, 128 characters allowed"),
            @Size(min = 1, message = "Please enter street and number")})
    private String address;

    @Column(name = "zip")
    @NotNull(message = "Please enter zip code")
    @Pattern(regexp = "^[0-9]{5}$", message = "Please enter a valid zip code e.g. 43452 (5 digit required)")
    private String zip;

    @Column(name = "city")
    @NotNull(message = "Please enter city")
    @Size.List({
            @Size(max = 64, message = "City name is too long, 64 characters allowed"),
            @Size(min = 1, message = "Please enter city")})
    private String city;

    @Column(name = "phone")
    @NotNull(message = "Please enter a phone number")
    @Size.List({
            @Size(max = 45, message = "Phone is too long, 45 characters allowed"),
            @Size(min = 1, message = "Please enter a phone number")})
    private String phone;

    @Column(name = "fax")
    @NotNull(message = "Please enter a fax")
    @Size.List({
            @Size(max = 45, message = "Fax is too long, 45 characters allowed"),
            @Size(min = 1, message = "Please enter a fax")})
    private String fax;

    @Column(name = "email")
    @NotNull(message = "Please enter a email")
    @Size.List({
            @Size(max = 45, message = "Email is too long, 45 characters allowed"),
            @Size(min = 1, message = "Please enter a email")})
    @Pattern(regexp = "^\\w+[\\w.]*@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,3}$", message = "Please enter a valid email address e.g. info@hotel.com")
    private String email;

    @Column(name = "website")
    @NotNull(message = "Please enter a website")
    @Size.List({
            @Size(max = 45, message = "Website is too long, 45 characters allowed"),
            @Size(min = 1, message = "Please enter a website")})
    @Pattern(regexp = "^(www\\.)([a-z0-9]+(-[a-z0-9]+)*\\.)+[a-z]{2,}$", message = "Please enter a valid url e.g. www.hotel.com")
    private String website;

    @Column(name = "description")
    @NotNull(message = "Please add hotel description")

    @Size(max = 500, message = "Description is too long, 500 characters allowed")
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
