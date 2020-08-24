package jm.stockx.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

/**Реализация портфолио аккаунта пользователя. Доки могут быть удалены после финальной строчки проекта.
 * Созданно для отражения слабых мест кода. После решения проблемы, удаляем ее из списка.
 * 1. Защищен ли Id от изменения аннотацией lombok getter?
 * 2. Требуется ли реализация удаления всех связей, в случае удаления аккаунта сейчас?
        */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="portfolio")
public class UserPortfolio {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    private User user;

    @OneToMany(orphanRemoval = true, cascade = {CascadeType.ALL})
    private Set<ItemForPortfolio> itemPortfolio;

    public UserPortfolio(User user) {
        this.user = user;
        this.itemPortfolio = new HashSet<>();
    }

}
