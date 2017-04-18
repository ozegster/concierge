package ba.codecentric.base.domain;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room_type")
public class RoomType {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank(message = "Please enter name of the room type")
    @Size(max = 80, message = "Room type name is too long, 80 characters allowed")
    private String name;

    @Min(value = 1, message = "Minimum number of people is 1")
    @Max(value = 6, message = "Maximum number of people is 6")
    @Column(name = "number_of_people")
    private Integer numberOfPeople;

    @Min(value = 0, message = "Minimum number of kids is 0")
    @Max(value = 3, message = "Maximum number of kids is 3")
    @Column(name = "number_of_kids")
    private Integer numberOfKids;

    @NotNull(message = "Please enter size of the room")
    private Integer size;

    @NotEmpty(message = "Please enter image of the room")
    private String image;

    @ManyToOne
    @JoinColumn(name = "bed_id")
    private BedType bedType;

    @ManyToMany
    @JoinTable(name = "room_type_feature", joinColumns = {@JoinColumn(name = "room_type_id")}, inverseJoinColumns = {
            @JoinColumn(name = "feature_id")})
    private List<Feature> features = new ArrayList<>();

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

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Integer getNumberOfKids() {
        return numberOfKids;
    }

    public void setNumberOfKids(Integer numberOfKids) {
        this.numberOfKids = numberOfKids;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BedType getBedType() {
        return bedType;
    }

    public void setBedType(BedType bedType) {
        this.bedType = bedType;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

}
