package org.samtar.cms.modules.accesscontrols.cmsModules.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.samtar.cms.modules.shared.enums.CmsModules;

import java.time.LocalDate;

@Entity
@Table(name = "cms_modules")
public class CmsModuleEntity {
    @Id
    Long id;

    @NotNull
    @Column(unique = true, nullable = false)
    String moduleName;

    @NotBlank
    @Column(unique = true, nullable = false)
    String moduleCode;

    @Column(name = "release_date")
    @CreationTimestamp
    LocalDate releaseDate;

    public CmsModuleEntity() {
    }

    public CmsModuleEntity(String moduleName, LocalDate releaseDate) {
        this.moduleName = moduleName;
        this.releaseDate = releaseDate;
    }

    public CmsModuleEntity(Long id, String moduleName, LocalDate releaseDate) {
        this.id = id;
        this.moduleName = moduleName;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
}
