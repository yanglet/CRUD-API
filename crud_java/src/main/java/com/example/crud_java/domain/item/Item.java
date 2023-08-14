package com.example.crud_java.domain.item;

import com.example.crud_java.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER")
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

    public void updateName(String name) {
        this.name = name;
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
