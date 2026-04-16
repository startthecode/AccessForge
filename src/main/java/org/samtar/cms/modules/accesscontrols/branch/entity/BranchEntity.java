package org.samtar.cms.modules.accesscontrols.branch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.samtar.cms.modules.shared.enums.Genders;


@Entity
@Table(name = "branch")
public class BranchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "branch_id")
    @SequenceGenerator(name = "branch_id",sequenceName = "branch_id",allocationSize = 5)
    Long id;

    @NotBlank
    @Column(unique = true,nullable = false)
    String branch;
}

