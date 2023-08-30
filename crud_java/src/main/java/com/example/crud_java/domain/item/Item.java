package com.example.crud_java.domain.item;

import com.example.crud_java.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

/**
 * 완벽하게 DDD 가져가려면 POJO 로 만들고
 * Infra 에서 엔티티로 가져가야하는데 그건 좀 ,,,,, 불편하니까 이렇게..
 */
@Entity
@Table(name = "ITEM")
@Where(clause = "deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long itemNo;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private boolean deleted = false;

    private LocalDateTime deleteDate = null;

    public static Item of(String name, Long quantity) {
        Item item = new Item();
        item.name = name;
        item.quantity = quantity;
        return item;
    }

    public void update(String name, Long quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void updateQuantity(String type, Long quantity) {
        if (type == "ADD") {
            this.quantity = this.quantity + quantity;
        } else if (type == "MINUS") {
            this.quantity = this.quantity - quantity;
        }
    }

    public void delete() {
        this.deleted = true;
        this.deleteDate = LocalDateTime.now();
    }
}
