package ba.codecentric.base.domain;

import javax.persistence.*;

@Entity
@Table(name = "bed_type")
public class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "type")
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Bed{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
