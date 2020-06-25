package model.api;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ShowCase {

    private String images;
    private ArrayList<Shelve> shelves = new ArrayList<>();

    public List<Shelve> getShelves(){
        return Collections.unmodifiableList(shelves);
    }

    public Shelve getShelve(String displayName){
        List<Shelve> filteredShelves = getShelves().stream().filter(x -> x.getDisplayName().equalsIgnoreCase(displayName)).collect(Collectors.toList());
        return (filteredShelves.size() > 0) ? filteredShelves.get(0) : null;
    }
}
