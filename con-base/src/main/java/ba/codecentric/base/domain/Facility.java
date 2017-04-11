package ba.codecentric.base.domain;

import javax.persistence.*;

@Entity
public class Facility {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "facility_name")
    private String facilityName;

    private String description;

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
                ", facilityTypeId=" + facilityTypeId +
                '}';
    }
}
