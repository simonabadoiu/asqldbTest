package test.java.org.n52.hibernate;

import java.io.Serializable;
import java.sql.Array;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import main.java.org.n52.sos.config.rasdaman.HibernateMArrayType;
import rasj.RasGMArray;
import rasj.RasMInterval;

@Entity
@Table (name = "Rastest")
public class RastestMInterval implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @Column(name = "coll")
    @Type(type = "main.java.org.n52.sos.config.rasdaman.HibernateMIntervalType")
    private RasMInterval coll;


    public RastestMInterval() {

    }

    public RastestMInterval(int id, RasMInterval coll) {
        super();
        this.id = id;
        this.coll = coll;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public RasMInterval getColl() {
        return coll;
    }

    public void setColl(RasMInterval coll) {
        this.coll = coll;
    }
}
