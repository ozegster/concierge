package ba.codecentric.base.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Facility {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "facility_name")
    @NotBlank(message = "Please enter name of the facility")
    @Size(max = 80, message = "Name is too long, 80 characters allowed")
    private String facilityName;

    @NotBlank(message = "Please add facility description")
    @Size(max = 400, message = "Description is too long, 400 characters allowed")
    private String description;

    @NotBlank(message = "Please add facility image")
    @Size(max = 128, message = "Address of image is too long, 128 characters allowed")
    private String image;

    @ManyToOne
    @JoinColumn(name = "facility_type_id")
    private FacilityType facilityTypeId;

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

    public FacilityType getFacilityTypeId() {
        return facilityTypeId;
    }

    public void setFacilityTypeId(FacilityType facilityTypeId) {
        this.facilityTypeId = facilityTypeId;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", facilityName='" + facilityName + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", facilityTypeId=" + facilityTypeId +
                '}';
    }
}
