package model;
import jakarta.persistence.*;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor@NoArgsConstructor@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 350)
    private String name;

    private Float price;

    @Column(name = "thumbnail", length = 300)
    private String thumbnail;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
