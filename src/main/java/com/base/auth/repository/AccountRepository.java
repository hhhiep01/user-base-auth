package com.base.auth.repository;

import com.base.auth.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;

public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    public Account findAccountByUsername(String username);
    public Account findAccountByEmail(String email);
    public Account findAccountByPhone(String phone);
    public Account findAccountByCitizenIdCardAndIssuanceDate(String citizenIDCard, Date issuanceDate);
    public Account findAccountByResetPwdCode(String resetPwdCode);
    public Account findAccountByEmailOrUsername(String email, String username);
    public Page<Account> findAllByKind(int kind, Pageable pageable);
}
