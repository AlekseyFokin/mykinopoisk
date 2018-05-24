/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author sniffsnirr
 */
@Entity 
@Table(name="MOVIE")
public class Movie implements Serializable {
private int Id;
private String Name;
private String pic;
private String Startyear;
private String Auntr;
private String Lengthtime;
private Set <Actor> actors=new HashSet<Actor>();

    public Movie() {
    }

    public Movie(int Id, String Name, String pic, Set<Actor> actors, String StartYear, String auntr, String LengthTime) {
        this.Id=Id;
        this.Name = Name;
        this.pic = pic;
        this.actors = actors;
        this.Startyear = StartYear;
        this.Auntr = auntr;
        this.Lengthtime = LengthTime;
    }
@Id
//@GeneratedValue(strategy=GenerationType.IDENTITY)
//@GeneratedValue(strategy=GenerationType.TABLE)
//@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="ID")
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

@Column(name="NAME")
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

@Column(name="PIC")
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
@Column(name="STARTYEAR")
   public String getStartyear() {
        return Startyear;
    }

    public void setStartyear(String StartYear) {
        this.Startyear = StartYear;
    }

    @Column(name="AUNTR")
    public String getAuntr() {
        return Auntr;
    }

    public void setAuntr(String Auntr) {
        this.Auntr = Auntr;
    }
@Column(name="LENGTHTIME")
    public String getLengthtime() {
        return Lengthtime;
    }

    public void setLengthtime(String Lengthtime) {
        this.Lengthtime = Lengthtime;
    }
    @ManyToMany(fetch = FetchType.EAGER, cascade =  CascadeType.ALL)
    @JoinTable(name="MOVIE_ACTOR", joinColumns=@JoinColumn(name="MOVIE_ID"), inverseJoinColumns=@JoinColumn(name="ACTOR_ID"))
    public Set<Actor> getactors()
    {return this.actors;}
    
    public void setactors(Set<Actor>actors)
    {this.actors=actors;}
    
    public String toString()
    {
      return   Id+", "+Name+", "+Startyear+", "+Auntr+", "+Lengthtime+";";
    }
    
    public String toCSV()
    {
     //   Id+"; "+Name+"; "+this.actors.toCSV()+Year+"; "+Auntr+"; "+Length+";";
        final StringBuilder  str=new StringBuilder();
        actors.stream().forEach((Actor actor)->{
                                                               str.append( Id+"; "+Name.replaceAll(";", ".")+"; "+actor.toCSV()+Startyear+"; "+Auntr.replaceAll(";", ".")+"; "+Lengthtime.replaceAll(";", ".")+";\r\n");
                                                               }); 
        return   str.toString();
    }      

}
