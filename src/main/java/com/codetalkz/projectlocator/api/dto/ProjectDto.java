package com.codetalkz.projectlocator.api.dto;

public class ProjectDto {
    private String name;
    private String client;
    private String startDate;
    private String endDate;
    private String leader;
    private String description;
    private Integer[] locationIds;

    public ProjectDto(String name, String client, String startDate, String endDate, String leader, String description,
            Integer[] locationIds) {
        this.name = name;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leader = leader;
        this.description = description;
        this.locationIds = locationIds;
    }

    public String getName() {
        return name;
    }

    public String getClient() {
        return client;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getLeader() {
        return leader;
    }

    public String getDescription() {
        return description;
    }

    public Integer[] getLocationsIds() {
        if (locationIds == null) {
            return new Integer[0];
        }

        return locationIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocationsIds(Integer[] locationIds) {
        this.locationIds = locationIds;
    }

    @Override
    public String toString() {
        return "ProjectDto{" +
                "name='" + name + '\'' +
                ", client='" + client + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", leader='" + leader + '\'' +
                ", description='" + description + '\'' +
                ", locationIds=" + locationIds +
                '}';
    }
}
