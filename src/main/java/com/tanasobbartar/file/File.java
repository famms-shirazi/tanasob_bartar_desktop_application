package com.tanasobbartar.file;

import com.tanasobbartar.constants.AuditableEntity;
import com.tanasobbartar.profile.IndividualProfile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "file")
public class File extends AuditableEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private IndividualProfile profile;

}
