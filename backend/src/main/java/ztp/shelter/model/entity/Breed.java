package ztp.shelter.model.entity;


import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "breeds")
public class Breed
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_breed")
    private int idBreed;


    @Column(name = "name")
    private String name;
}
