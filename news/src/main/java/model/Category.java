package model;

import lombok.*;

import javax.persistence.*;

@Table(name = "categoies")
@Entity
@Setter
@Getter@Data@NoArgsConstructor@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = true)
    private String name;
}
