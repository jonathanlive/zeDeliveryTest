package model.api;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Shelve {

    private String id;
    private String type;
    private String displayName;
    private ArrayList<Item> items = new ArrayList<>();

    public List<Item> getItems(){
        return Collections.unmodifiableList(items);
    }

    public Item getItem(String displayName){
        List<Item> filteredItems = getItems().stream().filter(x -> x.getDisplayName().equalsIgnoreCase(displayName)).collect(Collectors.toList());
        return (filteredItems.size() > 0) ? filteredItems.get(0) : null;
    }
}
