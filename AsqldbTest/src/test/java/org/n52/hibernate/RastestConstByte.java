package test.java.org.n52.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table (name = "Rastest")
public class RastestConstByte implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "coll")
    @Type(type = "java.lang.Byte")
    private Byte coll;


    public RastestConstByte() {

    }

    public RastestConstByte(Byte coll) {
        super();
        this.coll = coll;
    }

    public Byte getColl() {
        return coll;
    }

    public void setColl(Byte coll) {
        this.coll = coll;
    }
}
