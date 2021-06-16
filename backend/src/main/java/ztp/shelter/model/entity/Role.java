package ztp.shelter.model.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="roles")
public class Role
{
    //TODO Strategia identity wymaga polaczenia z baza, sa podobno prostsze sposoby
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_role")
    private int idRole;

    @Column(name="name")
    private String name;


}