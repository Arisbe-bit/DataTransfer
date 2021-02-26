package com.neoris.tcl.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Functions {

    public static void addInfoMessage(String summary, String detail) {
        addMessage(summary, detail, FacesMessage.SEVERITY_INFO);
    }
    
    public static void addErrorMessage(String summary, String detail) {
        addMessage(summary, detail, FacesMessage.SEVERITY_ERROR);
    }

    public static void addWarnMessage(String summary, String detail) {
        addMessage(summary, detail, FacesMessage.SEVERITY_WARN);
    }
    
    /**
     * Sorts a Map by Value
     * @param unsortedMap
     * @return
     */
    public static Map<String, Integer> sortMapByValue(Map<String, Integer> unsortedMap) {
        Map<String, Integer> sortedMap = new HashMap<>();
        unsortedMap.
            entrySet().
            stream().
            sorted(Map.Entry.comparingByValue()).
            forEach( m -> sortedMap.put(m.getKey(), m.getValue()));        
                    //collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldValue, newValue) -> oldValue, HashMap::new));
        return sortedMap;
    }

    private static void addMessage(String summary, String detail, Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    class MonthComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            // TODO Auto-generated method stub
            return o2 - o1;
        }


        
    }
}
