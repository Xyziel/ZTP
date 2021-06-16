package ztp.shelter.model.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sizes")
public class Size
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_size")
    private int idSize;

    @Column(name = "name")
    private String name;
}
