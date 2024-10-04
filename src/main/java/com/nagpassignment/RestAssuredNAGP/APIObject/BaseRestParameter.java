package com.nagpassignment.RestAssuredNAGP.APIObject;



import java.util.HashMap;
import java.util.Map;

public class BaseRestParameter {
    private Map<String, Object> header = new HashMap<>();
    private Map<String, Object> query = new HashMap<>();
    private Map<String, Object> path = new HashMap<>();

    public BaseRestParameter() {
    }

    public Map<String, Object> getHeader() {
        return this.header;
    }

    public void setHeader(Map<String, Object> header) {
        this.header = header;
    }

    public <T> void setHeader(T... objects) throws Exception  {
      

        for (int i = 0; i < objects.length; i += 2) {
            if (!(objects[i] instanceof String)) {
                throw new Exception("Header key " + objects[i] + " must be a string");
            }

            this.header.put((String) objects[i], objects[i + 1]);
        }
    }

    public Map<String, Object> getQuery() {
        return this.query;
    }

    public void setQuery(Map<String, Object> query) {
        this.query = query;
    }

    public void addQuery(Map<String, Object> query) {
        this.query.putAll(query);
    }

    public <T> void setQuery(T... objects) throws Exception {
        this.validateArguments(objects);

        for (int i = 0; i < objects.length; i += 2) {
            if (!(objects[i] instanceof String)) {
                throw new Exception("Query key " + objects[i] + " must be a string");
            }

            this.query.put((String) objects[i], objects[i + 1]);
        }
    }

    public Map<String, Object> getPath() {
        return this.path;
    }

    public void setPath(Map<String, Object> path) {
        this.path = path;
    }

    public <T> void setPath(T... objects) throws Exception {
        this.validateArguments(objects);

        for (int i = 0; i < objects.length; i += 2) {
            if (!(objects[i] instanceof String)) {
                throw new Exception("Path key " + objects[i] + " must be a string");
            }

            this.path.put((String) objects[i], objects[i + 1]);
        }
    }

    public void clear() {
        this.header.clear();
        this.query = new HashMap<>();
        this.path.clear();
    }

    public String toString() {
        String queryContent = this.query.size() == 0 ? "" : ", query : " + this.query;
        String pathContent = this.path.size() == 0 ? "" : ", path : " + this.path;
        return "{ header : " + this.header + queryContent + pathContent + "}";
    }

    private void validateArguments(Object[] objects) throws Exception {
        if (objects.length % 2 != 0) {
            throw new Exception("You cannot give an odd number of elements");
        } else if (objects.length == 0) {
            throw new Exception("You need to provide at least two arguments");
        }
    }
}
