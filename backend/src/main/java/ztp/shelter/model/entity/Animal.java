package ztp.shelter.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "animals")
public class Animal
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private int idAnimal;

    @Column(name="name")
    private String name;

    @Column(name="arrival_date")
    private Date arrivalDate;

    @Column(name="description")
    private String description;

    @ManyToOne(targetEntity = Breed.class, cascade=CascadeType.MERGE)
    @JoinColumn(name = "breed_id", referencedColumnName = "id_breed")
    private Breed breed;

    @ManyToOne(targetEntity = Size.class, cascade=CascadeType.MERGE)
    @JoinColumn(name = "size_id", referencedColumnName = "id_size")
    private Size size;




    @JsonIgnore
    public Boolean checkIfAnyNull()
    {
        if(this.name == null) return true;
        if(this.arrivalDate == null) return true;
        if(this.description == null) return true;
        if(this.breed == null) return true;
        if(this.size == null) return true;

        return false;
    }

    @JsonIgnore
    public Animal(int idAnimal, String name)
    {
        this.idAnimal = idAnimal;
        this.name = name;
    }

    @JsonIgnore
    public Animal(String name)
    {
        this.name = name;
    }

    @JsonIgnore
    public Animal(String name, Date arrivalDate, String description, Breed breed, Size size)
    {
        this.name = name;
        this.arrivalDate = arrivalDate;
        this.description = description;
        this.breed = breed;
        this.size = size;
    }

//    @ManyToMany(mappedBy = "animals", fetch = FetchType.LAZY)
//    List<User> users;

//    @ManyToMany(mappedBy = "likedAnimals")
//    List<User> likes;

}
