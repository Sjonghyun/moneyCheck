package com.project.moneycheck.mapper;

import com.project.moneycheck.mapper.sql.SecuritySQL;
import com.project.moneycheck.security.AuthValue;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AuthMapper {

    @SelectProvider(value = SecuritySQL.class, method = "loadUserByName")
    AuthValue loadUserByName(@Param("username") String username);

    @Options(useGeneratedKeys = true, keyProperty = "authValue.sc_no")
    @InsertProvider(value = SecuritySQL.class, method = "insertAuthValue")
    void insertAuthValue(@Param("authValue") AuthValue authValue);

    @UpdateProvider(value = SecuritySQL.class, method = "updatePW")
    void updatePW(int sc_no, String encPwd);
}
