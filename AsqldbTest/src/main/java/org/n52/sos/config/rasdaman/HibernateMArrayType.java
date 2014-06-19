package main.java.org.n52.sos.config.rasdaman;

import org.hibernate.HibernateException;

import rasj.RasGMArray;

public class HibernateMArrayType extends AbstractArrayBasedHibernateUserType<RasGMArray> {

    public HibernateMArrayType() {
        super(RasGMArray.class);
    }

    @Override
    protected RasGMArray decode(RasGMArray array) throws HibernateException {
        return array;
    }

    @Override
    protected RasGMArray encode(RasGMArray t) throws HibernateException {
        return t;
    }

}
