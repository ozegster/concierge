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
import java.util.Date;

@Entity
@Table(name = "room_check_in")
public class RoomCheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Please enter guest name")
    @Size(max = 128, message = "Name is too long, 128 characters allowed")
    private String name;

    @NotBlank(message = "Please enter guest last name")
    @Size(max = 128, message = "Last name is too long, 128 characters allowed")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Please enter a email")
    @Size(max = 45, message = "Email is too long, 45 characters allowed")
    @Pattern(regexp = "^\\w+[\\w\\.]*@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,20}$", message = "Please enter a valid email address e.g. info@hotel.com")
    private String email;

    @NotNull(message = "Please enter check-in date")
    @Column(name = "check_in")
    private Date checkIn;

    @NotNull(message = "Please enter check-out date")
    @Column(name = "check_out")
    private Date checkOut;

    @NotNull(message = "Please enter number of adults")
    @Min(value = 1, message = "Minimum number of adults is 1")
    @Max(value = 6, message = "Maximum number of adults is 6")
    @Column(name = "number_of_adults")
    private Integer numberOfAdults;

    @NotNull(message = "Please enter number of kids")
    @Min(value = 0, message = "Minimum number of kids is 0")
    @Max(value = 3, message = "Maximum number of kids is 3")
    @Column(name = "number_of_kids")
    private Integer numberOfKids;

    private Integer password;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(Integer numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public Integer getNumberOfKids() {
        return numberOfKids;
    }

    public void setNumberOfKids(Integer numberOfKids) {
        this.numberOfKids = numberOfKids;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
