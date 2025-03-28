package org.Employees.Analysis;

import java.util.List;

@FunctionalInterface
public interface Parser {
    public List<String> parse(String Path);

}
