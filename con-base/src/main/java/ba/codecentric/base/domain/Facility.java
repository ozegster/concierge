package ba.codecentric.base.domain;

import ba.codecentric.base.validation.annotation.UniqueFacilityName;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class Facility {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "facility_name")
    @NotBlank(message = "Please enter name of the facility")
    @Size(max = 80, message = "Facility name is too long, 80 characters allowed")
    @UniqueFacilityName
    private String facilityName;

    @NotBlank(message = "Please add facility description")
    @Size(max = 400, message = "Description is too long, 400 characters allowed")
    private String description;

    @NotBlank(message = "Please add facility image")
    @Size(max = 128, message = "Address of image is too long, 128 characters allowed")
    private String image;


    @Min(value = -2, message = "Floor is too low, allowed to -2")
    @Max(value = 10, message = "Floor is too high, allowed to 10")
    private Integer floor;

    @ManyToOne
    @JoinColumn(name = "facility_type_id")
    private FacilityType facilityType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public FacilityType getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(FacilityType facilityType) {
        this.facilityType = facilityType;
    }
}
