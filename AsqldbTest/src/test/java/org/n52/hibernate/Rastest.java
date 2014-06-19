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

@Entity
@Table (name = "Rastest")
public class Rastest implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @Type(type = "main.java.org.n52.sos.config.rasdaman.HibernateMArrayType")
    private RasGMArray coll;
    

    public Rastest()
    {

    }
    
    public Rastest(int id, RasGMArray coll) {
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
    
    public RasGMArray getColl() {
        return coll;
    }
    
    public void setColl(RasGMArray coll) {
        this.coll = coll;
    }
}
