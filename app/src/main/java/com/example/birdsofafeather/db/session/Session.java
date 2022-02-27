package com.example.birdsofafeather.db.session;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "sessions")
public class Session {

    // will autogenerate key when added to DB (insert method will return long id)
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    long id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "filters")
    List<String> filters;

    @ColumnInfo(name = "sort_options")
    List<String> sort_options;

    @ColumnInfo(name = "nearby_users")
    List<Integer> nearby_users;

    public Session(String name) {
        this.name = name;
        this.filters = new ArrayList<>();
        this.sort_options = new ArrayList<>();
        this.nearby_users = new ArrayList<>();
    }

    //getters
    public String getName() {
       return this.name;
    }

    public List<String> getFilters() {
       return this.filters;
    }

    public List<String> getSortOptions() {
        return this.sort_options;
    }

    public List<Integer> getNearbyUsers() {
       return this.nearby_users;
    }

    //setters
    public void setName(String name) {
       this.name = name;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public void setSortOptions(List<String> sortOptions) {
        this.sort_options = sortOptions;
    }

    public void setNearbyUsers(List<Integer> userIds) {
        this.nearby_users = userIds;
    }

    // additions
    public void addFilter(String filter) {
        this.filters.add(filter);
    }

    public void addSortOption(String sortOption) {
        this.sort_options.add(sortOption);
    }

    public void addNearbyUser(Integer userId) {
        this.nearby_users.add(userId);
    }

    //deletions
    public void removeFilter(String filter) {
        this.filters.remove(filter);
    }

    public void removeSortOption(String sortOption) {
        this.sort_options.remove(sortOption);
    }

    public void removeNearbyUser(Integer user_id){
        this.nearby_users.remove(user_id);
    }


}