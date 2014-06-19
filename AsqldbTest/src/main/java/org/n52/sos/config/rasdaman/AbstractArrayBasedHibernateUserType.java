package main.java.org.n52.sos.config.rasdaman;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import rasj.RasGMArray;

public abstract class AbstractArrayBasedHibernateUserType<T> extends AbstractHibernateUserType {
    
    public AbstractArrayBasedHibernateUserType(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int[] sqlTypes() {
        return new int[] { Types.ARRAY };
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws
            HibernateException, SQLException {
        RasGMArray array = (RasGMArray)rs.getObject(names[0]);
        return (array == null) ? null : decode(array);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws
            HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.ARRAY);
        } else {
            st.setObject(index, encode((T) value));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object deepCopy(Object value) throws HibernateException {
        return (value == null) ? null : decode(encode((T) value));
    }

    protected abstract T decode(RasGMArray array) throws HibernateException;

    protected abstract RasGMArray encode(T t) throws HibernateException;

}
