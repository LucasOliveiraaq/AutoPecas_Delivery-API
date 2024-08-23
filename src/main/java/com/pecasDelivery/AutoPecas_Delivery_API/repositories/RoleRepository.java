package com.pecasDelivery.AutoPecas_Delivery_API.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pecasDelivery.AutoPecas_Delivery_API.model.Role;
import com.pecasDelivery.AutoPecas_Delivery_API.model.RoleName;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role> findByName(RoleName roleName);
}
