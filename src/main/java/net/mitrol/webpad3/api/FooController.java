package net.mitrol.webpad3.api;

import lombok.extern.slf4j.Slf4j;
import net.mitrol.webpad3.resource.Foo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/foos")
public class FooController {
    private static final List<Foo> foos = new ArrayList<>(Arrays.asList(new Foo(1, "First Foo"), new Foo(2, "Second Foo"), new Foo(3, "Third Foo")));

    @GetMapping(value = "/{id}")
    public Foo findOne(@PathVariable Long id) {
        return foos.stream().filter(foo -> foo.getId() == id).findFirst().orElseThrow();
    }

    @GetMapping
    public List<Foo> findAll() {
        return foos;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public List<Foo> create(@RequestBody Foo newFoo) {
        newFoo.setId(foos.size());
        foos.add(newFoo);
        return foos;
    }
}
