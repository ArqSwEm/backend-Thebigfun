package com.thebigfun.payment_service.repository;import com.thebigfun.payment_service.model.entity.Payment_Service;import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;@Repositorypublic interface Payment_ServiceRepository extends JpaRepository <Payment_Service, Integer>{}