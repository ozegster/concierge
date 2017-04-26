package ba.codecentric.base.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull(message = "Please enter the room number")
    private Integer number;

    @NotNull(message = "Please enter the floor number")
    @Column(name = "floor_number")
    private Integer floorNumber;

    @NotNull(message = "Please select the room type")
    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

}
