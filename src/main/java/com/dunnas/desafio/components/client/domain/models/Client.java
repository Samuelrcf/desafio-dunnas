package com.dunnas.desafio.components.client.domain.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.dunnas.desafio.components.order.application.exceptions.InsufficientBalanceException;
import com.dunnas.desafio.components.order.domain.models.Order;
import com.dunnas.desafio.components.user.domain.models.User;

public class Client {

	private Long id;
	private String name;
	private String cpf;
	private LocalDate birthDate;
	private BigDecimal balance;
	private User user;
	private List<Order> orders;
	
	public Client(Long id, String name, String cpf, LocalDate birthDate, BigDecimal balance, User user,
			List<Order> orders) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.balance = balance;
		this.user = user;
		this.setOrders(orders);
	}

	public Client(Long id, String name, String cpf, LocalDate birthDate, BigDecimal balance, User user) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.balance = balance;
		this.user = user;
	}
	
	public Client(String name, String cpf, LocalDate birthDate, BigDecimal balance, User user) {
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.balance = balance;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, birthDate, cpf, id, name);
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(balance, other.balance) && Objects.equals(birthDate, other.birthDate)
				&& Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	public void decreaseBalance(BigDecimal amount) {
	    if (this.balance.compareTo(amount) < 0) {
	        throw new IllegalArgumentException("Saldo insuficiente");
	    }
	    this.balance = this.balance.subtract(amount);
	}
	
    public void ensureHasBalance(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente");
        }
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

}
