package com.dunnas.desafio.components.product.domain.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Discount {
    private static final int SCALE = 2;

    private Long id;
    private BigDecimal value;


    public Discount(Long id, BigDecimal value) {
        validateValue(value);

        this.id = id;
        this.value = value.setScale(SCALE, RoundingMode.HALF_EVEN);
    }

    public void validateValue(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("Desconto não pode ser nulo");
        }
        if (value.scale() > SCALE) {
            throw new IllegalArgumentException("Desconto deve ter no máximo duas casas decimais");
        }
        if (value.compareTo(BigDecimal.ZERO) < 0 || value.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("Desconto deve ser um valor entre 0.00 e 1.00");
        }
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.multiply(BigDecimal.valueOf(100)).stripTrailingZeros().toPlainString() + "%";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount)) return false;
        Discount discount = (Discount) o;
        return Objects.equals(id, discount.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
