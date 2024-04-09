package edu.tcu.cs.hogwartsartifactsonline.wizard;

import edu.tcu.cs.hogwartsartifactsonline.artifact.Artifact;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wizard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //automatically generates key values for entities
    //lets the JPA provider choose the most appropriate strategy based on the underlying database and configuration
    private Integer id;

    private String name;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "owner") //one wizard has many artifacts
    private List<Artifact> artifacts = new ArrayList<>();

    public Wizard() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public void addArtifact(Artifact artifact) {
        artifact.setOwner(this);
        this.artifacts.add(artifact);

    }

    public Integer getNumberOfArtifacts() {
        return this.artifacts.size();
    }

    //streams thru artifacts, sets each artifact's owner null to detach them
    //then creates new list and assigns the artifact here -> removes them from list
    //common way to clear a collection
    public void removeAllArtifacts() {
        this.artifacts.stream().forEach(artifact -> artifact.setOwner(null));
        this.artifacts = new ArrayList<>();
    }

    public void removeArtifact(Artifact artfactToBeAssigned) {
        //remove artifact owner
        artfactToBeAssigned.setOwner(null);
        this.artifacts.remove(artfactToBeAssigned);
    }
}
