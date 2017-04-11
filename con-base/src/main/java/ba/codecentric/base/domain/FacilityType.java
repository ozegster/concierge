package ba.codecentric.base.domain;

import javax.persistence.*;

@Entity
@Table(name = "facility_type")
public class FacilityType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "facility_type")
    private String facilityType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    @Override
    public String toString() {
        return "FacilityType{" +
                "id=" + id +
                ", facilityType='" + facilityType + '\'' +
                '}';
    }
}





