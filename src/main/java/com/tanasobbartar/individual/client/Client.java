package com.tanasobbartar.individual.client;

import com.tanasobbartar.individual.Individual;
import com.tanasobbartar.profile.IndividualProfile;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "client")
public class Client extends Individual {

    @OneToMany
    private List<IndividualProfile> profile;

}
