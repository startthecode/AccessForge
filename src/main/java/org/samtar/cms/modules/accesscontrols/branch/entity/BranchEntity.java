package org.samtar.cms.modules.accesscontrols.branch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

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

    public BranchEntity(String branch) {
        this.branch = branch;
    }

    public BranchEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
