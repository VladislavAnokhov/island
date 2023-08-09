package com.javarush.island.anokhov.Island;

import com.javarush.island.anokhov.nature.Nature;

import java.awt.desktop.PreferencesEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.javarush.island.anokhov.constants.ApplicationCommunication.locationInfo;

public class Location {


    private List <Nature> natures = new ArrayList<>();


    public void comeIn(Nature animal){
        natures.add(animal);
    }
    public void remove (Nature animalOrPlant){
        natures.remove(animalOrPlant);
    }
    public void setNatures(List<Nature> natures) {
        this.natures = natures;
    }

    public List<Nature> getNatures() {
        return natures;
    }
    public List<Nature> getTypeOfNatures (Nature nature){
        List<Nature> result = new ArrayList<>();
        for (Nature findNature : natures){
            if (findNature.getClass().getSimpleName().equalsIgnoreCase(nature.getClass().getSimpleName())){
                result.add(findNature);
            }
        }
        return result;

    }

    @Override
    public String toString (){
        String result ="";
        for (int i = 0; i < natures.size(); i++) {
            if (natures.size()>1){
                if (natures.get(i)==null){
                    result=result+null;
                }
                else {
            result = result + natures.get(i).getName()+", ";}}
            else result = result + natures.get(i).getName();
            }

        return locationInfo + result ;//Arrays.toString(natures.toArray())
    }


}
