package ba.codecentric.base.domain;

import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @Pattern(regexp = "^\\w+[\\w\\.]*@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,20}$", message = "Please enter a valid email address e.g. info@hotel.com")
    private String email;

    @NotBlank(message = "Please enter a website")
    @Size(max = 45, message = "Website is too long, 45 characters allowed")
    @Pattern(regexp = "^(www\\.)([a-z0-9]+(-[a-z0-9]+)*\\.)+[a-z]{2,}$", message = "Please enter a valid url e.g. www.hotel.com")
    private String website;

    @NotBlank(message = "Please add hotel description")
    @Size(max = 500, message = "Description is too long, 500 characters allowed")
    private String description;

    @NotNull(message = "Please enter a valid Check-in time")
    @Column(name = "check_in")
    private Time checkIn;

    @NotNull(message = "Please enter a valid Check-out time")
    @Column(name = "check_out")
    private Time checkOut;

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

    public void setCheckIn(String checkIn) {
            this.checkIn = parseStringToTime(checkIn);
    }

    public Time getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
            this.checkOut = parseStringToTime(checkOut);
    }

    public boolean isParsable(String time) {
        final String TIME_WITHOUT_SECONDS = ("^([0-1]\\d|2[0-3]):([0-5]\\d)$");
          return (time != null) && time.matches(TIME_WITHOUT_SECONDS);
    }

    public Time parseStringToTime(String time) {
        final String EXTEND_SECONDS = ":00";
        if(isParsable(time)){
            return Time.valueOf(time + EXTEND_SECONDS);
        }  else {
            return null;
        }
    }
}