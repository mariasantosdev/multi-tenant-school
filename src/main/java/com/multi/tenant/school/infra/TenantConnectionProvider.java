package com.multi.tenant.school.infra;

import lombok.AllArgsConstructor;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.UnknownUnwrapTypeException;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@AllArgsConstructor
public class TenantConnectionProvider implements MultiTenantConnectionProvider {
    private final DataSource datasource;

    @Override
    public Connection getAnyConnection() throws SQLException {
        return datasource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(Object o) throws SQLException {
        String tenantIdentifier = (String) o;
        final Connection connection = getAnyConnection();
        setTenant(tenantIdentifier, connection);
        return connection;
    }

    //TODO alterar aqui pois sera mysql
    private void setTenant(String tenantIdentifier, Connection connection) throws SQLException {
        connection.createStatement()
                .execute(String.format("SET search_path TO \"%s\";", tenantIdentifier));
    }

    @Override
    public void releaseConnection(Object o, Connection connection) throws SQLException {
        setTenant(TenantIdentifierResolver.DEFAULT_TENANT, connection);
        releaseAnyConnection(connection);
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return DataSource.class.isAssignableFrom(unwrapType)
                || MultiTenantConnectionProvider.class.isAssignableFrom(unwrapType);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T> T unwrap(Class<T> unwrapType) {
        if (MultiTenantConnectionProvider.class.isAssignableFrom(unwrapType)) {
            return (T) this;
        } else if (DataSource.class.isAssignableFrom(unwrapType)) {
            try {
                return (T) getAnyConnection();
            } catch (SQLException e) {
                throw new UnknownUnwrapTypeException(unwrapType);
            }
        } else {
            throw new UnknownUnwrapTypeException(unwrapType);
        }
    }

}
