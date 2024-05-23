package by.mycompany.restapilibrary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "UsersBooks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "book_id")
    private Long bookId;
}
