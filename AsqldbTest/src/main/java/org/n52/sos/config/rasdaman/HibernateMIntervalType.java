package main.java.org.n52.sos.config.rasdaman;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import rasj.RasGMArray;
import rasj.RasMInterval;

public class HibernateMIntervalType extends AbstractHibernateUserType {

    public HibernateMIntervalType() {
        super(RasMInterval.class);
        // TODO Auto-generated constructor stub
    }

    @Override
    public int[] sqlTypes() {
        return new int[] { Types.OTHER}; 
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names,
            SessionImplementor session, Object owner)
                    throws HibernateException, SQLException {
        RasMInterval interval = (RasMInterval) rs.getObject(names[0]);
        return (interval == null) ? null : interval;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,
            SessionImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.ARRAY);
        } else {
            st.setObject(index, (RasMInterval) value);
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return (value == null) ? null : value;
    }



}
