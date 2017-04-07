package ba.codecentric.base.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Column(name = "number_of_people")
    private int numberOfPeople;

    @Column(name = "number_of_kids")
    private int numberOfKids;

    private int size;

    private String image;

    @ManyToOne
    @JoinColumn(name = "bed_id")
    private Bed bed;

    @ManyToMany
    @JoinTable(name = "room_feature", joinColumns = {@JoinColumn(name = "room_id")}, inverseJoinColumns = {
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

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getNumberOfKids() {
        return numberOfKids;
    }

    public void setNumberOfKids(int numberOfKids) {
        this.numberOfKids = numberOfKids;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfPeople=" + numberOfPeople +
                ", numberOfKids=" + numberOfKids +
                ", size=" + size +
                ", image='" + image + '\'' +
                ", bed=" + bed +
                ", features=" + features +
                '}';
    }
}
