package main.java.org.n52.sos.config.rasdaman;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

public abstract class AbstractHibernateUserType implements UserType {
    private Class<?> clazz;
    
    public AbstractHibernateUserType(Class<?> clazz) {
        this.clazz = clazz;
    }
    
    @Override
    public Class<?> returnedClass() {
        return this.clazz;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return (Serializable) deepCopy(cached);
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) deepCopy(value);
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) {
            return true;
        } else if (x == null || y == null) {
            return false;
        } else {
            return x.equals(y);
        }
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return (x != null) ? x.hashCode() : 0;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}
