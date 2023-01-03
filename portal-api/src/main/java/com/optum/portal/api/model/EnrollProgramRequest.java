package com.optum.portal.api.model;

import java.util.List;

public class EnrollProgramRequest {

    private Long userId;
    private List<Long> programIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getProgramIds() {
        return programIds;
    }

    public void setProgramIds(List<Long> programIds) {
        this.programIds = programIds;
    }
}
