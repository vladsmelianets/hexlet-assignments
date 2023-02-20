package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(value = "", produces = "application/json")
    public List<Map<String, String>> getPeople() {
        String query = "select * from person";
        return jdbc.query(query, getMapRowMapper());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Map<String, String> getPerson(@PathVariable(name = "id") String id) {
        String query = "select * from person where id=?";
        Map<String, String> person;
        try {
            person = jdbc.queryForObject(query, getMapRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        return person;
    }

    private RowMapper<Map<String, String>> getMapRowMapper() {
        return (rs, rowNum) -> Map.of("id", String.valueOf(rs.getLong("id")),
                "first_name", rs.getString("first_name"),
                "last_name", rs.getString("last_name"));
    }
// END
}
