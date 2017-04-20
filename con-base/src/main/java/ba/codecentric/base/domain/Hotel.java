package ba.codecentric.base.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Time;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Please enter name of the hotel")
    @Size(max = 128, message = "Name is too long, 128 characters allowed")
    private String name;

    @Max(value = 5, message = "Rating must be less than 5")
    @Min(value = 1, message = "Rating must be greater than 0")
    private int rating;

    @NotBlank(message = "Please enter street and number")
    @Size(max = 128, message = "Address is too long, 128 characters allowed")
    private String address;

    @NotBlank(message = "Please enter zip code")
    @Pattern(regexp = "^[0-9]{5}$", message = "Please enter a valid zip code e.g. 43452 (5 digit required)")
    private String zip;

    @NotBlank(message = "Please enter city")
    @Size(max = 64, message = "City name is too long, 64 characters allowed")
    private String city;

    @NotBlank(message = "Please enter a phone number")
    @Size(max = 45, message = "Phone is too long, 45 characters allowed")
    private String phone;

    @NotBlank(message = "Please enter a fax")
    @Size(max = 45, message = "Fax is too long, 45 characters allowed")
    private String fax;

    @NotBlank(message = "Please enter a email")
    @Size(max = 45, message = "Email is too long, 45 characters allowed")
    @Email(message = "Please enter a valid email address e.g. info@hotel.com")
    private String email;

    @NotBlank(message = "Please enter a website")
    @Size(max = 45, message = "Website is too long, 45 characters allowed")
    @Pattern(regexp = "^(www\\.)([a-z0-9]+(-[a-z0-9]+)*\\.)+[a-z]{2,}$", message = "Please enter a valid url e.g. www.hotel.com")
    private String website;

    @NotBlank(message = "Please add hotel description")
    @Size(max = 500, message = "Description is too long, 500 characters allowed")
    private String description;

    @NotNull(message = "Please add Check-in time")
    @Column(name = "check_in")
    private Time checkIn;

    @NotNull(message = "Please add Check-out time")
    @Column(name = "check_out")
    private Time checkOut ;

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

    public Time getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Time checkIn) { this.checkIn = checkIn; }

    public Time getCheckOut() { return checkOut; }

    public void setCheckOut(Time checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", address='" + address + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", description='" + description + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", country=" + country +
                '}';
    }
}
